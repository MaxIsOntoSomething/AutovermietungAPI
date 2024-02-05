package com.SalerProjekt.repository;
import com.SalerProjekt.entity.User;
import com.SalerProjekt.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    User findUserByRole(UserRole userRole);
}
