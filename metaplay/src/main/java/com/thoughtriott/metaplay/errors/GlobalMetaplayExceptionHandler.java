package com.thoughtriott.metaplay.errors;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalMetaplayExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";
	private static final Logger logger = Logger.getLogger(GlobalMetaplayExceptionHandler.class);

	@ExceptionHandler(MetaplayNotFoundException.class)
	public String metaplayNotFoundHandler() {
		return DEFAULT_ERROR_VIEW;
	}
	
	//SQLException.class
	// DataAccessException.class
	@ExceptionHandler({ PersistenceException.class, TransactionSystemException.class})
	public String databaseError(HttpServletRequest req, Exception exception, HttpSession session) {
		 logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		session.setAttribute("url", req.getRequestURL());
		session.setAttribute("exception", exception);
		return "error_integrityViolation";
	}
	
	@ExceptionHandler({ArrayIndexOutOfBoundsException.class, NullPointerException.class})
	public String coderSucksError(HttpServletRequest req, Exception exception, HttpSession session) {
		 logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		session.setAttribute("url", req.getRequestURL());
		session.setAttribute("exception", exception);
		System.out.println("The exception type is: " + exception.getMessage());
		System.out.println("Stack trace: " + exception.getStackTrace());
		return "error_CoderStillLearning";
	}
	
	@ExceptionHandler({NestedServletException.class})
	public String servletException(HttpServletRequest req, Exception exception, HttpSession session) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		session.setAttribute("url", req.getRequestURL());
		session.setAttribute("exception", exception);
		return "error_CoderStillLearning";
	}
	
	@ExceptionHandler({BeanCreationException.class})
	public String servletExceptionBeanCreation(HttpServletRequest req, Exception exception, HttpSession session) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		session.setAttribute("url", req.getRequestURL());
		session.setAttribute("exception", exception);
		return "error_CoderStillLearning";
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	public String servletExceptionIllegalArgument(HttpServletRequest req, Exception exception, HttpSession session) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		session.setAttribute("url", req.getRequestURL());
		session.setAttribute("exception", exception);
		return "error_CoderStillLearning";
	}
	
	@ExceptionHandler({AmazonS3Exception.class})
	public String amazonException(HttpServletRequest req, Exception exception, HttpSession session) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception
				+ "\n QUERY STRING: " + req.getQueryString());
		session.setAttribute("url", req.getRequestURL());
		session.setAttribute("exception", exception);
		return "error_CoderStillLearning";
	}

	@ExceptionHandler({CsrfException.class})
	public String badCsrfToken(HttpServletRequest req, Exception exception, HttpSession session) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		session.setAttribute("url", req.getRequestURL());
		session.setAttribute("exception", exception);
		return "error_Security";
	}
	

	@ExceptionHandler(MetaplayServerErrorException.class)
	public String metaplayServerErrorHandler() {
		return DEFAULT_ERROR_VIEW;
	}
	
	@ExceptionHandler(MetaplayBadRequestException.class)
	public String metaplayBadRequestHandler() {
		return DEFAULT_ERROR_VIEW;
	}
	
	@ExceptionHandler(MetaplayUnsupportedMediaException.class)
	public String metaplayUnsupportedMediaHandler() {
		return DEFAULT_ERROR_VIEW;
	}
	
	@ExceptionHandler(MetaplayUnauthorizedException.class)
	public String metaplayUnauthorizedHandler() {
		return DEFAULT_ERROR_VIEW;
	}
}
