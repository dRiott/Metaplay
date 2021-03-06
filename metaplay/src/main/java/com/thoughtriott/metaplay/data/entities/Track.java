package com.thoughtriott.metaplay.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import java.util.Collection;
import java.util.Iterator;

@Entity
@Table(name = "track")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"playlists", "audioFile", "lyrics"})
public class Track extends MetaplayEntity {
	
// --------------------------Constructors--------------------------
	public Track() {			
	}
	
// --------------------------Fields--------------------------
//	@OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private Collection<Playlist_Track> playlistTracks;

	@Column(name="length_seconds")
	private int length;
	
	private String lyrics;
	
	private int bpm;
	
	@Column(name="track_number")
	private int trackNumber;
	
	@ManyToOne
	@JoinColumn(name = "album_id", nullable=false)
	@JsonBackReference
	private Album album;
	
	@ManyToMany(mappedBy="tracks", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<Playlist> playlists;
	
	@Column(name="entity_type")
	private String entityType = "track";
	
	@Lob
	@Column(name="audio_file")
	private byte[] audioFile;
	
//--------------------------Getters & Setters--------------------------
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void setLengthMinSec(int minutes, int seconds) {
		this.length = (minutes*60) + seconds;
	}
	public void setLengthFromStringMinSec(String minutes, String seconds) {
		this.length = (Integer.parseInt(minutes)*60) + Integer.parseInt(seconds);
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
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
	
//	public Collection<Playlist_Track> getPlaylistTracks() {
//		return playlistTracks;
//	}
//
//	public void setPlaylistTracks(Collection<Playlist_Track> playlistTracks) {
//		this.playlistTracks = playlistTracks;
//	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Collection<Playlist> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(Collection<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public byte[] getAudioFile() {
		return audioFile;
	}

	public void setAudioFile(byte[] audioFile) {
		this.audioFile = audioFile;
	}
	
//--------------------------Non-G&S Methods--------------------------
	//change the order of a track in a given playlist
	public void setTrackOrderInPlaylist(Playlist playlist, int trackNumber) {
		
//		Iterator<Playlist_Track> trackIt = this.getPlaylistTracks().iterator();
//			while(trackIt.hasNext()) {
//				Playlist_Track pt = trackIt.next();
//				if(pt.getPlaylist() == playlist){
//					pt.setTrackNumber(trackNumber);
//				} else {
//					System.out.println("No playlist by that name was found.");
//				}
//			}
			
//			Iterator<Playlist_Track> playlistIt = playlist.getPlaylistTracks().iterator();
//			while(playlistIt.hasNext()) {
//				Playlist_Track pt = playlistIt.next();
//				if(pt.getPlaylist() == playlist) {
//					if(pt.getTrackNumber() >= trackNumber && pt.getTrack()!=this) {
//						pt.setTrackNumber(trackNumber + 1);
//					}
//				} else {
//					System.out.println("No playlist by that name was found.");
//				}
//			}
			
		System.out.println("Successfully updated track order. Track: " + this.getName() + " is now position #" 
				+ trackNumber + " in the playlist \"" + playlist.getName() + "\".");

	}

//--------------------------Collection Adders and Removers--------------------------
	
//	//adds a Playlist_Track to Collection<Playlist_Track>
//	public void addPlaylist(Playlist playlist, int trackNumber) {
//		if (getPlaylistTracks()!=null && !getPlaylistTracks().contains(playlist)) {
//			getPlaylistTracks().add(new Playlist_Track(this, playlist, trackNumber));
//		}
//	}

//	// removes a Member from Collection<Member>, setting its Artist_Member to null.
//	public void removePlaylist(Playlist playlist) {
//		if (getPlaylistTracks()!=null && getPlaylistTracks().contains(playlist)) {
//			getPlaylistTracks().remove(playlist);
//		}
//		if (playlist.getTracks().contains(this)) {
//			playlist.removeTrack(this);
//		}
//	}

//--------------------------Collection Printers--------------------------

	public String getPlaylistsToString () {
		if(playlists!=null) {
			Iterator<Playlist> it = playlists.iterator();
			String playlistsString = "";
			int size = playlists.size();
			while(it.hasNext()) {
				Playlist currentPlaylist = it.next();
				if (size==0) {
					playlistsString = "no playlists.";
				} else if(size==1) {
					playlistsString = "{" + currentPlaylist.getName() + "}";
				} else {
					if(playlistsString.length() == 0) {
						playlistsString = "{" + currentPlaylist.getName();
					} else if (!it.hasNext()) {
						playlistsString = playlistsString + ", " + currentPlaylist.getName() + "}";
					} else {
						playlistsString = playlistsString + ", " + currentPlaylist.getName();
					}
				}
			} return playlistsString;
		} return "Playlist list was null.";
	}
	
	public String getAlbumToString () {
		if(album!=null) {
			return album.getName();
		}
		return "Album is null.";
	}
	
	
	
//--------------------------toString()--------------------------

	//Tracks and Playlists: @ManyToMany. B/c StackOverflowError --> Altered toString(): playlists, playlistTracks, album.getName()
	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", length=" + length
				+ ", lyrics=" + lyrics + ", bpm=" + bpm + ", trackNumber=" + trackNumber + ", album=" + getAlbumToString()
				+ ", playlists=" + getPlaylistsToString() + "]";
	}
	
}


//--------------------------Notes / Old Code--------------------------

//public String getPlaylistTracksToString () {
//if(getPlaylistTracks()!=null) {
//Iterator<Playlist_Track> it = getPlaylistTracks().iterator();
//
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
//} else {return null;}
//}	
