package com.musicapp.service;

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
	@Autowired
	private ArtistService artistService;

	public Collection<Song> findAll(Long artistId) {
		log.info("Listing all songs");
		return artistRepository.findAllSongs(artistId);
	}

	public Song addSong(Long artistId,Song newSong) {
		try {
			log.info("\n-------------------\nAdding: " + newSong.getName() + "\n-------------------");
			Artist artist = new Artist();
			songRepository.save(newSong);
			artist = artistService.findArtist(artistId);
//			for (Song c : newSong.getSongs()) {
//				if (c.getId() != null) {
//					Long songId = c.getId(); 
//					Song exSong = songRepository.findBySongId(songId);
//					newSong.getSongs().add(exSong);
//				}
//			}
			artist.getSongs().add(newSong);
			artistRepository.save(artist);
			return newSong;

		} catch (Exception e) {
			log.error("Required fields are null. Error: " + e.getMessage());
			throw new NullFieldsException();
		}
	}

	public Song findSong(Long id) {
		log.info("Returning the song number: " + id);
		return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
	}

	public Song updateSong(Song newSong, Long id) {
		return songRepository.findById(id).map(song -> {

			log.info("\n-------------------\nChanging the song: " + id + "\nNew song name: " + newSong.getName()
					+ "\nNew date: " + newSong.getDate() + "\n-------------------");

			song.setName(newSong.getName());
			song.setDate(newSong.getDate());
			return songRepository.save(song);
		}).orElseThrow(() -> {
			log.error("Could not find Song " + id);
			return new SongNotFoundException(id);
		});
	}

	public void deleteSong(Long artistId, Long songId) {
		try {
			log.info("Trying to delete...");
			Song song = findSong(songId);
			Artist artistSong = artistService.findArtist(artistId);
			artistSong.getSongs().remove(song);
			songRepository.delete(song);
			artistRepository.save(artistSong);
			log.info("Song " + songId + " successful deleted");

		} catch (Exception e) {
			log.error("Could not find Song " + artistId);
			throw new SongNotFoundException(artistId);
		}
	}
}
