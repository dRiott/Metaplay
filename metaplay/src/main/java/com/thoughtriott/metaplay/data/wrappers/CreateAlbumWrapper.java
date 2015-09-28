package com.thoughtriott.metaplay.data.wrappers;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	private Date releaseDate;
	//for track entity
	private String track1;
	private String track2;
	private String track3;
	private String track4;
	private String track5;
	private String track6;
	private String track7;
	private String track8;
	private String track9;
	private String track10;
	private String track11;
	private String track12;
	private String track13;
	private String track14;
	private String track15;
	private String track16;
	private String track17;
	private String track18;
	private String track19;
	private String track20;
	private String track21;
	private String track22;
	private String track23;
	private String track24;
	private String track25;
	private String track26;
	private String track27;
	private String track28;
	private String track29;
	private String track30;
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
	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTrack1() {
		return track1;
	}

	public void setTrack1(String track1) {
		this.track1 = track1;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getTrack3() {
		return track3;
	}

	public void setTrack3(String track3) {
		this.track3 = track3;
	}

	public String getTrack4() {
		return track4;
	}

	public void setTrack4(String track4) {
		this.track4 = track4;
	}

	public String getTrack5() {
		return track5;
	}

	public void setTrack5(String track5) {
		this.track5 = track5;
	}

	public String getTrack6() {
		return track6;
	}

	public void setTrack6(String track6) {
		this.track6 = track6;
	}

	public String getTrack7() {
		return track7;
	}

	public void setTrack7(String track7) {
		this.track7 = track7;
	}

	public String getTrack8() {
		return track8;
	}

	public void setTrack8(String track8) {
		this.track8 = track8;
	}

	public String getTrack9() {
		return track9;
	}

	public void setTrack9(String track9) {
		this.track9 = track9;
	}

	public String getTrack10() {
		return track10;
	}

	public void setTrack10(String track10) {
		this.track10 = track10;
	}

	public String getTrack11() {
		return track11;
	}

	public void setTrack11(String track11) {
		this.track11 = track11;
	}

	public String getTrack12() {
		return track12;
	}

	public void setTrack12(String track12) {
		this.track12 = track12;
	}

	public String getTrack13() {
		return track13;
	}

	public void setTrack13(String track13) {
		this.track13 = track13;
	}

	public String getTrack14() {
		return track14;
	}

	public void setTrack14(String track14) {
		this.track14 = track14;
	}

	public String getTrack15() {
		return track15;
	}

	public void setTrack15(String track15) {
		this.track15 = track15;
	}

	public String getTrack16() {
		return track16;
	}

	public void setTrack16(String track16) {
		this.track16 = track16;
	}

	public String getTrack17() {
		return track17;
	}

	public void setTrack17(String track17) {
		this.track17 = track17;
	}

	public String getTrack18() {
		return track18;
	}

	public void setTrack18(String track18) {
		this.track18 = track18;
	}

	public String getTrack19() {
		return track19;
	}

	public void setTrack19(String track19) {
		this.track19 = track19;
	}

	public String getTrack20() {
		return track20;
	}

	public void setTrack20(String track20) {
		this.track20 = track20;
	}

	public String getTrack21() {
		return track21;
	}

	public void setTrack21(String track21) {
		this.track21 = track21;
	}

	public String getTrack22() {
		return track22;
	}

	public void setTrack22(String track22) {
		this.track22 = track22;
	}

	public String getTrack23() {
		return track23;
	}

	public void setTrack23(String track23) {
		this.track23 = track23;
	}

	public String getTrack24() {
		return track24;
	}

	public void setTrack24(String track24) {
		this.track24 = track24;
	}

	public String getTrack25() {
		return track25;
	}

	public void setTrack25(String track25) {
		this.track25 = track25;
	}

	public String getTrack26() {
		return track26;
	}

	public void setTrack26(String track26) {
		this.track26 = track26;
	}

	public String getTrack27() {
		return track27;
	}

	public void setTrack27(String track27) {
		this.track27 = track27;
	}

	public String getTrack28() {
		return track28;
	}

	public void setTrack28(String track28) {
		this.track28 = track28;
	}

	public String getTrack29() {
		return track29;
	}

	public void setTrack29(String track29) {
		this.track29 = track29;
	}

	public String getTrack30() {
		return track30;
	}

	public void setTrack30(String track30) {
		this.track30 = track30;
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
