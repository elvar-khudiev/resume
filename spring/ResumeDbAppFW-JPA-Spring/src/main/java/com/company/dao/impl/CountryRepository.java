package com.company.dao.impl;

import com.company.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>, CountryRepositoryCustom {

    Country findByName(String name);
    Country findByNameAndNationality(String name, String nationality);
}
