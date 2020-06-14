package com.company.service;

import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    public CustomUserDetailsServiceImpl() {
        System.err.println("\nConstructor - CustomUserDetailsServiceImpl\n");
    }

    @Autowired
    private UserServiceInter userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.err.println("\n----- loadUserByUsername (email) -----\n");

        User user = userService.getByEmail(email);
        System.err.println(user);
        if (user != null) {
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(email);

            builder.disabled(false);
            builder.password(user.getPassword());
            String[] authoritiesArr = null;

            if (user.getAuthorityId().getName().equals("ADMIN")) {
                authoritiesArr = new String[]{"ADMIN", "USER"};
            } else if (user.getAuthorityId().getName().equals("USER")) {
                authoritiesArr = new String[]{"USER"};
            }
            builder.authorities(authoritiesArr);
            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
