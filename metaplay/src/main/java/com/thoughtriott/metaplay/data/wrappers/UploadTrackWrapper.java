package com.thoughtriott.metaplay.data.wrappers;

import org.springframework.web.multipart.MultipartFile;

public class UploadTrackWrapper {

	private String id;
	private MultipartFile mp3;
	
	
	
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
	
	
	
}
