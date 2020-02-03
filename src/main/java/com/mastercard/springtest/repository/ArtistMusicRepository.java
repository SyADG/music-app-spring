package com.mastercard.springtest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.springtest.entity.ArtistMusic;

public interface ArtistMusicRepository extends JpaRepository<ArtistMusic, Long>{
	public List<ArtistMusic> findByArtistId(Long artistId);
}
