package com.sripiranavan.freelance.spring.speedhome.security;

import com.sripiranavan.freelance.spring.speedhome.entities.User;
import com.sripiranavan.freelance.spring.speedhome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new SecurityUser(user);
    }
}
