package xyz.theclashfruit.woid.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import xyz.theclashfruit.woid.gson.ProjectMetaGson;
import xyz.theclashfruit.woid.R;
import xyz.theclashfruit.woid.utils.StorageUtil;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {

    private ArrayList<String> localDataSet;
    private final OnItemClickListener clickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imgFileType;
        public final TextView textFileName;
        public final LinearLayout wholeView;

        public ViewHolder(View view) {
            super(view);

            imgFileType = view.findViewById(R.id.imgFileType);
            textFileName = view.findViewById(R.id.textFileName);
            wholeView = view.findViewById(R.id.wholeView);
        }
    }

    public FileListAdapter(ArrayList<String> dataSet, OnItemClickListener listener) {
        localDataSet = dataSet;
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.file_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileListAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.textFileName.setText(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

