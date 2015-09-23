package com.thoughtriott.metaplay.data.wrappers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTrackWrapper {

	
// --------------------------Fields--------------------------	
	@NotNull
	@Size(min = 1, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String artist;
	
	@NotNull
	@Size(min = 0, max = 59)
	private int lengthSeconds;
	
	@NotNull
	@Size(min = 0, max = 360)
	private int lengthMinutes;
	
	@Size(min = 0, max = 5000)
	private String lyrics;
	
	@Size(min = 0, max = 500)
	private int bpm;
	
	@Size(min = 1, max = 500)
	private int trackNumber;
	
	@Size(min = 0, max = 30)
	private String album;

	@Size(min = 0, max = 30)
	String albumCover;
	
// --------------------------Constructors--------------------------
	public CreateTrackWrapper () {
	}
	
//--------------------------Getters & Setters--------------------------	
	
	public String getAlbumCover() {
		return albumCover;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getLengthSeconds() {
		return lengthSeconds;
	}

	public void setLengthSeconds(int lengthSeconds) {
		this.lengthSeconds = lengthSeconds;
	}

	public int getLengthMinutes() {
		return lengthMinutes;
	}

	public void setLengthMintues(int lengthMinutes) {
		this.lengthMinutes = lengthMinutes;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setAlbumCover(String albumCover) {
		this.albumCover = albumCover;
	}
	
}
