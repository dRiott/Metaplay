<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay &copy Albums</title>
	
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
</head>

<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container">
		
		<h1>Albums</h1>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>Name</th><th>Album Cover</th><th>Artist</th><th>Record Label</th><th>Description</th>
				</tr>
				<c:forEach items="${albums}" var="album">
					<tr>
						<td><a href="<spring:url value="/browse/album/${album.id}"/>">${album.name}</a></td>
						<td>
						<img src="/metaplay/image/retrieve?foldername=album&filename=${album.name}" style="max-width: 100px; height:auto;" alt="Image not found" 
						onerror="this.onerror=null; this.src='http://localhost:8080/metaplay/resources/img/default.gif'"/></td>
						<td><c:choose>
								<c:when test="${album.artist!=null}">
									<a href="<spring:url value="/browse/artist/${album.artist.id}"/>">${album.artist.name}</a>
								</c:when>
								<c:otherwise>
									No artist listed for this album.
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${album.recordLabel!=null}">
									<a href="<spring:url value="/browse/recordlabel/${album.recordLabel.id}"/>">${album.recordLabel.name}</a>
								</c:when>
								<c:otherwise>
									No record label assigned.
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${album.description!=null && !album.description.isEmpty()}">
									<span>${album.description}</span>
								</c:when>
								<c:otherwise>
									No description given.
								</c:otherwise>
							</c:choose>
						</td>
						
					</tr>	
				</c:forEach>
			</tbody>
		</table>

	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>