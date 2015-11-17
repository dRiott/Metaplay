<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>David Riott</title>
  
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<spring:url value='/resources/lib/pdf.js'/>"></script>
    <script src="<spring:url value='/resources/js/about.js'/>"></script>

</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		<div class="row drRow">

			<div class="row">
                <%--If in firefox, use PDF.js to generate pdf data into this canvas tag--%>
                <canvas class="col-md-8" id="the-canvas" style="display:none;"></canvas>

                <%--If in chrome, use the object tag--%>
                <div class="col-md-8" id="pdfDiv" style="display:none;">
					<object width="800" height="1115"
                            data="<spring:url value='/resources/pdf/Riott_David_Nov2015.pdf'/>"
                            type="application/pdf">
                        <p>It appears you don't have a PDF plugin for this browser.
                            No biggie... you can <a href="http://metaplay.me/resources/pdf/Riott_David_Nov2015.pdf">click here to
                                download the PDF file.</a></p>
                    </object>
				</div>

                <div class="col-md-3">
                    <h1>Other Links</h1>
                    <p><a class="mono" href="mailto:david.riott1@gmail.com">david.riott1@gmail.com</a></p>
                    <p><a class="mono" href="http://metaplay.me/resources/pdf/Riott_David_Nov2015.pdf" download="Riott, David - Resume, 2015.pdf">Download A Resume</a></p>
                    <p><a class="mono" href="http://www.linkedin.com/in/davidriott">LinkedIn</a></p>
                    <p><a class="mono" href="http://www.github.com/dRiott">GitHub Profile</a></p>
                    <p><a class="mono" href="https://github.com/dRiott/Metaplay">Metaplay's Repository</a></p>
                    <p><a class="mono" href="https://github.com/dRiott/Metaplay/blob/master/README.md">Metaplay's ReadMe</a></p>
                </div>
			</div>
		</div>
	</div>			
</body>
</html>