package com.thoughtriott.metaplay.data.entities;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "artist")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"artistImage", "biography"})
public class Artist extends MetaplayEntity {
	
	// --------------------------Constructors--------------------------
	public Artist() {
	}

	public Artist(String artistName) {
		this.name = artistName;
	}

	// --------------------------Fields--------------------------	
	@ManyToOne
	@JoinColumn(name = "genre_id", nullable=false)
    @JsonBackReference
	private Genre genre;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "location_id", nullable=false)
    @JsonBackReference
	private Location location;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="artist_member", 
		joinColumns = @JoinColumn(name = "artist_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "member_id", referencedColumnName="id"))
    @JsonManagedReference
	private List<Member> members = new LinkedList<Member>();
	
	@OneToMany(mappedBy="artist", cascade = CascadeType.PERSIST)
	@JsonManagedReference
	private List<Album> albums = new LinkedList<Album>();
	
	private String biography;
	
	@Lob
	@Column(name="artist_image")
	private byte[] artistImage;
	
	@Column(name="entity_type")
	private String entityType = "artist";

	//--------------------------Getters & Setters--------------------------	
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public byte[] getArtistImage() {
		return artistImage;
	}

	public void setArtistImage(byte[] artistImage) {
		this.artistImage = artistImage;
	}
	
	// --------------------------Collection Adders and Removers--------------------------	
	//adds a Member to Collection<Member>
	public void addMember(Member member) {
		System.out.println("In Arist addMember()");
		if (getMembers()!=null && !getMembers().contains(member)) {
			System.out.println("Made it past first condition (1/2): getMembers()!=null && !getMembers().contains(member)");
			getMembers().add(member);
			System.out.println("getMembers().add(member) called.");
			if (!member.getArtists().contains(this)) {
				System.out.println("Made it nested condition (Within 1): !member.getArtists().contains(this)");
				member.addArtist(this);
				System.out.println("Called member.addArtist(this)");
			}
		} else if(getMembers()==null) {
			System.out.println("Made it past second condition (2/2): getMembers()!=null && !getMembers().contains(member)");
			getMembers().add(member);
			System.out.println("getMembers().add(member) called.");
			if (!member.getArtists().contains(this)) {
				System.out.println("Made it nested condition (Within 2): !member.getArtists().contains(this)");
				member.addArtist(this);
				System.out.println("Called member.addArtist(this)");
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
	public void addAlbum(Album album) {
		if (getAlbums()!=null && !getAlbums().contains(album)) {
			getAlbums().add(album);
			if (album.getArtist() != null) {
				//cleanup: remove this album from it's old artist's list of albums
				album.getArtist().getAlbums().remove(album);
			}
			album.setArtist(this);
			System.out.println("Artist.addAlbum(): after calling album.setArtist(this), album.getArtist() is: " + album.getArtist());
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
		if(members!=null) {
			Iterator<Member> it = members.iterator();
			String membersString = "";
			int size = members.size();
			while(it.hasNext()) {
				Member currentMember = it.next();
				if (size==0) {
					membersString = "No members.";
				} else if(size==1) {
					membersString = "{" + currentMember.getName() + "}";
				} else {
					if(membersString.length() == 0) {
						membersString = "{" + currentMember.getName();
					} else if (!it.hasNext()) {
						membersString = membersString + ", " + currentMember.getName() + "}";
					} else {
						membersString = membersString + ", " + currentMember.getName();
					}
				}
			} return membersString;
		} return "Members list was null.";
	}
	
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
	
	public String getGenreToString () {
		if(genre!=null) {
			return genre.getName();
		}
		return "Genre is null.";
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
		return "Artist [id=" + id + ", genre=" + getGenreToString() + ", location=" + getLocationToString() + ", members=" 
			+ getMembersToString() + ", albums=" + getAlbumsToString() + ", name=" + name + ", biography=" + biography + "]";
	}
}
