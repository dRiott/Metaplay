package com.thoughtriott.metaplay.data.wrappers;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Track;

public class CreatePlaylistWrapper {

// --------------------------Fields--------------------------	
	@NotNull
	@Size(min=3, max=16)
	private String name;
	
	@Size(min=5, max=5000)
	private String description;
	
	private List<Account> accounts;
	private List<Track> tracks;
	
// --------------------------Constructors--------------------------
	public CreatePlaylistWrapper() {
	}

//--------------------------Getters & Setters--------------------------	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
}
