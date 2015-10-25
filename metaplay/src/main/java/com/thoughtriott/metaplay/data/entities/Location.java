package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "location")
public class Location extends MetaplayEntity {
	
	// --------------------------Constructors--------------------------
	public Location() {
	}
	
	public Location (String city, String state) {
		this.city = city;
		this.state = state;
	}
	
	public Location (String city, String state, String country) {
		this.city = city;
		this.state = state;
		this.country = country;
	}

	// --------------------------Fields--------------------------
	@NotNull
	@Size(min=3, max=40)
	private String city;
	@NotNull
	@Size(min=3, max=40)
	private String state;

	@OneToMany(mappedBy = "location")
	@JsonManagedReference
	private Collection<RecordLabel> recordLabels;

	@OneToMany(mappedBy = "location")
	@JsonManagedReference
	private Collection<Artist> artists;
	
	@Column(name="entity_type")
	private String entityType = "location";
	
	private String country;

	// --------------------------Getters & Setters--------------------------
	public Collection<RecordLabel> getRecordLabels() {
		return recordLabels;
	}

	public void setRecordLabels(Collection<RecordLabel> recordLabels) {
		this.recordLabels = recordLabels;
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

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
			Artist currentArtist = it.next();
			if(artistsString.length() > 1) {
			artistsString = artistsString + ", " + currentArtist.getName();
			} else if (!it.hasNext()) {
				artistsString = artistsString + ", " + currentArtist.getName() + "}";
			} else {
				artistsString = "Artists: {" + currentArtist.getName();
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
			RecordLabel currentRecordLabel = it.next();
			if(recordLabelsString.length() > 1) {
				recordLabelsString = recordLabelsString + ", " + currentRecordLabel.getName();
			} else if (!it.hasNext()) {
				recordLabelsString = recordLabelsString + ", " + currentRecordLabel.getName() + "}";
			} else {
				recordLabelsString = "Record Labels: {" + currentRecordLabel.getName();
			}
		}
		return recordLabelsString;
		}
		return "No record labels.";
	}	
	
	// --------------------------toString()--------------------------
	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + ", state=" + state + ", recordLabels=" + getRecordLabelsToString()
				+ ", artists=" + getArtistsToString() + "]";
	}
	
}
