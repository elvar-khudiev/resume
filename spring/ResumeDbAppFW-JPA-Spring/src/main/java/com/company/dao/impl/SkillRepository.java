package com.company.dao.impl;

import com.company.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>, SkillRepositoryCustom {

    Skill findByName(String name);
}
