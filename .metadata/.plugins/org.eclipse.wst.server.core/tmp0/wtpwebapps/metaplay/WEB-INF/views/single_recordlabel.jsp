<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Record Label</title>

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
			
		<a href="<spring:url value="/browse/recordlabels"/>" class="btn btn-default">Back To Browse</a>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>