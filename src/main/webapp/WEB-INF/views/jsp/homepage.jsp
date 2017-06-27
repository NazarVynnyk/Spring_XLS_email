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

<
<div class="jumbotron">
    <div class="container">
        <h1></h1>
        <p> Add email for sending report </p>

        <form method="post" action="sendMail" enctype="multipart/form-data">
            <table border="0" width="80%">
                <tr>
                    <td><input name="email" type="text" title="email"/></td>
                </tr>

                <tr>
                    <td>
                        <input type="submit" class="btn btn-primary btn-lg" value="send email"/>
                    </td>
                </tr>
            </table>
        </form>
        <h1></h1>

    </div>
</div>

<div>
    <footer>
        <p>&copy; Home.com 2017</p>
    </footer>
</div>

</body>
</html>