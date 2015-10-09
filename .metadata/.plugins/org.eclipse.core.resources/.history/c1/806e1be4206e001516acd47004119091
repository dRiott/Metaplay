<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Playlist Manager</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1>Playlist</h1>
		</div>

		<spring:url value="/playlist/review" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post" modelAttribute="playlist">

			<div class="row">

				<div class="form-group">
					<label for="playlist-id">Playlist ID</label>
					<form:input type="text" path="id" cssClass="form-control"
						id="playlist-id" />
				</div>
				
				
				<div class="form-group">
					<label for="playlist-name">Name</label>
					<form:input path="name" cssClass="form-control" id="playlist-name" />
				</div>

			<%-- 	<div class="form-group">
					<label for="playlist-type">Type</label>
					<form:select path="type" items="${typeOptions}" cssClass="selectpicker" />

				</div> --%>

				<div class="form-group">
					<label for="description">Description</label>
					<form:textarea path="description" cssClass="form-control"
						id="description" rows="3" />
				</div>
				

				<button type="submit" class="btn btn-default">Submit</button>
			</div>

		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>