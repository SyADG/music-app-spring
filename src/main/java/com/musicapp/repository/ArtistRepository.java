package com.musicapp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.musicapp.entity.Artist;
import com.musicapp.entity.Song;
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
	@Query("SELECT songs FROM Artist a where a.id = ?1")
	public Collection<Song> findAllSongs(Long id);
}