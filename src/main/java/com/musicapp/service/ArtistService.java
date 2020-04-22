package com.musicapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicapp.entity.Artist;
import com.musicapp.exceptions.ArtistNotFoundException;
import com.musicapp.exceptions.NullFieldsException;
import com.musicapp.repository.ArtistRepository;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class ArtistService {
	@Autowired
	private ArtistRepository artistRepository;

	public List<Artist> findAll() {
		log.info("Listing all artists");
		return artistRepository.findAll();
	}

	public Artist addArtist(Artist newArtist) throws NullFieldsException {
		try {
			if (newArtist.getName() == null || newArtist.getGenre() == null) {
				throw new NullFieldsException("Name or genre must not be null");
			} else {
				log.info("\n-------------------\nAdding artist named: " + newArtist.getName() + " " + "\nGenre: "
						+ newArtist.getGenre() + "\n-------------------");
				return artistRepository.save(newArtist);
			}
		} catch (NullFieldsException nfe) {
			throw nfe;
		} catch (Exception e) {
			log.error("Unknown error. Message: " + e.getMessage());
			throw new RuntimeException("Unkown error: " + e.getMessage());
		}
	}

	public Artist findArtist(Long id) {
		log.info("Returning the artist number: " + id);
		return artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(id));
	}

	public Artist updateArtist(Artist newArtist, Long id) throws ArtistNotFoundException {
		try {
			Artist artist = new Artist();
			artist.setId(id);
			artist.setName(newArtist.getName());
			artist.setGenre(newArtist.getGenre());

			log.info("\n-------------------\nChanging the artist: " + id + "\nNew artist name: " + newArtist.getName()
					+ "\nNew Genre: " + newArtist.getGenre() + "\n-------------------");
			return artistRepository.save(artist);

		} catch (Exception e) {
			log.error("Could not find Artist " + id);
			throw new RuntimeException("RuntimeException: " + e.getMessage());
		}
	}

	public void deleteArtist(Long id) throws ArtistNotFoundException {
		try {
			artistRepository.deleteById(id);
			log.info("Artist " + id + " successful deleted");
		} catch (Exception e) {
			log.error("Could not find Artist " + id);
			throw new RuntimeException("RuntimeException: " + e.getMessage());
		}
	}
}