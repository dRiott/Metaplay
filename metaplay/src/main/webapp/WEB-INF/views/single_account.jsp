<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Account</title>

<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">
		<div class="row">

			<div class="form-group">
				<label for="project-name">Account</label> <span>${account.accountname}</span>
			</div>

			<div class="form-group">
				<label for="project-name">Email</label> <span>${account.email}</span>
			</div>
			
			<div class="form-group">
				<label for="project-name">Roles</label>
				<c:choose>
					<c:when test="${roles.size()==0 }">
						<c:out value="There are currently no roles assigned to ${account.accountname}." />
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
				<label for="project-name">Playlists</label>
				<div>
					<c:if test="${account.playlists.size()==0 }">
							<c:out value="This account has no playlists. Weird." />
					</c:if>
					<ul>
						<c:forEach items="${account.playlists}" var="playlist">
							<li><a href="<spring:url value="/browse/playlist/${playlist.id}"/>">${playlist.name}</a></li>
							<li>
								<c:choose>
									<c:when test="${playlist.accounts.size()==0}">
										<span>This playlist has no accounts. Weird. /></span>
									</c:when>
									<c:otherwise>
										<c:forEach items="${playlist.accounts}" var="account" varStatus="count">	
											<c:choose>
												<c:when test="${count.index!=(playlist.accounts.size-1)}">
													<span><a href="<spring:url value="/browse/account/${account.accountname.id}"/>">${account.accountname}</a>,</span>
												</c:when>
												<c:otherwise>
													<span>${account.accountname}</span>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</li>
							<li>
								<c:choose>
									<c:when test="${playlist.description!=null}">
										<span>${playlist.description}</span>
									</c:when>
									<c:otherwise>
										<span>No description provided.</span>				
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="form-group">
				<label for="project-name">Registration Date</label>
				<c:choose>
					<c:when test="${account.registrationDate!=null}">
						<span><fmt:formatDate type="date" dateStyle="long" value="${account.registrationDate}"/></span>
					</c:when>
					<c:otherwise>
						<span>No registration date has been added.</span>				
					</c:otherwise>
				</c:choose>
			</div>

		<a href="<spring:url value="/browse/accounts"/>" class="btn btn-default">Back To Browse</a>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>