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
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-right" role="search">
                <a href="<c:url value="/newUser"/>" class="btn btn-primary">Sign up</a>
                <a href="<c:url value="/login"/>" class="btn btn-success">Sign in</a>
            </form>
        </div>

    </div>
</nav>

<div class="well signInPanel">
    <div class="row">

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

        <form:form role="form" method="POST"  modelAttribute="signinform" action="loginPost" class="login_form" >
            <div class="form-group">
                <form:errors path="email" cssClass="error"/>
                <label for="email">Email address</label>
                <form:input type="email" class="form-control " id="email" placeholder="Enter email" path="email"/>
            </div>
            <div class="form-group">
                <form:errors path="password" cssClass="error"/>
                <label for="password">Password</label>
                <form:input type="password" class="form-control" id="password" placeholder="Password" path="password"/>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox"> Remember me
                </label>
            </div>
            <button type="submit" class="btn btn-success col-md-5">Sign In</button>
            <a href="#" class="btn btn-Link col-md-5">(forgot password)</a>
        </form:form>
    </div>
</div>

<script>
</script>
</body>
</html>
