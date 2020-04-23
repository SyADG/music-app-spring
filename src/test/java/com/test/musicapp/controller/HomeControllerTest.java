package com.test.musicapp.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.musicapp.controller.ArtistController;
import com.musicapp.controller.HomeController;



@WebMvcTest(controllers = ArtistController.class)
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	@InjectMocks
	private HomeController homeController;
	
	@Test
	public void testHello() {
		String hello = homeController.hello();
		Assert.assertEquals("Hello Artists!", hello);
	}
}
