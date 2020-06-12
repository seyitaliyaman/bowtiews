package com.bowtie.ws.Repository;

import com.bowtie.ws.Entity.User;
import com.bowtie.ws.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserAccount(UserAccount userAccount);
    Optional<User> findByMailAddress(String mailAddress);
}
