package com.thoughtriott.metaplay.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Playlist;
import com.thoughtriott.metaplay.data.entities.Playlist_Track;
import com.thoughtriott.metaplay.data.wrappers.CreatePlaylistWrapper;
import com.thoughtriott.metaplay.data.wrappers.RepositoryKeeper;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
@RequestMapping("/playlist")
public class PlaylistController extends RepositoryKeeper {

    @PersistenceContext
    EntityManager eman;

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addPlaylist(Model model){
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("tracks", trackRepository.findAll());
        return "playlist_add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String savePlaylist(@RequestBody ObjectNode[] playlistInfo, @AuthenticationPrincipal User activeUser){
        System.out.println("Line 42");

        String playlistName = "";
        String playlistDescription = "";
        List<Integer> accountIds = new ArrayList<>();
        SortedMap<Integer, Integer> trackIds = new TreeMap<>();

        //iterate throught the json object and add the info to java variables: playlistName, playlistDescription, accountIds, trackIds
        for (ObjectNode info : playlistInfo) {
            if(info.has("name")) {
                playlistName = info.get("name").asText();
                playlistDescription = info.get("description").asText();
            } else if(info.has("trackId")){
                trackIds.put(info.get("trackId").asInt(), info.get("trackNumber").asInt());
            } else if (info.has("id")) {
                accountIds.add(info.get("id").asInt());
            }
        }

        System.out.println("Line 59");
        //creating a new playlist
        Playlist savedPlaylist = playlistRepository.saveAndFlush(new Playlist(playlistName, playlistDescription));

        if(trackIds.size() > 0) {
            for (Entry<Integer, Integer> entry : trackIds.entrySet()) {
                playlistTrackRepository.saveAndFlush(new Playlist_Track(entry.getKey(), savedPlaylist.getId(), entry.getValue()));
            }
        }

        System.out.println("Line 71");

        // adding current logged in account to the playlist.
        Account activeAccount = accountRepository.findAccountByAccountname(activeUser.getUsername()).get(0);
        System.out.println(activeAccount.getAccountname());
        accountRepository.save(activeAccount.addPlaylist(savedPlaylist));

        //adding the selected friend accounts to the shared playlist.
        if(accountIds.size() > 0) {
            for(int accountId : accountIds) {
                Account friendAccount = accountRepository.findOne(accountId);
                accountRepository.save(friendAccount.addPlaylist(savedPlaylist));
            }
        }
        System.out.println("Line 84");


        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(savedPlaylist);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    //playlist account search on playlist_add page, using jquery UI autocomplete.
    @RequestMapping(value="/accountsearch", method=RequestMethod.GET)
    public @ResponseBody String findAccount(@RequestParam("term") String term) {
        List<Account> accounts = accountRepository.findAccountByAccountnameLike(term+"%");

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(accounts);
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

    // ------------------------------ Regular Methods ------------------------------
//
//	@Transactional
//	public void insertPlaylistTrack(int trackId, int playlistId, int trackNumber) {
//		Query query = eman.createQuery("INSERT INTO track_playlist (track_id, playlist_id, track_number) VALUES(?,?,?)");
//		query.setParameter(1, trackId);
//		query.setParameter(2, playlistId);
//		query.setParameter(3, trackNumber);
//		query.executeUpdate();
//	}

    // ------------------------------ Model Attributes ------------------------------
    @ModelAttribute("createPlaylistWrapper")
    public CreatePlaylistWrapper getCreatePlaylistWrapper() {
        return new CreatePlaylistWrapper();
    }
	

}
