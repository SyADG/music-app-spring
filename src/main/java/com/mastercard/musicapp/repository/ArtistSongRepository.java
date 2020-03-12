package com.mastercard.musicapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mastercard.musicapp.entity.ArtistSong;

@Repository
public interface ArtistSongRepository extends JpaRepository<ArtistSong, Long>{
	public List<ArtistSong> deleteBySongId(Long songId);
	public List<ArtistSong> findByArtistId(Long artistId);
	public List<ArtistSong> deleteByArtistId(Long artistId);
	public List<ArtistSong> findArtistSongsIdBySongId(Long artistId);
}
