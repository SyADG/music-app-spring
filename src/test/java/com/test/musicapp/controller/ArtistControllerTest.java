package com.test.musicapp.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.musicapp.controller.ArtistController;
import com.musicapp.entity.Artist;
import com.musicapp.exceptions.NullFieldsException;
import com.musicapp.service.ArtistService;

@WebMvcTest(controllers = ArtistController.class)
@RunWith(MockitoJUnitRunner.class)
public class ArtistControllerTest {

	@InjectMocks
	private ArtistController artistController;

	@Mock
	private ArtistService artistService;

	@Test
	public void testFindAll() {
		Artist artist1 = new Artist(1L, "Artist1", "Rock", null);
		Artist artist2 = new Artist(2L, "Xitaozin", "Metal", null);
		List<Artist> artists = new ArrayList<>();
		artists.add(artist1);
		artists.add(artist2);
		Mockito.when(artistService.findAll()).thenReturn(artists);

		ResponseEntity<List<Artist>> result = artistController.findAll();

		Assert.assertEquals(result.getBody().size(), 2);
		Assert.assertEquals(result.getBody().get(0).getName(), artist1.getName());
		Assert.assertEquals(result.getBody().get(1).getName(), artist2.getName());

		Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAddArtist() throws NullFieldsException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.when(artistService.addArtist(Mockito.any(Artist.class))).thenReturn(Mockito.any());

		Artist artist = new Artist(1L, "Artist", "Genre", null);
		ResponseEntity<Artist> responseEntity = artistController.addArtist(artist);

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testFindArtist() {
		Artist artist1 = new Artist(1L, "Artist1", "Rock", null);
		Artist artist2 = new Artist(2L, "Xitaozin", "Metal", null);
		List<Artist> artists = new ArrayList<>();
		artists.add(artist1);
		artists.add(artist2);
		Mockito.when(artistService.findArtist(Mockito.anyLong())).thenReturn(artists.get(Mockito.anyInt()));

		ResponseEntity<Artist> resultArtist1 = artistController.findArtist(1L);

		Assert.assertEquals(resultArtist1.getBody().getName(), artist1.getName());
		Assert.assertEquals(resultArtist1.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdateArtist() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.when(artistService.updateArtist(Mockito.any(Artist.class), Mockito.anyLong())).thenReturn(null);
		Artist artist = new Artist(1L, "Artist", "Genre", null);
		ResponseEntity<Artist> responseEntity = artistController.updateArtist(artist, 1L);

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testDeleteArtist() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		ResponseEntity<Void> deleteArtist = artistController.deleteArtist(Mockito.anyLong());
		Assert.assertEquals(deleteArtist.getStatusCode(), HttpStatus.NO_CONTENT);
	}
}
