package xyz.theclashfruit.woid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SimpleAdapter;
import android.widget.Toolbar;

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

    Handler handle = new Handler();

    new Timer().schedule(new TimerTask() {
      public void run() {
        handle.post(new Runnable() {
          public void run() {
            Intent i1 = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i1);
          }
        });
      }
    }, 1500);
  }
}