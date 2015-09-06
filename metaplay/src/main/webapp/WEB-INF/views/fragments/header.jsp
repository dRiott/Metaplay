<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">

		<div class="navbar-header">
			<a class="navbar-brand" href="<spring:url value="/"/>">Playlist Management</a>
		</div>

		<ul class="nav navbar-nav">

			<li><a href="#">Home</a></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Artists
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/artist/add"/>">Add</a></li>
					<li><a href="<spring:url value="/artist/find"/>">Find</a></li>
				</ul></li>
				
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Albums
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/album/add"/>">Add</a></li>
					<li><a href="<spring:url value="/album/add"/>">Find</a></li>
				</ul></li>
				
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Tracks
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/track/add"/>">Add</a></li>
					<li><a href="<spring:url value="/track/find"/>">Find</a></li>
				</ul></li>
			
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">Playlists
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/playlist/add"/>">Add</a></li>
					<li><a href="<spring:url value="/playlist/find"/>">Find</a></li>
				</ul></li>
				
				<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">More
					<span class="caret"></span>
			</a>

				<ul class="dropdown-menu" role="menu">
					<li><a href="<spring:url value="/more/recordlabels"/>">Record Labels</a></li>
					<li><a href="<spring:url value="/more/groupmembers"/>">Group Members</a></li>
					<li><a href="<spring:url value="/more/locations"/>">Locations</a></li>
				</ul></li>

		</ul>

	</div>
</nav>