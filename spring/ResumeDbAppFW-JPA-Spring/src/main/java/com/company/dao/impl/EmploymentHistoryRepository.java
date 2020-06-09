package com.company.dao.impl;

import com.company.entity.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Integer>, EmploymentHistoryRepositoryCustom {

    EmploymentHistory findByHeader(String header);
}
