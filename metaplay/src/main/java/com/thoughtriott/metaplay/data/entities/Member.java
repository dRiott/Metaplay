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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "member")
public class Member {
	
	// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany(mappedBy="members")
    @JsonBackReference
	private Collection<Artist> artists;

	@Column(name="first_name")
	private String firstName;

	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="stage_name")
	private String stageName;
	
// --------------------------Constructors--------------------------
		public Member() {
		}

		public Member(String lastName) {
			this.lastName = lastName;
		}
		
		public Member(String lastName, String firstName) {
			this.lastName = lastName;
			this.firstName = firstName;
		}
		
		public Member(String lastName, String firstName, String middleName) {
			this.lastName = lastName;
			this.firstName = firstName;
			this.middleName = middleName;
		}

//--------------------------Getters & Setters--------------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
			if (getArtists()!=null && !getArtists().contains(artist)) {
				getArtists().add(artist);
				if (!artist.getMembers().contains(this)) {
					artist.addMember(this);
				}
			}
		}
		
		// removes a Artist from Collection<Artist>.
		public void removeArtist(Artist artist) {
			if (getArtists()!=null && getArtists().contains(artist)) {
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
				Artist currentArtist = it.next();
				if(artistsString.length() > 1) {
				artistsString = artistsString + ", " + currentArtist.getName();
				} else if (!it.hasNext()) {
					artistsString = artistsString + ", " + currentArtist.getName() + "}";
				} else {
					artistsString = "Artists: {" + currentArtist.getName();
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
