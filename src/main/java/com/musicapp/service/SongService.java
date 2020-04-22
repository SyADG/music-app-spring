package com.musicapp.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicapp.entity.Artist;
import com.musicapp.entity.Song;
import com.musicapp.exceptions.NullFieldsException;
import com.musicapp.exceptions.SongNotFoundException;
import com.musicapp.repository.ArtistRepository;
import com.musicapp.repository.SongRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class SongService {
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private SongRepository songRepository;

	public Collection<Song> findAll(Long artistId) {
		log.info("Listing all songs");
		return artistRepository.findAllSongs(artistId);
	}

	public Song addSong(Long artistId, Song newSong) throws NullFieldsException {
		try {
			if (newSong.getName() == null || newSong.getDate() == null) {
				throw new NullFieldsException("Name or date must not be empty");
			} else {
				log.info("\n-------------------\nAdding: " + newSong.getName() + "\n-------------------");
				Artist artist = artistRepository.findArtistById(artistId);
				newSong.setArtists(new ArrayList<Artist>());
				newSong.getArtists().add(artist);
				return songRepository.save(newSong);
			}
		} catch (NullFieldsException nfe) {
			throw nfe;
		} catch (Exception e) {
			log.error("Unknown error: Message: " + e.getMessage());
			throw new RuntimeException("RuntimeException: " + e.getMessage());
		}
	}

	public Song findSong(Long id) {
		log.info("Returning the song number: " + id);
		return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
	}

	public Song updateSong(Song newSong, Long id) {
		try {
			Song song = new Song();
			song.setId(id);
			song.setName(newSong.getName());
			song.setDate(newSong.getDate());

			log.info("\n-------------------\nChanging the artist: " + id + "\nNew artist name: " + newSong.getName()
					+ "\nNew Genre: " + newSong.getDate() + "\n-------------------");
			return songRepository.save(song);

		} catch (Exception e) {
			log.error("Could not find Artist " + id);
			System.out.println("Mesage" + e.getMessage());
			throw new RuntimeException("RuntimeException: " + e.getMessage());
		}
	}

	public void deleteSong(Long songId) {
		log.info("Trying to delete...");
		try {
			songRepository.deleteById(songId);
			log.info("Song " + songId + " successful deleted");
		} catch (Exception e) {
			log.error("Unknown error: " + e.getMessage());
			throw new RuntimeException("RuntimeException: " + e.getMessage());
		}
	}
}
