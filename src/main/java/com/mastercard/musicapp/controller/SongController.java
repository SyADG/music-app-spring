package com.mastercard.musicapp.controller;

import java.util.Collection;

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

import com.mastercard.musicapp.entity.ArtistSong;
import com.mastercard.musicapp.entity.Song;
import com.mastercard.musicapp.service.SongService;

@RestController
@RequestMapping(value = "artists/{id}/songs")
public class SongController {

	@Autowired
	private SongService songService;

	@GetMapping("/list")
	public ResponseEntity<Collection<Song>> findAll(@PathVariable(name = "id", required = true) Long artistId) {
		Collection<Song> findAll = songService.findAll(artistId);
		return new ResponseEntity<Collection<Song>>(findAll, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ArtistSong> addSong(@RequestBody ArtistSong artistSong) {
		ArtistSong saveArtistSong = songService.addSong(artistSong);
		return new ResponseEntity<ArtistSong>(saveArtistSong, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Song> findSong(@PathVariable Long id) {
		Song findSong = songService.findSong(id);
		return new ResponseEntity<Song>(findSong, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Song> updateSong(@RequestBody Song newSong, @PathVariable Long id) {
		Song updateSong = songService.updateSong(newSong, id);
		return new ResponseEntity<Song>(updateSong, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		songService.deleteSong(id);
		return ResponseEntity.noContent().build();
	}
}
