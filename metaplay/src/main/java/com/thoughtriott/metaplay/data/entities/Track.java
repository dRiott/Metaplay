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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "track")
public class Track {
	
// --------------------------Constructors--------------------------

	public Track() {			
	}
	
// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Collection<Playlist_Track> playlistTracks;

	private String name;
	
	@Column(name="length_seconds")
	private int length;
	
	private String lyrics;
	
	private int bpm;
//	private String bpmString;
//	private String minutes;
//	private String seconds;
	
	@Column(name="track_number")
	private int trackNumber;
	
	@ManyToOne
	@JoinColumn(name = "album_id", nullable=false)
	@JsonBackReference
	private Album album;
	
	@ManyToMany(mappedBy="tracks", cascade = CascadeType.ALL)
	@JsonBackReference
	private Collection<Playlist> playlists;
	
//--------------------------Getters & Setters--------------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	public void setLengthFromStringMinSec(String minutes, String seconds) {
		int min = Integer.parseInt(minutes);
		int sec = Integer.parseInt(seconds);
		int length = (min*60) + sec;
		this.length = length;
	}

//	public String getMinutes() {
//		return minutes;
//	}
//
//	public void setMinutes(String minutes) {
//		this.minutes = minutes;
//	}
//
//	public String getSeconds() {
//		return seconds;
//	}
//
//	public void setSeconds(String seconds) {
//		this.seconds = seconds;
//	}

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

//	public String getBpmString() {
//		return bpmString;
//	}
//
//	public void setBpmString(String bpmString) {
//		this.bpmString = bpmString;
//	}

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
		if (getPlaylistTracks()!=null && !getPlaylistTracks().contains(playlist)) {
			getPlaylistTracks().add(new Playlist_Track(this, playlist, trackNumber));
		}
	}
		
	// removes a Member from Collection<Member>, setting its Artist_Member to null.
	public void removePlaylist(Playlist playlist) {
		if (getPlaylistTracks()!=null && getPlaylistTracks().contains(playlist)) {
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
			Playlist currentPlaylist = it.next();
			if(playlistsString.length() > 1) {
			playlistsString = playlistsString + ", " + currentPlaylist.getName();
			} else if (!it.hasNext()) {
				playlistsString = playlistsString + ", " + currentPlaylist.getName() + "}";
			} else {
				playlistsString = "Playlists: {" + currentPlaylist.getName();
			}
		}
		return playlistsString;
		} return "No playlists.";
	}

	public void setPlaylists(Collection<Playlist> playlists) {
		this.playlists = playlists;
	}
	
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
		} else {return null;}
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
