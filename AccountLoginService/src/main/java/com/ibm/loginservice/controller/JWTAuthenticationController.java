package com.ibm.loginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.loginservice.config.JwtTokenUtil;
import com.ibm.loginservice.dto.JwtResponse;
import com.ibm.loginservice.dto.UserDTO;
import com.ibm.loginservice.serviceImpl.AccountLoginService;


@RestController
public class JWTAuthenticationController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	AccountLoginService accountLoginService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userdto) throws Exception {

			authenticate(userdto);

			final UserDetails userDetails = accountLoginService.loadUserByUsername(userdto.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	public void authenticate(UserDTO user) throws Exception{
		UserDTO dto = accountLoginService.getUser(user);
		if (dto == null) {
			throw new UsernameNotFoundException("User not found" + user.getUsername());
		} 
		else if (!user.getPassword().equals(dto.getPassword())) {
			throw new BadCredentialsException("Invalid Credentials");
		}
	}
	
}
