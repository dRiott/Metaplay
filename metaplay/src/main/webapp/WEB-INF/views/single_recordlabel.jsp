<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Record Label</title>

	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		<div class="row drRow">
			<h1 class="dH1">Record Label: ${recordlabel.name}</h1>

			<div class="form-group">
				<label>Description</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${recordlabel.description!=null && !recordlabel.description.isEmpty()}">
							<span class="dSpan">${recordlabel.description}</span>
						</c:when>
						<c:otherwise>
							No description given yet.
						</c:otherwise>
					</c:choose>
				</div>
			</div>
	
			<div class="form-group">
				<label>Location</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${recordlabel.location!=null}">
							<span><a href="<spring:url value="/browse/location/${recordlabel.location.id}"/>">${recordlabel.location.city}, ${recordlabel.location.state}</a></span>
						</c:when>
						<c:otherwise>
							<td>No location assigned yet.</td>
						</c:otherwise>
					</c:choose>
				</div>	
			</div>
			
			<div class="form-group">
				<label>Albums</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${recordlabel.albums.size()==0 }">
							<c:out value="This record label has no albums. Hmm..." /><span class="dSpan"></span>
							<a href="<spring:url value="/album/add"/>" class="btn btn-default">Go To Add Album Page</a>
						</c:when>
						<c:otherwise>
							<ul>
								<c:forEach items="${recordlabel.albums}" var="album">
									<li><a href="<spring:url value="/browse/album/${album.id}"/>">${album.name}</a></li>
								</c:forEach>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>	
			</div>
		
		
			<div class="form-group">
				<br/><hr/>
				<a href="<spring:url value="/browse/recordlabels"/>" class="btn btn-default">Back To Browse</a>
			</div>	

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>