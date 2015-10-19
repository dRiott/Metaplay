package com.thoughtriott.metaplay.data.wrappers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.repositories.jpa.AccountRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.AlbumRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.ArtistRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.GenreRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.LocationRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.MemberRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.PlaylistRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.RecordLabelRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.RoleRepository;
import com.thoughtriott.metaplay.data.repositories.jpa.TrackRepository;
//import com.thoughtriott.metaplay.data.repositories.mongo.AudioFileRepository;
import com.thoughtriott.metaplay.utilities.DateFormatter;

public abstract class RepositoryKeeper {

	@Autowired
	protected AccountRepository accountRepository;
	@Autowired
	protected AlbumRepository albumRepository;
	@Autowired
	protected ArtistRepository artistRepository;
	@Autowired
	protected GenreRepository genreRepository;
	@Autowired
	protected LocationRepository locationRepository;
	@Autowired
	protected MemberRepository memberRepository;
	@Autowired
	protected PlaylistRepository playlistRepository;
	@Autowired
	protected RecordLabelRepository recordLabelRepository;
	@Autowired
	protected RoleRepository roleRepository;
	@Autowired
	protected TrackRepository trackRepository;
	@Autowired
	protected DateFormatter dateFormatter;
//	@Autowired
//	protected AudioFileRepository afrepo;
	
	@PersistenceContext
	protected EntityManager em;
	
}
