package com.vivianaranha.mapsapp.Geofence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.vivianaranha.mapsapp.R;

import java.util.List;

import Retrofit.APIUtils;
import Retrofit.BusInfo;
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
    retrofitInterface = APIUtils.getRetrofitService();
    retrofitInterface.RESULT_CALL().enqueue(new Callback<BusInfo>() {
      @Override
      public void onResponse(Call<BusInfo> call, Response<BusInfo> response) {
        if (response.isSuccessful()){
          Log.d("====D", "Response : " + response.body());
          NamedGeofence namedGeofence = new NamedGeofence();

          List<Result> results = response.body().getResult();

          for (Result result: results) {

            namedGeofence.id = String.valueOf(result.getId());
            namedGeofence.name = result.getBusid(); // TODO: School Name from JSON Response would be better I think.
            namedGeofence.latitude = Double.parseDouble(result.getLAT());
            namedGeofence.longitude = Double.parseDouble(result.getLONG());
          }
        }
      }

      @Override
      public void onFailure(Call<BusInfo> call, Throwable t) {
        Log.d(getLocalClassName(),"JSON error message : " + t.getMessage());

        Toast.makeText(getApplicationContext(),"Unable to retrieve info from the server. Please try again later",Toast.LENGTH_SHORT)
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
