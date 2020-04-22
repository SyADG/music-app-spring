package com.musicapp.entity;

import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "songs")
@EntityListeners(AuditingEntityListener.class)
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Song name must not be blank!")
	@NotNull(message = "Song name must be informed!")
	@Column
	private String name;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Date must be informed!")
	@Column
	private Date date;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, targetEntity = Artist.class)
	@JoinTable(name = "artist_song", joinColumns = { @JoinColumn(name = "songs_id") }, inverseJoinColumns = {
			@JoinColumn(name = "artists_id") })
	private Collection<Artist> artists;
}
