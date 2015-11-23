<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Account</title>
 
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

			<div class="form-group">
				<h1 class="dH1">Account: ${account.accountname}</h1>
				<div class="form-group">
					<img src="/image/retrieve?foldername=profilePictures&filename=${account.accountname}" style="max-width: 500px; height:auto;"
						alt="Image not found" id="avatarPic" onerror="this.onerror=null; this.src='<spring:url value='/resources/img/default.gif'/>'" width="144" height="103"/>
				</div>
			</div>

			<div class="form-group">
				<label>Email</label>
				<div class="form-group">
					<a class="mono" href="mailto:${account.email}">${account.email}</a>
				</div>
			</div>
			
			<div class="form-group">
				<label>Roles</label>
				<c:choose>
					<c:when test="${account.roles.size()==0 }">
						<div class="form-group">
							<c:out value="There are currently no roles assigned to ${account.accountname}."/>
						</div>
					</c:when>
					<c:otherwise>
						<table style="border: 2px solid">
							<tr>
								<th style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">Role Name</th>
								<th style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">Role Description</th>
							</tr>
							<c:forEach items="${account.roles}" var="role">
								<tr>
									<td style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">${role.name}</td>
									<td style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">${role.description}</td>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label>Playlists</label>
				<div class="form-group">
					<c:if test="${account.playlists.size()==0 }">
							<c:out value="This account has no playlists. Weird." />
					</c:if>
					<ul>
						<c:forEach items="${account.playlists}" var="playlist">
							<li><a href="<spring:url value="/browse/playlist/${playlist.id}"/>">${playlist.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="form-group">
				<label>Registration Date</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${account.registrationDate!=null}">
							<span class="dSpan"><fmt:formatDate type="date" dateStyle="long" value="${account.registrationDate}"/></span>
						</c:when>
						<c:otherwise>
							<span class="dSpan">No registration date has been added.</span>				
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="form-group">
				<br/>
				<br/>
				<a href="<spring:url value="/browse/accounts"/>" class="btn btn-default">Back To Browse</a>
			</div>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>