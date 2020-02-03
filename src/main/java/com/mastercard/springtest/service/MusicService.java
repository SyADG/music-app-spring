package com.mastercard.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mastercard.springtest.entity.ArtistMusic;
import com.mastercard.springtest.entity.Artist;
import com.mastercard.springtest.entity.Music;
import com.mastercard.springtest.exceptions.EmployeeNotFoundException;
import com.mastercard.springtest.repository.ArtistMusicRepository;
import com.mastercard.springtest.repository.MusicRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MusicService {
	@Autowired
	private MusicRepository repository;
	@Autowired
	private ArtistMusicRepository artistMusicRepository;
	
	public List<ArtistMusic> findAll(Long artistId) {
		return artistMusicRepository.findByArtistId(artistId);
	}

	public Music addMusic(@RequestBody Music newMusic, Long id) {
		ArtistMusic artistMusic = new ArtistMusic();
		
		log.info("\n-------------------\nAdding music named: " + newMusic.getName() + " " + "\nDate: "
				+ newMusic.getDate() + "\n-------------------");

		Music saveMusic = repository.save(newMusic);
		artistMusic.setArtist(new Artist(id));
		artistMusic.setMusic(saveMusic);
		artistMusicRepository.save(artistMusic);
		return saveMusic;
	}

	public Music findMusic(@PathVariable Long id) {
		log.info("Returning the music number: " + id);
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Music updateMusic(@RequestBody Music newArtist, @PathVariable Long id) {
		return repository.findById(id).map(music -> {

			log.info("\n-------------------\nChanging the music: " + id + "\nNew music name: " + newArtist.getName()
					+ "\nNew date: " + newArtist.getDate() + "\n-------------------");

			music.setName(newArtist.getName());
			music.setDate(newArtist.getDate());
			return repository.save(music);
		}).orElseThrow(() -> {
			log.error("Could not found Music " + id);
			return new EmployeeNotFoundException(id);
		});
	}

	public void deleteMusic(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			log.info("Music " + id + " successful deleted");
		} else {
			log.info("Could not found Music " + id);
		}
	}
}
