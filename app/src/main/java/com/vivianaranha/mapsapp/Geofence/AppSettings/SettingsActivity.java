package com.vivianaranha.mapsapp.Geofence.AppSettings;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.Switch;

import com.vivianaranha.mapsapp.R;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatActivity {


    private Switch vibration_2,vibration_1,Sound_1,Sound_2;

    private SharedPreferences SchoolToHomeVibrationPref,SchoolToHomeSoundPref,HomeToSchoolVibrationPref,HomeToSchoolSoundPref;

    private String SchoolToHomeVibrationPref_Key,SchoolToHomeSoundPref_key,HomeToSchoolVibrationPref_key,HomeToSchoolSoundPref_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_settings_layout);

        HomeToSchoolSoundPref_key = "HomeToSchoolSoundPref_Key";
        SchoolToHomeVibrationPref_Key = "SchoolToHomeVibrationPref_Key";
        SchoolToHomeSoundPref_key = "SchoolToHomeSoundPref_key";
        HomeToSchoolVibrationPref_key = "HomeToSchoolVibrationPref_key";

        SchoolToHomeSoundPref = PreferenceManager.getDefaultSharedPreferences(this);
        SchoolToHomeVibrationPref = PreferenceManager.getDefaultSharedPreferences(this);
        HomeToSchoolSoundPref = PreferenceManager.getDefaultSharedPreferences(this);
        HomeToSchoolSoundPref = PreferenceManager.getDefaultSharedPreferences(this);


        vibration_1 =  findViewById(R.id.vibration_1);
        vibration_2 = findViewById(R.id.vibration_2);
        Sound_1 = findViewById(R.id.sound_1);
        Sound_2 = findViewById(R.id.sound_2);


    }

    public void checkSound_2(View view){
        if (Sound_1.isChecked()){
            // TODO: Vibrate device and set preference.

        } else {
            // TODO: remove preference
        }
    }

    public void checkVibration_1(View view){
        if (vibration_1.isChecked()){
      // TODO: Vibrate device and set preference.
    } else {
        // TODO: remove preference
    }

}

    public void checkVibration_2(View view){
        if (vibration_2.isChecked()){
            // TODO: Vibrate device and set preference.
            SchoolToHomeVibrationPref =
        } else {
            // TODO: remove preference
        }
    }

    public void checkSound_1(View view){
        if (Sound_1.isChecked()){
            // TODO: Vibrate device and set preference.
        } else {
            // TODO: remove preference
        }
    }
}
