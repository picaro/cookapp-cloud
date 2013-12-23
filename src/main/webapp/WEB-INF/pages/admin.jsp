<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cookapp</title>
    <link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/main.css>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/bootstrap/3.0.2/css/bootstrap.min.css" media="all"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/dropdown.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/modal.js"></script>
</head>
<body>


<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">ABOUT US</a></li>
                <li><a href="#">CONTACT US</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">USER<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Sign out</a></li>
                        <li><a href="#">Settings</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="col-md-2 well" style="height:400px;">
    <div class="list-group">
        <a href="#" class="list-group-item active">
            Test Menu
        </a>
        <a href="#" class="list-group-item">1</a>
        <a href="#" class="list-group-item">2</a>
        <a href="#" class="list-group-item">3</a>
        <a href="#" class="list-group-item">4</a>
    </div>

</div>


<div class="col-md-10" style="height:100px">

    <nav class="navbar navbar-inverse" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Admin</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-">
            <ul class="nav navbar-nav">
                <li ><a href="#">Shops</a></li>
                <li ><a href="#">Shop  list</a></li>
                <li ><a href="#">Circles</a></li>
                <li ><a href="#">Persons</a></li>
                <li ><a href="#">Products</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>


    <div class="panel panel-default">
        <div class="panel-body" style="height: 600px;">
            Basic panel example
        </div>
    </div>
</div>

</body>
</html>
