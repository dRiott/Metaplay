package com.thoughtriott.metaplay.data.wrappers;

import com.thoughtriott.metaplay.data.entities.Member;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateArtistWrapper {

// --------------------------Fields--------------------------	
	@NotNull
	@Size(min = 3, max = 50)
	String name;
	@Size(min = 3)
	String biography;
	@Size(min = 3, max = 20)
	String genreName;
	@Size(min = 3, max = 20)
	String newGenreName;
	@Size(min = 3, max = 5000)
	String newGenreDescription;
	@Size(min = 3, max = 40)
	String locationCity;
	@Size(min = 3, max = 25)
	String locationState;
	@Size(min = 3, max = 25)
	String locationCountry;
	@Size(min = 3, max = 25)
	String newLocationCountry;
	
//	@Size(min = 3, max = 50)
//	String member1;
//	@Size(min = 3, max = 50)
//	String member1StageName;
//	@Size(min = 3, max = 50)
//	String member2;
//	@Size(min = 3, max = 50)
//	String member2StageName;
//
//	@Size(min = 3, max = 50)
//	String member3;
//	@Size(min = 3, max = 50)
//	String member3StageName;
//
//	@Size(min = 3, max = 50)
//	String member4;
//	@Size(min = 3, max = 50)
//	String member4StageName;
//
//	@Size(min = 3, max = 50)
//	String member5;
//	@Size(min = 3, max = 50)
//	String member5StageName;
//	
//	@Size(min = 3, max = 50)
//	String member6;
//	@Size(min = 3, max = 50)
//	String member6StageName;
	
	@Size(min = 3, max = 50)
	String albumNameFromList;
	@Size(min = 3, max = 50)
	String theNewAlbumName;
	@Size(min = 1, max = 5)
	String albumNumTracks;
	String albumReleaseDate;
	
	@Size(min = 3, max = 30)
	private String recordLabelFromList;
	@Size(min = 3, max = 30)
	private String theNewRecordLabel;
	@Size(min = 3, max = 40)
	private String recordLabelCity;
	@Size(min = 3, max = 25)
	private String recordLabelState;

	private MultipartFile albumCover;
	private MultipartFile artistImage;
	
	private List<Member> members;

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

	public String getNewGenreName() {
		return newGenreName;
	}

	public void setNewGenreName(String newGenreName) {
		this.newGenreName = newGenreName;
	}

	public String getNewGenreDescription() {
		return newGenreDescription;
	}

	public void setNewGenreDescription(String newGenreDescription) {
		this.newGenreDescription = newGenreDescription;
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
	
	public String getLocationCountry() {
		return locationCountry;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}
	

	public String getNewLocationCountry() {
		return newLocationCountry;
	}

	public void setNewLocationCountry(String newLocationCountry) {
		this.newLocationCountry = newLocationCountry;
	}
	
//	public String getMember1() {
//		return member1;
//	}
//
//	public void setMember1(String member1) {
//		this.member1 = member1;
//	}
//
//	public String getMember2() {
//		return member2;
//	}
//
//	public void setMember2(String member2) {
//		this.member2 = member2;
//	}
//
//	public String getMember3() {
//		return member3;
//	}
//
//	public void setMember3(String member3) {
//		this.member3 = member3;
//	}
//
//	public String getMember4() {
//		return member4;
//	}
//
//	public void setMember4(String member4) {
//		this.member4 = member4;
//	}
//
//	public String getMember5() {
//		return member5;
//	}
//
//	public void setMember5(String member5) {
//		this.member5 = member5;
//	}
//
//	public String getMember6() {
//		return member6;
//	}
//
//	public void setMember6(String member6) {
//		this.member6 = member6;
//	}
//
//	public String getMember1StageName() {
//		return member1StageName;
//	}
//
//	public void setMember1StageName(String member1StageName) {
//		this.member1StageName = member1StageName;
//	}
//
//	public String getMember2StageName() {
//		return member2StageName;
//	}
//
//	public void setMember2StageName(String member2StageName) {
//		this.member2StageName = member2StageName;
//	}
//
//	public String getMember3StageName() {
//		return member3StageName;
//	}
//
//	public void setMember3StageName(String member3StageName) {
//		this.member3StageName = member3StageName;
//	}
//
//	public String getMember4StageName() {
//		return member4StageName;
//	}
//
//	public void setMember4StageName(String member4StageName) {
//		this.member4StageName = member4StageName;
//	}
//
//	public String getMember5StageName() {
//		return member5StageName;
//	}
//
//	public void setMember5StageName(String member5StageName) {
//		this.member5StageName = member5StageName;
//	}
//
//	public String getMember6StageName() {
//		return member6StageName;
//	}
//
//	public void setMember6StageName(String member6StageName) {
//		this.member6StageName = member6StageName;
//	}

	public String getAlbumNameFromList() {
		return albumNameFromList;
	}

	public void setAlbumNameFromList(String albumNameFromList) {
		this.albumNameFromList = albumNameFromList;
	}

	public String getTheNewAlbumName() {
		return theNewAlbumName;
	}

	public void setTheNewAlbumName(String theNewAlbumName) {
		this.theNewAlbumName = theNewAlbumName;
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

	public MultipartFile getAlbumCover() {
		return albumCover;
	}

	public String getRecordLabelFromList() {
		return recordLabelFromList;
	}

	public void setRecordLabelFromList(String recordLabelFromList) {
		this.recordLabelFromList = recordLabelFromList;
	}

	public String getTheNewRecordLabel() {
		return theNewRecordLabel;
	}

	public void setTheNewRecordLabel(String theNewRecordLabel) {
		this.theNewRecordLabel = theNewRecordLabel;
	}

	public String getRecordLabelCity() {
		return recordLabelCity;
	}

	public void setRecordLabelCity(String recordLabelCity) {
		this.recordLabelCity = recordLabelCity;
	}

	public String getRecordLabelState() {
		return recordLabelState;
	}

	public void setRecordLabelState(String recordLabelState) {
		this.recordLabelState = recordLabelState;
	}

	public void setAlbumCover(MultipartFile albumCover) {
		this.albumCover = albumCover;
	}

	public MultipartFile getArtistImage() {
		return artistImage;
	}

	public void setArtistImage(MultipartFile artistImage) {
		this.artistImage = artistImage;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
