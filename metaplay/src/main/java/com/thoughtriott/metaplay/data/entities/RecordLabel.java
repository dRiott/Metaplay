package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "recordlabel")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"description"})
public class RecordLabel extends MetaplayEntity {
	
	
// --------------------------Constructors--------------------------
	public RecordLabel() {
	}

	public RecordLabel(String recordLabelName, Location location) {
		this.name = recordLabelName;
		this.location = location;
	}
	
	// --------------------------Fields--------------------------	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "location_id", nullable=false)
	@JsonBackReference
	private Location location;

	@OneToMany(mappedBy = "recordLabel")
	@JsonManagedReference
	private Collection<Album> albums;
	
	@Column(name="entity_type")
	private String entityType = "recordlabel";
	
//--------------------------Getters & Setters--------------------------	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
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

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
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
		if(albums!=null) {
			Iterator<Album> it = albums.iterator();
			String albumsString = "";
			int size = albums.size();
			while(it.hasNext()) {
				Album currentAlbum = it.next();
				if (size==0) {
					albumsString = "No albums.";
				} else if(size==1) {
					albumsString = "{" + currentAlbum.getName() + "}";
				} else {
					if(albumsString.length() == 0) {
						albumsString = "{" + currentAlbum.getName();
					} else if (!it.hasNext()) {
						albumsString = albumsString + ", " + currentAlbum.getName() + "}";
					} else {
						albumsString = albumsString + ", " + currentAlbum.getName();
					}
				}
			} return albumsString;
		} return "Albums list was null.";
	}
	
	public String getLocationToString () {
		if(location!=null) {
			return location.getName();
		}
		return "Location is null.";
	}
	
//--------------------------toString()--------------------------
	
	@Override
	public String toString() {
		return "RecordLabel [id=" + id + ", name=" + name + ", location=" + getLocationToString() + ", description=" + description + ", albums=" + getAlbumsToString() + "]";
	}

}
