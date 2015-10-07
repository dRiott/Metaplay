<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">

		<div class="navbar-header">
			<a class="navbar-brand" href="<spring:url value="/"/>">MetaPlay
				&copy</a>
		</div>

		<ul class="nav navbar-nav">


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
					<li><a href="<spring:url value="/account/byebye"/>">I Go
							Bye Bye</a></li>
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
	</div>
</nav>