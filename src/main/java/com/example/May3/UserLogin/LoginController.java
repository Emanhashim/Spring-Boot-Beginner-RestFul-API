package com.example.May3.UserLogin;

import com.example.May3.Domain.RegisterUser;
import com.example.May3.UserSignUp.SignUpResponse;
import com.example.May3.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "SignIn User Endpoint", description = "Here we take new user's PhoneNumber and Password to Signin")
@ApiResponses(value ={
        @ApiResponse(code = 404, message = "web user that a requested page is not available "),
        @ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
        @ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
        @ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
        @ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
@RequestMapping("/api/users")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    private RegisterUser registerUser;

    private UserDetails userDetails;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;



    @PostMapping("/login")
    @ApiOperation(value ="This is UserManagement Module For Sign in User with (Username and Password)")
    public ResponseEntity<?> createAuthenticationToken( @RequestBody LoginRequest authenticationRequest) throws AuthenticationException  {

        boolean userExists = loginRepository
                .findByUsername(authenticationRequest.getUsername())
                .isPresent();
        if(userExists) {
            userDetails = loginService
                    .loadUserByUsername(authenticationRequest.getUsername());
            registerUser=loginRepository.findByUsername(authenticationRequest.getUsername()).get();
        }

        else{

            return ResponseEntity
                    .badRequest()
                    .body(new SignUpResponse("Error: Username does not exist"));

        }
        registerUser =	loginRepository.findByUsername(authenticationRequest.getUsername()).get();
        Authentication authentication =   authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        userDetails = loginService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt= jwtUtil.generateToken(userDetails);
        loginRepository.findByUsername(authenticationRequest.getUsername()).get().setResetPasswordToken(jwt);
        registerUser =	loginRepository.findByUsername(authenticationRequest.getUsername()).get();

//		}



        return ResponseEntity.ok(new LoginResponse(registerUser.getId(),
                registerUser.getUsername(),
                registerUser.getRoles(),
                registerUser.getCountry(),
                registerUser.getGender(),jwt));

    }

}
