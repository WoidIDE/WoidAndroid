package xyz.theclashfruit.woid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;

import io.github.rosemoe.sora.widget.CodeEditor;
import xyz.theclashfruit.woid.fragments.EditorCodeFragment;
import xyz.theclashfruit.woid.fragments.EditorFilesFragment;
import xyz.theclashfruit.woid.fragments.EditorLayoutFragment;
import xyz.theclashfruit.woid.gson.ProjectMetaGson;
import xyz.theclashfruit.woid.utils.StorageUtil;
import xyz.theclashfruit.woid.utils.WoidUtils;

public class EditorActivity extends AppCompatActivity {

  private Toolbar toolBar;
  private ViewPager viewPager;
  private TabLayout tabLayout;

  private EditorCodeFragment editorCodeFragment;
  private EditorLayoutFragment editorLayoutFragment;
  private EditorFilesFragment editorFilesFragment;

  String currentFile;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editor);

    toolBar   = findViewById(R.id.toolBar);
    viewPager = findViewById(R.id.viewPager);
    tabLayout = findViewById(R.id.tabLayout);

    setSupportActionBar(toolBar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    Intent intent = getIntent();

    Gson gson = new Gson();

    ProjectMetaGson projectMeta = null;

    try {
      projectMeta = gson.fromJson(StorageUtil.readFile(intent.getStringExtra("projectPath") + "/meta.json"), ProjectMetaGson.class);

      getSupportActionBar().setSubtitle(projectMeta.getProjectName().replaceAll("\\s+",""));

      currentFile = intent.getStringExtra("projectPath") + "/android/app/src/main/java/" + projectMeta.getPackageName().replaceAll("\\.","/") + "/MainActivity.java";
    } catch (IOException e) {
      e.printStackTrace();
    }

    viewPager.setAdapter(new FragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 3));
    tabLayout.setupWithViewPager(viewPager);

    viewPager.setOffscreenPageLimit(4);

    editorCodeFragment = (EditorCodeFragment) viewPager.getAdapter().instantiateItem(viewPager, 0);
    editorLayoutFragment = (EditorLayoutFragment) viewPager.getAdapter().instantiateItem(viewPager, 1);
    editorFilesFragment = (EditorFilesFragment) viewPager.getAdapter().instantiateItem(viewPager, 2);

    editorCodeFragment.currentFile = intent.getStringExtra("projectPath") + "/android/app/src/main/java/" + projectMeta.getPackageName().replaceAll("\\.","/") + "/MainActivity.java";

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int _position) {

      }

      @Override
      public void onPageScrollStateChanged(int _scrollState) {

      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    menu.add("Compile")
            .setIcon(R.drawable.ic_play_arrow)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menu.add("Save")
            .setIcon(R.drawable.ic_save)
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
    }
    return super.onOptionsItemSelected(item);
  }

  public static class FragmentAdapter extends FragmentStatePagerAdapter {
    Context context;
    int tabCount;

    public FragmentAdapter(Context context, FragmentManager fm, int tabCount) {
      super(fm);
      this.context = context;
      this.tabCount = tabCount;
    }

    @Override
    public int getCount(){
      return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int _position) {
      if (_position == 0) return "Code";
      if (_position == 1) return "Layout";
      if (_position == 2) return "Files";
      else return null;
    }

    @Override
    public Fragment getItem(int _position) {
      if (_position == 0) return new EditorCodeFragment();
      if (_position == 1) return new EditorLayoutFragment();
      if (_position == 2) return new EditorFilesFragment();
      else return null;
    }
  }
}