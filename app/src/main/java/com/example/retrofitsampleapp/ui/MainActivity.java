package com.example.retrofitsampleapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitsampleapp.data.GitHubApi;
import com.example.retrofitsampleapp.domain.GitProjectEntity;
import com.example.retrofitsampleapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private GitHubApi gitHubApi = retrofit.create(GitHubApi.class);

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private GitProjectAdapter adapter = new GitProjectAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadProjects("borhammere");
    }

    private void loadProjects(String username) {
        showProgress(true);
        gitHubApi.getProjects(username).enqueue(new Callback<List<GitProjectEntity>>() {
            @Override
            public void onResponse(Call<List<GitProjectEntity>> call, Response<List<GitProjectEntity>> response) {
                showProgress(false);
                if (response.isSuccessful()) {
                    List<GitProjectEntity> projects = response.body();
                    adapter.setData(projects);
                    Toast.makeText(MainActivity.this, "Size " + projects.size(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error Code " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<GitProjectEntity>> call, Throwable t) {
                showProgress(false);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void showProgress(boolean shouldShow) {
        if (shouldShow) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

}