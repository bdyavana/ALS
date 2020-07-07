package com.ibm.loginservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.loginservice.config.JwtTokenUtil;
import com.ibm.loginservice.dto.JwtRequest;
import com.ibm.loginservice.dto.JwtResponse;
import com.ibm.loginservice.serviceImpl.AccountLoginService;


@RestController
public class JWTAuthenticationController {
	
	private static Logger logger = LoggerFactory.getLogger(JWTAuthenticationController.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	AccountLoginService accountLoginService;
	
	@Autowired
	private AuthenticationManager aAuthnticationManager;
	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest aRequest) throws Exception {

		try {
			aAuthnticationManager.authenticate(new UsernamePasswordAuthenticationToken(aRequest.getUsername(), aRequest.getPassword()));
		}
		catch (BadCredentialsException e) {
			logger.error("Invalid username or password", e);
			throw new Exception("Invalid username or password", e);
		}
		final UserDetails userDetails = accountLoginService.loadUserByUsername(aRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));

	}
	
}
