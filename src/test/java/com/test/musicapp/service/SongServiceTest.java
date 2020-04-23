package com.test.musicapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import com.musicapp.entity.Song;
import com.musicapp.exceptions.NullFieldsException;
import com.musicapp.exceptions.SongNotFoundException;
import com.musicapp.repository.ArtistRepository;
import com.musicapp.repository.SongRepository;
import com.musicapp.service.SongService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {
	@InjectMocks
	private SongService songService;
	@Mock
	private SongRepository songRepository;
	@Mock
	private ArtistRepository artistRepository;

	private Song createDefaultSong() {
		Song song = new Song(1L, "Song", new Date(1999 - 01 - 01), null);
		song.setArtists(new ArrayList<Artist>());
		song.getArtists().add(createDefaultArtist());
		return song;
	}

	private Artist createDefaultArtist() {
		Artist artist = new Artist(1L, "Name", "Genre", null);
		return artist;
	}

	@Test
	public void testFindallShouldReturnList() {
		Collection<Song> songs = songService.findAll(Mockito.anyLong());
		Assert.assertNotNull(songs);
	}

	@Test(expected = SongNotFoundException.class)
	public void testFindByIdShouldThrowException() {
		Mockito.when(songRepository.findById(Mockito.anyLong())).thenThrow(SongNotFoundException.class);
		songService.findSong(Mockito.anyLong());
	}

	@Test
	public void testFindByIdShouldReturnSong() {
		Mockito.when(songRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(createDefaultSong()));
		songService.findSong(Mockito.anyLong());
	}

	@Test(expected = Exception.class)
	public void testAddSongShouldThrowException() throws NullFieldsException {
		Mockito.when(songRepository.save(Mockito.any())).thenReturn(Mockito.mock(Exception.class));
		songService.addSong(createDefaultArtist().getId(), createDefaultSong());
	}

	@Test
	public void testAddSongShouldReturnSong() throws NullFieldsException {
		Mockito.when(artistRepository.findArtistById(Mockito.anyLong())).thenReturn(createDefaultArtist());
		Mockito.when(songRepository.save(Mockito.any())).thenReturn(createDefaultSong());
		songService.addSong(createDefaultArtist().getId(), createDefaultSong());
	}

	@Test
	public void testWhenDeleteSongShouldReturnNull() {
		songService.deleteSong(Mockito.anyLong());
		Mockito.verify(songRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}

	@Test
	public void testUpdateSongShouldReturnSong() {
		Mockito.when(songRepository.save(Mockito.any())).thenReturn(Mockito.any());
		songService.updateSong(createDefaultSong(), createDefaultSong().getId());
	}

	@Test(expected = RuntimeException.class)
	public void testUpdateShouldThrowException() {
		Mockito.when(songRepository.save(Mockito.any())).thenReturn(Mockito.mock(Exception.class));
		songService.updateSong(createDefaultSong(), createDefaultSong().getId());
	}

	@Test(expected = RuntimeException.class)
	public void testWhenDeleteSongShouldThrowException() {
		Mockito.doThrow(Mockito.mock(RuntimeException.class)).when(songRepository).deleteById(Mockito.anyLong());
		songService.deleteSong(Mockito.anyLong());
	}

	@Test(expected = NullFieldsException.class)
	public void testWhenAddEmptySongShouldThrowNullFieldsException() throws NullFieldsException {
		Song song = new Song();
		Mockito.when(songService.addSong(Mockito.anyLong(), song)).thenThrow(Mockito.mock(NullFieldsException.class));
		songService.addSong(Mockito.anyLong(), song);
	}

	@Test(expected = NullFieldsException.class)
	public void testWhenAddSongWithoutNameShouldThrowNullFieldsException() throws NullFieldsException {
		Song song = new Song();
		song.setDate(new Date(1999 - 01 - 01));
		Mockito.when(songService.addSong(Mockito.anyLong(), song)).thenThrow(Mockito.mock(NullFieldsException.class));
		songService.addSong(Mockito.anyLong(), song);
	}

	@Test(expected = NullFieldsException.class)
	public void testWhenAddSongWithoutDateShouldThrowNullFieldsException() throws NullFieldsException {
		Song song = new Song();
		song.setName("Song");
		Mockito.when(songService.addSong(Mockito.anyLong(), song)).thenThrow(Mockito.mock(NullFieldsException.class));
		songService.addSong(Mockito.anyLong(), song);
	}
}
