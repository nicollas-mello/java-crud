package com.voltando.project.repositories;

import com.voltando.project.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
