package com.ood.OODPro.Controllers;

import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Payload.Request.SigninRequest;
import com.ood.OODPro.Payload.Request.SignupRequest;
import com.ood.OODPro.Payload.Response.SignInResponse;
import com.ood.OODPro.Payload.Response.SignupResponse;
import com.ood.OODPro.Payload.Response.UserResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired(required = false)
	AuthenticationManager authenticationManager;
	@Autowired(required = false)
	UserRepository userRepository;

	@Autowired
	JwtTokenUtil jwtUtils;

	@GetMapping("/getUserInfo")
	@CrossOrigin(origins = "http://localhost:8101")
	public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
		System.out.println("triggered user info api"+request.getCookies());

		String id = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));

		System.out.println("id: " + id);
		Optional<UserEntity> userEntity = userRepository.findOneByEmailIdIgnoreCase(id);


		return ResponseEntity.ok()
				.body(new UserResponse(userEntity.get().getId(), userEntity.get().getEmailId(),
						userEntity.get().getPhoneNumber(), userEntity.get().getName()));

	}


}
