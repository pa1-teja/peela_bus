package com.vivianaranha.mapsapp.Geofence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.vivianaranha.mapsapp.R;

import Retrofit.Result;
import Retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllGeofencesActivity extends AppCompatActivity {

  // region Overrides
private RetrofitInterface retrofitInterface;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_all_geofences);

    setTitle(R.string.app_title);
//    if (savedInstanceState == null) {
//      getSupportFragmentManager().beginTransaction()
//              .add(R.id.container, new AllGeofencesFragment())
//              .commit();
//    }

    GeofenceController.getInstance().init(this);
    getJSONResponse();
  }
  
  public void getJSONResponse(){
    retrofitInterface.RESULT_CALL().enqueue(new Callback<Result>() {

      @Override
      public void onResponse(Call<Result> call, Response<Result> response) {
        if (response.isSuccessful()){
          NamedGeofence namedGeofence = new NamedGeofence();
          namedGeofence.id = String.valueOf(response.body().getId());
          namedGeofence.name = response.body().getBusid(); // TODO: School Name from JSON Response would be better I think.
          namedGeofence.latitude = Double.parseDouble(response.body().getLAT());
          namedGeofence.longitude = Double.parseDouble(response.body().getLONG());
        }
      }

      @Override
      public void onFailure(Call<Result> call, Throwable t) {
        Log.d(getLocalClassName().toString(),"error msg : " + t.getMessage());
        Toast.makeText(getApplicationContext(),"Unable to load the information from the server. Please try again after some time", Toast.LENGTH_SHORT)
                .show();

      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_all_geofences, menu);

    MenuItem item = menu.findItem(R.id.action_delete_all);

    if (GeofenceController.getInstance().getNamedGeofences().size() == 0) {
      item.setVisible(false);
    }

    return true;
  }

  @Override
  protected void onResume() {
    super.onResume();

    int googlePlayServicesCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    Log.i(AllGeofencesActivity.class.getSimpleName(), "googlePlayServicesCode = " + googlePlayServicesCode);

    if (googlePlayServicesCode == 1 || googlePlayServicesCode == 2 || googlePlayServicesCode == 3) {
      GooglePlayServicesUtil.getErrorDialog(googlePlayServicesCode, this, 0).show();
    }
  }

  // endregion
}
