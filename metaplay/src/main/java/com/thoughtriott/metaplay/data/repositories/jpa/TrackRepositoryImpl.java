package com.thoughtriott.metaplay.data.repositories.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtriott.metaplay.data.entities.Track;

public class TrackRepositoryImpl implements TrackRepositoryCustom {

	@Autowired
	TrackRepository trackRepository;

	// returns List<String> of each Track name
	@Override
	public List<String> findAllToListString() {
		List<Track> trackList = trackRepository.findAll();
		Iterator<Track> it = trackList.iterator();
		List<String> trackStringList = new ArrayList<String>();
		while (it.hasNext()) {
			Track track = it.next();
			trackStringList.add(track.getName());
		}
		if (trackList.size() == 0) {
			System.out.println(
					"TrackRepositoryImpl: findAllToListString() - The List<String> trackStringList was empty, returning null");
			return null;
		} else {
			return trackStringList;
		}
	}

	// grabs all Tracks and formats them into a nicely presented String
	@Override
	public String findAllToFormattedString() {
		List<String> tracks = findAllToListString();
		Iterator<String> it = tracks.iterator();
		String fullList = "";
		while (it.hasNext()) {
			String track = it.next();
			fullList = fullList + track + "\n";
		}
		return fullList;
	}

}
