package xyz.theclashfruit.woid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PluginsFragment extends Fragment {
  public PluginsFragment() {
    // Required empty public constructor
  }

  public static PluginsFragment newInstance() {
    return new PluginsFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View viewInflater = inflater.inflate(R.layout.fragment_plugins, container, false);

    return viewInflater;
  }
}