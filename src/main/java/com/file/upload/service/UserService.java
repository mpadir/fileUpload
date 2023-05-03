package com.file.upload.service;

import com.file.upload.model.User;
import com.file.upload.model.response.UserResponse;

public interface UserService {

    UserResponse register(User user);
}
