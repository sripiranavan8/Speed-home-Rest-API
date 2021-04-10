package com.sripiranavan.freelance.spring.speedhome.data;

import com.sripiranavan.freelance.spring.speedhome.entities.Client;
import com.sripiranavan.freelance.spring.speedhome.entities.Role;
import com.sripiranavan.freelance.spring.speedhome.entities.User;
import com.sripiranavan.freelance.spring.speedhome.repositories.ClientRepository;
import com.sripiranavan.freelance.spring.speedhome.repositories.RoleRepository;
import com.sripiranavan.freelance.spring.speedhome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitData {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    public void LoadData(ApplicationReadyEvent event){
        if (roleRepository.count() == 0){
            Role role = new Role();
            role.setRoleName("Guest");
            roleRepository.save(role);

            User user = new User();
            user.setUserName("Sripiranavan");
            user.setPassword(passwordEncoder.encode("12345"));
            user.setUserRole(role);
            userRepository.save(user);

            Client client = new Client();
            client.setClientId("client1");
            client.setSecret(passwordEncoder.encode("secret1"));
            client.setGrantType("password");
            client.setScope("read");
            clientRepository.save(client);
        }
    }
}
