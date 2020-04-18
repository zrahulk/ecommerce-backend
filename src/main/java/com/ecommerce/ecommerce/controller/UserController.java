package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.config.JWTTokenProvider;
import com.ecommerce.ecommerce.constants.SecurityConstants;
import com.ecommerce.ecommerce.payload.JWTLoginResponse;
import com.ecommerce.ecommerce.payload.LoginRequest;
import com.ecommerce.ecommerce.service.MapValidationService;
import com.ecommerce.ecommerce.validator.UserPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.ecommerce.dao.UserRepo;
import com.ecommerce.ecommerce.pojo.CustomUserDetails;
import com.ecommerce.ecommerce.pojo.User;
import com.ecommerce.ecommerce.service.UserService;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	private MapValidationService mapValidationService;
	@Autowired
	private UserPasswordValidator userPasswordValidator;
	@Autowired
	UserRepo userRepo;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to new world";
	}

	@PostMapping("/api/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request,
											  BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap !=null) return errorMap;

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt  = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JWTLoginResponse(true,jwt));
	}

	@GetMapping("/api/user" )
	public ResponseEntity<Object> user() {
		return new ResponseEntity<Object>(
				userRepo.findByEmail("z.rahul.k@gmail.com").get().getRoles(),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/checkEmail")
	public boolean checkEmail(@RequestBody String email) {
		if(userRepo.getByEmail(email)!=null) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/ForgetPassword")
	public String forgetPassword(@RequestBody String email ) {
		User user = userRepo.getByEmail(email); 
		if(user==null) {
			return "wrong Email";
		}
		
		return "Password is "+user.getPassword();
		
	}
	
	@PostMapping("/registration")
	public ResponseEntity<?> registerUserRole(@Valid @RequestBody User user, BindingResult result) {

		userPasswordValidator.validate(user,result);
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);

		if(errorMap !=null) return errorMap;



		return new ResponseEntity<User>(
				userService.registerUserRole(user),
				HttpStatus.CREATED);
	}
	
	
	@Secured("ROLE_USER")
	@GetMapping("/secured")
	public ResponseEntity<Object> secWelcome(@AuthenticationPrincipal CustomUserDetails userDetailSevice) {
			
		return new ResponseEntity<Object>(userDetailSevice,HttpStatus.OK);
	}
	
	
	
}
