package xyz.theclashfruit.woid.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import xyz.theclashfruit.woid.EditorActivity;
import xyz.theclashfruit.woid.R;
import xyz.theclashfruit.woid.other.FileListAdapter;
import xyz.theclashfruit.woid.other.ProjectListAdapter;
import xyz.theclashfruit.woid.utils.StorageUtil;

public class EditorFilesFragment extends Fragment {
  public String currentPath;
  private List<File> folderFileList;

  public EditorFilesFragment() {
    // Required empty public constructor
  }

  public static EditorFilesFragment newInstance() {
    return new EditorFilesFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View viewInflater = inflater.inflate(R.layout.fragment_editor_files, container, false);

    RecyclerView fileList = viewInflater.findViewById(R.id.fileList);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    ArrayList<String> fileListString = new ArrayList<String>();

    fileListString.add("..");

    FileListAdapter adapter = new FileListAdapter(fileListString, item -> {
      if(item.equals("..")) {
        int endIndex = item.lastIndexOf("/");
        String newPath = item.substring(0, endIndex);

        Log.d("pc", newPath);

        currentPath = newPath;

        folderFileList = StorageUtil.getFiles(currentPath);

        fileListString.removeAll(fileListString);

        for (File file : folderFileList) {
          fileListString.add(file.getAbsolutePath());

        }
      }
    });

    fileList.setHasFixedSize(true);
    fileList.setLayoutManager(linearLayoutManager);
    fileList.setAdapter(adapter);

    folderFileList = StorageUtil.getFiles(currentPath);

    for (File file : folderFileList) {
      fileListString.add(file.getAbsolutePath());
      adapter.notifyDataSetChanged();
    }

    return viewInflater;
  }
}