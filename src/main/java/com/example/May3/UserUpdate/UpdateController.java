package com.example.May3.UserUpdate;


import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ApiResponses(value ={
		@ApiResponse(code = 404, message = "web user that a requested page is not available "),
		@ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
		@ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
		@ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
		@ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
@RequestMapping("/api/users")
public class UpdateController {
	
	@Autowired
	private UpdateService updateService;

	@Autowired
	PasswordEncoder passwordEncoder;
	

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UpdateRequest request) {
		String pho= request.getPassword();
        String pho2=request.getNewpass();

		String newpassword= passwordEncoder.encode(pho2);
        if(pho.matches(pho2)) {
        	
            return ResponseEntity.badRequest().body(new UpdateResponse("Password same as before chose a different one"));
        }
        
		
		return updateService.updatePassword(request,newpassword,pho);
	}

}
