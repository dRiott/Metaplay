package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.thoughtriott.metaplay.data.entities.Playlist;
import com.thoughtriott.metaplay.data.wrappers.CreatePlaylistWrapper;
import com.thoughtriott.metaplay.data.wrappers.RepositoryKeeper;

@Controller
@RequestMapping("/playlist")
public class PlaylistController extends RepositoryKeeper {

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addPlaylist(){
		return "playlist_add";
	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Playlist playlist) {
		System.out.println("PlaylistController - review(): invoking");
		return "playlist_review";
	}

	@RequestMapping(value="/save")
	public String savePlaylist(@ModelAttribute Playlist playlist, SessionStatus status){
		System.out.println("PlaylistController - savePlaylist(): invoking");
		return "redirect:/playlist/add";
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
