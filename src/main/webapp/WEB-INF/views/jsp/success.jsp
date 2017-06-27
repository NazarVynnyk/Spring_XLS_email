<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Test project</title>

    <spring:url value="/resources/core/css/home.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Users activity information </a>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <h1></h1>
        <p> Report was successful send to email </p>

    </div>
</div>

<div>
    <footer>
        <p>&copy; Home.com 2017</p>
    </footer>
</div>

</body>
</html>