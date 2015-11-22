package com.thoughtriott.metaplay.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
import com.thoughtriott.metaplay.data.entities.Playlist_Track;
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
    
    @RequestMapping(value="/edit/{playlistId}", method=RequestMethod.GET)
    public String edotPlaylist(Model model, @PathVariable("playlistId") int playlistId){
    	model.addAttribute("accounts", accountRepository.findAll());
    	model.addAttribute("tracks", trackRepository.findAll());
    	model.addAttribute("playlist", playlistRepository.findOne(playlistId));
    	return "playlist_add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String savePlaylist(@RequestBody ObjectNode[] playlistInfo, @AuthenticationPrincipal User activeUser){
        String playlistName = "";
        String playlistDescription = "";
        Playlist playlistToEdit = null;
        Playlist savedPlaylist = null;
        List<Integer> accountIds = new ArrayList<>(); //holds the account ids received via JSON
        SortedMap<Integer, Integer> trackIds = new TreeMap<>();  //holds the track ids and track_numbers received via JSON

        //iterate through the json object and add the info to java variables: playlistName, playlistDescription, accountIds, trackIds
        for (ObjectNode info : playlistInfo) {
            if(info.has("name")) {
                playlistName = info.get("name").asText();
                playlistDescription = info.get("description").asText();
                
                //if there is a playlist to edit, look up its id (if not, id will == -1)
                int playlistId = Integer.parseInt(info.get("id").asText());
                if(playlistId!=-1) {
                	playlistToEdit = playlistRepository.findOne(playlistId);
                }
            } else if(info.has("trackId")){
                trackIds.put(info.get("trackId").asInt(), info.get("trackNumber").asInt());
            } else if (info.has("id")) {
                accountIds.add(info.get("id").asInt());
            }
        }

        // Determine if the user is creating a new playlist or updating one.
        if(playlistToEdit==null) { //creating a new playlist,
        	savedPlaylist = playlistRepository.saveAndFlush(new Playlist(playlistName, playlistDescription));
        	
        	//add all the Playlist_Track relationships because this is a new playlist.
        	if(trackIds.size() > 0) {
                for (Entry<Integer, Integer> entry : trackIds.entrySet()) {
                    playlistTrackRepository.saveAndFlush(new Playlist_Track(entry.getKey(), savedPlaylist.getId(), entry.getValue()));
                }
            }
        	
        	// adding current logged in account to the playlist, only happens when creating a new playlist
            Account activeAccount = accountRepository.findAccountByAccountname(activeUser.getUsername()).get(0);
            accountRepository.saveAndFlush(activeAccount.addPlaylist(savedPlaylist));

        } else { //playlistToEdit != null, updating it.
        	playlistToEdit.setName(playlistName);
        	playlistToEdit.setDescription(playlistDescription);
        	savedPlaylist = playlistRepository.saveAndFlush(playlistToEdit);
        	
        	if(trackIds.size() > 0) {
                for (Entry<Integer, Integer> entry : trackIds.entrySet()) { //for each track in the playlist...
                	//look to see if the track is already on the playlist
                	List<Playlist_Track> pts = playlistTrackRepository.findPlaylist_TrackByTrackIdAndPlaylistId(entry.getKey(),  savedPlaylist.getId());
                	if(!pts.isEmpty()) {
                		//we found a Playlist_Track for this playlist, update its track_number, it's order in the playlist may have changed.
                		playlistTrackRepository.saveAndFlush(pts.get(0).setTrackNumber(entry.getValue()));
                	} else {
                		//Playlist_Track didn't exist, add it as a new relationship.
                        playlistTrackRepository.saveAndFlush(new Playlist_Track(entry.getKey(), savedPlaylist.getId(), entry.getValue()));
                	}
                } //end iteration through each track submitted to the playlist.
            } //end track setting
        } //end updating an existing playlist's tracks.

        //adding the selected friend accounts to the playlist
        if(accountIds.size() > 0) {
            for(int accountId : accountIds) {
                Account friendAccount = accountRepository.findOne(accountId);
                if(!friendAccount.containsPlaylist(savedPlaylist.getId())){ //add only if they don't already belong to the playlist.
                	accountRepository.saveAndFlush(friendAccount.addPlaylist(savedPlaylist));
                }
            }
        }
        
        try { //write the savedPlaylist to send back to the AJAX call
            return new ObjectMapper().writeValueAsString(savedPlaylist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    } //end savePlaylist()

    
    //deletes the playlist!
    @RequestMapping(value="/delete", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String deletePlaylist(@RequestParam("id") int id) {
    	System.out.println(id);
    	playlistRepository.delete(id);
    	
    	try {
			return new ObjectMapper().writeValueAsString("Success");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    //playlist account search for accountnames in the jquery UI autocomplete.
    @RequestMapping(value="/accountsearch", method=RequestMethod.GET)
    public @ResponseBody String findAccount(@RequestParam("term") String term) {
        List<Account> accounts = accountRepository.findAccountByAccountnameLike(term+"%");

        try {
            return new ObjectMapper().writeValueAsString(accounts);
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
