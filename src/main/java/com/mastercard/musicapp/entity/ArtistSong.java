package com.mastercard.musicapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "artist_song")
@EntityListeners(AuditingEntityListener.class)
public class ArtistSong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Artist must be informed!")
	@ManyToOne
	private Artist artist;

	@NotNull(message = "Song must be informed!")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Song song;
}
