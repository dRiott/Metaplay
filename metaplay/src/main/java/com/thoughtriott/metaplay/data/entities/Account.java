package com.thoughtriott.metaplay.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "account")
@JsonIgnoreProperties({"password", "enabled", "profilePicture", "avatar"})
public class Account extends MetaplayEntity {

	// --------------------------Constructors--------------------------
	public Account() {
	}	
	
	// --------------------------Fields--------------------------
	@ManyToMany
	@JoinTable(name="account_role", 
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "role_id", referencedColumnName="id"))
	private List<Role> roles;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="playlist_account", 
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "playlist_id", referencedColumnName="id"))
	private List<Playlist> playlists;
	
	private String accountname;
	
	private String password;

	private String email;

	private boolean enabled;

	@Column(name="entity_type")
	private String entityType = "account";
	
	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;
	
	@Lob
	@Column(name="profile_picture")
	private byte[] profilePicture;
	
	@Transient
	private MultipartFile avatar;
	
	@OneToMany(mappedBy = "account")
	@JsonManagedReference
	private Collection<Request> requests;
	
	//--------------------------Getters & Setters--------------------------
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

	//--------------------------Collection Adders and Removers--------------------------
	
	/* this method is used to check if Account.playlists contains a playlist with a given id.
	 * when updating a playlist, its other fields might change, and the regular collection.contains() method
	 * would return false, even though there are existing join table account_playlist relationships. */
	public boolean containsPlaylist(int playlistId) {
		for(Playlist p : playlists) {
			if (p.id == playlistId) {
				return true;
			} 
		}
		return false;
	}
	
	public Account addRole(Role role) {
		if (getRoles()!=null && !getRoles().contains(role)) {
			getRoles().add(role);
		}
		return this;
	}
	
	public Account removeRole(Role role) {
		List<Role> roles = getRoles();
		Iterator<Role> it = roles.iterator();
		Role targetedRole = null;
		while(it.hasNext()){
			Role nextRole = it.next();
			if(nextRole.getName().equals(role.getName())) {
				targetedRole = nextRole;
			}
		}
		if(targetedRole!=null){
			roles.remove(targetedRole);
		}
		return this;
	}
	
	public Account addRequest(Request request) {
		if (requests!=null && !requests.contains(request)) {
			requests.add(request);
		}
		return this;
	}
	
	public Account addPlaylist(Playlist playlist) {
		if (playlists!=null && !playlists.contains(playlist)) {
			playlists.add(playlist);
			if (playlist.getAccounts()!=null && playlist.getAccounts().contains(this)) {
				playlist.addAccount(this);
			}
		}
		return this;
	}
	
	public Account removePlaylist(Playlist playlist)  {
		List<Playlist> playlists = getPlaylists();
		Iterator<Playlist> it = playlists.iterator();
		Playlist targetedPlaylist = null;
		while(it.hasNext()){
			Playlist nextPlaylist = it.next();
			if(nextPlaylist.getName().equals(playlist.getName())) {
				targetedPlaylist = nextPlaylist;
			}
		}
		if(targetedPlaylist!=null){
			playlists.remove(targetedPlaylist);
		}
		return this;
	}
	
	//--------------------------Collection Printers--------------------------
	public String getRolesToString () {
		if(getRoles()!=null) {
		Iterator<Role> it = getRoles().iterator();
		String rolesString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(rolesString.length() > 1) {
			rolesString = rolesString + ", " + it.next().getName();
			} else if (!it.hasNext()) {
				rolesString = rolesString + ", " + it.next().getName() + "}";
			} else {
				rolesString = "Roles: {" + it.next().getName();
			}
		}
		return rolesString;
		}return "No roles";
	}
	
	public String getPlaylistsToString () {
		if(getPlaylists()!=null) {
		Iterator<Playlist> it = getPlaylists().iterator();
		String playlistsString = "";
		while(it.hasNext()) {
			//if-else prevents ", " from being appended the first time, appends } on the final time.
			if(playlistsString.length() > 1) {
			playlistsString = playlistsString + ", " + it.next().getName();
			} else if (!it.hasNext()) {
				playlistsString = playlistsString + ", " + it.next().getName() + "}";
			} else {
				playlistsString = "Playlists: {" + it.next().getName();
			}
		}
		return playlistsString;
		} return "No playlists";
	}
	
	public String getRequestsToString () {
		if(getRequests()!=null) {
			Iterator<Request> it = getRequests().iterator();
			String requestsString = "";
			while(it.hasNext()) {
				//if-else prevents ", " from being appended the first time, appends } on the final time.
				if(requestsString.length() > 1) {
					requestsString = requestsString + ", " + it.next().getRequest();
				} else if (!it.hasNext()) {
					requestsString = requestsString + ", " + it.next().getRequest() + "}";
				} else {
					requestsString = "Requests: {" + it.next().getRequest();
				}
			}
			return requestsString;
		} return "No requests";
	}
	
	//--------------------------toString()--------------------------
	//Roles and Accounts: @ManyToMany. B/c StackOverflowError --> Altered toString(): roles, playlists
	@Override
	public String toString() {
		return "Account [id=" + id + ", roles= " + getRolesToString() + ", playlists=" + getPlaylistsToString() + ", accountname=" + accountname
				+ ", password=" + password + ", registrationDate=" + registrationDate + ", email=" + email + ", requests=" + getRequestsToString() + "]";
	}

}
