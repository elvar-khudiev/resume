package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Integer>, EmploymentHistoryRepositoryCustom {

    EmploymentHistory findByHeader(String header);
}
