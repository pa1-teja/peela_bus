package com.vivianaranha.mapsapp.Geofence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.vivianaranha.mapsapp.Geofence.AppSettings.SettingsActivity;
import com.vivianaranha.mapsapp.MainActivity;
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
    GeofenceController.getInstance().init(this);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
              .add(R.id.container, new AllGeofencesFragment())
              .commit();
    }

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
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.settings_screen)
      startActivity(new Intent(this,SettingsActivity.class));
    if (item.getItemId() == R.id.pick_drop)
      startActivity(new Intent(this, MainActivity.class));
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
