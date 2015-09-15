package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {
	
// --------------------------Constructors--------------------------
	public Member() {
	
	}

// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(mappedBy="members")
	private Collection<Artist> artists;

	@Column(name="first_name")
	private String firstName;

	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="stage_name")
	private String stageName;

//--------------------------Getters & Setters--------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Collection<Artist> artists) {
		this.artists = artists;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

// --------------------------Collection Adders and Removers--------------------------	

		//adds a Artist to Collection<Artist>
		public void addArtist(Artist artist) {
			if (!getArtists().contains(artist)) {
				getArtists().add(artist);
				if (!artist.getMembers().contains(this)) {
					artist.addMember(this);
				}
			}
		}
		
		// removes a Artist from Collection<Artist>.
		public void removeArtist(Artist artist) {
			if (getArtists().contains(artist)) {
				getArtists().remove(artist);
				if (artist.getMembers().contains(this)) {
					artist.removeMember(this);
				}
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
			} return artistsString;
		} return "No artists.";
	}			
//--------------------------toString()--------------------------
	
	//Members and Artists: @ManyToMany. B/c StackOverflowError --> Altered toString(): artists
	@Override
	public String toString() {
		return "Member [id=" + id + ", artists=" + this.getArtistsToString() + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", stageName=" + stageName + "]";
	}

}