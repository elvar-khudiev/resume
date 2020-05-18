package com.company.dao.impl;

import com.company.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Integer>, UserSkillRepositoryCustom {

    UserSkill findByPower(String power);

    @Query(value = "select * from user_skill us where us.user_id = ?", nativeQuery = true)
    List<UserSkill> findByUserId(int id);

    @Query(value = "select * from user_skill us where us.skill_id = ?", nativeQuery = true)
    UserSkill findBySkill(int skillId);
}
