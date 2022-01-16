package xyz.theclashfruit.woid.fragments;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import xyz.theclashfruit.woid.R;
import xyz.theclashfruit.woid.utils.StorageUtil;

public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.root_preferences, rootKey);

    Preference appCrash       = (Preference) findPreference("app_crash");
    Preference projectsDelete = (Preference) findPreference("projects_delete");

    appCrash.setOnPreferenceClickListener(preference -> {
      throw new RuntimeException("you asked for it...");
    });

    projectsDelete.setOnPreferenceClickListener(preference -> {
      StorageUtil.deleteDirectory(getActivity().getFilesDir().getPath() + "/projects");
      StorageUtil.createDirectory(getActivity().getFilesDir().getPath() + "/projects");

      return true;
    });
  }
}