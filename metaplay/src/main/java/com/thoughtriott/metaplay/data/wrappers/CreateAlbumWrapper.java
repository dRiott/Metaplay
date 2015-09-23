package com.thoughtriott.metaplay.data.wrappers;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thoughtriott.metaplay.data.entities.Artist;

public class CreateAlbumWrapper {

// --------------------------Fields--------------------------	
	@NotNull
	@Size(min=3, max=16)
	private String name;
	private String description;
	private int length;
	private int numTracks;
	private Artist artist;
	private Date releaseDate;

	//for track entity
	private String trackName;
	private int trackLength;
	private String trackLyrics;
	private int trackBpm;
	private int trackNumber;

// --------------------------Constructors--------------------------
	
	public CreateAlbumWrapper() {
	}

	public CreateAlbumWrapper(String name, String description, int length, int numTracks, Artist artist, Date releaseDate,
		String trackName, int trackLength, String trackLyrics, int trackBpm, int trackNumber) {
		super();
		this.name = name;
		this.description = description;
		this.length = length;
		this.numTracks = numTracks;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.trackName = trackName;
		this.trackLength = trackLength;
		this.trackLyrics = trackLyrics;
		this.trackBpm = trackBpm;
		this.trackNumber = trackNumber;
	}	
	
//--------------------------Getters & Setters--------------------------	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNumTracks() {
		return numTracks;
	}

	public void setNumTracks(int numTracks) {
		this.numTracks = numTracks;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public int getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(int trackLength) {
		this.trackLength = trackLength;
	}

	public String getTrackLyrics() {
		return trackLyrics;
	}

	public void setTrackLyrics(String trackLyrics) {
		this.trackLyrics = trackLyrics;
	}

	public int getTrackBpm() {
		return trackBpm;
	}

	public void setTrackBpm(int trackBpm) {
		this.trackBpm = trackBpm;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
}
