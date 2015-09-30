<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Location</title>

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
				<label for="project-name">City</label> <span>${location.city}</span>
			</div>

			<div class="form-group">
				<label for="project-name">State</label> <span>${location.state}</span>
			</div>
			
			<div class="form-group">
				<label for="project-name">Record Labels</label> 
				<div>
					<c:if test="${location.recordLabels.size()==0 }">
						<c:out value="This location has no record labels." />
					</c:if>
					<ul>
						<c:forEach items="${location.recordLabels}" var="recordLabel">
							<li>${recordLabel}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="form-group">
				<label for="project-name">Artists</label>
				<div>
					<c:if test="${location.artists.size()==0 }">
						<c:out value="This location has no artists." />
					</c:if>
					<ul>
						<c:forEach items="${location.artists}" var="artist">
							<li>${artist}</li>
						</c:forEach>
					</ul>
				</div>
			</div>

		<a href="<spring:url value="/browse/locations"/>" class="btn btn-default">Back To Browse</a>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>