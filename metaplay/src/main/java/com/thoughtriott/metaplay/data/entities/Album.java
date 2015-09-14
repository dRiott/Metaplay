package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "album")
public class Album {
	
// --------------------------Constructors--------------------------
	public Album() {
					
	}
	

// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String description;

	@ManyToOne
	@JoinColumn(name = "artist_id", nullable=false)
	private Artist artist;
	
	@OneToMany(mappedBy="album")
	private Collection<Track> tracks;
	
	@Column(name="num_tracks")
	private int numTracks;
	
	//album_cover BLOB!!
	
	@Column(name="release_date")
	@Temporal(TemporalType.DATE)
	private java.util.Date releaseDate;
	
	@Column(name="length_seconds")
	private int length;
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public int getNumTracks() {
		return numTracks;
	}

	public void setNumTracks(int numTracks) {
		this.numTracks = numTracks;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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
	
	public Collection<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Collection<Track> tracks) {
		this.tracks = tracks;
	}
	
//--------------------------Collection Adders and Removers--------------------------

	// adds an Track to Collection<Track>, removing it's Album, and setting to this.
	public void addTrack(Track track) {
		if (!getTracks().contains(track)) {
			getTracks().add(track);
			if (track.getAlbum() != null) {
				track.getAlbum().getTracks().remove(track);
			}
			track.setAlbum(this);
		}
	}

	// removes an Track from Collection<Track>, setting its Artist to null.
	public void removeTrack(Track track) {
		if (getTracks().contains(track)) {
			getTracks().remove(track);
			track.setAlbum(null);
		}
	}

//--------------------------Collection Printers--------------------------
	
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
		} return "No tracks";
	}
	
	public String getArtistToString () {
		if(artist!=null) {
			return artist.toString();
		}
		return "Artist is null.";
	}
	

//--------------------------toString()--------------------------
	
	//In Tracks: album.getName()
	//B/c StackOverflowError, altered this toString(): artist.getName()
	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", description=" + description + ", artist=" + getArtistToString()
				+ ", tracks=" + this.getTracksToString() + ", numTracks=" + numTracks + ", releaseDate=" + releaseDate + ", length="
				+ length + "]";
	}

	
}
