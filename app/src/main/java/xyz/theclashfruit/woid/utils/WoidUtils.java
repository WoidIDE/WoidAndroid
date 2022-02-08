package xyz.theclashfruit.woid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.FileOutputStream;
import java.util.Locale;

import xyz.theclashfruit.woid.MainActivity;

// WoidUtils is just there to make some codes easier to remember and write, and also to make the code cleaner.

public class WoidUtils {
  Context context;

  public void GenerateLogFile(StackTraceElement[] stackTraceElements) {
    String logOutput = "--------- Stack trace ---------\n\n" + stackTraceElements.toString()+"\n\n" + "-------------------------------";

    StorageUtil.createFile(context.getFilesDir().getPath() + "/debug.log", logOutput);
  }

  public static void showToast(Context context, String text) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
  }

  public static void setLocale(Activity activity, String languageCode) {
    Locale locale = new Locale(languageCode);
    Locale.setDefault(locale);
    Resources resources = activity.getResources();
    Configuration config = resources.getConfiguration();
    config.setLocale(locale);
    resources.updateConfiguration(config, resources.getDisplayMetrics());
  }
}
