package com.thoughtriott.metaplay.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import java.util.Collection;
import java.util.Iterator;

@Entity
@Table(name = "member")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"unparsedName"})
public class Member extends MetaplayEntity {
	
// --------------------------Fields--------------------------
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
	
	@Column(name="entity_type")
	private String entityType = "member";

	@Transient
	private String unparsedName;
	
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
	
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	public String getUnparsedName() {
		return unparsedName;
	}

	public void setUnparsedName(String unparsedName) {
		this.unparsedName = unparsedName;
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
	
	//Members and Artists: @ManyToMany. B/c StackOverflowError --> Altered toString(): artists
	@Override
	public String toString() {
		return "Member [id=" + id + ", artists=" + getArtistsToString() + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", stageName=" + stageName + "]";
	}

}
