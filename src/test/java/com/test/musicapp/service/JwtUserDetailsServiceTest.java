package com.test.musicapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.musicapp.entity.User;
import com.musicapp.model.UserDTO;
import com.musicapp.repository.UserRepository;
import com.musicapp.service.JwtUserDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class JwtUserDetailsServiceTest {
	@InjectMocks
	private JwtUserDetailsService jwtService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder bcryptEncoder;

	public JwtUserDetailsServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	private User createDefaultUser() {
		User user = new User();
		user.setId(1L);
		user.setUsername("user");
		user.setPassword("password");
		return user;
	}

	private UserDTO createDefaultUserDTO() {
		UserDTO user = new UserDTO();
		user.setUsername("user");
		user.setPassword(bcryptEncoder.encode("password"));
		return user;
	}

	@Test
	public void testWhenRegisterUserShouldReturnUser() {
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(createDefaultUser());
		jwtService.save(createDefaultUserDTO());
	}

	@Test(expected = Exception.class)
	public void testWhenRegisterUserShouldThrowException() {
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(Mockito.mock(Exception.class));
		jwtService.save(createDefaultUserDTO());
	}

	@Test
	public void testWhenLoadUserNameShouldReturnUserDetails() {
		Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(createDefaultUser());
		jwtService.loadUserByUsername(Mockito.anyString());
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testWhenLoadUserNameShouldThowUsernameNotFoundException() {
		Mockito.when(userRepository.findByUsername(Mockito.anyString()))
				.thenThrow(Mockito.mock(UsernameNotFoundException.class));
		jwtService.loadUserByUsername("");
	}
}
