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
@Table(name = "genre")
public class Genre {

// --------------------------Constructors--------------------------
	public Genre() {
				
	}	
	
// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "genre")
	private Collection<Artist> artists;

	private String name;
	private String description;

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

	public Collection<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Collection<Artist> artists) {
		this.artists = artists;
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

//--------------------------toString()--------------------------

	//In artist: genre.getName()
	@Override
	public String toString() {
		return "Genre [id=" + id + ", artists=" + this.getArtistsToString() + ", name=" + name + ", description=" + description + "]";
	}

}
