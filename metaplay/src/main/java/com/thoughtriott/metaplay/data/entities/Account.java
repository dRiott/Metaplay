package com.thoughtriott.metaplay.data.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
	private int id;

	@ManyToMany
	@JoinTable(name="account_role", 
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "role_id", referencedColumnName="id"))
	private Collection<Role> roles;

	@ManyToMany
	@JoinTable(name="playlist_account", 
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName="id"),
		inverseJoinColumns= @JoinColumn(name = "playlist_id", referencedColumnName="id"))
	private Collection<Playlist> playlists;
	
	private String accountname;
	private String password;
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;

//--------------------------Getters & Setters--------------------------
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Collection<Playlist> playlists) {
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

	//--------------------------Collection Adders and Removers--------------------------
	//adds a Role to Collection<Role>
	public void addRole(Role role) {
		if (!getRoles().contains(role)) {
			getRoles().add(role);
//			if (!role.getAccounts().contains(this)) {
//				role.addAccount(this);
//			}
		}
	}
	
	// removes a Member from Collection<Member>.
	public void removeRole(Role role) {
		if (getRoles().contains(role)) {
			getRoles().remove(role);
			if (role.getAccounts().contains(this)) {
				role.removeAccount(this);
			}
		}
	}
	
	//adds a Role to Collection<Role>
	public void addPlaylist(Playlist playlist) {
		if (!getPlaylists().contains(playlist)) {
			getPlaylists().add(playlist);
			if (!playlist.getAccounts().contains(this)) {
				playlist.addAccount(this);
			}
		}
	}
	
	// removes a Playlist from Collection<Playlist>.
	public void removePlaylist(Playlist playlist) {
		if (getPlaylists().contains(playlist)) {
			getPlaylists().remove(playlist);
			if (playlist.getAccounts().contains(this)) {
				playlist.removeAccount(this);
			}
		}
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
		return "Account [id=" + id + ", roles= " + this.getRolesToString() + ", playlists=" + this.getPlaylistsToString() + ", accountname=" + accountname
				+ ", password=" + password + ", registrationDate=" + registrationDate + ", email=" + email + "]";
	}

}