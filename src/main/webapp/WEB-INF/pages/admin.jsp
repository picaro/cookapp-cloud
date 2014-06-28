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

    <script src="${pageContext.request.contextPath}/resources/js/dropdown.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/modal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/tab.js"></script>

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
                        <li><a href="<c:url value="/logout"/>">Sign out</a></li>
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

<ul class="nav nav-tabs">
        <li class="active"><a href="#persons" data-toggle="tab">Persons</a></li>
        <li><a href="#shops" data-toggle="tab">Shops</a></li>
        <li><a href="#shop" data-toggle="tab">Shop</a></li>
        <li><a href="#products" data-toggle="tab">Products</a></li>
        <li><a href="#circles" data-toggle="tab">Circles</a></li>
    </ul>

    <div class="tab-content" style="margin-top: 20px;">
        <div class="tab-pane fade in active" id="persons">
            <jsp:include page="/person/allPersons"/>
        </div>
        <div class="tab-pane fade" id="shops">
            <jsp:include page="/shopList/allShops"/>
        </div>
        <div class="tab-pane fade" id="shop">
            <jsp:include page="/shop/allShops"/>
        </div>
        <div class="tab-pane fade" id="products">
            <jsp:include page="/product/allProducts"/>
        </div>
        <div class="tab-pane fade" id="circles">
            <jsp:include page="/circle/allCircles"/>
        </div>
    </div>

</div>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/3.0.2/js/bootstrap.js"></script>
</body>
</html>
