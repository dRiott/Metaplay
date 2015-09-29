package com.thoughtriott.metaplay.data.wrappers;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thoughtriott.metaplay.data.entities.Track;

public class CreateAlbumWrapper {

// --------------------------Fields--------------------------	
	@NotNull
	@Size(min=3, max=16)
	private String name;
	private String description;
	private int lengthMinutes;
	private int lengthSeconds;
	private int numTracks;

	@NotNull
	@Size(min = 3, max = 50)
	private String artistFromList;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String theNewArtist;
	
	private String releaseDate;
	
	
	private List<Track> tracks;
	
	private int trackLength;
	private String trackLyrics;
	private int trackBpm;
	private int trackNumber;

// --------------------------Constructors--------------------------
	
	public CreateAlbumWrapper() {
	}

//--------------------------Getters & Setters--------------------------	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistFromList() {
		return artistFromList;
	}

	public void setArtistFromList(String artistFromList) {
		this.artistFromList = artistFromList;
	}

	public String getTheNewArtist() {
		return theNewArtist;
	}

	public void setTheNewArtist(String theNewArtist) {
		this.theNewArtist = theNewArtist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLengthMinutes() {
		return lengthMinutes;
	}

	public void setLengthMinutes(int lengthMinutes) {
		this.lengthMinutes = lengthMinutes;
	}

	public int getLengthSeconds() {
		return lengthSeconds;
	}

	public void setLengthSeconds(int lengthSeconds) {
		this.lengthSeconds = lengthSeconds;
	}

	public int getNumTracks() {
		return numTracks;
	}

	public void setNumTracks(int numTracks) {
		this.numTracks = numTracks;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
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
