package com.mastercard.musicapp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.musicapp.entity.Artist;
import com.mastercard.musicapp.entity.Song;
import com.mastercard.musicapp.service.ArtistService;
import com.mastercard.musicapp.service.SongService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin()
@RestController
@RequestMapping(value = "artists/{id}/songs")
public class SongController {

	@Autowired
	private SongService songService;
	@Autowired
	private ArtistService artistService;

	@ApiOperation("List all songs of one artist.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return a list of song."),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in.")
	})
	@GetMapping
	public ResponseEntity<Collection<Song>> findAll(@PathVariable(name = "id", required = true) Long artistId) {
		Collection<Song> findAll = songService.findAll(artistId);
		return new ResponseEntity<Collection<Song>>(findAll, HttpStatus.OK);
	}

	@ApiOperation("Insert a new song to one or more artists in database.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return a new song and artist."),
		@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing.")
	})
	@PostMapping("/add")
	public ResponseEntity<Artist> addSong(@RequestBody Artist song) {	
		Artist saveSong = songService.addSong(song);
		return new ResponseEntity<Artist>(saveSong, HttpStatus.CREATED);
	}

	@ApiOperation("Get one song from the database via song and artist id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return one song with specified id."),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Song> findSong(@PathVariable Long id) {
		Song findSong = songService.findSong(id);
		return new ResponseEntity<Song>(findSong, HttpStatus.OK);
	}

	@ApiOperation("Update a song with id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return the updated song."),
		@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing."),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.")
	})
	@PutMapping("/update/{id}")
	public ResponseEntity<Song> updateSong(@RequestBody Song newSong, @PathVariable Long id) {
		Song updateSong = songService.updateSong(newSong, id);
		return new ResponseEntity<Song>(updateSong, HttpStatus.CREATED);
	}

	@ApiOperation("Remove a song via song and artist id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "It will return a NO_CONTENT if the song is removed from database"),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.")
	})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		songService.deleteSong(id);
		return ResponseEntity.noContent().build();
	}
}
