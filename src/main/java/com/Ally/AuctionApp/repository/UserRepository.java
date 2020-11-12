package com.Ally.AuctionApp.repository;

import com.Ally.AuctionApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
}
