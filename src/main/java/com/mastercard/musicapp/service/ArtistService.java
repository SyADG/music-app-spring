package com.mastercard.musicapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mastercard.musicapp.entity.Artist;
import com.mastercard.musicapp.exceptions.ArtistNotFoundException;
import com.mastercard.musicapp.exceptions.NullFieldsException;
import com.mastercard.musicapp.repository.ArtistRepository;
import com.mastercard.musicapp.repository.ArtistSongRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class ArtistService {
	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private ArtistSongRepository artistSongRepository;

	public List<Artist> findAll() {
		log.info("Listing all artists");
		return artistRepository.findAll();
	}

	public Artist addArtist(@RequestBody Artist newArtist) throws NullFieldsException {
		try {
			log.info("\n-------------------\nAdding artist named: " + newArtist.getName() + " " + "\nGenre: "
					+ newArtist.getGenre() + "\n-------------------");
			return artistRepository.save(newArtist);
		} catch (Exception e) {
			log.error("Required fields are null.");
			throw new NullFieldsException();
		}
	}

	public Artist findArtist(@PathVariable Long id) {
		log.info("Returning the artist number: " + id);
		return artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(id));
	}

	public Artist updateArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
		return artistRepository.findById(id).map(artist -> {

			log.info("\n-------------------\nChanging the artist: " + id + "\nNew artist name: " + newArtist.getName()
					+ "\nNew Genre: " + newArtist.getGenre() + "\n-------------------");

			artist.setName(newArtist.getName());
			artist.setGenre(newArtist.getGenre());
			return artistRepository.save(artist);
		}).orElseThrow(() -> {
			log.error("Could not find Artist " + id);
			return new ArtistNotFoundException(id);
		});
	}

	public void deleteArtist(@PathVariable Long id) {
		try {
			artistSongRepository.deleteByArtistId(id);
			artistRepository.deleteById(id);
			log.info("Artist " + id + " successful deleted");
		} catch (Exception e) {
			log.error("Could not find Artist " + id);
			throw new ArtistNotFoundException(id);
		}
	}
}