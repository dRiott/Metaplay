package com.thoughtriott.metaplay.data.entities;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist {
	
// --------------------------Constructors--------------------------
	public Playlist() {
	}

// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
	private List<Playlist_Track> playlistTracks;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="track_playlist", 
	joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name = "track_id", referencedColumnName="id"))
	private List<Track> tracks;
	
	@ManyToMany(mappedBy = "playlists")
	private List<Account> accounts;
	
	private String name;
	private String description;

//--------------------------Getters & Setters--------------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Playlist_Track> getPlaylistTracks() {
		return playlistTracks;
	}

	public void setPlaylistTracks(List<Playlist_Track> playlistTracks) {
		this.playlistTracks = playlistTracks;
	}
	
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
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
	

//--------------------------Collection Adders and Removers--------------------------
	
	//adds a Track to List<Playlist_Track>
	public void addTrackToPlaylistTrack(Track track, int trackNumber) {
		if (getPlaylistTracks()!=null && !getPlaylistTracks().contains(track)) {
			getPlaylistTracks().add(new Playlist_Track(track, this, trackNumber));
//			if (!track.getPlaylistTracks().contains(this)) {
//				track.getPlaylistTracks().add(new Playlist_Track(track, this, trackNumber));
//			}
		}
	}
	
	// removes a Track from List<Playlist_Track>
	public void removeTrackToPlaylistTrack(Track track) {
		if (getPlaylistTracks()!=null && getPlaylistTracks().contains(track)) {
			getPlaylistTracks().remove(track);
//			if (track.getPlaylistTracks().contains(this)) {
//				track.getPlaylistTracks().remove(this);
//			}
		}
	}
	
	//adds a Track to List<Tracks>
	public void addTrack(Track track, int trackNumber) {
		if (getTracks()!=null && !getTracks().contains(track)) {
			getTracks().add(track);
//			if (!track.getPlaylists().contains(this)) {
//				track.addPlaylist(this, trackNumber);
//			}
		}
	}
	
	// removes a Track from List<Tracks>
	public void removeTrack(Track track) {
		if (getTracks()!=null && getTracks().contains(track)) {
			getTracks().remove(track);
			if (track.getPlaylists().contains(this)) {
				track.removePlaylist(this);
			}
		}
	}
	
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

	public String getPlaylistTracksToString () {
		if(getPlaylistTracks()!=null) {
		Iterator<Playlist_Track> it = getPlaylistTracks().iterator();
		String playlistTracksString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			Playlist_Track currentPlaylistTrack = it.next();
			if(playlistTracksString.length() > 1) {
			playlistTracksString = playlistTracksString + ", " + "Playlist: " + currentPlaylistTrack.getPlaylist().getName()
					+ ", Track: " + currentPlaylistTrack.getTrack().getName() + ", Track Number In Playlist: " +
					currentPlaylistTrack.getTrackNumber();
			} else if (!it.hasNext()) {
				playlistTracksString = playlistTracksString + ", " + "Playlist: " + currentPlaylistTrack.getPlaylist().getName()
						+ ", Track: " + currentPlaylistTrack.getTrack().getName() + ", Track Number In Playlist: " +
						currentPlaylistTrack.getTrackNumber() + "}";
			} else {
				playlistTracksString = "Playlist_Tracks: {" + "Playlist: " + currentPlaylistTrack.getPlaylist().getName()
						+ ", Track: " + currentPlaylistTrack.getTrack().getName() + ", Track Number In Playlist: " +
						currentPlaylistTrack.getTrackNumber();
			}
		}
		return playlistTracksString;
		} return "No PlaylistTracks.";
	}	
	
	public String getTracksToString () {
		if(getTracks()!=null) {
		Iterator<Track> it = getTracks().iterator();
		String tracksString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			Track currentTrack = it.next();
			if(tracksString.length() > 1) {
			tracksString = tracksString + ", " + currentTrack.getName();
			} else if (!it.hasNext()) {
				tracksString = tracksString + ", " + currentTrack.getName() + "}";
			} else {
				tracksString = "Tracks: {" + currentTrack.getName();
			}
		}
		return tracksString;
		} return "No tracks.";
	}
	
	public String getAccountsToString () {
		if(getAccounts()!=null) {
		Iterator<Account> it = getAccounts().iterator();
		String accountsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			Account currentAccount = it.next();
			if(accountsString.length() > 1) {
			accountsString = accountsString + ", " + currentAccount.getAccountname();
			} else if (!it.hasNext()) {
				accountsString = accountsString + ", " + currentAccount.getAccountname() + "}";
			} else {
				accountsString = "Accounts: {" + currentAccount.getAccountname();
			}
		}
		return accountsString;
		} return "No accounts";
	}
//--------------------------toString()--------------------------
	
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", playlistTracks=" + this.getPlaylistTracksToString() + ", tracks=" + this.getTracksToString() + ", accounts="
				+ this.getAccountsToString() + ", name=" + name + ", description=" + description + "]";
	}
	
}
