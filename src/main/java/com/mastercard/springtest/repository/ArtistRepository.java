package com.mastercard.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mastercard.springtest.entity.Artist;
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{

}
