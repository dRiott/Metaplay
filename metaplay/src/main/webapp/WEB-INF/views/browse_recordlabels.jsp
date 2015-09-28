<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metaplay &copy Tracks</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>			

	<div class="container">
		
		<h2>Record Labels</h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>Name</th><th>Description</th><th>Location</th>
				</tr>
				<c:forEach items="${recordlabels}" var="recordlabel">
					<tr>
						<td><a href="<spring:url value="/browse/recordlabel/${recordlabel.id}"/>">${recordlabel.name}</a></td>
						<td>${recordlabel.description}</td>
						<td>${recordlabel.location}</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>