package com.vdudnyk.blogbackend.post;

import lombok.Data;

import java.util.List;

@Data
class AddPostRequest {
    private String title;
    private String content;
    private List<String> tags;
}
