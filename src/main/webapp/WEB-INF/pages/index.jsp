<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Cookapp</title>
    <link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/main.css>
    <link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/half-slider.css>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/bootstrap/3.0.2/css/bootstrap.min.css" media="all"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

</head>
<body class="body_background">

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="container">
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
                        <a href="<c:url value="/newUser"/>" class="btn btn-primary">Sign up</a>
                        <c:choose>
                            <c:when test="${pageContext.request.userPrincipal.authenticated}">
                                <a href="<c:url value="/logout"/>" class="btn btn-success">Sign out</a></c:when>
                            <c:otherwise>
                                <a href="<c:url value="/login"/>" class="btn btn-success">Sign in</a></c:otherwise>
                        </c:choose>

                    </form>
                </div>
            </div>
        </div>
    </div>
</nav>

<!-- Carousel
================================================== -->
<!-- Full Page Image Background Carousel Header -->
<header id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for Slides -->
    <div class="carousel-inner">
        <div class="item active">
            <!-- Set the first background image using inline CSS below. -->
            <div class="fill" style="background-image:url('/resources/img/slide_1.jpg');"></div>

        </div>
        <div class="item">
            <!-- Set the second background image using inline CSS below. -->
            <div class="fill" style="background-image:url('/resources/img/slide_2.jpg');"></div>

        </div>
        <div class="item">
            <!-- Set the third background image using inline CSS below. -->
            <div class="fill" style="background-image:url('/resources/img/slide_3.jpg');"></div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="icon-prev"></span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="icon-next"></span>
    </a>

</header>

<!-- /.container -->


<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
</body>
</html>
