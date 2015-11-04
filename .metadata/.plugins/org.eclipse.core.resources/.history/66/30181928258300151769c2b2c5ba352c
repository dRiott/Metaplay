<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metaplay &copy Artists</title>

	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
	<script src="<spring:url value="/resources/js/showHideTextarea.js"/>"></script>
	
	

</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container">
		
		<h1 class="dH1">Artists</h1>
		<table class="table table-hover" id="tableWithLongText">
			<tbody>
				<tr>
					<th>Name</th><th>Biography</th>
				</tr>
				<c:forEach items="${artists}" var="artist" varStatus="count">
				<c:set var="index" value="${count.index+1}"/>
					<tr>
						<c:set var="bioLength" value="${artist.biography.length()}"/>
						<td><a href="<spring:url value="/browse/artist/${artist.id}"/>">${artist.name}</a></td>
						<td id="containsBio${index}">${fn:substring(artist.biography, 0, 300)}<span id="moreLessBiography${index}" style="display:none;" >${fn:substring(artist.biography, 300, bioLength)}</span></td>
						<td>
							<button id="showButton${index}" class="btn btn-default">More</button>
							<button id="hideButton${index}" class="btn btn-default" style="display:none">Less</button>
						</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>

</html>