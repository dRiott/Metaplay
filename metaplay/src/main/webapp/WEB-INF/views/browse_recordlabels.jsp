<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metaplay &copy Record Labels</title>

	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container">
		
		<h1>Record Labels</h1>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>Name</th><th>Description</th><th>Location</th>
				</tr>
				<c:forEach items="${recordlabels}" var="recordlabel">
					<tr>
						<td><a href="<spring:url value="/browse/recordlabel/${recordlabel.id}"/>">${recordlabel.name}</a></td>
						<td><c:choose>
								<c:when test="${recordlabel.description!=null && !recordlabel.description.isEmpty()}">
									<span>${recordlabel.description}</span>
								</c:when>
								<c:otherwise>
									No description given yet.
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${recordlabel.location!=null}">
									<span><a href="<spring:url value="/browse/location/${recordlabel.location.id}"/>">${recordlabel.location.city}, ${recordlabel.location.state}</a></span>
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
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>