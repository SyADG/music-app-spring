package com.mastercard.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.springtest.entity.Artist;
import com.mastercard.springtest.service.ArtistService;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@GetMapping("/list")
	public List<Artist> findAll() {
		return artistService.findAll();
	}

	@PostMapping("/add")
	public Artist addArtist(@RequestBody Artist newArtist) {
		return artistService.addArtist(newArtist);
	}

	@GetMapping("/{id}")
	public Artist findArtist(@PathVariable Long id) {
		return artistService.findArtist(id);
	}

	@PutMapping("/update/{id}")
	public Artist updateArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
		return artistService.updateArtist(newArtist, id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteArtist(@PathVariable Long id) {
		artistService.deleteArtist(id);
	}
}