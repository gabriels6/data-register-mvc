package com.gomes.dataregister.repository;

import com.gomes.dataregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsernameAndPassword(String Username, String Password);

    User findByUsername(String username);
}
