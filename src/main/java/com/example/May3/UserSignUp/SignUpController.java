package com.example.May3.UserSignUp;

import com.example.May3.Domain.RegisterUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor

@RequestMapping("/api/users")
@Api(value = "Signup User Endpoint", description = "Here we take new user data inorder to register")
@ApiResponses(value ={
        @ApiResponse(code = 404, message = "web user that a requested page is not available "),
        @ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
        @ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
        @ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
        @ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})

public class SignUpController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SignUpService signUpService;
    @Autowired
    SignUpRepository signUpRepository;



    @PostMapping("/signup")
        public ResponseEntity<?> register(@RequestBody SignUpRequest request) {
            String pass1 = request.getPassword();
            String pass2 = request.getConfirmPassword();

            if (!pass1.matches(pass2)) {
                return ResponseEntity.badRequest().body(new SignUpResponse("Error: Passwords don't match!"));
            }

            RegisterUser user = new RegisterUser(request.getFirstname(),
                                                request.getLastname(),
                    passwordEncoder.encode(request.getPassword()),
                                            request.getUsername(),
                                            request.getBirthday(),
                                            request.getEmail(),
                                            request.getGender(),
                                            request.getCountry());

                     return signUpService.signUp(user, pass1);
        }
    }


