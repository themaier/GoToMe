package com.asd.gotome.usermanagement.repo;

import com.asd.gotome.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
