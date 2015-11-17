<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<nav class="navbar navbar-default">
	<div class="container-fluid">

<!-- Logo -->		
		<div class="imageLogoDiv" >
			<a class="navbar-brand" href="<spring:url value="/"/>">
				<img class="imageLogo logo" src="<spring:url value="/resources/img/vinylDark.gif" />" id="logo"	alt="logoDark.gif" style="width:auto\9"/>
				<img class="imageLogoLayer logo" src="<spring:url value="/resources/img/vinyl.gif" />" id="logoLightLayer" alt="logoLight.gif" style="width:auto\9"/>
			</a>
		</div>
		
		<div class="DRnavBarDiv">
			<ul class="nav navbar-nav" >
	
				<li class="dropdown" ><a> </a></li>
				
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
	
				<!-- Artists -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Add
						<span class="caret"></span>
				</a>
	
					<ul class="dropdown-menu" role="menu">
						<li><a href="<spring:url value="/artist/add"/>">Artist</a></li>
						<li><a href="<spring:url value="/album/add"/>">Album</a></li>
						<li><a href="<spring:url value="/location/add"/>">Location</a></li>
						<li><a href="<spring:url value="/playlist/add"/>">Playlist</a></li>					
						<li><a href="<spring:url value="/role/add"/>">Role</a></li>					
						<li><a href="<spring:url value="/track/add"/>">Track</a></li>
					</ul></li>

				<!-- More -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Extras
					<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<spring:url value="/role/assign"/>">Assign Roles</a></li>
						<li><a href="<spring:url value="/payment/process"/>">Payment</a></li>
						<li><a href="<spring:url value="/track/404"/>">4 Tha Drake Fanz</a></li>
						<li><a href="<spring:url value="/audio/upload"/>">Upload Mp3</a></li>
					</ul>
				</li>
				
				<!-- About -->
				<li><a href="<spring:url value="/about"/>">About</a></li>
			</ul>
		</div>
			
		<ul class="nav navbar-nav pull-right">
			<c:set var="authentication" value="${pageContext.request.userPrincipal}"/>
			<c:choose>
				<c:when test="${authentication!=null}">
					<c:set var="currentUser" value="${authentication.name}"/>
					
					<!-- PASSING THE ACCOUNTNAME TO JAVASCRIPT FOR AJAX CALL TO GET ACCOUNT -->
					<input id="accountname" type="hidden" value="<c:out value="${currentUser}"/>"/>
				
					<c:url var="logoutUrl" value="/logout"/>
					<!-- Form MUST BE A POST TO PROTECT AGAINST CROSS-SITE REQUEST FORGERY -->
					<form:form class="navbar-form pull-right" action="${logoutUrl}" method="post">
						<input type="submit" class="btn btn-default" value="Logout" />
					</form:form>
						<li class="dropdown"><a href="#" class="dropdown-toggle" id="accountnameOut"
						data-toggle="dropdown" role="button" aria-expanded="false"><c:out value="${currentUser}"/>
						<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<!-- THIS A HREF IS SET MY JAVASCRIPT AJAX CALL -->
								<li id="profileLI"><a id="targetLink" href="#">Profile</a></li>	
								<li><a href="<spring:url value="/logout"/>">I Go Bye Bye</a></li>	
							</ul>		
						</li>
				</c:when>
				<c:otherwise>
					<p class="navbar-text pull-right">
						<c:url value="/account/add" var="accountAddUrl"/>
						<a href="${accountAddUrl}">Add A New Account</a>
					</p>
				</c:otherwise>
			</c:choose>
		</ul>

	</div>
</nav>

<script src="<spring:url value="/resources/js/ajaxAccountnameHeader.js"/>"></script>