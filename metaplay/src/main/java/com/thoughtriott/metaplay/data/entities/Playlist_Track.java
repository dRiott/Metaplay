package com.thoughtriott.metaplay.data.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "track_playlist")
@IdClass(Playlist_TrackPK.class)
public class  Playlist_Track {
	
// --------------------------Constructors--------------------------
	public Playlist_Track() {
		
	}
	
	public Playlist_Track(Track track, Playlist playlist, int trackNumber) {
//		this.id = new Playlist_TrackPK(track.getId(), playlist.getId());
		this.track = track;
		this.playlist = playlist;
		this.trackNumber = trackNumber;
	}

// --------------------------Fields--------------------------
	
	@Id
	@Column(name="track_id", insertable=false, updatable=false)
	private int trackId;

	@Id
	@Column(name="playlist_id", insertable=false, updatable=false)
	private int playlistId;

	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="track_id", referencedColumnName="id")
	private Track track;

	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="playlist_id", referencedColumnName="id")
	private Playlist playlist;

	@Column(name="track_number")
	private int trackNumber;
	
//--------------------------Getters & Setters--------------------------

	public int getTrackTrackId() {
		return this.track.getId();
	}

	public int getPlaylistPlaylistId() {
		return this.playlist.getId();
	}
	
	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	
//--------------------------toString()--------------------------
	
	//In track: playlistTracks.size(), In playlist: playlistTracks.size()
	@Override
	public String toString() {
		return "Playlist_Track [track=" + track + ", playlist=" + playlist + ", trackNumber="
				+ trackNumber + "]";
	}

}

//Class Required for Composite Primary Key. Rules: 
// 1. It must have a default constructor without arguments.
// 2. It must implement the java.io.Serializable interface.
// 3. It must override the methods equals and hashCode.


@SuppressWarnings("serial")
class Playlist_TrackPK implements Serializable {
	

	private int trackId;
	private int playlistId;

	public Playlist_TrackPK () {
		// no-arg constructor
	}
	
	public Playlist_TrackPK(int trackId, int playlistId) {
		super();
		this.trackId = trackId;
		this.playlistId = playlistId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playlistId;
		result = prime * result + trackId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist_TrackPK other = (Playlist_TrackPK) obj;
		if (playlistId != other.playlistId)
			return false;
		if (trackId != other.trackId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Playlist_TrackPK [trackId=" + trackId + ", playlistId=" + playlistId + "]";
	}
	
	
}