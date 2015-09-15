package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
	
// --------------------------Constructors--------------------------
	public Track() {
					
	}
	

// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
	private Collection<Playlist_Track> playlistTracks;

	private String name;
	
	@Column(name="length_seconds")
	private int length;
	
	private String lyrics;
	
	private int bpm;
	
	@Column(name="track_number")
	private int trackNumber;
	
	@ManyToOne
	@JoinColumn(name = "album_id", nullable=false)
	private Album album;
	
	@ManyToMany(mappedBy="tracks", cascade = CascadeType.ALL)
	private Collection<Playlist> playlists;
	
//--------------------------Getters & Setters--------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void setLengthMinSec(int minutes, int seconds) {
		int length = (minutes*60) + seconds;
		this.length = length;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	
	public Collection<Playlist_Track> getPlaylistTracks() {
		return playlistTracks;
	}

	public void setPlaylistTracks(Collection<Playlist_Track> playlistTracks) {
		this.playlistTracks = playlistTracks;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Collection<Playlist> getPlaylists() {
		return playlists;
	}
	
//--------------------------Non-G&S Methods--------------------------
	
	//change the order of a track in a given playlist
	public void setTrackOrderInPlaylist(Playlist playlist, int trackNumber) {
		
		Iterator<Playlist_Track> trackIt = this.getPlaylistTracks().iterator();
			while(trackIt.hasNext()) {
				Playlist_Track pt = trackIt.next();
				if(pt.getPlaylist() == playlist){
					pt.setTrackNumber(trackNumber);
				} else {
					System.out.println("No playlist by that name was found.");
				}
			}
			
			Iterator<Playlist_Track> playlistIt = playlist.getPlaylistTracks().iterator();
			while(playlistIt.hasNext()) {
				Playlist_Track pt = playlistIt.next();
				if(pt.getPlaylist() == playlist) {
					if(pt.getTrackNumber() >= trackNumber && pt.getTrack()!=this) {
						pt.setTrackNumber(trackNumber + 1);
					}
				} else {
					System.out.println("No playlist by that name was found.");
				}
			}
			
		System.out.println("Successfully updated track order. Track: " + this.getName() + " is now position #" 
				+ trackNumber + " in the playlist \"" + playlist.getName() + "\".");

	}

//--------------------------Collection Adders and Removers--------------------------
	
	//adds a Playlist_Track to Collection<Playlist_Track>
	public void addPlaylist(Playlist playlist, int trackNumber) {
		if (!getPlaylistTracks().contains(playlist)) {
			getPlaylistTracks().add(new Playlist_Track(this, playlist, trackNumber));
		}
	}
		
	// removes a Member from Collection<Member>, setting its Artist_Member to null.
	public void removePlaylist(Playlist playlist) {
		if (getPlaylistTracks().contains(playlist)) {
			getPlaylistTracks().remove(playlist);
		}
		if (playlist.getTracks().contains(this)) {
			playlist.removeTrack(this);
		}
	}

//--------------------------Collection Printers--------------------------

	public String getPlaylistsToString () {
		if(getPlaylists()!=null) {
		Iterator<Playlist> it = getPlaylists().iterator();
		String playlistsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(playlistsString.length() > 1) {
			playlistsString = playlistsString + ", " + it.next().getName();
			} else if (!it.hasNext()) {
				playlistsString = playlistsString + ", " + it.next().getName() + "}";
			} else {
				playlistsString = "Playlists: {" + it.next().getName();
			}
		}
		return playlistsString;
		} return "No playlists.";
	}

	public void setPlaylists(Collection<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public String getPlaylistTracksToString () {
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
	}	
	
	public String getAlbumToString () {
		if(album!=null) {
			return album.toString();
		}
		return "Album is null.";
	}
		
	
//--------------------------toString()--------------------------


	//Tracks and Playlists: @ManyToMany. B/c StackOverflowError --> Altered toString(): playlists, playlistTracks, album.getName()
	@Override
	public String toString() {
		return "Track [id=" + id + ", playlistTracks=" + getPlaylistTracksToString() + ", name=" + name + ", length=" + length
				+ ", lyrics=" + lyrics + ", bpm=" + bpm + ", trackNumber=" + trackNumber + ", album=" + getAlbumToString()
				+ ", playlists=" + getPlaylistsToString() + "]";
	}
	
}