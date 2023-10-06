package org.reflectme.requestservice.service.impl;


import lombok.AllArgsConstructor;
import org.reflectme.requestservice.util.UserData;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnvServiceImpl implements UserDetailsService {
    @Override
    public UserData loadUserByUsername(String name) {
        var password = System.getenv(name + "_service_password");
        System.out.println(password);
        return new UserData(
                name,
                password
        );
    }

}
