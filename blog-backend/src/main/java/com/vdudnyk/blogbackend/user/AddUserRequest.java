package com.vdudnyk.blogbackend.user;

import lombok.Data;

@Data
class AddUserRequest {
    private String username;
    private String password;
}
