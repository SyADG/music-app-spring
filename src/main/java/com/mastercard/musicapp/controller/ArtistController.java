package com.mastercard.musicapp.controller;

import java.util.List;

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
import com.mastercard.musicapp.service.ArtistService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin()
@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

	@Autowired
	private ArtistService artistService;
	
	@ApiOperation("List all artists from database.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return a list of artists."),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in.")
	})
	@GetMapping
	public ResponseEntity<List<Artist>> findAll() {
		List<Artist> findAll = artistService.findAll();
		return new ResponseEntity<List<Artist>>(findAll, HttpStatus.OK);
	}

	@ApiOperation("Insert a new artist in database.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return a new artist."),
		@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing.")
	})
	@PostMapping("/add")
	public ResponseEntity<Artist> addArtist(@RequestBody Artist newArtist) {
		Artist addArtist = artistService.addArtist(newArtist);
		return new ResponseEntity<Artist>(addArtist, HttpStatus.CREATED);
	}

	@ApiOperation("Get one artist from the database via id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return one artist with specified id."),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Artist> findArtist(@PathVariable Long id) {
		Artist findArtist = artistService.findArtist(id);
		return new ResponseEntity<Artist>(findArtist, HttpStatus.OK);
	}

	@ApiOperation("Update the artist with id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return the updated artist."),
		@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing."),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.")
	})
	@PutMapping("/update/{id}")
	public ResponseEntity<Artist> updateArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
		Artist updateArtist = artistService.updateArtist(newArtist, id);
		return new ResponseEntity<Artist>(updateArtist, HttpStatus.CREATED);
	}

	@ApiOperation("Remove an artist by id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "It will return a NO_CONTENT if the artist is removed from database"),
		@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.")
	})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
		artistService.deleteArtist(id);
		return ResponseEntity.noContent().build();
	}
}