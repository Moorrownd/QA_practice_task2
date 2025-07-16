package com.example.api.dto;

public class PostDto {
    public String title;
    public String body;
    public int userId;

    public PostDto(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
