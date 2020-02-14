package com.mastercard.musicapp.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mastercard.musicapp.entity.ArtistSong;
import com.mastercard.musicapp.entity.Song;
import com.mastercard.musicapp.exceptions.NullFieldsException;
import com.mastercard.musicapp.exceptions.SongNotFoundException;
import com.mastercard.musicapp.repository.ArtistSongRepository;
import com.mastercard.musicapp.repository.SongRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class SongService {
	@Autowired
	private SongRepository songRepository;
	@Autowired
	private ArtistSongRepository artistSongRepository;

	public Collection<Song> findAll(Long artistId) {
		log.info("Listing all songs");
		return songRepository.findAllArtistSongs(artistId);
	}

	public ArtistSong addSong(ArtistSong artistSong) {
		try {
			log.info("\n-------------------\nAdding song named: " + artistSong.getSong().getName() + "\nDate: "+ artistSong.getSong().getDate() +"\n-------------------");
			return artistSongRepository.save(artistSong);
		} catch (Exception e) {
			log.error("Failed to add song");
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
			List<ArtistSong> artistSongId = artistSongRepository.findArtistSongsIdBySongId(id);
			songRepository.deleteById(id);
			artistSongRepository.delete(artistSongId.get(0));
			
			log.info("Song " + id + " successful deleted");
		} catch (Exception e) {
			log.error("Could not found Song " + id);
			throw new SongNotFoundException(id);
		}
	}
}
