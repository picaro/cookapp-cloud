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
<div class="panel panel-default">
    <div class="panel-body">
        <table class="table table-hover table-striped table-bordered">
            <thead>
            <tr class="info">
                <th> Name</th>
                <th> Note</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="circle" items="${circleList}">
                <tr>
                    <td><c:out value="${circle.name}"/></td>
                    <td><c:out value="${circle.note}"/></td>
                    <td>
                        <a href="#" class="btn btn-success">Edit</a>
                        <a href="#" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
