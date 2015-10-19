<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Genre</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">
		<div class="row">

			<h1 style="font-family: Times, serif;"><em>Genre: ${genre.name}</em></h1>
			
			<div class="form-group" style="float:clear;"></div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="singleGenreDescription">Description</label><br/>
					<c:set var="bioLength" value="${genre.description.length()}"/>
					<span>${fn:substring(genre.description, 0, 300)}</span><!-- 
					 --><span style="display:none;" id="hiddenBiography">${fn:substring(genre.description, 300, bioLength)}</span>
					<button id="showButton" class="btn btn-default">More</button>
					<button id="hideButton" class="btn btn-default" style="display:none">Less</button>
				</div>
			</div>
		
			<div class="form-group" style="float:clear;"></div>
			
			<div class="form-group">
				<label for="singleGenreArtists">Artists</label>
				<table class="table table-hover" style="width:60%;">
					<c:forEach items="${genre.artists}" var="artist">
						<tr>
							<td><a href="<spring:url value="/browse/artist/${artist.id}"/>">${artist.name}</a></td>
						</tr>	
					</c:forEach>
				</table>
			</div>
		
		<a href="<spring:url value="/browse/genres"/>" class="btn btn-default">Back To Browse</a>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	<script src="<spring:url value="/resources/js/singleGenreDescriptionShowHide.js"/>"></script>
	<script> $(window).load(showHideDescription()); </script>
</body>
</html>