package com.vivianaranha.mapsapp.Geofence;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;
import com.vivianaranha.mapsapp.R;

import java.util.ArrayList;
import java.util.List;

import Retrofit.APIUtils;
import Retrofit.BusInfo;
import Retrofit.Result;
import Retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllGeofencesFragment extends Fragment {

  // region Properties

  private ViewHolder viewHolder;
  private static int PERMISSION_LOCATION_REQUEST_CODE = 1;
  private RetrofitInterface retrofitInterface;
  private NamedGeofence namedGeofence;

  private ViewHolder getViewHolder() {
    return viewHolder;
  }

  private AllGeofencesAdapter allGeofencesAdapter;

  // endregion

  // region Overrides

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }


  public static boolean checkPermission(final Context context) {
    return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
  }

  private void showPermissionDialog() {
    if (!checkPermission(getContext())) {
      ActivityCompat.requestPermissions(
              getActivity(),
              new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
              PERMISSION_LOCATION_REQUEST_CODE);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_all_geofences, container, false);
    viewHolder = new ViewHolder();
    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getViewHolder().populate(view);

    showPermissionDialog();

    viewHolder.geofenceRecyclerView.setHasFixedSize(true);

      namedGeofence = new NamedGeofence();
      getJSONResponse();

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    viewHolder.geofenceRecyclerView.setLayoutManager(layoutManager);

    allGeofencesAdapter = new AllGeofencesAdapter(GeofenceController.getInstance().getNamedGeofences());
    viewHolder.geofenceRecyclerView.setAdapter(allGeofencesAdapter);
    allGeofencesAdapter.setListener(new AllGeofencesAdapter.AllGeofencesAdapterListener() {
      @Override
      public void onDeleteTapped(NamedGeofence namedGeofence) {
        List<NamedGeofence> namedGeofences = new ArrayList<>();
        namedGeofences.add(namedGeofence);
        GeofenceController.getInstance().removeGeofences(namedGeofences, geofenceControllerListener);
      }
    });

//    viewHolder.actionButton.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        AddGeofenceFragment dialogFragment = new AddGeofenceFragment();
//        dialogFragment.setListener(AllGeofencesFragment.this);
//        dialogFragment.show(getActivity().getSupportFragmentManager(), "AddGeofenceFragment");
//      }
//    });

    refresh();
  }

  public void getJSONResponse(){
    retrofitInterface = APIUtils.getRetrofitService();
    retrofitInterface.RESULT_CALL().enqueue(new Callback<BusInfo>() {
      @Override
      public void onResponse(Call<BusInfo> call, Response<BusInfo> response) {
        if (response.isSuccessful()){
          Log.d("====D", "Response : " + response.body());

          List<Result> results = response.body().getResult();

          for (Result result: results) {

            namedGeofence.id = String.valueOf(result.getId());
            namedGeofence.name = result.getBusid(); // TODO: School Name from JSON Response would be better I think.
            namedGeofence.latitude = Double.parseDouble(result.getLAT());
            namedGeofence.longitude = Double.parseDouble(result.getLONG());
            namedGeofence.radius = 2000;// TODO: This is a hard coded value, please think about how you can handle the radius part.
          }
          GeofenceController.getInstance().addGeofence(namedGeofence, geofenceControllerListener);
        }
      }

      @Override
      public void onFailure(Call<BusInfo> call, Throwable t) {
        Log.d(getTag(),"JSON error message : " + t.getMessage());

        Toast.makeText(getActivity(),"Unable to retrieve info from the server. Please try again later",Toast.LENGTH_SHORT)
                .show();
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_delete_all) {
      AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
      builder.setMessage(R.string.AreYouSure)
              .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  GeofenceController.getInstance().removeAllGeofences(geofenceControllerListener);
                }
              })
              .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  // User cancelled the dialog
                }
              })
              .create()
              .show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  // endregion


  // region GeofenceControllerListener

  private GeofenceController.GeofenceControllerListener geofenceControllerListener = new GeofenceController.GeofenceControllerListener() {
    @Override
    public void onGeofencesUpdated() {
      refresh();
    }

    @Override
    public void onError() {
      showErrorToast();
    }
  };

  // endregion

  // region Private

  private void refresh() {
    allGeofencesAdapter.notifyDataSetChanged();

    if (allGeofencesAdapter.getItemCount() > 0) {
      getViewHolder().emptyState.setVisibility(View.INVISIBLE);
    } else {
      getViewHolder().emptyState.setVisibility(View.VISIBLE);

    }

    getActivity().invalidateOptionsMenu();

  }

  private void showErrorToast() {
    Toast.makeText(getActivity(), getActivity().getString(R.string.Toast_Error), Toast.LENGTH_SHORT).show();
  }

  // endregion

  // region AddGeofenceFragmentListener


  // endregion

  // region Inner classes

  static class ViewHolder {
    ViewGroup container;
    ViewGroup emptyState;
    RecyclerView geofenceRecyclerView;
    ActionButton actionButton;

    public void populate(View v) {
      container = (ViewGroup) v.findViewById(R.id.fragment_all_geofences_container);
      emptyState = (ViewGroup) v.findViewById(R.id.fragment_all_geofences_emptyState);
      geofenceRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_all_geofences_geofenceRecyclerView);
      actionButton = (ActionButton) v.findViewById(R.id.fragment_all_geofences_actionButton);

      actionButton.setImageResource(R.drawable.fab_plus_icon);
    }
  }

  // endregion
}
