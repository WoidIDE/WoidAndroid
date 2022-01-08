package xyz.theclashfruit.woid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class EditorActivity extends AppCompatActivity {

  private Toolbar toolBar;
  private ViewPager viewPager;
  private TabLayout tabLayout;

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

    viewPager.setAdapter(new FragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 3));
    tabLayout.setupWithViewPager(viewPager);

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
    menu.add("Save")
            .setIcon(R.drawable.ic_save)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    menu.add("Import Files")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    return true;
  }

  public class FragmentAdapter extends FragmentStatePagerAdapter {
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