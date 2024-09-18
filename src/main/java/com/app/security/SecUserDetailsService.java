package com.app.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SecUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("jairo","$2a$10$94e06Qz1AgeZ8m1JfvMLq.K6T26PMksBkN7O/PACe.eqBCtlnUfFK", new ArrayList<>());
    }

}
