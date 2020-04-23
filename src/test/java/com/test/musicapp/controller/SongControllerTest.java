package com.test.musicapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import com.google.common.collect.Iterables;
import com.musicapp.controller.SongController;
import com.musicapp.entity.Song;
import com.musicapp.exceptions.NullFieldsException;
import com.musicapp.service.SongService;

@WebMvcTest(controllers = SongController.class)
@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {
	@InjectMocks
	private SongController songController;
	@Mock
	private SongService songService;

	@Test
	public void testFindAll() {
		Song song1 = new Song(1L, "Song", new Date(1999 - 12 - 12), null);
		Song song2 = new Song(2L, "Evidencias", new Date(1999 - 12 - 12), null);
		Collection<Song> songs = new ArrayList<>();
		songs.add(song1);
		songs.add(song2);
		Mockito.when(songService.findAll(Mockito.anyLong())).thenReturn(songs);

		ResponseEntity<Collection<Song>> result = songController.findAll(Mockito.anyLong());

		Assert.assertEquals(result.getBody().size(), 2);
		Assert.assertEquals(Iterables.get(result.getBody(), 0).getName(), song1.getName());
		Assert.assertEquals(Iterables.get(result.getBody(), 1).getName(), song2.getName());

		Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAddSong() throws NullFieldsException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.when(songService.addSong(Mockito.anyLong(), Mockito.any(Song.class))).thenReturn(Mockito.any());

		Song song = new Song(1L, "Song", new Date(1999 - 12 - 12), null);
		ResponseEntity<Song> responseEntity = songController.addSong(Mockito.anyLong(), song);

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testFindSong() {
		Song song1 = new Song(1L, "Song", new Date(1999 - 12 - 12), null);
		Song song2 = new Song(2L, "Evidencias", new Date(1999 - 12 - 12), null);
		List<Song> songs = new ArrayList<>();
		songs.add(song1);
		songs.add(song2);
		Mockito.when(songService.findSong(Mockito.anyLong())).thenReturn(songs.get(Mockito.anyInt()));

		ResponseEntity<Song> resultArtist1 = songController.findSong(1L);

		Assert.assertEquals(resultArtist1.getBody().getName(), song1.getName());
		Assert.assertEquals(resultArtist1.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testUpdateArtist() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.when(songService.updateSong(Mockito.any(Song.class), Mockito.anyLong())).thenReturn(null);
		Song Song = new Song(1L, "Song", new Date(1999 - 12 - 12), null);
		ResponseEntity<Song> responseEntity = songController.updateSong(Song, 1L);

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	public void testDeleteArtist() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		ResponseEntity<Void> deleteSong = songController.deleteSong(Mockito.anyLong());
		Assert.assertEquals(deleteSong.getStatusCode(), HttpStatus.NO_CONTENT);
	}
}
