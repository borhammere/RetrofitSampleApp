package com.example.retrofitsampleapp.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitsampleapp.domain.GitProjectEntity;
import com.example.retrofitsampleapp.R;

public class GitProjectViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView = itemView.findViewById(R.id.title_text_view);
    private TextView subtitleTextView = itemView.findViewById(R.id.subtitle_text_view);

    public GitProjectViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(GitProjectEntity gitProjectEntity) {
        titleTextView.setText(gitProjectEntity.getName());
        subtitleTextView.setText(gitProjectEntity.getDescription());
    }

}
