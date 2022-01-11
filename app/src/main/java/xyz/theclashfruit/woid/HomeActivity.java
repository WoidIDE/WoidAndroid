package xyz.theclashfruit.woid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private Toolbar toolBar;
  private DrawerLayout drawerLayout;
  private NavigationView drawerLayoutNavView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    toolBar = findViewById(R.id.toolBar);
    drawerLayout = findViewById(R.id.drawer_layout);
    drawerLayoutNavView =  findViewById(R.id.nav_view);

    setSupportActionBar(toolBar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolBar, R.string.app_name, R.string.app_name);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();

    drawerLayoutNavView.setNavigationItemSelectedListener(this);

    if(savedInstanceState == null) {
      getSupportActionBar().setSubtitle("Projects");

      HomeFragment homeFragment = HomeFragment.newInstance();
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      fragmentTransaction.replace(R.id.fragmentContainer, homeFragment);
      fragmentTransaction.commit();

      try {
        String[] files = getAssets().list("AndroidPreGeneratorFiles");

        Gson gson = new GsonBuilder().create();
        String jsonArray = gson.toJson(files);
        Log.d("asd", jsonArray);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    FragmentManager drawerFragmentManager = getSupportFragmentManager();
    FragmentTransaction drawerFragmentTransaction = drawerFragmentManager.beginTransaction();

    switch (item.getItemId()) {
      case R.id.menuHome:
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayoutNavView.setCheckedItem(R.id.menuHome);
        getSupportActionBar().setSubtitle("Projects");

        HomeFragment homeFragment = HomeFragment.newInstance();

        drawerFragmentTransaction.replace(R.id.fragmentContainer, homeFragment);
        drawerFragmentTransaction.commit();

        break;
      case R.id.menuPlugins:
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayoutNavView.setCheckedItem(R.id.menuPlugins);
        getSupportActionBar().setSubtitle("Plugins");

        PluginsFragment pluginsFragment = PluginsFragment.newInstance();

        drawerFragmentTransaction.replace(R.id.fragmentContainer, pluginsFragment);
        drawerFragmentTransaction.commit();

        break;
      case R.id.menuSettings:
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayoutNavView.setCheckedItem(R.id.menuSettings);
        getSupportActionBar().setSubtitle("Settings");

        SettingsFragment settingsFragment = new SettingsFragment();

        drawerFragmentTransaction.replace(R.id.fragmentContainer, settingsFragment);
        drawerFragmentTransaction.commit();

        break;
    }

    return false;
  }
}