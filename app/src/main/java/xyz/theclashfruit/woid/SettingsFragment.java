package xyz.theclashfruit.woid;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.root_preferences, rootKey);

    Preference myPref = (Preference) findPreference("app_crash");
    assert myPref != null;
    myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      public boolean onPreferenceClick(Preference preference) {
        throw new RuntimeException("you asked for it...");
      }
    });
  }
}