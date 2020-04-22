package com.test.musicapp.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.musicapp.entity.Artist;
import com.musicapp.exceptions.ArtistNotFoundException;
import com.musicapp.exceptions.NullFieldsException;
import com.musicapp.repository.ArtistRepository;
import com.musicapp.service.ArtistService;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

	@InjectMocks
	private ArtistService artistService;
	@Mock
	private ArtistRepository artistRepository;

	public ArtistServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	private Artist createDefaultArtist() {
		Artist artist = new Artist();
		artist.setId(1L);
		artist.setGenre("Genre");
		artist.setName("Name");
		artist.setSongs(null);
		return artist;
	}

	@Test
	public void testFindallShouldReturnList() {
		List<Artist> artists = artistService.findAll();
		Assert.assertNotNull(artists);
	}

	@Test(expected = RuntimeException.class)
	public void testAddArtistShouldThrowException() throws NullFieldsException {
		Mockito.when(artistRepository.save(Mockito.any())).thenReturn(Mockito.mock(Exception.class));
		artistService.addArtist(createDefaultArtist());
	}

	@Test
	public void testAddArtistShouldReturnArtist() throws NullFieldsException {
		Mockito.when(artistRepository.save(Mockito.any())).thenReturn(createDefaultArtist());
		artistService.addArtist(createDefaultArtist());
	}

	@Test(expected = ArtistNotFoundException.class)
	public void testFindByIdShouldThrowException() {
		Mockito.when(artistRepository.findById(Mockito.anyLong())).thenThrow(ArtistNotFoundException.class);
		artistService.findArtist(Mockito.anyLong());
	}

	@Test
	public void testFindByIdShouldReturnArtist() {
		Mockito.when(artistRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(createDefaultArtist()));
		artistService.findArtist(Mockito.anyLong());
	}

	@Test
	public void testUpdateArtistShouldReturnArtist() {
		Mockito.when(artistRepository.save(Mockito.any())).thenReturn(createDefaultArtist());
		artistService.updateArtist(createDefaultArtist(), createDefaultArtist().getId());
	}

	@Test(expected = RuntimeException.class)
	public void testUpdateArtistShouldThrowException() {
		Mockito.when(artistRepository.save(Mockito.any())).thenReturn(Mockito.mock(Exception.class));
		artistService.updateArtist(createDefaultArtist(),createDefaultArtist().getId());
	}

	@Test
	public void testWhenDeleteArtistShouldReturnNull() {
		artistService.deleteArtist(Mockito.anyLong());
		Mockito.verify(artistRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test(expected = RuntimeException.class)
	public void testWhenDeleteArtistShouldThrowException() {
		Mockito.doThrow(Mockito.mock(RuntimeException.class)).when(artistRepository).deleteById(Mockito.anyLong());
		artistService.deleteArtist(Mockito.anyLong());
	}

}
