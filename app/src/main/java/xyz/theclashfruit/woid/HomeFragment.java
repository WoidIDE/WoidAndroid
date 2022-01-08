package xyz.theclashfruit.woid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment {
  public HomeFragment() {
    // Required empty public constructor
  }

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View viewInflater = inflater.inflate(R.layout.fragment_home, container, false);

    FloatingActionButton newProjectFab = viewInflater.findViewById(R.id.newProject);

    newProjectFab.setOnClickListener(v -> {
      Intent i1 = new Intent();

      i1.setClass(getActivity(), EditorActivity.class);
      startActivity(i1);
      getActivity().finish();
    });

    return viewInflater;
  }
}