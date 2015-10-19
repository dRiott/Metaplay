package com.thoughtriott.metaplay.data.wrappers;

import org.springframework.web.multipart.MultipartFile;

public class UploadTrackWrapper {
	
	// --------------------------Fields--------------------------	
	private String id;
	private MultipartFile mp3;
	private String filename;
	
	// --------------------------Constructors--------------------------
	public UploadTrackWrapper() {
	}
	
	//--------------------------Getters & Setters--------------------------	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public MultipartFile getMp3() {
		return mp3;
	}
	
	public void setMp3(MultipartFile mp3) {
		this.mp3 = mp3;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
