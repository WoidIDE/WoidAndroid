package xyz.theclashfruit.woid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.IOException;

import io.github.rosemoe.sora.widget.CodeEditor;
import xyz.theclashfruit.woid.fragments.EditorCodeFragment;
import xyz.theclashfruit.woid.fragments.EditorFilesFragment;
import xyz.theclashfruit.woid.fragments.HomeFragment;
import xyz.theclashfruit.woid.gson.ProjectMetaGson;
import xyz.theclashfruit.woid.utils.StorageUtil;
import xyz.theclashfruit.woid.utils.WoidUtils;

public class EditorActivity extends AppCompatActivity {

  private Toolbar toolBar;

  private EditorCodeFragment editorCodeFragment;
  private EditorFilesFragment editorFilesFragment;

  String currentFile;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editor);

    toolBar   = findViewById(R.id.toolBar);

    setSupportActionBar(toolBar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    Intent intent = getIntent();

    Gson gson = new Gson();

    ProjectMetaGson projectMeta = null;

    try {
      projectMeta = gson.fromJson(StorageUtil.readFile(intent.getStringExtra("projectPath") + "/meta.json"), ProjectMetaGson.class);

      getSupportActionBar().setSubtitle(projectMeta.getProjectName().replaceAll("\\s+","").concat("- MainActivity.java*"));

      currentFile = intent.getStringExtra("projectPath") + "/android/app/src/main/java/" + projectMeta.getPackageName().replaceAll("\\.","/") + "/MainActivity.java";
    } catch (IOException e) {
      e.printStackTrace();
    }


    editorCodeFragment = new EditorCodeFragment();
    editorFilesFragment = new EditorFilesFragment();

    editorCodeFragment.currentFile = currentFile;

    if(savedInstanceState == null) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      fragmentTransaction.replace(R.id.fragmentContainer, editorCodeFragment);
      fragmentTransaction.commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    menu.add("Compile")
            .setIcon(R.drawable.ic_play_arrow)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menu.add("Save")
            .setIcon(R.drawable.ic_save)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menu.add("Files")
            .setIcon(R.drawable.ic_folder)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menu.add("Import Files")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    menu.add("Export Project")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    menu.add("Logs")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    CharSequence title = item.getTitle();
    if ("Compile".contentEquals(title)) {
      WoidUtils.showToast(getApplicationContext(), "Not Yet Implemented.");

      return true;
    } else if ("Save".contentEquals(title)) {
      CodeEditor codeEditor = editorCodeFragment.getView().findViewById(R.id.code_editor);

      StorageUtil.createFile(currentFile, codeEditor.getText().toString());

      String last = currentFile.substring(currentFile.lastIndexOf('/') + 1);

      WoidUtils.showToast(getApplicationContext(), "File \"" + last + "\" Saved.");

      return true;
    } else if ("Files".contentEquals(title)) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      fragmentTransaction.replace(R.id.fragmentContainer, editorFilesFragment);
      fragmentTransaction.commit();

      item.setTitle("Editor");
      item.setIcon(R.drawable.ic_edit);
    } else if ("Editor".contentEquals(title)) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      fragmentTransaction.replace(R.id.fragmentContainer, editorCodeFragment);
      fragmentTransaction.commit();

      item.setTitle("Files");
      item.setIcon(R.drawable.ic_folder);
    }
    return super.onOptionsItemSelected(item);
  }
}