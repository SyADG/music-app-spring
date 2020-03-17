package com.mastercard.musicapp.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mastercard.musicapp.entity.Artist;
import com.mastercard.musicapp.entity.Song;
import com.mastercard.musicapp.exceptions.NullFieldsException;
import com.mastercard.musicapp.exceptions.SongNotFoundException;
import com.mastercard.musicapp.repository.ArtistRepository;
import com.mastercard.musicapp.repository.SongRepository;

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

	public Artist addSong(Artist newSong) {
		try {
			log.info("\n-------------------\nAdding: " + newSong.getSongs() + "\n-------------------");
			Artist artist = new Artist();
			songRepository.saveAll(newSong.getSongs());
			artist = artistService.findArtist(newSong.getId());
//			for (Song c : newSong.getSongs()) {
//				if (c.getId() != null) {
//					Long songId = c.getId(); 
//					Song exSong = songRepository.findBySongId(songId);
//					newSong.getSongs().add(exSong);
//				}
//			}
			artist.getSongs().addAll(newSong.getSongs());
			return artistRepository.save(artist);

		} catch (Exception e) {
			log.error("Required fields are null. Error: " + e.getMessage());
			throw new NullFieldsException();
		}
	}

	public Song findSong(@PathVariable Long id) {
		log.info("Returning the song number: " + id);
		return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
	}

	public Song updateSong(@RequestBody Song newSong, @PathVariable Long id) {
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

	public void deleteSong(@PathVariable Long id) {
		try {
//			List<ArtistSong> artistSongId = artistSongRepository.findArtistSongsIdBySongId(id);
//			songRepository.deleteAll();
//			artistSongRepository.delete(artistSongId.get(0));
//			
//			log.info("Song " + id + " successful deleted");

//			songRepository.deleteSongById(id);
			songRepository.deleteById(id);
			log.info("Song " + id + " successful deleted");

		} catch (Exception e) {
			log.error("Could not find Song " + id);
			throw new SongNotFoundException(id);
		}
	}
}
