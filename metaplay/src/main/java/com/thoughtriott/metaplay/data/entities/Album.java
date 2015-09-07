package com.thoughtriott.metaplay.data.entities;

public class Album {

	//fields
	private int artistId;
	private String name;
	private int numTracks;
	private String releaseDate;
	private String albumCover;
	
	public Album(int artistId, String name, int numTracks, String releaseDate, String albumCover) {
		super();
		this.artistId = artistId;
		this.name = name;
		this.numTracks = numTracks;
		this.releaseDate = releaseDate;
		this.albumCover = albumCover;
	}
	
	public Album() {
	//default constructor for Spring to instantiate an Album
	}

	//getters & setters
	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAlbumCover() {
		return albumCover;
	}

	public void setAlbumCover(String albumCover) {
		this.albumCover = albumCover;
	}

	@Override
	public String toString() {
		return "Album [artistId=" + artistId + ", name=" + name + ", numTracks=" + numTracks + ", releaseDate="
				+ releaseDate + ", albumCover=" + albumCover + "]";
	}
}
