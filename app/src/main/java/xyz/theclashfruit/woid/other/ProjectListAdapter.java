package xyz.theclashfruit.woid.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import xyz.theclashfruit.woid.gson.ProjectMetaGson;
import xyz.theclashfruit.woid.R;
import xyz.theclashfruit.woid.utils.StorageUtil;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

  private ArrayList<String> localDataSet;
  private final OnItemClickListener clickListener;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public final TextView textView;
    public final TextView textView2;
    public final LinearLayout wholeView;

    public ViewHolder(View view) {
      super(view);

      textView = view.findViewById(R.id.textView3);
      textView2 = view.findViewById(R.id.textView5);
      wholeView = view.findViewById(R.id.wholeView);
    }
  }

  public ProjectListAdapter(ArrayList<String> dataSet, OnItemClickListener listener) {
    localDataSet = dataSet;
    clickListener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    View view = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.project_list, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ProjectListAdapter.ViewHolder viewHolder, final int position) {
    try {
      Gson gson = new Gson();

      ProjectMetaGson projectMeta = gson.fromJson(StorageUtil.readFile(localDataSet.get(position) + "/meta.json"), ProjectMetaGson.class);

      viewHolder.textView.setText(projectMeta.getProjectName());
      viewHolder.textView2.setText(projectMeta.getPackageName());

      viewHolder.wholeView.setOnClickListener(v -> {
        clickListener.onItemClick(localDataSet.get(position));
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getItemCount() {
    return localDataSet.size();
  }
}

