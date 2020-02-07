package com.mastercard.musicapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.musicapp.entity.ArtistSong;

public interface ArtistSongRepository extends JpaRepository<ArtistSong, Long>{
	public List<ArtistSong> deleteBySongId(Long songId);
	public List<ArtistSong> findByArtistId(Long artistId);
	public List<ArtistSong> deleteByArtistId(Long artistId);
	public List<ArtistSong> findArtistSongsIdBySongId(Long artistId);
}
