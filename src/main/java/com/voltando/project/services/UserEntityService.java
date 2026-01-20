package com.voltando.project.services;

import com.voltando.project.dtos.UserCreateDto;
import com.voltando.project.dtos.UserResponseDto;
import com.voltando.project.entities.UserEntity;
import com.voltando.project.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public List<UserResponseDto> listUsers() {
        return userEntityRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                ))
                .toList();
    }

    public UserEntity createUser(UserCreateDto userCreateDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreateDto.getUsername());
        userEntity.setEmail(userCreateDto.getEmail());
        userEntity.setSalary(userCreateDto.getSalary());

        return userEntityRepository.save(userEntity);
    }

    public UserEntity updateUser(UserCreateDto userCreateDto, Integer id) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        userEntity.setUsername(userCreateDto.getUsername());
        userEntity.setEmail(userCreateDto.getEmail());
        userEntity.setSalary(userCreateDto.getSalary());

        return userEntityRepository.save(userEntity);
    }

    public void deleteUser(Integer id) {

        if (!userEntityRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        userEntityRepository.deleteById(id);
    }

}
