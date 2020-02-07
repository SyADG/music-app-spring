package com.mastercard.musicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mastercard.musicapp.entity.Artist;
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
}
