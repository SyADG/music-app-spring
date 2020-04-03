package com.musicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musicapp.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
}
