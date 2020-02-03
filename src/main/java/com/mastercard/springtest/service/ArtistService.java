package com.mastercard.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mastercard.springtest.entity.Artist;
import com.mastercard.springtest.exceptions.EmployeeNotFoundException;
import com.mastercard.springtest.repository.ArtistRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArtistService {
	@Autowired
	private ArtistRepository repository;

	public List<Artist> findAll() {
		log.info("Listing all artists");
		return repository.findAll();
	}

	public Artist addArtist(@RequestBody Artist newArtist) {

		log.info("\n-------------------\nAdding artist named: " + newArtist.getName() + " " + "\nGenre: "
				+ newArtist.getGenre() + "\n-------------------");

		return repository.save(newArtist);
	}

	public Artist findArtist(@PathVariable Long id) {
		log.info("Returning the artist number: " + id);
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Artist updateArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
		return repository.findById(id).map(artist -> {

			log.info("\n-------------------\nChanging the artist: " + id + "\nNew artist name: " + newArtist.getName()
					+ "\nNew Genre: " + newArtist.getGenre() + "\n-------------------");

			artist.setName(newArtist.getName());
			artist.setGenre(newArtist.getGenre());
			return repository.save(artist);
		}).orElseThrow(() -> {
			log.error("Could not found Artist " + id);
			return new EmployeeNotFoundException(id);
		});
	}

	public void deleteArtist(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			log.info("Artist " + id + " successful deleted");
		} else {
			log.info("Could not found Artist " + id);
		}
	}
}