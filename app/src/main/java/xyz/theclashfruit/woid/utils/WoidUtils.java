package xyz.theclashfruit.woid.utils;

import android.content.Context;
import android.widget.Toast;

// WoidUtils is just there to make some codes easier to remember and write, and also to make the code cleaner.

public class WoidUtils {
  public boolean GenerateLogFile() {
    // TODO: to be implemented.

    return true;
  }

  public static void showToast(Context context, String text) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
  }
}
