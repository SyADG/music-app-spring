package com.mastercard.musicapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.musicapp.entity.Artist;
import com.mastercard.musicapp.service.ArtistService;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@GetMapping("/list")
	public ResponseEntity<List<Artist>> findAll() {
		List<Artist> findAll = artistService.findAll();
		return new ResponseEntity<List<Artist>>(findAll, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Artist> addArtist(@RequestBody Artist newArtist) {
		Artist addArtist = artistService.addArtist(newArtist);
		return new ResponseEntity<Artist>(addArtist, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Artist> findArtist(@PathVariable Long id) {
		Artist findArtist = artistService.findArtist(id);
		return new ResponseEntity<Artist>(findArtist, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Artist> updateArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
		Artist updateArtist = artistService.updateArtist(newArtist, id);
		return new ResponseEntity<Artist>(updateArtist,HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
		artistService.deleteArtist(id);
		return ResponseEntity.noContent().build();
	}
}