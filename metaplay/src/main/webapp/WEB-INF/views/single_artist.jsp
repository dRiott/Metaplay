<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Artist</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>

	<div class="container">
		<div class="row">

			<div class="form-group">
				<label for="project-name">Name</label> <span>${artist.name}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Biography</label> <span>${artist.biography}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Genre</label> <span>${artist.genre}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Record Label</label> <span>${artist.recordlabel}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Location</label> <span>${artist.location}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Members</label> 
				<div>
					<c:if test="${artist.members.size()==0 }">
						<c:out value="This location has no record labels." />
					</c:if>
					<ul>
						<c:forEach items="${artist.members}" var="member">
							<li>${member}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="form-group">
				<label for="project-name">Albums</label>
				<div>
					<c:if test="${artist.albums.size()==0 }">
						<c:out value="This artist has no albums. Hmm..." />
					</c:if>
					<ul>
						<c:forEach items="${artist.albums}" var="album">
							<li>${album}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="form-group">
				<label for="project-name">Image</label> <span>${artist.artistImage}</span>
			</div>
		
		<a href="<spring:url value="/browse/artists"/>" class="btn btn-default">Back To Browse</a>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>