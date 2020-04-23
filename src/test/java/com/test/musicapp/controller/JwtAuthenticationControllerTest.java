package com.test.musicapp.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import com.musicapp.controller.JwtAuthenticationController;
import com.musicapp.model.JwtRequest;
import com.musicapp.model.UserDTO;
import com.musicapp.security.JwtTokenUtil;
import com.musicapp.service.JwtUserDetailsService;

@WebMvcTest(controllers = JwtAuthenticationController.class)
@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationControllerTest {
	@InjectMocks
	private JwtAuthenticationController jwtController;

	@Mock
	private JwtUserDetailsService jwtService;
	@Mock
	private AuthenticationManager authManager;
	@Mock
	private JwtTokenUtil jwtTokenUtil;

	@Test
	public void testCreateAuthenticationToken() throws Exception {
		JwtRequest jwtRequest = new JwtRequest("user", "password");
		Mockito.when(jwtService.loadUserByUsername(Mockito.anyString())).thenReturn(null);
		Mockito.when(authManager.authenticate(Mockito.any())).thenReturn(null);
		Mockito.when(jwtTokenUtil.generateToken(Mockito.any())).thenReturn(Mockito.anyString());
		ResponseEntity<?> createAT = jwtController.createAuthenticationToken(jwtRequest);
		Assert.assertEquals(HttpStatus.OK, createAT.getStatusCode());
	}

	@Test(expected = Exception.class)
	public void testCreateAuthenticationTokenShouldThrowDisabledException() throws Exception {
		JwtRequest jwtRequest = new JwtRequest("user", "password");
		Mockito.when(authManager.authenticate(Mockito.any())).thenThrow(Mockito.mock(DisabledException.class));
		jwtController.createAuthenticationToken(jwtRequest);
	}

	@Test(expected = Exception.class)
	public void testCreateAuthenticationTokenShouldThrowBadCredentialsException() throws Exception {
		JwtRequest jwtRequest = new JwtRequest("user", "password");
		Mockito.when(authManager.authenticate(Mockito.any())).thenThrow(Mockito.mock(BadCredentialsException.class));
		jwtController.createAuthenticationToken(jwtRequest);
	}

	@Test
	public void testRegisterUser() throws Exception {
		UserDTO user = new UserDTO("user", "password");
		Mockito.when(jwtService.save(Mockito.any())).thenReturn(null);
		ResponseEntity<?> saveUser = jwtController.saveUser(user);
		Assert.assertEquals(HttpStatus.OK, saveUser.getStatusCode());
	}
}
