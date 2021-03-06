package com.musicapp.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "artists")
@EntityListeners(AuditingEntityListener.class)
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Artist name must not be blank!")
	@NotNull(message = "Artist name must be informed!")
	@Column(name = "name")
	private String name;

	@NotBlank(message = "Genre must not be blank!")
	@NotNull(message = "Genre must be informed")
	@Column(name = "genre")
	private String genre;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, targetEntity = Song.class)
	@JoinTable(name = "artist_song", joinColumns = { @JoinColumn(name = "artists_id") }, inverseJoinColumns = {
			@JoinColumn(name = "songs_id") })
	private Collection<Song> songs;
}
