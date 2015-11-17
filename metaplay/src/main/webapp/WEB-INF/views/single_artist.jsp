<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Artist</title>

	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/showHideTextarea.js"/>"></script>

</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		<div class="row drRow">

			<h1 class="dH1">Artist: ${artist.name}</h1>
			
			<div class="form-group" style="clear:both;"></div>
			
			<div class="row">
				<div class="col-md-8">
					<label>Biography</label><br/>
					<c:set var="bioLength" value="${artist.biography.length()}"/>
					<span>${fn:substring(artist.biography, 0, 300)}</span><!-- 
					 --><span style="display:none;" id="hiddenBiography">${fn:substring(artist.biography, 300, bioLength)}</span>
					<button id="showButton" class="btn btn-default">More</button>
					<button id="hideButton" class="btn btn-default" style="display:none">Less</button>
				</div>
			</div>
		
			<div class="form-group" style="clear:both;"></div>
			
			<div class="form-group">
				<label>Genre</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${artist.genre!=null}">
							<a href="<spring:url value="/browse/genre/${artist.genre.id}"/>">${artist.genre.name}</a>
						</c:when>
						<c:otherwise>
							<span>No genre assigned yet. They're probably Christian Death Metal, my best guess.</span>
						</c:otherwise>
					</c:choose>
				</div>	
			</div>
			<div class="form-group">
				<label>Location</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${artist.location!=null}">
							<c:choose>
								<c:when test="${artist.location.country.equals('United States')}">
									<a href="<spring:url value="/browse/location/${artist.location.id}"/>">${artist.location.city}, ${artist.location.state}</a>
								</c:when>
								<c:otherwise>
									<a href="<spring:url value="/browse/location/${artist.location.id}"/>">${artist.location.city}, ${artist.location.country}</a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
								No location assigned yet.
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group">
				<label>Members</label>
				<div>
					<c:set var="membersSize" value="${artist.members.size()}"/>
					<c:if test="${membersSize==0 }">
						<c:out value="No members have been added." />
					</c:if>
					<c:forEach items="${artist.members}" var="member" varStatus="count">
						<c:choose>
							<c:when test="${member.lastName!=null}">
								<c:if test="${member.firstName!=null}">${member.firstName}</c:if>
								<c:if test="${member.middleName!=null}">${member.middleName}</c:if>
								<c:if test="${member.lastName!=null}">${member.lastName}</c:if>
								<c:if test="${member.stageName!=null}">- Stage Name: ${member.stageName}</c:if>
								<c:if test="${membersSize != count.index+1}"><br/></c:if>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="form-group">
				<label>Albums</label>
				<div class="form-group">
					<c:choose>
							<c:when test="${artist.albums.size()==0 }">
									<c:out value="This artist has no albums. Hmm..." /><span class="dSpan"></span>
									<a href="<spring:url value="/album/add"/>" class="btn btn-default">Go To Add Album Page</a>
							</c:when>
						
						<c:otherwise>
							<ul>
								<c:forEach items="${artist.albums}" var="album">
									<li>
										<div class="form-group" >
											<img src="/image/retrieve?foldername=album&filename=${album.name}" style="max-width: 100px;height:auto;"
											alt="Image not found" onerror="this.onerror=null; this.src='<spring:url value='/resources/img/default.gif'/>'"/>
										</div>
										<a href="<spring:url value="/browse/album/${album.id}"/>">${album.name}</a>
										<c:choose>
											<c:when test="${album.releaseDate!=null}">
												<span class="dSpan">(<fmt:formatDate type="date" dateStyle="long" value="${album.releaseDate}"/>)</span>
											</c:when>
											<c:otherwise>
												<span class="dSpan">Unknown Date, Probably Midnight, Thursday, 1 January 1970, not counting leap seconds.</span>				
											</c:otherwise>
										</c:choose>
									</li>
								</c:forEach>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group">
				<label>Image</label>
				<div class="form-group" >
					<img src="/image/retrieve?foldername=artist&filename=${artist.name}" style="max-width: 500px;height:auto;"
					alt="Image not found" onerror="this.onerror=null; this.src='<spring:url value='/resources/img/default.gif'/>'"/>
				</div>
			</div>
		
			<div class="form-group">
				<br/><hr/><hr/>
				<a href="<spring:url value="/browse/artists"/>" class="btn btn-default">Back To Browse</a>
			</div>
		
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>