package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {
	
// --------------------------Constructors--------------------------
	public Location() {
			
	}

// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String city;
	private String state;

	@OneToMany(mappedBy = "location")
	private Collection<RecordLabel> recordLabels;

	@OneToMany(mappedBy = "location")
	private Collection<Artist> artists;

// --------------------------Getters & Setters--------------------------
	public Collection<RecordLabel> getRecordLabels() {
		return recordLabels;
	}

	public void setRecordLabels(Collection<RecordLabel> recordLabels) {
		this.recordLabels = recordLabels;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Collection<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Collection<Artist> artists) {
		this.artists = artists;
	}

// --------------------------Collection Adders and Removers--------------------------
	// adds a RecordLabel to Collection<RecordLabel>, removing it's Location, and setting to this.
	public void addRecordLabel(RecordLabel recordLabel) {
		if (getRecordLabels()!=null && !getRecordLabels().contains(recordLabel)) {
			getRecordLabels().add(recordLabel);
			if (recordLabel.getLocation() != null) {
				recordLabel.getLocation().getArtists().remove(recordLabel);
			}
			recordLabel.setLocation(this);
		}
	}

	// removes a RecordLabel from Collection<RecordLabel>, setting its Location to null.
	public void removeRecordLabel(RecordLabel recordLabel) {
		if (getRecordLabels()!=null && getRecordLabels().contains(recordLabel)) {
			getRecordLabels().remove(recordLabel);
			recordLabel.setLocation(null);
		}
	}

	// adds an Artist to Collection<Artist>, removing it's Location, and setting to this.
	public void addArtist(Artist artist) {
		if (!getArtists().contains(artist)) {
			getArtists().add(artist);
			if (artist.getLocation() != null) {
				artist.getLocation().getArtists().remove(artist);
			}
			artist.setLocation(this);
		}
	}

	// removes an Artist from Collection<Artist>, setting its Location to null.
	public void removeArtist(Artist artist) {
		if (getArtists().contains(artist)) {
			getArtists().remove(artist);
			artist.setLocation(null);
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
		}
		return "No artists.";
	}	
	
	public String getRecordLabelsToString () {
		if(getRecordLabels()!=null) {
		Iterator<RecordLabel> it = getRecordLabels().iterator();
		String recordLabelsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(recordLabelsString.length() > 1) {
				recordLabelsString = recordLabelsString + ", " + it.next().getName();
			} else if (!it.hasNext()) {
				recordLabelsString = recordLabelsString + ", " + it.next().getName() + "}";
			} else {
				recordLabelsString = "Record Labels: {" + it.next().getName();
			}
		}
		return recordLabelsString;
		}
		return "No record labels.";
	}	
	
// --------------------------toString()--------------------------
	
	//In RecordLabel: location.getCity(), In Artist: location.getCity()
	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + ", state=" + state + ", recordLabels=" + this.getRecordLabelsToString()
				+ ", artists=" + this.getArtistsToString() + "]";
	}

}
