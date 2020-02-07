package com.mastercard.musicapp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mastercard.musicapp.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
	@Query("select new Song(song.id,song.name,song.date) from Song song " + 
			"join ArtistSong artistSong on song.id = artistSong.song.id " + 
			"join Artist artist on artist.id = artistSong.artist.id " +
			"where artist.id = ?1")
	public Collection<Song> findAllArtistSongs(Long id);
}
