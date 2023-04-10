package com.ood.OODPro.Controllers;

import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Payload.Request.SigninRequest;
import com.ood.OODPro.Payload.Request.SignupRequest;
import com.ood.OODPro.Payload.Response.SignInResponse;
import com.ood.OODPro.Payload.Response.SignupResponse;
import com.ood.OODPro.Utils.JwtTokenUtil;
import com.ood.OODPro.pojo.UserDetailsPojo;
import com.ood.OODPro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired(required = false)
	AuthenticationManager authenticationManager;
	@Autowired(required = false)
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtTokenUtil jwtUtils;


	@PostMapping("/signin")
	@CrossOrigin(origins = "http://localhost:8101")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {
		System.out.println("triggered sigin api");

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmailId(), signinRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsPojo userDetails = (UserDetailsPojo) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new SignInResponse(userDetails.getId(), userDetails.getEmailId(),
						userDetails.getPhoneNumber(), userDetails.getFullName(), jwtCookie.getValue()));

	}
	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:8101")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println("triggered signup api with body: " + signUpRequest.toString());
		if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Error: PhoneNumber is already registered!"));
		}

		if (userRepository.existsByEmailId(signUpRequest.getEmailId())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserEntity user = new UserEntity();
		user.setName(signUpRequest.getFullName());
		user.setEmailId(signUpRequest.getEmailId());
		user.setPhoneNumber(signUpRequest.getPhoneNumber());

		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		userRepository.save(user);

		return ResponseEntity.ok(new SignupResponse("User registered successfully!"));
	}
}
