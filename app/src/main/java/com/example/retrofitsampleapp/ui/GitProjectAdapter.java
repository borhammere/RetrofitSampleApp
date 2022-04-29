package com.example.retrofitsampleapp.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitsampleapp.domain.GitProjectEntity;
import com.example.retrofitsampleapp.R;

import java.util.List;

public class GitProjectAdapter extends RecyclerView.Adapter<GitProjectViewHolder> {
    private List<GitProjectEntity> data;

    public void setData(List<GitProjectEntity> projects) {
        data = projects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GitProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GitProjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_git_project, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GitProjectViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private GitProjectEntity getItem(int pos) {
        return data.get(pos);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
