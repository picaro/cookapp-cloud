<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <a class="navbar-brand" href="<c:url value="/"/>">HOME</a>
            <a class="navbar-brand" href="<c:url value="/admin"/>">ADMIN</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">ABOUT US</a></li>
                <li><a href="#">CONTACT US</a></li>
            </ul>

            <form class="navbar-form navbar-right" role="search">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.authenticated}">
                        <a href="<c:url value="/logout"/>"  class="btn btn-success">Sign out</a></c:when>
                    <c:otherwise>
                        <a href="<c:url value="/login"/>"  class="btn btn-success">Sign in</a></c:otherwise>
                </c:choose>

            </form>
        </div>

    </div>
</nav>

<div class="col-md-2 well" style="height:400px;">
    <div class="list-group">
        <a href="#" class="list-group-item active">
            Test Menu
        </a>
        <a href="#" class="list-group-item">Shop list</a>
        <a href="#" class="list-group-item">Family</a>
        <a href="#" class="list-group-item">Shops</a>
        <a href="#" class="list-group-item">Fridge</a>
    </div>

</div>

<div class="container">
    <div class="jumbotron logo_background">
        <div class="container">
            <div class="col-sm-2">
                <jsp:include page="/product/allProducts"/>
            </div>
            <div class="col-sm-4">
            </div>
        </div>
    </div>

</div>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-8 twitter">aaa</div>
            <div class="col-md-4 sitemap">sss</div>
            <div class="col-md-6 social"></div>
            <div class="col-md-6 footer-logo"></div>
        </div>
    </div>
</footer>
<script>
</script>
</body>
</html>
