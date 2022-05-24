package com.example.May3.UserLogin;

import com.example.May3.Domain.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RegisterUser registerUser = loginRepository.findByUsername(email).get();
        String passwor = registerUser.getPassword();

        return new User(email, passwor, new ArrayList<>());
    }

    public ResponseEntity<?> LoginUser(UserDetails username, String jwt) {
        RegisterUser registerUser = loginRepository.findByUsername(username.getUsername()).get();
        boolean userExists = loginRepository.findByUsername(registerUser.getUsername()).isPresent();

        if (!userExists) {
            System.out.println("Wrong username or password");
        }

        return ResponseEntity.ok(new
                LoginResponse(registerUser.getId(),
                registerUser.getUsername(),
                registerUser.getRoles(),
                registerUser.getCountry(),
                registerUser.getGender(),
                jwt));
    }
}
