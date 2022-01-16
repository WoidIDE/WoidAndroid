package xyz.theclashfruit.woid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;

import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorColorScheme;

public class EditorCodeFragment extends Fragment {
  String currentFile;

  public EditorCodeFragment() {
    // Required empty public constructor
  }

  public static EditorCodeFragment newInstance() {
    return new EditorCodeFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View viewInflater = inflater.inflate(R.layout.fragment_editor_code, container, false);

    CodeEditor editor = (CodeEditor) viewInflater.findViewById(R.id.code_editor);
    editor.setEditorLanguage(new JavaLanguage());

    try {
      editor.setText(StorageUtil.readFile(currentFile));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return viewInflater;
  }
}