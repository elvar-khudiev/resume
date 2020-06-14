package com.company.dao.impl;

import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , UserRepositoryCustom {

    User findByName(String name);
    User findByNameAndSurname(String name, String surname);

    @Query(value = "select * from user u where u.email = ?", nativeQuery = true)
    User findByEmail(String email);
}
