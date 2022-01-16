package xyz.theclashfruit.woid;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

      generateAndroidFiles(context, context.getFilesDir().getPath() + "/projects/" + projectName.replaceAll("\\s+","") + "/android", projectName, metaJson);

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static void generateAndroidFiles(Context context, String path, String projectName, ProjectMetaGson metaJson) throws IOException {
    String formattedPackageName = metaJson.getPackageName().replaceAll("\\.","/");

    ProjectFileGeneratorUtils.unzipFromAssets(context, "AndroidTemplate.zip", path);

    StorageUtil.createDirectory(path + "/app/src/main/java/" + formattedPackageName);
    StorageUtil.move(path + "/app/src/main/java/$packagename/MainActivity.java", path + "/app/src/main/java/" + formattedPackageName + "/MainActivity.java");
    StorageUtil.deleteDirectory(path + "/app/src/main/java/$packagename");

    ArrayList<String> filesToReplaceIn = new ArrayList<String>();

    filesToReplaceIn.add("/settings.gradle");
    filesToReplaceIn.add("/app/build.gradle");
    filesToReplaceIn.add("/app/src/main/AndroidManifest.xml");
    filesToReplaceIn.add("/app/src/main/java/" + formattedPackageName + "/MainActivity.java");

    for (String file : filesToReplaceIn) {
      String currentFileRead = StorageUtil.readFile(path + file);

      try {
        currentFileRead = currentFileRead.replaceAll("\\$packagename", metaJson.getPackageName());
        currentFileRead = currentFileRead.replaceAll("\\$\\{minSdkVersion\\}", metaJson.getMinSdk().toString());
        currentFileRead = currentFileRead.replaceAll("\\$\\{targetSdkVersion\\}", metaJson.getTargetSdk().toString());
        currentFileRead = currentFileRead.replaceAll("\\$appname", projectName);
      } catch (Exception e) {
        Log.e("Error", e.toString());
      }

      StorageUtil.deleteFile(path + file);
      StorageUtil.createFile(path + file, currentFileRead);
    }
  }
}
