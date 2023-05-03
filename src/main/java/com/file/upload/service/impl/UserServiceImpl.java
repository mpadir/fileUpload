package com.file.upload.service.impl;

import com.file.upload.model.User;
import com.file.upload.model.response.UserResponse;
import com.file.upload.repository.UserRepository;
import com.file.upload.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserResponse register(User user) {
        repository.save(user);
        UserResponse response = new UserResponse();
        response.setUsername(user.getUserName());
        response.setPassword(user.getPassword());
        return response;
    }
}
