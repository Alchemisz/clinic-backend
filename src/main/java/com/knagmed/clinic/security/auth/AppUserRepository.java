package com.knagmed.clinic.security.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE AppUser au SET au.password = :newPassword WHERE au.id = :appUserId")
    void updateUserPassword(@Param("newPassword") String newPassword, @Param("appUserId") Long appUserId);

}
