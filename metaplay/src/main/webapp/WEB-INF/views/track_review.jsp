<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Track Manager</title>

    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon"/>
    <link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>

    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body class="delayedReveal">
    <jsp:include page="../views/fragments/headerSecurity.jsp"/>

    <div class="drContainer">
        <spring:url value="/track/save" var="thisURL"/>
        <form:form action="${thisURL}" method="post" modelAttribute="createTrackWrapper">

            <div class="row">
                <h2>Please Review the Track for Accuracy</h2>

                <div class="form-group">
                    <%--Spacing hack with span to get appearance on the same line.--%>
                    <label>* Track Number</label> <span
                        class="dSpan">${createTrackWrapper.trackNumber}</span>
                </div>
                <div class="form-group">
                    <label>* Name</label> <span class="dSpan">${createTrackWrapper.name }</span>
                </div>
                <div class="form-group">
                    <label>Artist</label>
                    <c:if test="${createTrackWrapper.artistFromList!='** New Artist **' }">
                        <c:out value="${createTrackWrapper.artistFromList}"/>
                    </c:if>
                    <c:if test="${createTrackWrapper.artistFromList=='** New Artist **' }">
                        <c:out value="${createTrackWrapper.theNewArtist}"/>
                    </c:if>
                </div>

                <div class="form-group">
                    <label>Album</label>
                    <c:if test="${createTrackWrapper.albumFromList!='** New Album **' }">
                        <c:out value="${createTrackWrapper.albumFromList}"/>
                    </c:if>
                    <c:if test="${createTrackWrapper.albumFromList=='** New Album **' }">
                        <c:out value="${createTrackWrapper.theNewAlbum}"/>
                    </c:if>
                </div>

                <%--Spacing hack with span to get appearance on the same line.--%>
                <div class="form-group">
                    <label>Album Cover</label> <span
                        class="dSpan">${createTrackWrapper.albumCover}</span>
                </div>
                <div class="form-group">
                    <label>* Length: Minutes</label> <span
                        class="dSpan">${createTrackWrapper.lengthMinutes}</span>
                </div>
                <div class="form-group">
                    <label>* Seconds</label> <span
                        class="dSpan">${createTrackWrapper.lengthSeconds}</span>
                </div>
                <div class="form-group">
                    <label>BPM</label> <span class="dSpan">${createTrackWrapper.bpm}</span>
                </div>
                <div class="form-group">
                    <label>Lyrics</label> <span class="dSpan">${createTrackWrapper.lyrics}</span>
                </div>

                <a href="<spring:url value="/track/add"/>" class="btn btn-default">Edit</a>
                <a href="<spring:url value="/track/save"/>" class="btn btn-default">Save</a>

            </div>
        </form:form>
    </div>
    <jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>