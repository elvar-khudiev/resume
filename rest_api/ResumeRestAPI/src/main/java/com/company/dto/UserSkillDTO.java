package com.company.dto;

import com.company.entity.UserSkill;

public class UserSkillDTO {
    private Integer id;
    private Integer power;
    private SkillDTO skill;

    public UserSkillDTO() {
    }

    public UserSkillDTO(UserSkill userSkill) {
//        System.out.println("userSkill id = " + userSkill.getId());
//        System.out.println("userSkill power = " + userSkill.getPower());
//        System.out.println("userSkill skill id = " + userSkill.getSkill().getId());
//        System.out.println("userSkill skill name = " + userSkill.getSkill().getName());
//        System.out.println("userSkill user name = " + userSkill.getUser().getName());

        this.id = userSkill.getId();
        this.power = userSkill.getPower();
        this.skill = new SkillDTO(userSkill.getSkillId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public SkillDTO getSkill() {
        return skill;
    }

    public void setSkill(SkillDTO skill) {
        this.skill = skill;
    }
}
