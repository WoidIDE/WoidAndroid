package xyz.theclashfruit.woid;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;

public class ProjectInterface {
  public static boolean generateNewProject(Context context, String projectName, String packageName, String minSdk, String targetSdk) {
    if(!StorageUtil.isFileExist(context.getFilesDir().getPath() + "/projects/" + projectName.replaceAll("\\s+","") + "/meta.json"))
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

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private boolean generateAndroidFiles(String path) {
    return true;
  }
}
