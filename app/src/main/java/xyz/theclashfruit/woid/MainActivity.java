package xyz.theclashfruit.woid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SimpleAdapter;
import android.widget.Toolbar;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Window window = getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setNavigationBarColor(Color.parseColor("#14181f"));

    if(!StorageUtil.isDirectoryExists(getFilesDir().getPath() + "/projects"))
      StorageUtil.createDirectory(getFilesDir().getPath() + "/projects");

    if(!StorageUtil.isDirectoryExists(getFilesDir().getPath() + "/plugins"))
      StorageUtil.createDirectory(getFilesDir().getPath() + "/plugins");

    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("xyz.theclashfruit.woid_preferences", Context.MODE_PRIVATE);

    if(sharedPref.getBoolean("dark_mode", false))
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    else
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    Handler handle = new Handler();

    new Timer().schedule(new TimerTask() {
      public void run() {
        handle.post(() -> {
          Intent i1 = new Intent(getApplicationContext(), HomeActivity.class);
          startActivity(i1);
          finish();
        });
      }
    }, 1500);
  }
}