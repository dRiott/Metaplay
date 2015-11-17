<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Genres</title>
  
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/showHideTextarea.js"/>"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		
		<h1 class="dH1">Genres</h1>
		<table class="table table-hover browseTable" id="tableWithLongText">
			<tbody>
				<tr>
					<th>Name</th><th>Description</th>
				</tr>
				<c:forEach items="${genres}" var="genre" varStatus="count">
					<c:set var="index" value="${count.index+1}"/>
					<tr>
						<c:set var="bioLength" value="${genre.description.length()}"/>
						<td><a href="<spring:url value="/browse/genre/${genre.id}"/>">${genre.name}</a></td>
						<td id="containsBio${index}">${fn:substring(genre.description, 0, 300)}<span style="display:none;" id="moreLessBiography${index}">${fn:substring(genre.description, 300, bioLength)}</span></td>
						<td>
							<button id="showButton${index}" class="btn btn-default">More</button>
							<button id="hideButton${index}" class="btn btn-default" style="display:none">Less</button>
						</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>