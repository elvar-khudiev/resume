package com.company;

import com.company.dao.impl.UserRepository;
import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.company.service.inter.UserServiceInter;

@SpringBootApplication
public class ResumeDbAppFwJpaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeDbAppFwJpaSpringApplication.class, args);
    }

    @Autowired
    UserServiceInter userService;

    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//				List<User> list = userService.getAll(null, null, null);
//				for (int i = 0; i < list.size(); i++) {
//					User user = list.get(i);
//					System.out.println("User " + (i + 1) + " - " + user.getName() + " - " + user.getEmail() + " - " + user.getPassword());
//                }

                System.out.println(userService.getAll(null, null, null));

//                User user = new User();
//                user.setEmail("elvarkhudiev" +
//                        (userService.getAll(null, null, null).size() + 1)
//                        + "@mail.ru");
//                System.out.println(userService.add(user));

//                List<UserSkill> list = userSkillRepo.getAllUserSkill();
//                System.out.println(list);
            }
        };

        return clr;
    }

}
