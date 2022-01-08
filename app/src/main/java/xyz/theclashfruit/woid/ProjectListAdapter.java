package xyz.theclashfruit.woid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

  private String[] localDataSet;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;

    public ViewHolder(View view) {
      super(view);
      textView = (TextView) view.findViewById(R.id.textView);
    }

    public TextView getTextView() {
      return textView;
    }
  }

  public ProjectListAdapter(String[] dataSet) {
    localDataSet = dataSet;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    View view = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.project_list, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ProjectListAdapter.ViewHolder viewHolder, final int position) {
    viewHolder.getTextView().setText(localDataSet[position]);
  }

  @Override
  public int getItemCount() {
    return localDataSet.length;
  }
}

