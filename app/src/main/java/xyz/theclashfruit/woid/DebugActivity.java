package xyz.theclashfruit.woid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import io.github.rosemoe.sora.widget.CodeEditor;
import xyz.theclashfruit.woid.utils.WoidUtils;

public class DebugActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_debug);

    Intent intent = getIntent();
    String error = intent.getStringExtra("error");

    Window window = getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setNavigationBarColor(Color.parseColor("#14181f"));

    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("xyz.theclashfruit.woid_preferences", Context.MODE_PRIVATE);

    if(sharedPref.getBoolean("dark_mode", false))
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    else
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    AlertDialog.Builder messageBox = new AlertDialog.Builder(this);

    messageBox.setTitle("An Error Occurred");
    messageBox.setMessage(error);
    messageBox.setCancelable(false);
    messageBox.setPositiveButton("Report", (dialog, which) -> {
      WoidUtils.showToast(this, "Soon");
      finishAffinity();
    });
    messageBox.setNegativeButton("Close App", (dialog, which) -> {
      finishAffinity();
    });
    messageBox.show();
  }
}