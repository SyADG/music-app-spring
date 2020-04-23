package com.test.musicapp.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.musicapp.entity.User;
import com.musicapp.model.UserDTO;
import com.musicapp.repository.UserRepository;
import com.musicapp.service.JwtUserDetailsService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class JwtUserDetailsServiceTest {
	@InjectMocks
	private JwtUserDetailsService jwtService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder bcryptEncoder;

	private User createDefaultUser() {
		User user = new User(1L,"user","password");
		return user;
	}

	private UserDTO createDefaultUserDTO() {
		UserDTO user = new UserDTO("user", bcryptEncoder.encode("password"));
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
		Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null)
				.thenThrow(UsernameNotFoundException.class);
		jwtService.loadUserByUsername(Mockito.anyString());
	}

}
