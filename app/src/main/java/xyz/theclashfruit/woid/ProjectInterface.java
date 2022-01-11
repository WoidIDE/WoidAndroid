package xyz.theclashfruit.woid;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.util.regex.Pattern;

public class ProjectInterface {
  public static boolean generateNewProject(Context context, String projectName, String packageName, String minSdk, String targetSdk) {
    if(!StorageUtil.isFileExist(context.getFilesDir().getPath() + "/projects/" + projectName.replaceAll("\\s+","") + "/meta.json") && !projectName.equals(""))
      StorageUtil.createDirectory(context.getFilesDir().getPath() + "/projects/" + projectName.replaceAll("\\s+",""));
    else
      return false;

    try {
      StorageUtil.createDirectory(context.getFilesDir().getPath() + "/projects/" + projectName.replaceAll("\\s+","") + "/android");

      ProjectMetaGson metaJson = new ProjectMetaGson();

      metaJson.setMetaVersion("1");
      metaJson.setProjectName(projectName);
      metaJson.setPackageName(packageName);
      metaJson.setMinSdk(Integer.parseInt(minSdk));
      metaJson.setTargetSdk(Integer.parseInt(targetSdk));

      Gson gson = new Gson();
      String json = gson.toJson(metaJson);

      StorageUtil.createFile(context.getFilesDir().getPath() + "/projects/" + projectName.replaceAll("\\s+","") + "/meta.json", json);

      generateAndroidFiles(context.getFilesDir().getPath() + "/projects/" + projectName.replaceAll("\\s+","") + "/android", projectName, packageName);

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static boolean generateAndroidFiles(String path, String projectName, String packageName) {
    String formattedPackageName = packageName.replaceAll("\\.","/");

    Log.d("pkg", formattedPackageName);

    StorageUtil.createDirectory(path + "/app/src/main/java/" + formattedPackageName);
    StorageUtil.createFile(path + "/app/src/main/java/" + formattedPackageName + "/MainActivity.java", "package " + packageName + ";\n" +
            "\n" +
            "import androidx.appcompat.app.AppCompatActivity;\n" +
            "\n" +
            "import android.os.Bundle;\n" +
            "\n" +
            "public class MainActivity extends AppCompatActivity {\n" +
            "\n" +
            "  @Override\n" +
            "  protected void onCreate(Bundle savedInstanceState) {\n" +
            "    super.onCreate(savedInstanceState);\n" +
            "    setContentView(R.layout.activity_main);\n" +
            "  }\n" +
            "}");

    return true;
  }
}
