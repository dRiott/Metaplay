//package com.thoughtriott.metaplay.controllers;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.thoughtriott.metaplay.data.documents.AudioFile;
//import com.thoughtriott.metaplay.data.repositories.jpa.AlbumRepository;
//import com.thoughtriott.metaplay.data.repositories.jpa.ArtistRepository;
//import com.thoughtriott.metaplay.data.repositories.jpa.TrackRepository;
//import com.thoughtriott.metaplay.data.repositories.mongo.AudioFileRepository;
//import com.thoughtriott.metaplay.data.wrappers.RepositoryKeeper;
//import com.thoughtriott.metaplay.data.wrappers.UploadTrackWrapper;
//
//@Controller
//@RequestMapping("/mongo")
//public class MongoController extends RepositoryKeeper {
//
////	@Autowired
////	ArtistRepository artistRepository;
////	@Autowired
////	AlbumRepository albumRepository;
////	@Autowired
////	TrackRepository trackRepository;
////	@Autowired
////	AudioFileRepository afrepo;
//	
//	@RequestMapping(value="/upload", method=RequestMethod.GET)
//	public String getUploadPage(){
//		return "upload_mp3";
//	}
//	
//	@RequestMapping(value="/upload", method=RequestMethod.POST)
//	public String saveTrack(@ModelAttribute UploadTrackWrapper utw) {
//		System.out.println("Invoking the saveTrack() from MongoController.");
//		try {
//			byte[] bytes = utw.getMp3().getBytes();
//			afrepo.save(new AudioFile(utw.getId(), bytes));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "redirect:/mongo/upload";
//	}
//	
//	//for viewing that one that works... for now only!
//	@RequestMapping(value="/audio", method=RequestMethod.GET)
//	public String getAudio(){
//		return "audio";
//	}
//	
//	// ------------------------------ Model Attributes ------------------------------
//		@ModelAttribute("uploadTrackWrapper")
//		public UploadTrackWrapper getUploadTrackWrapper() {
//			return new UploadTrackWrapper();
//		}
//}