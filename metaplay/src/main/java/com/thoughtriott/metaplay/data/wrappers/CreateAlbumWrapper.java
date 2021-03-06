package com.thoughtriott.metaplay.data.wrappers;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateAlbumWrapper {

// --------------------------Fields--------------------------	
	@NotNull
	@Size(min=3, max=16)
	private String name;
	private String albumFromList;
	private String description;
	private int lengthMinutes;
	private int lengthSeconds;
	private int numTracks;
	private String releaseDate;

	@NotNull
	@Size(min = 3, max = 50)
	private String artistFromList;
	@NotNull
	@Size(min = 3, max = 50)
	private String theNewArtist;
	
	@Size(min = 3, max = 30)
	private String recordLabelFromList;
	@Size(min = 3, max = 30)
	private String theNewRecordLabel;
	@Size(min = 3, max = 40)
	private String recordLabelCity;
	@Size(min = 3, max = 25)
	private String recordLabelState;
	@Size(min = 3, max = 25)
	private String recordLabelCountry;
	@Size(min = 3, max = 25)
	private String recordLabelNewCountry;
	
	private List<CreateTrackWrapper> createTrackWrappers;
	
	private MultipartFile albumCover;

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

	public String getAlbumFromList() {
		return albumFromList;
	}

	public void setAlbumFromList(String albumFromList) {
		this.albumFromList = albumFromList;
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
	
	public List<CreateTrackWrapper> getCreateTrackWrappers() {
		return createTrackWrappers;
	}

	public void setCreateTrackWrappers(List<CreateTrackWrapper> createTrackWrappers) {
		this.createTrackWrappers = createTrackWrappers;
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

	public MultipartFile getAlbumCover() {
		return albumCover;
	}

	public void setAlbumCover(MultipartFile albumCover) {
		this.albumCover = albumCover;
	}

	public String getRecordLabelCountry() {
		return recordLabelCountry;
	}

	public void setRecordLabelCountry(String recordLabelCountry) {
		this.recordLabelCountry = recordLabelCountry;
	}

	public String getRecordLabelNewCountry() {
		return recordLabelNewCountry;
	}

	public void setRecordLabelNewCountry(String recordLabelNewCountry) {
		this.recordLabelNewCountry = recordLabelNewCountry;
	}
	
}
