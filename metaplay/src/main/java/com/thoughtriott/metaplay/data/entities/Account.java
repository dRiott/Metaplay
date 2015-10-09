package com.thoughtriott.metaplay.data.entities;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "account")
public class Account {

// --------------------------Constructors--------------------------
	public Account() {
				
	}	
	
// --------------------------Fields--------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany
	@JoinTable(name="account_role", 
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "role_id", referencedColumnName="id"))
	private List<Role> roles;

	@ManyToMany
	@JoinTable(name="playlist_account", 
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "playlist_id", referencedColumnName="id"))
	private List<Playlist> playlists;
	
	private String accountname;
	private String password;
	private String email;
	private boolean enabled;
	
	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;

//--------------------------Getters & Setters--------------------------
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	//--------------------------Collection Adders and Removers--------------------------
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
	
	public Account addPlaylist(Playlist playlist) {
		if (getPlaylists()!=null && !getPlaylists().contains(playlist)) {
			getPlaylists().add(playlist);
			if (!playlist.getAccounts().contains(this)) {
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
		}return "No roles";
	}
	
//--------------------------toString()--------------------------

	//Roles and Accounts: @ManyToMany. B/c StackOverflowError --> Altered toString(): roles, playlists
	@Override
	public String toString() {
		return "Account [id=" + id + ", roles= " + getRolesToString() + ", playlists=" + getPlaylistsToString() + ", accountname=" + accountname
				+ ", password=" + password + ", registrationDate=" + registrationDate + ", email=" + email + "]";
	}

}
