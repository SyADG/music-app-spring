package com.mastercard.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mastercard.springtest.entity.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long>{

}
