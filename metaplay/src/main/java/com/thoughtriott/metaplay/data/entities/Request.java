package com.thoughtriott.metaplay.data.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "request")
public class Request extends MetaplayEntity {
	
	//constructor
	public Request() {
	}
	
	//fields
	private String request;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable=false)
	@JsonBackReference
	private Account account;
	
	// access methods
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	//toString
	@Override
	public String toString() {
		return "Request [account=" + account + ", request=" + request + "]";
	}
	
}
