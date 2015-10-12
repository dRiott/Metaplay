package com.thoughtriott.metaplay.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.jets3t.service.S3Service;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.jets3t.service.security.ProviderCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.thoughtriott.metaplay.data.entities.Account;
import com.thoughtriott.metaplay.data.entities.Role;
import com.thoughtriott.metaplay.data.repositories.AccountRepository;
import com.thoughtriott.metaplay.data.repositories.RoleRepository;
import com.thoughtriott.metaplay.data.wrappers.CreateAccountWrapper;

@Controller
@RequestMapping("/account")
@SessionAttributes(value = { "createAccountWrapper, loginStatus, counter" })
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	RoleRepository roleRepository;
	
	private static final String BUCKETNAME = "metaplaypictures";
	private static final String AWS_KEY = "AKIAIBWGLPRG2FMCGDKA";
	private static final String AWS_SECRET_KEY = "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";

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAccount() {
		return "account_add";
	}

	@RequestMapping(value = "/save")
	public String saveAccount(@ModelAttribute CreateAccountWrapper caw) {
		System.out.println("AccountController: saveAccount() - invoking saveAcount");
		Account newAccount = new Account();
		newAccount.setRegistrationDate(new Date());
		newAccount.setEnabled(true);
		newAccount.setAccountname(caw.getAccountname());
		newAccount.setEmail(caw.getEmail());
		newAccount.setPassword(caw.getPassword());
		saveImage(caw.getProfilePicture(), caw.getAccountname());
		Account savedAccount = accountRepository.saveAndFlush(newAccount);
		Role role = roleRepository.findRoleOrderByName("Lurker").get(0);
		savedAccount.addRole(role);
		accountRepository.saveAndFlush(savedAccount);
		return "redirect:/account/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(HttpSession session) {
		if (session.getAttribute("loginStatus") == null) {
			int counter = 0;
			session.setAttribute("counter", counter);
		}
		System.out.println("AccountController: getLoginPage() - SessionAttribute \"loginStatus\": " + session.getAttribute("loginStatus"));
		return "account_login";
	}
	
	//Redirects so that when spring handles the login stuff, you can still end up at a @PathVariable jsp page.
	@RequestMapping("/profile")
	public String getUserAndRedirect(@AuthenticationPrincipal User activeUser, Model model) {
		Account activeAccount = accountRepository.findAccountByAccountname(activeUser.getUsername()).get(0);
		model.addAttribute("accountId", activeAccount.getId());
		return "redirect:/account/{accountId}";
	}
	
	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public String showProfile(@PathVariable Integer accountId, Model model, @AuthenticationPrincipal User activeUser) {
		int activeId = accountRepository.findAccountByAccountname(activeUser.getUsername()).get(0).getId();
		if(accountId != activeId) {
			//need to throw an error here because a user tried to access a profile page that was not there own.
			model.addAttribute("accountId", activeId);
			return "redirect:/account/{accountId}";
		} else {
		Account account = accountRepository.getOne(accountId);
		List<Role> rolesList = account.getRoles();
		model.addAttribute("roles", rolesList);
		model.addAttribute(account);
		return "account_profile";
		}
	}
	
	//Utility method for uploading an image to S3, see: http://www.jets3t.org/toolkit/code-samples.html#downloading
	@RequestMapping(value = "/saveimage")
	private void saveImage(MultipartFile image, String accountname) {
		try {
			AWSCredentials awsCredentials = new AWSCredentials(AWS_KEY, AWS_SECRET_KEY);	
			S3Service s3 = new RestS3Service((ProviderCredentials) awsCredentials);
			S3Bucket bucket = s3.getBucket(BUCKETNAME);
			
			S3Object imageObject = new S3Object(accountname);
			imageObject.setDataInputStream(image.getInputStream());
			imageObject.setContentLength(image.getSize());
			imageObject.setContentType(image.getContentType());
			System.out.println("Content type: " + image.getContentType());
			AccessControlList acl = new AccessControlList();
			acl.setOwner(bucket.getOwner());
			acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
			imageObject.setAcl(acl);
			s3.putObject(bucket, imageObject);
	
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new AmazonS3Exception(e.getMessage());
		}
	}
	
	//Utility method for getting an image from S3, see: http://www.jets3t.org/toolkit/code-samples.html#downloading
	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	private void getImage(@RequestParam("accountId") Integer accountId, HttpServletResponse response, HttpServletRequest request) {
		try {
			String accountname = accountRepository.findOne(accountId).getAccountname();
			AWSCredentials awsCredentials = new AWSCredentials(AWS_KEY, AWS_SECRET_KEY);	
			S3Service s3 = new RestS3Service((ProviderCredentials) awsCredentials);
			S3Object objectComplete = s3.getObject(BUCKETNAME, accountname);

		    response.setContentType("image/jpeg, image/png, image/gif");
		    byte[] bytes = IOUtils.toByteArray(objectComplete.getDataInputStream());
			response.getOutputStream().write(bytes);
			response.getOutputStream().close();
		} catch (Exception e) {
			throw new AmazonS3Exception(e.getMessage());
		}
	}

	@ModelAttribute("createAccountWrapper")
	public CreateAccountWrapper getCAW() {
		return new CreateAccountWrapper();
	}

}

////////////////////////////////////////////////////

//verifying object has been completely downloaded
		/*S3Object downloadedObject = s3.getObject(testBucket, "helloWorld.txt");
		String textData = ServiceUtils.readInputStreamToString(
		downloadedObject.getDataInputStream(), "UTF-8");
		boolean valid = downloadedObject.verifyData(textData.getBytes("UTF-8"));
		System.out.println("Object verified? " + valid);*/

//alternative amazonS3 business
		/*AmazonS3Client s3client = new AmazonS3Client(awsCredentials);	
		String folderName = accountname;
		// upload file to folder and set it to public
		String fileName = folderName + SUFFIX + image.getOriginalFilename();
		S3Bucket bucket =s3client.getBucketLocation(BUCKETNAME);
		s3client.putObject(new PutObjectRequest(bucketName, fileName, 
				new File("C:\\Users\\user\\Desktop\\testvideo.mp4"))
				.withCannedAcl(CannedAccessControlList.PublicRead));
		*/

//creating an S3 folder to store pictures
	/*private static final String SUFFIX = "/";
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
					folderName + SUFFIX, emptyContent, metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}*/

/*@RequestMapping(value = "/save")
public String saveAccount(@ModelAttribute CreateAccountWrapper caw, SessionStatus status, HttpSession session, Model model) {
	System.out.println("AccountController: saveAccount() - invoking saveAcount");
	Account newAccount = (Account) session.getAttribute("account");
	newAccount.setRegistrationDate(new Date());
	newAccount.setEnabled(true);
	Account savedAccount = accountRepository.saveAndFlush(newAccount);
	Role role = roleRepository.findRoleOrderByName("Lurker").get(0);
	savedAccount.addRole(role);

	Account activeAccount = accountRepository.saveAndFlush(savedAccount);
	status.setComplete();
	model.addAttribute("accountId", activeAccount.getId());
	return "redirect:/account/{accountId}";
}*/

/*@RequestMapping(value="/login", method=RequestMethod.POST)
public String performLogin(@ModelAttribute Account accountToLogin, HttpSession session, WebRequest request, SessionStatus status, Model model){
	String loginAccountname =  accountToLogin.getAccountname();
	String loginPassword = accountToLogin.getPassword();
	System.out.println("AccountController: performLogin() - Accountname: " + loginAccountname);
	System.out.println("AccountController: performLogin() - Password: " + loginPassword);
	if(accountRepository.findAccountByAccountname(loginAccountname)!=null) {
		Account dbAccount = accountRepository.findAccountByAccountname(loginAccountname).get(0);
		String dbAccountPassword = dbAccount.getPassword();
		if(loginPassword.equals(dbAccountPassword)) {
			status.setComplete();
			request.removeAttribute("loginStatus", WebRequest.SCOPE_SESSION);
			System.out.println("AccountController: performLogin() - SessionAttribute \"loginStatus\": " + session.getAttribute("loginStatus"));
			model.addAttribute("accountId", dbAccount.getId());
			return "redirect:/account/{accountId}";
		} else {
			System.out.println("AccountController: performLogin() - login failed...");
			int counter = (int) session.getAttribute("counter");
			if(counter >= 3) 
				return "404";
			counter++;
			System.out.println(counter);
			String loginStatus = "fuckedUp";
			session.setAttribute("loginStatus", loginStatus);
			session.setAttribute("counter", counter);
			System.out.println("Counter in session: " + session.getAttribute("counter"));
			return "redirect:login";
		} 	
	} else return "redirect:login";
}*/
