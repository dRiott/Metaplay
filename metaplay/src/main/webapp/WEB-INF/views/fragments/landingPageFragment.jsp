<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<nav class="navbar navbar-default">
	<div class="container-fluid drNavDiv">
	
		<!-- Logo -->		
		<div class="imageLogoDiv" >
			<a class="navbar-brand" href="<spring:url value="/"/>">
				<img class="imageLogo logo" src="<spring:url value="/resources/img/vinylDark.gif" />" id="logo"	alt="logoDark.gif" style="width:auto\9"/>
				<img class="imageLogoLayer logo" src="<spring:url value="/resources/img/vinyl.gif" />" id="logoLightLayer" alt="logoLight.gif" style="width:auto\9"/>
			</a>
		</div>

		<!-- BEGIN HEADER LINKS -->
		<div class="DRnavBarDiv">
			<ul class="nav navbar-nav" style="padding-top: 10px;">
	
				<!-- Search -->
				<li><a id="searchLink" href="<spring:url value="/search"/>">Search</a></li>

				<!-- Browse -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Browse <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<spring:url value="/browse/accounts"/>">Accounts</a></li>
						<li><a href="<spring:url value="/browse/albums"/>">Albums</a></li>
						<li><a href="<spring:url value="/browse/artists"/>">Artists</a></li>
						<li><a href="<spring:url value="/browse/genres"/>">Genres</a></li>
						<li><a href="<spring:url value="/browse/locations"/>">Locations</a></li>
						<li><a href="<spring:url value="/browse/members"/>">Members</a></li>
						<li><a href="<spring:url value="/browse/playlists"/>">Playlists</a></li>
						<li><a href="<spring:url value="/browse/recordlabels"/>">Record Labels</a></li>
						<li><a href="<spring:url value="/browse/tracks"/>">Tracks</a></li>
					</ul>
				</li>
	
				<!-- More -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Extras<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<spring:url value="/payment/process"/>">Payment</a></li>
						<li><a href="<spring:url value="/role/add"/>">Roles</a></li>
					</ul>
				</li>
				
				<!-- About -->
				<li><a href="<spring:url value="/about"/>">About</a></li>
				
			</ul>
		</div>
		
		<!-- Accountname / New Account, Logout -->
		<c:set var="authentication" value="${pageContext.request.userPrincipal}"/>
		<c:choose>
			<c:when test="${authentication!=null}">
				<c:set var="currentUser" value="${authentication.name}"/>
				<c:url var="logoutUrl" value="/logout"/>
				<!-- Form MUST BE A POST TO PROTECT AGAINST CROSS-SITE REQUEST FORGERY -->
				<form:form class="navbar-form pull-right" action="${logoutUrl}" method="post">
					<input type="submit" class="btn btn-default drHeaderButton" value="Logout" />
				</form:form>
				<p class="navbar-text pull-right">
					<c:out value="${currentUser}"/>
				</p>
			</c:when>
			<c:otherwise>
				<p class="navbar-text pull-right">
					<c:url value="/account/add" var="accountAddUrl"/>
					<c:url value="/account/login" var="loginUrl"/>
					<a href="${accountAddUrl}" class="btn btn-default drHeaderButton">New Account</a>
					<a href="${loginUrl}" class="btn btn-default drHeaderButton">Login</a>
				</p>
			</c:otherwise>
		</c:choose>
			
	</div>
</nav>