package com.ood.OODPro.Controllers;

import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Payload.Request.SigninRequest;
import com.ood.OODPro.Payload.Request.SignupRequest;
import com.ood.OODPro.Payload.Response.SignInResponse;
import com.ood.OODPro.Payload.Response.SignupResponse;
import com.ood.OODPro.Utils.JwtTokenUtil;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
	@Autowired(required = false)
	AuthenticationManager authenticationManager;
	@Autowired(required = false)
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {
		System.out.println("triggered sigin api");

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(SigninRequest.getEmail(), SigninRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsService userDetails = (UserDetailsService) authentication.getPrincipal();

		ResponseCookie jwtCookie = JwtTokenUtil.getUsernameFromToken(userDetails);


		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new SignInResponse(userDetails.getPhone(),
						userDetails.getUsername(),
						userDetails.getEmail()
						));
		return "Welcome to Springboot";
		
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println("triggered signup api");
		if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Error: PhoneNumber is already registered!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmailId())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserEntity user = new UserEntity(signUpRequest.getPhoneNumber(),
				signUpRequest.getEmailId(),
				encoder.encode(signUpRequest.getPassword()));
		userRepository.save(user);

		return ResponseEntity.ok(new SignupResponse("User registered successfully!"));
	}
}
