<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Genre</title>

	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/showHideTextarea.js"/>"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		<div class="row drRow">

			<h1 class="dH1">Genre: ${genre.name}</h1>
			
			<div class="form-group" style="clear:both;"></div>
			
			<div class="row">
				<div class="col-md-8">
					<label>Description</label><br/>
					<c:set var="bioLength" value="${genre.description.length()}"/>
					<span>${fn:substring(genre.description, 0, 300)}</span><!-- 
					 --><span style="display:none;" id="hiddenBiography">${fn:substring(genre.description, 300, bioLength)}</span>
					<button id="showButton" class="btn btn-default">More</button>
					<button id="hideButton" class="btn btn-default" style="display:none">Less</button>
				</div>
			</div>
		
			<div class="form-group" style="clear:both;"></div>
			
			<div class="form-group">
				<label>Artists</label>
				
				<table class="table table-hover" style="width:60%;">
					<c:choose>
						<c:when test="${genre.artists.size()==0 }">
								<br/>
								<c:out value="This genre has no artists. Hmm..." />
								<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Go To Add Artist Page</a>
						</c:when>
						<c:otherwise>
							<c:forEach items="${genre.artists}" var="artist">
								<tr>
									<td><a href="<spring:url value="/browse/artist/${artist.id}"/>">${artist.name}</a></td>
								</tr>	
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		
			<div class="form-group">
				<br/><hr/>
				<a href="<spring:url value="/browse/genres"/>" class="btn btn-default">Back To Browse</a>
			</div>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>