package com.asd.gotome.usermanagement.code.repo;

import com.asd.gotome.usermanagement.code.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
