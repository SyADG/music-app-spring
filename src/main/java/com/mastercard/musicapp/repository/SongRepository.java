package com.mastercard.musicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mastercard.musicapp.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
	//@Query("DELETE FROM Song WHERE ?1 IN(SELECT id FROM Artist.songs)")
	//public void deleteByIdCascade(Long id);
}
