<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cookapp</title>
    <link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/main.css>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/bootstrap/3.0.2/css/bootstrap.min.css" media="all"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">HOME</a>
            <a class="navbar-brand" href="admin">ADMIN</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">ABOUT US</a></li>
                <li><a href="#">CONTACT US</a></li>
            </ul>

            <form class="navbar-form navbar-right" role="search">
                <a href="login"  class="btn btn-success">Sign in</a>
            </form>
        </div>

    </div>
</nav>

<div class="container">
    <div class="jumbotron logo_background">
        <div class="container">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
            </div>
        </div>
    </div>
</div>

<script>
</script>
</body>
</html>
