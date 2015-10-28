package com.thoughtriott.metaplay.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Playlist;
import com.thoughtriott.metaplay.data.wrappers.CreatePlaylistWrapper;
import com.thoughtriott.metaplay.data.wrappers.RepositoryKeeper;


@Controller
@RequestMapping("/playlist")
public class PlaylistController extends RepositoryKeeper {

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addPlaylist(Model model){
		
		model.addAttribute("accounts", accountRepository.findAll());
		model.addAttribute("tracks", trackRepository.findAll());
		return "playlist_add";
	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Playlist playlist) {
		return "playlist_review";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public String savePlaylist(@RequestBody ObjectNode[] tracks, @AuthenticationPrincipal User activeUser){
		
		Account activeAccount = accountRepository.findAccountByAccountname(activeUser.getUsername()).get(0);

		
		System.out.println("in the right controller method...");
		System.out.println("Tracks length: " + tracks.length);
		
		for (ObjectNode track : tracks) {
			System.out.println(track);
			int trackId = track.get("trackId").asInt();
			int trackOrderNum = track.get("trackNumber").asInt();
			System.out.println("Track id: " + trackId + ", Track Number: " + trackOrderNum);
		}
		//"redirect:/playlist/add"
		return "success";
	}
	
	
	//playlist account search on playlist_add page, using jquery UI autocomplete.
	@RequestMapping(value="/accountsearch", method=RequestMethod.GET)
	public @ResponseBody String findAccount(@RequestParam("term") String term) {
		System.out.println("I've got this term: " + term);
		
		List<Account> accounts = accountRepository.findAccountByAccountnameLike(term+"%");

		List<String> accountNames = new ArrayList<String>(); 
		Iterator<Account> it = accounts.iterator();
		while(it.hasNext()) {
			accountNames.add(it.next().getAccountname());
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			String artistsStringified = mapper.writeValueAsString(accountNames);
			System.out.println("Stringified: " + artistsStringified);
			return artistsStringified;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "JSON Failed";
		}
	}
	
	@RequestMapping(value="/{playlistId}")
	public String findPlaylist(Model model, @PathVariable("playlistId") int playlistId) {
		model.addAttribute("playlist", playlistRepository.getOne(playlistId));
		return "single_playlist";
	}

	
	
	
	// ------------------------------ Model Attributes ------------------------------
	@ModelAttribute("createPlaylistWrapper")
	public CreatePlaylistWrapper getCreatePlaylistWrapper() {
		return new CreatePlaylistWrapper();
	}
	
	

}
