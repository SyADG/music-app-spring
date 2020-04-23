package com.test.musicapp.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musicapp.entity.Artist;
import com.musicapp.exceptions.ArtistNotFoundException;
import com.musicapp.exceptions.NullFieldsException;
import com.musicapp.repository.ArtistRepository;
import com.musicapp.service.ArtistService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

	@InjectMocks
	private ArtistService artistService;
	@Mock
	private ArtistRepository artistRepository;

	private Artist createDefaultArtist() {
		Artist artist = new Artist(1L, "Name", "Genre", null);
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
		artistService.findArtist(0L);
	}

	@Test
	public void testFindByIdShouldReturnArtist() {
		Mockito.when(artistRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(createDefaultArtist()));
		artistService.findArtist(createDefaultArtist().getId());
	}

	@Test
	public void testUpdateArtistShouldReturnArtist() {
		Mockito.when(artistRepository.save(Mockito.any())).thenReturn(createDefaultArtist());
		artistService.updateArtist(createDefaultArtist(), createDefaultArtist().getId());
	}

	@Test(expected = RuntimeException.class)
	public void testUpdateArtistShouldThrowException() {
		Mockito.when(artistRepository.save(Mockito.any())).thenReturn(Mockito.mock(Exception.class));
		artistService.updateArtist(createDefaultArtist(), createDefaultArtist().getId());
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

	@Test(expected = NullFieldsException.class)
	public void testWhenAddArtistWithoutNameShouldThrowNullFieldsException() throws NullFieldsException {
		Artist artist = new Artist();
		artist.setGenre("Genre");
		Mockito.when(artistService.addArtist(artist)).thenThrow(Mockito.mock(NullFieldsException.class));
		artistService.addArtist(artist);
	}

	@Test(expected = NullFieldsException.class)
	public void testWhenAddArtistWithoutGenreShouldThrowNullFieldsException() throws NullFieldsException {
		Artist artist = new Artist();
		artist.setName("Name");
		Mockito.when(artistService.addArtist(artist)).thenThrow(Mockito.mock(NullFieldsException.class));
		artistService.addArtist(artist);
	}

	@Test(expected = NullFieldsException.class)
	public void testWhenAddEmptyArtistShouldThrowNullFieldsException() throws NullFieldsException {
		Artist artist = new Artist();
		Mockito.when(artistService.addArtist(artist)).thenThrow(Mockito.mock(NullFieldsException.class));
		artistService.addArtist(artist);
	}
}
