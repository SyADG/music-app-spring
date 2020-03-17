package com.mastercard.musicapp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mastercard.musicapp.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
	
}
