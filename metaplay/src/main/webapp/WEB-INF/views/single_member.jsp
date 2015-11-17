<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Member</title>

	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body class="delayedReveal">

	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		<div class="row drRow">
			<h1>
				<em>Member: 
					<c:if test="${member.firstName!=null}">
						<c:out value="${member.firstName}"/>
					</c:if>
					<c:if test="${member.middleName!=null}">
						<c:out value="${member.middleName}"/>
					</c:if>
					${member.lastName}
				</em>
			</h1>

			<div class="form-group" style="clear:both;"></div>

			<div class="form-group">
				<label>Artists</label>
				<c:choose>
					<c:when test="${member.artists.size()==0 }">
						<c:out value="This member has no artists. Hmm..." /><span style="padding-left: 6px;"></span>
						<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Go To Add Artist Page</a>
					</c:when>
					<c:otherwise>
						<ul>
							<c:forEach items="${member.artists}" var="artist">
								<li><a href="<spring:url value="/browse/artist/${artist.id}"/>">${artist.name}</a></li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<br/><hr/>
				<a href="<spring:url value="/browse/accounts"/>" class="btn btn-default">Back To Browse</a>
			</div>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>