//package com.thoughtriott.metaplay.controllers;
//
//import java.util.List;
//import java.util.ListIterator;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.thoughtriott.metaplay.data.documents.AudioFile;
//import com.thoughtriott.metaplay.data.wrappers.RepositoryKeeper;
//
//@Controller
//@RequestMapping("/mongoaudio")
//public class MongoAudioController extends RepositoryKeeper {
//	
////	@Autowired
////	AudioFileRepository afrepo;
//
//	//Utility method for getting an image from S3, see: http://www.jets3t.org/toolkit/code-samples.html#downloading
//	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
//	private HttpServletResponse getAudioFromMongo(@RequestParam("id") String id, HttpServletResponse response) {
//		try {
//			//afrepo findOne wasn't working, using findAll() and iterating through...
//			AudioFile audioFile = null;
//			List<AudioFile> laf = afrepo.findAll();
//			if(laf!=null) {
//				ListIterator<AudioFile> audioIt = laf.listIterator();
//				while(audioIt.hasNext()) {
//					AudioFile listAudioFile = audioIt.next();
//					if(id.equals(listAudioFile.getId())) {
//						audioFile = listAudioFile;
//					}
//				}
//			}
//			if(audioFile!=null) {
//				System.out.println("AudioFile found, id: " + audioFile.getId());
//				byte[] bytes = audioFile.getMp3();
//				response.setContentType("audio/mpeg");
//				response.getOutputStream().write(bytes);
//				Thread.sleep(1000);
//				response.getOutputStream().close();
//			}
//			return response;
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}
//	
//}
