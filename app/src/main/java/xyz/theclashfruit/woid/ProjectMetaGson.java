package xyz.theclashfruit.woid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectMetaGson {

  @SerializedName("metaVersion")
  @Expose
  private String metaVersion;
  @SerializedName("projectName")
  @Expose
  private String projectName;
  @SerializedName("packageName")
  @Expose
  private String packageName;
  @SerializedName("minSdk")
  @Expose
  private Integer minSdk;
  @SerializedName("targetSdk")
  @Expose
  private Integer targetSdk;

  public String getMetaVersion() {
    return metaVersion;
  }

  public void setMetaVersion(String metaVersion) {
    this.metaVersion = metaVersion;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public Integer getMinSdk() {
    return minSdk;
  }

  public void setMinSdk(Integer minSdk) {
    this.minSdk = minSdk;
  }

  public Integer getTargetSdk() {
    return targetSdk;
  }

  public void setTargetSdk(Integer targetSdk) {
    this.targetSdk = targetSdk;
  }

}