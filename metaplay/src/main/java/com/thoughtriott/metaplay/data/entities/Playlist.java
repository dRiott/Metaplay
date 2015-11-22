package com.thoughtriott.metaplay.data.entities;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "playlist")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"accounts"})
public class Playlist extends MetaplayEntity {
	
// --------------------------Constructors--------------------------
	public Playlist() {
	}

	public Playlist(String name) {
		this.name = name;
	}
	
	public Playlist(String name, String description) {
		this.name = name;
		this.description = description;
	}

	// --------------------------Fields--------------------------
//	@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List<Playlist_Track> playlistTracks;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name="track_playlist", 
	joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name = "track_id", referencedColumnName="id"))
	private List<Track> tracks;
	
	@ManyToMany(mappedBy = "playlists", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private List<Account> accounts;
	
	private String description;

	@Column(name="entity_type")
	private String entityType = "playlist";

//--------------------------Getters & Setters--------------------------
	
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

//	public List<Playlist_Track> getPlaylistTracks() {
//		return playlistTracks;
//	}
//
//	public void setPlaylistTracks(List<Playlist_Track> playlistTracks) {
//		this.playlistTracks = playlistTracks;
//	}

//--------------------------Collection Adders and Removers--------------------------
	

	
	
	//adds a Track to List<Tracks>
	public void addTrack(Track track) {
		if (getTracks()!=null && !getTracks().contains(track)) {
			getTracks().add(track);
//			if (!track.getPlaylists().contains(this)) {
//				track.addPlaylist(this, trackNumber);
//			}
		}
	}
	
	// removes a Track from List<Tracks>
//	public void removeTrack(Track track) {
//		if (getTracks()!=null && getTracks().contains(track)) {
//			getTracks().remove(track);
//			if (track.getPlaylists().contains(this)) {
//				track.removePlaylist(this);
//			}
//		}
//	}
	
	//adds a User to List<User>
	public void addAccount(Account account) {
		if (getAccounts()!=null && !getAccounts().contains(account)) {
			getAccounts().add(account);
			if (!account.getPlaylists().contains(this)) {
				account.addPlaylist(this);
			}
		}
	}
			
	// removes a Member from List<Member>, setting its Artist_Member to null.
	public void removeAccount(Account account) {
		if (getAccounts()!=null && getAccounts().contains(account)) {
			getAccounts().remove(account);
			if (account.getPlaylists().contains(this)) {
				account.removePlaylist(this);
			}
		}	
	}


//--------------------------Collection Printers--------------------------

	public String getTracksToString () {
		if(tracks!=null) {
			Iterator<Track> it = tracks.iterator();
			String tracksString = "";
			int size = tracks.size();
			while(it.hasNext()) {
				Track currentTrack = it.next();
				if (size==0) {
					tracksString = "No tracks.";
				} else if(size==1) {
					tracksString = "{" + currentTrack.getName() + "}";
				} else {
					if(tracksString.length() == 0) {
						tracksString = "{" + currentTrack.getName();
					} else if (!it.hasNext()) {
						tracksString = tracksString + ", " + currentTrack.getName() + "}";
					} else {
						tracksString = tracksString + ", " + currentTrack.getName();
					}
				}
			} return tracksString;
		} return "Tracks list was null.";
	}
	
	public String getAccountsToString () {
		if(accounts!=null) {
			Iterator<Account> it = accounts.iterator();
			String accountsString = "";
			int size = accounts.size();
			while(it.hasNext()) {
				Account currentAccount = it.next();
				if (size==0) {
					accountsString = "No accounts.";
				} else if(size==1) {
					accountsString = "{" + currentAccount.getName() + "}";
				} else {
					if(accountsString.length() == 0) {
						accountsString = "{" + currentAccount.getName();
					} else if (!it.hasNext()) {
						accountsString = accountsString + ", " + currentAccount.getName() + "}";
					} else {
						accountsString = accountsString + ", " + currentAccount.getName();
					}
				}
			} return accountsString;
		} return "Accounts list was null.";
	}

//	//adds a Track to List<Playlist_Track>
//	public void addTrackToPlaylistTrack(Track track, int trackNumber) {
//		if (getPlaylistTracks()!=null && !getPlaylistTracks().contains(track)) {
//			getPlaylistTracks().add(new Playlist_Track(track.getId(), id, trackNumber));
//		}
//	}
//
//	// removes a Track from List<Playlist_Track>
//	public void removeTrackToPlaylistTrack(Track track) {
//		if (getPlaylistTracks()!=null && getPlaylistTracks().contains(track)) {
//			getPlaylistTracks().remove(track);
//		}
//	}
	
//--------------------------toString()--------------------------
	
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", tracks=" + getTracksToString() + ", accounts="
				+ getAccountsToString() + ", name=" + name + ", description=" + description + "]";
	}


} // end of Playlist.java


//--------------------------Notes / Old Code--------------------------
//public List<Playlist_Track> getPlaylistTracks() {
//return playlistTracks;
//}
//
//public void setPlaylistTracks(List<Playlist_Track> playlistTracks) {
//this.playlistTracks = playlistTracks;
//}

//public String getPlaylistTracksToString () {
//if(getPlaylistTracks()!=null) {
//Iterator<Playlist_Track> it = getPlaylistTracks().iterator();
//String playlistTracksString = "";
//while(it.hasNext()) {
//	//if-else prevents ", " from being appended the first time, appends } on the final time.
//	Playlist_Track currentPlaylistTrack = it.next();
//	if(playlistTracksString.length() > 1) {
//	playlistTracksString = playlistTracksString + ", " + "Playlist: " + currentPlaylistTrack.getPlaylist().getName()
//			+ ", Track: " + currentPlaylistTrack.getTrack().getName() + ", Track Number In Playlist: " +
//			currentPlaylistTrack.getTrackNumber();
//	} else if (!it.hasNext()) {
//		playlistTracksString = playlistTracksString + ", " + "Playlist: " + currentPlaylistTrack.getPlaylist().getName()
//				+ ", Track: " + currentPlaylistTrack.getTrack().getName() + ", Track Number In Playlist: " +
//				currentPlaylistTrack.getTrackNumber() + "}";
//	} else {
//		playlistTracksString = "Playlist_Tracks: {" + "Playlist: " + currentPlaylistTrack.getPlaylist().getName()
//				+ ", Track: " + currentPlaylistTrack.getTrack().getName() + ", Track Number In Playlist: " +
//				currentPlaylistTrack.getTrackNumber();
//	}
//}
//return playlistTracksString;
//} return "No PlaylistTracks.";
//}	