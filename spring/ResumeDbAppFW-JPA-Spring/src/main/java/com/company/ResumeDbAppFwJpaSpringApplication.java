package com.company;

import com.company.dao.impl.CountryRepository;
import com.company.entity.User;
import com.company.service.impl.CountryServiceImpl;
import com.company.service.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    @ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner run() {
        CommandLineRunner clr = args -> {
            System.err.println("CommandLineRunner");

//            userService.deleteByObject(userService.getByEmail("x"));
//            User u = new User(1, "x", "x", "x", "x", "x");
//            System.err.println(userService.add(u));

//            User u = userService.getByEmail("elvarkhudiev@mail.ru");
//
//            System.out.println("--------------------------------");
//            System.out.println("User: " + u);
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
