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
@Table(name = "recordlabel")
public class RecordLabel {
	
	
// --------------------------Constructors--------------------------
	public RecordLabel() {
				
	}

	public RecordLabel(String recordLabelName, Location location) {
		this.name = recordLabelName;
		this.location = location;
	}

	// --------------------------Fields--------------------------	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "location_id", nullable=false)
	private Location location;

	@OneToMany(mappedBy = "recordLabel")
	private Collection<Album> albums;
	
//--------------------------Getters & Setters--------------------------	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public Collection<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Collection<Album> albums) {
		this.albums = albums;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//--------------------------Collection Adders and Removers--------------------------
	//adds an Artist to Collection<Artist>, removing it's RecordLabel, and setting to this.
	public void addAlbum(Album album) {
		if (getAlbums()!=null && !getAlbums().contains(album)) {
			getAlbums().add(album);
			if (album.getRecordLabel() != null) {
				album.getRecordLabel().getAlbums().remove(album);
			}
			album.setRecordLabel(this);
		}
	}
	
	//removes an Artist from Collection<Artist>, setting its RecordLabel to null.
	public void removeAlbum(Album album) {
		if(getAlbums()!=null && getAlbums().contains(album)) {
			getAlbums().remove(album);
			album.setRecordLabel(null);
		}
	}	

	
//--------------------------Collection Printers--------------------------

	public String getAlbumsToString () {
		if(getAlbums()!=null) {
		Iterator<Album> it = getAlbums().iterator();
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
		return "RecordLabel [id=" + id + ", name=" + name + ", location=" + getLocationToString() + ", description=" + getDescription() + ", albums=" + getAlbumsToString() + "]";
	}

}
