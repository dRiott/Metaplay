package com.thoughtriott.metaplay.data.wrappers;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTrackWrapper {

	
// --------------------------Fields--------------------------	
	@NotNull
	@Size(min = 1, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String artistFromList;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String theNewArtist;
	
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
	private String albumFromList;

	@Size(min = 0, max = 30)
	private String theNewAlbum;

	@Size(min = 0, max = 30)
	private String albumCover;
	
	private MultipartFile mp3;
	
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

	public int getLengthSeconds() {
		return lengthSeconds;
	}

	public void setLengthSeconds(int lengthSeconds) {
		this.lengthSeconds = lengthSeconds;
	}

	public void setLengthMinutes(int lengthMinutes) {
		this.lengthMinutes = lengthMinutes;
	}

	public int getLengthMinutes() {
		return lengthMinutes;
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

	public String getAlbumFromList() {
		return albumFromList;
	}

	public void setAlbumFromList(String albumFromList) {
		this.albumFromList = albumFromList;
	}

	public String getTheNewAlbum() {
		return theNewAlbum;
	}

	public void setTheNewAlbum(String theNewAlbum) {
		this.theNewAlbum = theNewAlbum;
	}

	public void setAlbumCover(String albumCover) {
		this.albumCover = albumCover;
	}

	public void setMp3(MultipartFile mp3) {
		this.mp3 = mp3;
	}
	
	public MultipartFile getMp3() {
		return mp3;
	}

}
