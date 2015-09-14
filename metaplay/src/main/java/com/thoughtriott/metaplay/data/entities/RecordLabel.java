package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recordlabe")
public class RecordLabel {
	
	
// --------------------------Constructors--------------------------
	public RecordLabel() {
				
	}

// --------------------------Fields--------------------------	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "location_id", nullable=false)
	private Location location;

	@OneToMany(mappedBy = "recordLabel")
	private Collection<Artist> artists;
	
//--------------------------Getters & Setters--------------------------	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Collection<Artist> artists) {
		this.artists = artists;
	}
	
//--------------------------Collection Adders and Removers--------------------------
	//adds an Artist to Collection<Artist>, removing it's RecordLabel, and setting to this.
	public void addArtist(Artist artist) {
		if (!getArtists().contains(artist)) {
			getArtists().add(artist);
			if (artist.getRecordLabel() != null) {
				artist.getRecordLabel().getArtists().remove(artist);
			}
			artist.setRecordLabel(this);
		}
	}
	
	//removes an Artist from Collection<Artist>, setting its RecordLabel to null.
	public void removeArtist(Artist artist) {
		if(getArtists().contains(artist)) {
			getArtists().remove(artist);
			artist.setRecordLabel(null);
		}
	}	

	
//--------------------------Collection Printers--------------------------

	public String getArtistsToString () {
		if(getArtists()!=null) {
		Iterator<Artist> it = getArtists().iterator();
		String artistsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(artistsString.length() > 1) {
			artistsString = artistsString + ", " + it.next().getName();
			} else if (!it.hasNext()) {
				artistsString = artistsString + ", " + it.next().getName() + "}";
			} else {
				artistsString = "Artists: {" + it.next().getName();
			}
		}
		return artistsString;
		} return "No artists.";
	}
	
	public String getLocationToString () {
		if(location!=null) {
			return location.toString();
		}
		return "Location is null.";
	}
	
//--------------------------toString()--------------------------
	
	//In Artist: recordLabel.getName()
	//B/c StackOverflowError, altered this toString(): location.getCity()
	@Override
	public String toString() {
		return "RecordLabel [id=" + id + ", name=" + name + ", location=" + getLocationToString() + ", artists=" + this.getArtistsToString() + "]";
	}

}
