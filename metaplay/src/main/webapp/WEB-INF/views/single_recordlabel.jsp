<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Record Label</title>

<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1 style="font-family: Times, serif;"><em>Record Label: ${recordlabel.name}</em></h1>

			<div class="form-group">
				<label for="singleRecordLabelDescription">Description</label>
				<c:choose>
					<c:when test="${recordlabel.description!=null && !recordlabel.description.isEmpty()}">
						<span>${recordlabel.description}</span>
					</c:when>
					<c:otherwise>
						No description given yet.
					</c:otherwise>
				</c:choose>
			</div>
	
			<div class="form-group">
				<label for="singleRecordLabelLocation">Location</label>
				<c:choose>
					<c:when test="${recordlabel.location!=null}">
						<span><a href="<spring:url value="/browse/location/${recordlabel.location.id}"/>">${recordlabel.location.city}, ${recordlabel.location.state}</a></span>
					</c:when>
					<c:otherwise>
						<td>No location assigned yet.</td>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="form-group">
				<label for="recordLabel-album">Albums</label>
				<c:choose>
					<c:when test="${recordlabel.albums.size()==0 }">
						<c:out value="This record label has no albums. Hmm..." /><span style="padding-left: 6px;"></span>
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
		
		
			<div class="form-group">
				<hr/>
				<a href="<spring:url value="/browse/recordlabels"/>" class="btn btn-default">Back To Browse</a>
			</div>	

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>