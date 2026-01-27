package com.voltando.project.repositories;

import com.voltando.project.dtos.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
