package com.voltando.project.services;

import com.voltando.project.dtos.UserCreateDto;
import com.voltando.project.dtos.UserResponseDto;
import com.voltando.project.entities.UserEntity;
import com.voltando.project.repositories.RoleEntityRepository;
import com.voltando.project.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;

    public UserEntityService(UserEntityRepository userEntityRepository, RoleEntityRepository roleEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
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

    public UserEntity createUser(UserCreateDto dto) {
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setSalary(dto.getSalary());

        user.setPassword(dto.getPassword());
        user.setRoles(Set.of(roleEntityRepository.findByName("ROLE_USER")));

        return userEntityRepository.save(user);
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
