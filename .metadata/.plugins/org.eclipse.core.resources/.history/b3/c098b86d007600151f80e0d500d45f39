package com.thoughtriott.metaplay.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.thoughtriott.metaplay.data.wrappers.AmazonService;
import com.thoughtriott.metaplay.data.wrappers.UploadTrackWrapper;

@Controller
@RequestMapping("/audio")
public class AudioController extends AmazonService {

	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String getUploadPage(){
		return "upload_mp3";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String saveAudioFile(@ModelAttribute UploadTrackWrapper utw) {
		System.out.println("Invoking the saveTrack() from MongoController.");
		try {
			saveAudioFile(utw.getMp3(), utw.getId(), utw.getFilename());
		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		}
		return "redirect:/mongo/upload";
	}
	
	//for viewing that one that works... for now only!
	@RequestMapping(value="/S3audio", method=RequestMethod.GET)
	public String getAudio(){
		return "audio";
	}
	
	//Utility method for getting an audio file from S3, see: http://www.jets3t.org/toolkit/code-samples.html#downloading
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	private void getAudioFromService(@RequestParam("id") String id, @RequestParam("filename") String filename, HttpServletResponse response) {
		try {
			getAudioFile(id, filename, response);
		} catch (Exception e) {
			throw new AmazonS3Exception(e.getMessage());
		}
	}
	
	// ------------------------------ Model Attributes ------------------------------
			@ModelAttribute("uploadTrackWrapper")
			public UploadTrackWrapper getUploadTrackWrapper() {
				return new UploadTrackWrapper();
			}	
	
}
