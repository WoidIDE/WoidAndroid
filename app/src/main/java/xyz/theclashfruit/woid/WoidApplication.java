package xyz.theclashfruit.woid;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.os.Process;
import android.util.Log;

import java.util.Arrays;

import xyz.theclashfruit.woid.utils.StorageUtil;

public class WoidApplication extends android.app.Application {

  @Override
  public void onCreate() {
    super.onCreate();

    Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
      Log.e("WoidApplication", "Woid Crashed", e);

      String report = "---------  Debug log  ---------\n\nDevice Model: " + android.os.Build.MODEL + "\nDevice Vendor: " + android.os.Build.MANUFACTURER + "\n\nAndroid Version: " + Build.VERSION.RELEASE + "\n\n--------- Stack trace ---------\n\n" + e.toString() + "\n";

      for (StackTraceElement a : e.getStackTrace()) {
        report += "    " + a.toString()+"\n";
      }

      report += "\n-------------------------------\n\n";

      StorageUtil.createFile(getFilesDir() + "/debug.log", report);

      Intent i1 = new Intent(getApplicationContext(), DebugActivity.class);
      i1.putExtra("error", report);
      i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i1, PendingIntent.FLAG_ONE_SHOT);

      ((AlarmManager) getSystemService(ALARM_SERVICE)).set(
              AlarmManager.RTC, System.currentTimeMillis() + 1000, pendingIntent);

      Process.killProcess(Process.myPid());
    });
  }
}
