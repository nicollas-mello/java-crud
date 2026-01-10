package com.voltando.project.services;

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

    public List<UserEntity> listAll() {
        return userEntityRepository.findAll();
    }

    public UserEntity createUser(UserEntity userEntity) {
        return userEntityRepository.save(userEntity);
    }

    public UserEntity updateUser(UserEntity updateUserEntity, Integer id) {

        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        userEntity.setUsername(updateUserEntity.getUsername());
        userEntity.setEmail(updateUserEntity.getEmail());


        return userEntityRepository.save(userEntity);
    }

    public void deleteUser(Integer id) {

        if (!userEntityRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        userEntityRepository.deleteById(id);
    }

}
