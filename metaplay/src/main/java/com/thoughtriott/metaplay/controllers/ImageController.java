package com.thoughtriott.metaplay.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.thoughtriott.metaplay.data.wrappers.AmazonService;

@Controller
@RequestMapping("/image")
public class ImageController extends AmazonService {

	//Utility method for getting an image from S3, see: http://www.jets3t.org/toolkit/code-samples.html#downloading
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	private void getImageFromService(@RequestParam("foldername") String folderName, @RequestParam("filename") String fileName, HttpServletResponse response) {
		try {
			getImage(folderName, fileName, response);
		} catch (Exception e) {
			throw new AmazonS3Exception(e.getMessage());
		}
	}
	
}