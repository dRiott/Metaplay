package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {
	
// --------------------------Constructors--------------------------
	public Artist() {
					
	}


// --------------------------Fields--------------------------	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable=false)
	private Genre genre;
	
	@ManyToOne
	@JoinColumn(name = "recordlabel_id", nullable=false)
	private RecordLabel recordLabel;

	@ManyToOne
	@JoinColumn(name = "location_id", nullable=false)
	private Location location;
	
	@ManyToMany
	@JoinTable(name="artist_member", 
		joinColumns = @JoinColumn(name = "artist_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "member_id", referencedColumnName="id"))
	private Collection<Member> members;
	
	@OneToMany(mappedBy="artist")
	private Collection<Album> albums;
	
	private String name;
	
	private String biography;
	
	@Lob
	@Column(name="artist_image")
	private String artistImage;
	
//--------------------------Getters & Setters--------------------------	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public RecordLabel getRecordLabel() {
		return recordLabel;
	}

	public void setRecordLabel(RecordLabel recordLabel) {
		this.recordLabel = recordLabel;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Collection<Member> getMembers() {
		return members;
	}

	public void setMembers(Collection<Member> members) {
		this.members = members;
	}
	
	public Collection<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Collection<Album> albums) {
		this.albums = albums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getArtistImage() {
		return artistImage;
	}

	public void setArtistImage(String artistImage) {
		this.artistImage = artistImage;
	}
	
// --------------------------Collection Adders and Removers--------------------------	

	//adds a Member to Collection<Member>
	public void addMember(Member member) {
		
		if (getMembers()!=null && !getMembers().contains(member)) {
			getMembers().add(member);
			if (!member.getArtists().contains(this)) {
				member.addArtist(this);
			}
		}
	}
	
	// removes a Member from Collection<Member>, setting its Artist_Member to null.
	public void removeMember(Member member) {
		if (getMembers()!=null && getMembers().contains(member)) {
			getMembers().remove(member);
			if (member.getArtists().contains(this)) {
				member.removeArtist(this);
			}
		}
	}
	
	// adds an Album to Collection<Album>, removing it's Artist, and setting to this.
	public void addAlbum(Album artist) {
		if (getAlbums()!=null && !getAlbums().contains(artist)) {
			getAlbums().add(artist);
			if (artist.getArtist() != null) {
				artist.getArtist().getAlbums().remove(artist);
			}
			artist.setArtist(this);
		}
	}

	// removes an Album from Collection<Album>, setting its Artist to null.
	public void removeAlbum(Album artist) {
		if (getAlbums()!=null && getAlbums().contains(artist)) {
			getAlbums().remove(artist);
			artist.setArtist(null);
		}
	}
	
//--------------------------Collection Printers--------------------------

	public String getMembersToString () {
		if(getMembers()!=null) {
		Iterator<Member> it = getMembers().iterator();
		String membersString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(membersString.length() > 1) {
			membersString = membersString + ", " + it.next().getFirstName() + " " + it.next().getLastName();
			} else if (!it.hasNext()) {
				membersString = membersString + ", " + it.next().getFirstName() + " " + it.next().getLastName() + "}";
			} else {
				membersString = "Members: {" + it.next().getFirstName() + " " + it.next().getLastName();
			}
		}
		return membersString;
		} return "No members.";
	}

	public String getAlbumsToString () {
		if(getAlbums()!=null) {
		Iterator<Album> it = getAlbums().iterator();
		String albumsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(albumsString.length() > 1) {
				albumsString = albumsString + ", " + it.next().getName();
			} else if (!it.hasNext()) {
				albumsString = albumsString + ", " + it.next().getName() + "}";
			} else {
				albumsString = "Albums: {" + it.next().getName();
			}
		}
		return albumsString; 
		} return "No albums";
	}
	
	public String getGenreToString () {
		if(genre!=null) {
			return genre.toString();
		}
		return "Genre is null.";
	}
	
	public String getRecordLabelToString () {
		if(recordLabel!=null) {
			return recordLabel.toString();
		}
		return "recordLabel is null.";
	}
	
	public String getLocationToString () {
		if(location!=null) {
			return location.toString();
		}
		return "Location is null.";
	}
	
	

//--------------------------toString()--------------------------
	
	//In Member: artists.size(), In Album: artist.getName()
	//Altered toString(): recordLabel, location
	@Override
	public String toString() {
		
		return "Artist [id=" + id + ", genre=" + this.getGenreToString() + ", recordLabel=" + this.getRecordLabelToString() + ", location=" + this.getLocationToString()
				+ ", members=" + this.getMembersToString() + ", albums=" + this.getAlbumsToString() + ", name=" + name + ", biography=" + biography
				+ ", artistImage=" + artistImage + "]";
		}
	}
