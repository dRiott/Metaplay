<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metaplay &copy Members</title>

	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>

</head>
<body>
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container">
		
		<h1>Members</h1>
		<table class="table table-hover" style="width:40%">
			<tbody>
				<tr>
					<th>Member Name</th><th>Artists</th>
				</tr>
				<c:forEach items="${members}" var="member">
					<tr>
						<td>
							<a href="<spring:url value="/browse/member/${member.id}"/>">
								<c:if test="${member.firstName!=null}">
									<c:out value="${member.firstName}"/>
								</c:if>
								<c:if test="${member.middleName!=null}">
									<c:out value="${member.middleName}"/>
								</c:if>
								${member.lastName}
							</a>
						</td>
						<td>
							<c:forEach items="${member.artists}" var="artist">
								<a href="<spring:url value="/browse/artist/${artist.id}"/>">${artist.name}</a>
							</c:forEach>
						</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>