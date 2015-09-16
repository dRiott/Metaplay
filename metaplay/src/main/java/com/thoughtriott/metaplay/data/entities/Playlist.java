package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

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
	private int id;

	@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
	private Collection<Playlist_Track> playlistTracks;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="track_playlist", 
	joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name = "track_id", referencedColumnName="id"))
	private Collection<Track> tracks;
	
	@ManyToMany(mappedBy = "playlists")
	private Collection<Account> accounts;
	
	private String name;
	private String description;

//--------------------------Getters & Setters--------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Playlist_Track> getPlaylistTracks() {
		return playlistTracks;
	}

	public void setPlaylistTracks(Collection<Playlist_Track> playlistTracks) {
		this.playlistTracks = playlistTracks;
	}
	
	public Collection<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Collection<Track> tracks) {
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
	
	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
	

//--------------------------Collection Adders and Removers--------------------------
	
	//adds a Track to Collection<Playlist_Track>
	public void addTrackToPlaylistTrack(Track track, int trackNumber) {
		if (getPlaylistTracks()!=null && !getPlaylistTracks().contains(track)) {
			getPlaylistTracks().add(new Playlist_Track(track, this, trackNumber));
//			if (!track.getPlaylistTracks().contains(this)) {
//				track.getPlaylistTracks().add(new Playlist_Track(track, this, trackNumber));
//			}
		}
	}
	
	// removes a Track from Collection<Playlist_Track>
	public void removeTrackToPlaylistTrack(Track track) {
		if (getPlaylistTracks()!=null && getPlaylistTracks().contains(track)) {
			getPlaylistTracks().remove(track);
//			if (track.getPlaylistTracks().contains(this)) {
//				track.getPlaylistTracks().remove(this);
//			}
		}
	}
	
	//adds a Track to Collection<Tracks>
	public void addTrack(Track track, int trackNumber) {
		if (getTracks()!=null && !getTracks().contains(track)) {
			getTracks().add(track);
//			if (!track.getPlaylists().contains(this)) {
//				track.addPlaylist(this, trackNumber);
//			}
		}
	}
	
	// removes a Track from Collection<Tracks>
	public void removeTrack(Track track) {
		if (getTracks()!=null && getTracks().contains(track)) {
			getTracks().remove(track);
			if (track.getPlaylists().contains(this)) {
				track.removePlaylist(this);
			}
		}
	}
	
	//adds a User to Collection<User>
	public void addAccount(Account account) {
		if (getAccounts()!=null && !getAccounts().contains(account)) {
			getAccounts().add(account);
			if (!account.getPlaylists().contains(this)) {
				account.addPlaylist(this);
			}
		}
	}
			
	// removes a Member from Collection<Member>, setting its Artist_Member to null.
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
			if(playlistTracksString.length() > 1) {
			playlistTracksString = playlistTracksString + ", " + "Playlist: " + it.next().getPlaylist().getName()
					+ ", Track: " + it.next().getTrack().getName() + ", Track Number In Playlist: " +
					it.next().getTrackNumber();
			} else if (!it.hasNext()) {
				playlistTracksString = playlistTracksString + ", " + "Playlist: " + it.next().getPlaylist().getName()
						+ ", Track: " + it.next().getTrack().getName() + ", Track Number In Playlist: " +
						it.next().getTrackNumber() + "}";
			} else {
				playlistTracksString = "Playlist_Tracks: {" + "Playlist: " + it.next().getPlaylist().getName()
						+ ", Track: " + it.next().getTrack().getName() + ", Track Number In Playlist: " +
						it.next().getTrackNumber();
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
			if(tracksString.length() > 1) {
			tracksString = tracksString + ", " + it.next().getName();
			} else if (!it.hasNext()) {
				tracksString = tracksString + ", " + it.next().getName() + "}";
			} else {
				tracksString = "Tracks: {" + it.next().getName();
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
			if(accountsString.length() > 1) {
			accountsString = accountsString + ", " + it.next().getAccountname();
			} else if (!it.hasNext()) {
				accountsString = accountsString + ", " + it.next().getAccountname() + "}";
			} else {
				accountsString = "Accounts: {" + it.next().getAccountname();
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
