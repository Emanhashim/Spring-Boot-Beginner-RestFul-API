package com.example.May3.UserLogin;

import com.example.May3.Domain.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly = true)

public interface LoginRepository extends JpaRepository<RegisterUser, Long> {

    Optional<RegisterUser> findByResetPasswordToken(String resetPasswordToken);
    Optional<RegisterUser> findByUsername(String username);
    Optional<RegisterUser> findByEmail(String username);
}
