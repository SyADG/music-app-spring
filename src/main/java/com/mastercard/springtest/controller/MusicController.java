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

import com.mastercard.springtest.entity.ArtistMusic;
import com.mastercard.springtest.entity.Music;
import com.mastercard.springtest.service.MusicService;

@RestController
@RequestMapping(value = "artists/{id}/musics")
public class MusicController {

	@Autowired
	private MusicService musicService;

	@GetMapping("/list")
	public List<ArtistMusic> findAll(@PathVariable(name = "id", required = true) Long artistId) {
		return musicService.findAll(artistId);
	}

	@PostMapping("/add")
	public Music addMusic(@RequestBody Music newArtist, @PathVariable(name = "id", required = true) Long artistId) {
		return musicService.addMusic(newArtist, artistId);
	}

	@GetMapping("/{id}")
	public Music findMusic(@PathVariable Long id) {
		return musicService.findMusic(id);
	}

	@PutMapping("/update/{id}")
	public Music updateMusic(@RequestBody Music newMusic, @PathVariable Long id) {
		return musicService.updateMusic(newMusic, id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteMusic(@PathVariable Long id) {
		musicService.deleteMusic(id);
	}
}
