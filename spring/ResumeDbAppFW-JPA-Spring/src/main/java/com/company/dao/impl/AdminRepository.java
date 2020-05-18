package com.company.dao.impl;

import com.company.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, AdminRepositoryCustom {

    Admin findByName(String name);
    Admin findByNameAndSurname(String name, String surname);

    @Query(value = "select * from admin a where a.email = ?", nativeQuery = true)
    Admin findByEmail(String email);
}
