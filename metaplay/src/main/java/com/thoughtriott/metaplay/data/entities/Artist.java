package com.thoughtriott.metaplay.data.entities;

public class Artist {

	//fields
	private int artistId;
	private int genreId;
	private int recordlabelId;
	private int locationId;
	private String name;
	private String biography;
	private String artistImage;
	
	//getters & setters
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	public int getRecordlabelId() {
		return recordlabelId;
	}
	public void setRecordlabelId(int recordlabelId) {
		this.recordlabelId = recordlabelId;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
	
	public String getArtistImage() {
		return artistImage;
	}
	public void setArtistImage(String artistImage) {
		this.artistImage = artistImage;
	}
	
	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", genreId=" + genreId + ", recordlabelId=" + recordlabelId
				+ ", locationId=" + locationId + ", name=" + name + ", biography=" + biography + ", artistImage="
				+ artistImage + "]";
	}


}
