package xyz.theclashfruit.woid.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import xyz.theclashfruit.woid.R;

public class ToolsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.tools_preferences, rootKey);
    }
}