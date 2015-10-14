<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">

		<div class="navbar-header">
			<a class="navbar-brand" href="<spring:url value="/"/>">
			<img class="imageLogo" src="<spring:url value="/resources/img/vinylDark.gif" />" id="logo"
 				alt="logoDark.gif" style=""></img>
 				<img class="imageLogoLayer" src="<spring:url value="/resources/img/vinyl.gif" />" id="logoLightLayer"
 				alt="logoLight.gif" style=""></img>
			</a>
		</div>

		<ul class="nav navbar-nav">

			<li class="dropdown" style="padding-left:145px;"><a> </a></li>
		<!-- Browse -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Browse <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/browse/accounts"/>">Accounts</a></li>
					<li><a href="<spring:url value="/browse/albums"/>">Albums</a></li>
					<li><a href="<spring:url value="/browse/artists"/>">Artists</a></li>
					<li><a href="<spring:url value="/browse/genres"/>">Genres</a></li>
					<li><a href="<spring:url value="/browse/locations"/>">Locations</a></li>
					<li><a href="<spring:url value="/browse/playlists"/>">Playlists</a></li>
					<li><a href="<spring:url value="/browse/recordlabels"/>">Record Labels</a></li>
					<li><a href="<spring:url value="/browse/tracks"/>">Tracks</a></li>
				</ul>
			</li>

			<!-- Artists -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Artists
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/artist/add"/>">Add</a></li>
					<li><a href="<spring:url value="/artist/find"/>">Find</a></li>
				</ul></li>

			<!-- Albums -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Albums
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/album/add"/>">Add</a></li>
					<li><a href="<spring:url value="/album/add"/>">Find</a></li>
				</ul></li>

			<!-- Tracks -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Tracks
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/track/add"/>">Add</a></li>
					<li><a href="<spring:url value="/track/find"/>">Find</a></li>
				</ul></li>

			<!-- Playlists -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Playlists
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/playlist/add"/>">Add</a></li>
					<li><a href="<spring:url value="/playlist/find"/>">Find</a></li>
				</ul></li>

			<!-- More -->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">More
				<span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/location/add"/>">Add Location</a></li>
					<li><a href="<spring:url value="/role/assign"/>">Assign Roles</a></li>
					<li><a href="<spring:url value="/payment/process"/>">Payment</a></li>
					<li><a href="<spring:url value="/mongo/upload"/>">Upload Mp3</a></li>
				</ul>
			</li>

			<!-- Login Page -->
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Account
					<span class="caret"></span>
				</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/account/add"/>">New User?
							Start here!</a></li>
					<li><a href="<spring:url value="/account/login"/>">Login Ya
							Noggin'</a></li>
					<li><a href="<spring:url value="/account/byebye"/>">I Go Bye
							Bye</a></li>
				</ul></li>
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
					<a href="${accountAddUrl}">Add A New Account</a>
				</p>
			</c:otherwise>
		</c:choose>

	</div>
</nav>

