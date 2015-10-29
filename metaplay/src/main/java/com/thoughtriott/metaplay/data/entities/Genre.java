package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "genre")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"description"})
public class Genre extends MetaplayEntity {

// --------------------------Constructors--------------------------
	public Genre() {
	}	
	
	public Genre(String newGenreName, String newGenreDescription) {
		this.name = newGenreName;
		this.description = newGenreDescription;
	}

	// --------------------------Fields--------------------------
	@OneToMany(mappedBy = "genre")
    @JsonManagedReference
	private Collection<Artist> artists;

	private String description;
	
	@Column(name="entity_type")
	private String entityType = "genre";


//--------------------------Getters & Setters--------------------------
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	//--------------------------Collection Adders and Removers--------------------------
	//adds an Artist to Collection<Artist>, removing it's Genre, and setting to this.
	public void addArtist(Artist artist) {
		if (getArtists()!=null && !getArtists().contains(artist)) {
			getArtists().add(artist);
			if (artist.getGenre() != null) {
				artist.getGenre().getArtists().remove(artist);
			}
			artist.setGenre(this);
		}
	}
	
	//removes an Artist from Collection<Artist>, setting its Genre to null.
	public void removeArtist(Artist artist) {
		if(getArtists()!=null && getArtists().contains(artist)) {
			getArtists().remove(artist);
			artist.setGenre(null);
		}
	}
	
//--------------------------Collection Printers--------------------------

	public String getArtistsToString () {
		if(artists!=null) {
			Iterator<Artist> it = artists.iterator();
			String artistsString = "";
			int size = artists.size();
			while(it.hasNext()) {
				Artist currentArtist = it.next();
				if (size==0) {
					artistsString = "No artists.";
				} else if(size==1) {
					artistsString = "{" + currentArtist.getName() + "}";
				} else {
					if(artistsString.length() == 0) {
						artistsString = "{" + currentArtist.getName();
					} else if (!it.hasNext()) {
						artistsString = artistsString + ", " + currentArtist.getName() + "}";
					} else {
						artistsString = artistsString + ", " + currentArtist.getName();
					}
				}
			} return artistsString;
		} return "Artists list was null.";
	}

//--------------------------toString()--------------------------

	//In artist: genre.getName()
	@Override
	public String toString() {
		return "Genre [id=" + id + ", artists=" + getArtistsToString() + ", name=" + name + ", description=" + description + "]";
	}

}
