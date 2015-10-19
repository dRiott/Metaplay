//package com.thoughtriott.metaplay.data.documents;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document
//public class AudioFile {
//
//	@Id
//	private String id;
//	private byte[] mp3;
//	private String name;
//	private int length;
//	private String lyrics;
//	private int bpm;
//	private int trackNumber;
//	private String artistName;
//	private String albumName;
//	
//	public AudioFile() {
//	}
//	
//	public AudioFile(String id, byte[] mp3) {
//		this.id = id;
//		this.mp3 = mp3;
//	}
//	
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public byte[] getMp3() {
//		return mp3;
//	}
//
//	public void setMp3(byte[] mp3) {
//		this.mp3 = mp3;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getLength() {
//		return length;
//	}
//
//	public void setLength(int length) {
//		this.length = length;
//	}
//
//	public String getLyrics() {
//		return lyrics;
//	}
//
//	public void setLyrics(String lyrics) {
//		this.lyrics = lyrics;
//	}
//
//	public int getBpm() {
//		return bpm;
//	}
//
//	public void setBpm(int bpm) {
//		this.bpm = bpm;
//	}
//
//	public int getTrackNumber() {
//		return trackNumber;
//	}
//
//	public void setTrackNumber(int trackNumber) {
//		this.trackNumber = trackNumber;
//	}
//
//	public String getArtistName() {
//		return artistName;
//	}
//
//	public void setArtistName(String artistName) {
//		this.artistName = artistName;
//	}
//
//	public String getAlbumName() {
//		return albumName;
//	}
//
//	public void setAlbumName(String albumName) {
//		this.albumName = albumName;
//	}
//	
//	public void setLengthMinSec(int minutes, int seconds) {
//		int length = (minutes*60) + seconds;
//		this.length = length;
//	}
//	
//}