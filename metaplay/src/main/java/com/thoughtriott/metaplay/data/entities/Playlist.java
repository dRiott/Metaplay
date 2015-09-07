package com.thoughtriott.metaplay.data.entities;

public class Playlist {

	//fields
	private int playlistId;
	
	private String name;

	private String description;

	private String type;

	//getters & setters
	
	public int getId() {
		return playlistId;
	}

	public void setId(int playlistId) {
		this.playlistId = playlistId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		System.out.println("Spring MVC Data Binding In Action");
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", name=" + name + ", description=" + description + ", type="
				+ type + "]";
	}

	

}
