<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<jsp:include page="../fragments/header.jsp"/>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
	<jsp:include page="../fragments/landingPageFragment.jsp"/>
</sec:authorize>