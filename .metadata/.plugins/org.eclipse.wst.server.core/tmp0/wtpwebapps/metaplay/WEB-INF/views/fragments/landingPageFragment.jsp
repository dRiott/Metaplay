<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<nav class="navbar navbar-default">
	<div class="container-fluid">

		<div class="navbar-header">
			<a class="navbar-brand" href="<spring:url value="/"/>">
			<img class="image" src="<spring:url value="/resources/img/vinylDark2.gif" />" id="logo"
 				alt="logoDark.gif" style=""></img>
 				<img class="imageLayer" src="<spring:url value="/resources/img/vinyl.gif" />" id="logoLightLayer"
 				alt="logoLight.gif" style=""></img>
			</a>
		</div>

		<ul class="nav navbar-nav">
			<li class="dropdown" style="padding-left:145px;"><a> </a></li>

<!-- Login -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Account
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/account/add"/>">New User?
							Start here!</a></li>
					<li><a href="<spring:url value="/account/login"/>">Login
							Ya Noggin'</a></li>
				</ul></li>

<!-- Browse -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Browse <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/browse/accounts"/>">Accounts</a></li>
					<li><a href="<spring:url value="/browse/albums"/>">Albums</a></li>
					<li><a href="<spring:url value="/browse/artists"/>">Artists</a></li>
					<li><a href="<spring:url value="/browse/locations"/>">Locations</a></li>
					<li><a href="<spring:url value="/browse/playlists"/>">Playlists</a></li>
					<li><a href="<spring:url value="/browse/recordlabels"/>">Record Labels</a></li>
					<li><a href="<spring:url value="/browse/tracks"/>">Tracks</a></li>
				</ul>
			</li>

<!-- More -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">More<span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/payment/process"/>">Payment</a></li>
					<li><a href="<spring:url value="/role/add"/>">Roles</a></li>
					
				</ul>
			</li>
		</ul>
		
		<c:set var="authentication" value="${pageContext.request.userPrincipal}"/>
		<c:choose>
			<c:when test="${authentication!=null}">
				<c:set var="currentUser" value="${authentication.name}"/>
				<c:url var="logoutUrl" value="/logout"/>
				<!-- Form MUST BE A POST TO PROTECT AGAINST CROSS-SITE REQUEST FORGERY -->
				<form:form class="navbar-form pull-right" action="${logoutUrl}" method="post">
					<input type="submit" class="btn btn-default" value="Logout" />
				</form:form>
				<p class="navbar-text pull-right">
					<c:out value="${currentUser}"/>
				</p>
			</c:when>
			<c:otherwise>
				<p class="navbar-text pull-right">
					<c:url value="/account/add" var="accountAddUrl"/>
					<c:url value="/account/login" var="loginUrl"/>
					<a href="${accountAddUrl}" class="btn btn-default">New Account</a>
					<a href="${loginUrl}" class="btn btn-default">Login</a>
				</p>
			</c:otherwise>
		</c:choose>
		
	</div>
</nav>