package com.thoughtriott.metaplay.data.entities;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "album")
public class Album extends MetaplayEntity {

	// --------------------------Fields--------------------------
	private String description;

	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	@JsonBackReference
	private Artist artist;

	@OneToMany(mappedBy = "album")
	@JsonManagedReference
	private List<Track> tracks;
	
	@ManyToOne
	@JoinColumn(name = "recordlabel_id", nullable=false)
	@JsonBackReference
	private RecordLabel recordLabel;

	@Column(name = "num_tracks")
	private int numTracks;

	@Column(name = "release_date")
	@Temporal(TemporalType.DATE)
	private java.util.Date releaseDate;

	@Column(name="entity_type")
	private String entityType = "album";
	
	@Column(name = "length_seconds")
	private int length;
	
	@Lob
	@Column(name = "album_cover")
	private byte[] albumCover;
	
	
	// --------------------------Constructors--------------------------
	public Album() {
	}

	public Album(String name, String description) {
		this.name = name;
		this.description = description;
	}

	// used in testControllers --> BrowseControllerTest
	public Album(String name, String description, Artist artist, int numTracks, Date releaseDate, int length) {
		super();
		this.name = name;
		this.description = description;
		this.artist = artist;
		this.numTracks = numTracks;
		this.releaseDate = releaseDate;
		this.length = length;
	}

	// --------------------------Getters & Setters--------------------------
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

	public RecordLabel getRecordLabel() {
		return recordLabel;
	}

	public void setRecordLabel(RecordLabel recordLabel) {
		this.recordLabel = recordLabel;
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
		int length = (minutes * 60) + seconds;
		this.length = length;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	public byte[] getAlbumCover() {
		return albumCover;
	}

	public void setAlbumCover(byte[] albumCover) {
		this.albumCover = albumCover;
	}

	// --------------Collection Adders and Removers-------------
	// adds an Track to Collection<Track>, removing it's Album, setting to this.
	public void addTrack(Track track) {
		if (getTracks() != null && !getTracks().contains(track)) {
			getTracks().add(track);
			if (track.getAlbum() != null) {
				track.getAlbum().getTracks().remove(track);
			}
			track.setAlbum(this);
		}
	}

	// removes an Track from Collection<Track>, setting its Artist to null.
	public void removeTrack(Track track) {
		if (getTracks() != null && getTracks().contains(track)) {
			getTracks().remove(track);
			track.setAlbum(null);
		}
	}

	// ----------------Collection Printers----------------
	public String getTracksToString() {
		if (getTracks() != null) {
			Iterator<Track> it = getTracks().iterator();
			String tracksString = "";
			while (it.hasNext()) {
				// if-else prevents ", " from being appended the first time, appends } on the final time.
				Track currentTrack = it.next();
				if (tracksString.length() > 1) {
					tracksString = tracksString + ", " + currentTrack.getName();
				} else if (!it.hasNext()) {
					tracksString = tracksString + ", " + currentTrack.getName() + "}";
				} else {
					tracksString = "Tracks: {" + currentTrack.getName();
				}
			}
			return tracksString;
		}
		return "No tracks";
	}

	public String getArtistToString() {
		if (artist != null) {
			return artist.getName();
		}
		return "Artist is null.";
	}
	
	public String getRecordLabelToString () {
		if(recordLabel!=null) {
			return recordLabel.getName();
		}
		return "Record Label is null.";
	}

	// --------------------------toString()--------------------------
	// B/c StackOverflowError, altered this toString(): artist.getName()
	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", description=" + description + ", artist=" + getArtistToString()
				+ ", recordLabel=" + getRecordLabelToString() + ", tracks=" + getTracksToString() + ", numTracks=" + numTracks + ", releaseDate=" + releaseDate
				+ ", length=" + length + "]";
	}

}
