package xyz.theclashfruit.woid.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.FileOutputStream;

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
}
