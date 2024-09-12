package com.example.boot.Repository;

import com.example.boot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //User findByMail(User user);
}
