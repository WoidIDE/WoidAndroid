package xyz.theclashfruit.woid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.List;


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
    View viewInflater       = inflater.inflate(R.layout.fragment_home, container, false);
    View dialogViewInflater = inflater.inflate(R.layout.create_project_dialog, null);

    FloatingActionButton newProjectFab = viewInflater.findViewById(R.id.newProject);
    ListView projectList               = viewInflater.findViewById(R.id.projectList);

    TextView appName                  = dialogViewInflater.findViewById(R.id.app_name);
    TextView appPackage               = dialogViewInflater.findViewById(R.id.app_package);
    AutoCompleteTextView appMinSdk    = dialogViewInflater.findViewById(R.id.min_sdk);
    AutoCompleteTextView appTargetSdk = dialogViewInflater.findViewById(R.id.target_sdk);

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item);

    projectList.setAdapter(adapter);
    appMinSdk.setAdapter(sdkAdapter());
    appTargetSdk.setAdapter(sdkAdapter());

    newProjectFab.setOnClickListener(v -> {
      AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

      builder.setView(dialogViewInflater).setPositiveButton("Crate Project", (dialog, which) -> {
        WoidUtils.showToast(getActivity(), "Creating...");

        if(ProjectInterface.generateNewProject(getActivity().getApplicationContext(), appName.getText().toString(), appPackage.getText().toString(), appMinSdk.getText().toString(), appTargetSdk.getText().toString())) {
          WoidUtils.showToast(getActivity(), "Project created.");

          Intent i1 = new Intent(getContext(), EditorActivity.class);

          i1.putExtra("projectPath", getActivity().getApplicationContext().getFilesDir() + "/projects/" + appName.getText().toString().replaceAll("\\s+","") + "/android");
          i1.putExtra("projectName", appName.getText().toString());

          startActivity(i1);
          getActivity().finish();
        } else {
          WoidUtils.showToast(getActivity(), "Project already exists or there was an error.");
        };
      });

      AlertDialog dialog = builder.create();

      dialog.show();
    });

    List<File> fileList = StorageUtil.getFiles(getActivity().getApplicationContext().getFilesDir() + "/projects");

    for (File file : fileList) {
      Log.w("Files", "FileName: " + file.getAbsolutePath());
      adapter.add(file.getAbsolutePath());
      adapter.notifyDataSetChanged();
    }

    return viewInflater;
  }

  private ArrayAdapter<Number> sdkAdapter() {
    Number[] sdks = {9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

    return new ArrayAdapter<Number>(getActivity(), R.layout.list_item, sdks);
  }
}