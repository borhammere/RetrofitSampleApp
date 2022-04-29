package com.example.retrofitsampleapp.data;

import com.example.retrofitsampleapp.domain.GitProjectEntity;
import com.example.retrofitsampleapp.domain.GitUserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {
    @GET("users")
    Call<List<GitUserEntity>> getUsers();

    @GET("users/{user}/repos")
    Call<List<GitProjectEntity>> getProjects(@Path("user") String user);
}
