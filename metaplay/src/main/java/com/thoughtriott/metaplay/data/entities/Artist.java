package com.thoughtriott.metaplay.data.entities;

import java.util.List;

public class Artist {

	//fields
	private int artistId;
	private String genre;
	private int recordlabelId;
	private String location;
	private String name;
	private String biography;
	private Album album;
	private String artistImage;
	private List<String> members;
	
	//getters & setters
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getRecordlabelId() {
		return recordlabelId;
	}
	public void setRecordlabelId(int recordlabelId) {
		this.recordlabelId = recordlabelId;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getArtistImage() {
		return artistImage;
	}
	public void setArtistImage(String artistImage) {
		this.artistImage = artistImage;
	}
	
	public List<String> getMembers() {
		return members;
	}
	public void setMembers(List<String> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", genre=" + genre + ", recordlabelId=" + recordlabelId + ", location="
				+ location + ", name=" + name + ", biography=" + biography + ", album=" + album + ", artistImage="
				+ artistImage + ", members=" + members + "]";
	}


}
