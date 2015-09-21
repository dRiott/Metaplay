package com.thoughtriott.metaplay.data.wrappers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateArtistWrapper {

	
// --------------------------Fields--------------------------	
	@NotNull
	@Size(min = 3, max = 50)
	String name;
	@Size(min = 3)
	String biography;
	@Size(min = 3, max = 20)
	String genreName;
	@Size(min = 3, max = 40)
	String locationCity;
	@Size(min = 3, max = 25)
	String locationState;
	@Size(min = 3, max = 30)
	String recordLabelName;
	
	@Size(min = 3, max = 50)
	String member1;
	@Size(min = 3, max = 50)
	String member2;
	@Size(min = 3, max = 50)
	String member3;
	@Size(min = 3, max = 50)
	String member4;
	
	@Size(min = 3, max = 50)
	String albumName;
	@Size(min = 1, max = 5)
	String albumNumTracks;
	String albumReleaseDate;
	String albumAlbumCover;
	
// --------------------------Constructors--------------------------
	public CreateArtistWrapper () {
	}
	
//--------------------------Getters & Setters--------------------------	
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

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocationState() {
		return locationState;
	}

	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	public String getRecordLabelName() {
		return recordLabelName;
	}

	public void setRecordLabelName(String recordLabelName) {
		this.recordLabelName = recordLabelName;
	}

	public String getMember1() {
		return member1;
	}

	public void setMember1(String member1) {
		this.member1 = member1;
	}

	public String getMember2() {
		return member2;
	}

	public void setMember2(String member2) {
		this.member2 = member2;
	}

	public String getMember3() {
		return member3;
	}

	public void setMember3(String member3) {
		this.member3 = member3;
	}

	public String getMember4() {
		return member4;
	}

	public void setMember4(String member4) {
		this.member4 = member4;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumNumTracks() {
		return albumNumTracks;
	}

	public void setAlbumNumTracks(String albumNumTracks) {
		this.albumNumTracks = albumNumTracks;
	}

	public String getAlbumReleaseDate() {
		return albumReleaseDate;
	}

	public void setAlbumReleaseDate(String albumReleaseDate) {
		this.albumReleaseDate = albumReleaseDate;
	}

	public String getAlbumAlbumCover() {
		return albumAlbumCover;
	}

	public void setAlbumAlbumCover(String albumAlbumCover) {
		this.albumAlbumCover = albumAlbumCover;
	}
	
	
	
}
