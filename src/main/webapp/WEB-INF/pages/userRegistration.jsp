<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Cookapp</title>
    <link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/main.css>
    <link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/datepicker.css>

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
            <a class="navbar-brand" href="/">HOME</a>
        </div>
    </div>
</nav>
<c:url var="newUser" value="/newUser"/>
<div class="container">
    <div class="page-header">
        <h1>Create your personal account</h1>
    </div>
    <div class="container">
        <div class="col-md-5">
            <div class="well">
                <form:form method="POST" modelAttribute="person">
                    <div class="form-group">
                        <form:errors path="firstName" cssClass="error"/>
                        <form:input class="form-control" placeholder="First name" path="firstName"/>

                    </div>
                    <div class="form-group">
                        <form:errors path="lastName" cssClass="error"/>
                        <form:input class="form-control" placeholder="Last name" path="lastName"/>
                    </div>
                    <div class="form-group">
                        <form:input path="dob" type="text" class="datepicker form-control" placeholder="Birthday day"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="email" cssClass="error"/>
                        <form:input type="email" class="form-control" id="email" placeholder="Email" path="email"/>
                    </div>
                    <div class="form-group">
                        <form:input type="text" class="form-control" id="phone" placeholder="Phone" path="phone"/>
                    </div>

                    <div class="form-group">
                        <form:select path="gender" class="form-control">
                            <form:option value="NONE" label="-- Select Gender --"/>
                            <form:options items="${gender}"/>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <form:errors path="password" cssClass="error"/>
                        <form:input type="password" class="form-control" id="password" placeholder="Password"
                                    path="password"/>
                    </div>
                    <button type="submit" class="btn btn-success">Create an account</button>

                </form:form>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/3.0.2/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script>
    $('.datepicker').datepicker();
</script>
</body>
</html>