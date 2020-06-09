package com.company;

import com.company.dao.impl.UserRepository;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.service.impl.UserServiceImpl;
import com.company.service.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ResumeDbAppFwJpaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeDbAppFwJpaSpringApplication.class, args);
    }

    @Autowired
    UserServiceInter userService;

    @Autowired
    UserSkillServiceInter userSkillService;

    @Autowired
    SkillServiceInter skillService;

    @Autowired
    CountryServiceInter countryService;

    @Autowired
    EmploymentHistoryServiceInter employmentHistoryService;

    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = args -> {

//            System.out.println("--------------------------------");
//            System.err.println(userService.getById(1));
//            System.out.println("--------------------------------");
//            System.err.println(userSkillService.getById(1016));
//            System.out.println("--------------------------------");
//            System.err.println(skillService.getById(1));
//            System.out.println("--------------------------------");
//            System.err.println(countryService.getById(1));
//            System.out.println("--------------------------------");
//            System.err.println(employmentHistoryService.getById(1));
//            System.out.println("--------------------------------");

//            UserSkill c = userSkillService.getAllByUserId(2).get(0);
//            c.setPower(5);
//            userSkillService.update(c);
        };

        return clr;
    }

}
