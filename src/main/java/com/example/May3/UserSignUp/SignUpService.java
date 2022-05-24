package com.example.May3.UserSignUp;

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
public class SignUpService implements UserDetailsService {


    //    dependency injection or calling repository inside service
    @Autowired
    SignUpRepository signUpRepository;


//   this is default method while implementing userdetail service

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        RegisterUser registerUser = signUpRepository.findByUsername(email).get();
        String passCode = registerUser.getPassword();

        return new User(email, passCode, new ArrayList<>());

    }


    //    this  is response for requests
//    public String register(SignUpRequest request) {
//        return "works";
//    }


    //this function responsible to check data's from fields string
//    private boolean checkname(String input) {
//        char currentCharacter;
//        boolean numberPresent = false;
//        boolean upperCasePresent = false;
//        boolean lowerCasePresent = false;
//
//        for (int i = 0; i < input.length(); i++) {
//            currentCharacter = input.charAt(i);
//            if (Character.isDigit(currentCharacter)) {
//                numberPresent = true;
//            } else if (Character.isUpperCase(currentCharacter)) {
//                upperCasePresent = true;
//            } else if (Character.isLowerCase(currentCharacter)) {
//                lowerCasePresent = true;
//            }
//        }
//
//        return !numberPresent && upperCasePresent && lowerCasePresent;
//    }

//this functions checks users password data

    private boolean checkString(String input) {
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean lowerCasePresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (Character.isLowerCase(currentCharacter)) {
                lowerCasePresent = true;
            }
        }
        return numberPresent && upperCasePresent && lowerCasePresent;
    }


//    this checks password validation  and sends error message

    public ResponseEntity<?> Validation(SignUpRequest request) {

        return ResponseEntity.badRequest().body(new SignUpResponse("Error: Invalid Password"));
    }

//this is the signup function alone

    public ResponseEntity<?> signUp(RegisterUser registerUser, String passCode) {
        boolean userExists = signUpRepository.findByUsername(registerUser.getUsername()).isPresent();
        boolean userExist = signUpRepository.findByEmail(registerUser.getEmail()).isPresent();
        if (userExists) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Error: PhoneNumber  is already in Use!"));
        } else if (userExist) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Error: Email already in Use!"));
        }

        if (!checkString(passCode)) {
            return ResponseEntity.badRequest().body(
                    new SignUpResponse("Your password must have atleast 1 number, 1 uppercase and 1 lowercase letter"));
        } else if (passCode.chars().filter(ch -> ch != ' ').count() < 4
                || passCode.chars().filter(ch -> ch != ' ').count() > 8) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Your password must have 8 to 15 characters "));
        }


        if (registerUser.getEmail().isBlank() ) {
            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your email"));
        }
        if (registerUser.getFirstname().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your name "));
        }
        if (registerUser.getLastname().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your father name "));
        }
        if (registerUser.getUsername().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your phone "));
        }
        if (registerUser.getCountry().isBlank()) {

            return ResponseEntity.badRequest().body(new SignUpResponse("Enter your country "));
        }
        signUpRepository.save(registerUser);
        RegisterUser user = signUpRepository.findByUsername(registerUser.getUsername()).get();

//        this returns the response values to 201

        return ResponseEntity.ok(new SignUpResponse(
                user.getUsername(),
                user.getRoles(),
                user.getCountry(),
                user.getGender(),
                "Successfully Registered",
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getConfirmPassword(),
                user.getConfirmPassword()));
    }

}
