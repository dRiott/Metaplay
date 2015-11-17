<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Record Labels</title>
   
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		
		<h1 class="dH1">Record Labels</h1>
		<table class="table table-hover browseTable">
			<tbody>
				<tr>
					<th>Name</th><th>Description</th><th>Location</th>
				</tr>
				<c:forEach items="${recordlabels}" var="recordlabel">
					<tr>
						<td><a href="<spring:url value="/browse/recordlabel/${recordlabel.id}"/>">${recordlabel.name}</a></td>
						<td><c:choose>
								<c:when test="${recordlabel.description!=null && !recordlabel.description.isEmpty()}">
									<span class="dSpan">${recordlabel.description}</span>
								</c:when>
								<c:otherwise>
									No description given yet.
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${recordlabel.location!=null}">
									<span class="dSpan"><a href="<spring:url value="/browse/location/${recordlabel.location.id}"/>">${recordlabel.location.city}, ${recordlabel.location.state}</a></span>
								</c:when>
								<c:otherwise>
									<td>No location assigned yet.</td>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>