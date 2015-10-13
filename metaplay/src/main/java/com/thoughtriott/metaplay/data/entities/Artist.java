package com.thoughtriott.metaplay.data.entities;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
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

	public Artist(String artistName) {
		this.name = artistName;
	}

	// --------------------------Fields--------------------------	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable=false)
	private Genre genre;
	

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "location_id", nullable=false)
	private Location location;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="artist_member", 
		joinColumns = @JoinColumn(name = "artist_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "member_id", referencedColumnName="id"))
	private List<Member> members = new LinkedList<Member>();
	
	@OneToMany(mappedBy="artist", cascade = CascadeType.PERSIST)
	private List<Album> albums = new LinkedList<Album>();
	
	private String name;
	
	private String biography;
	
	@Lob
	@Column(name="artist_image")
	private String artistImage;
	
//--------------------------Getters & Setters--------------------------	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
		if(getMembers()!=null) {
		Iterator<Member> it = getMembers().iterator();
		String membersString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			Member currentMember = it.next();
			if(membersString.length() > 1) {
			membersString = membersString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName();
			} else if (!it.hasNext()) {
				membersString = membersString + ", " + currentMember.getFirstName() + " " + currentMember.getLastName() + "}";
			} else {
				membersString = "Members: {" + currentMember.getFirstName() + " " + currentMember.getLastName();
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
			Album currentAlbum = it.next();
			if(albumsString.length() > 1) {
				albumsString = albumsString + ", " + currentAlbum.getName();
			} else if (!it.hasNext()) {
				albumsString = albumsString + ", " + currentAlbum.getName() + "}";
			} else {
				albumsString = "Albums: {" + currentAlbum.getName();
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
		
		return "Artist [id=" + id + ", genre=" + getGenreToString() + ", location=" + getLocationToString()
				+ ", members=" + getMembersToString() + ", albums=" + getAlbumsToString() + ", name=" + name + ", biography=" + biography
				+ ", artistImage=" + artistImage + "]";
		}
	}
