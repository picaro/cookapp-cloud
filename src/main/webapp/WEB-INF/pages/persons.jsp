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
<div class="panel panel-default">
    <div class="panel-body">
        <table class="table table-hover table-striped table-bordered">
            <thead>
            <tr class="info">
                <th> First Name</th>
                <th> Last name</th>
                <th> Email</th>
                <th> Phone</th>
                <th> Gender</th>
                <th> Date registration</th>
                <th> Birthday</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="person" items="${personList}">
                <tr>
                    <td><c:out value="${person.firstName}"/></td>
                    <td><c:out value="${person.lastName}"/></td>
                    <td><c:out value="${person.email}"/></td>
                    <td><c:out value="${person.phone}"/></td>
                    <td><c:out value="${person.gender}"/></td>
                    <td><c:out value="${person.dob}"/></td>
                    <td><c:out value="${person.date_registration}"/></td>
                    <td>
                        <a href="#" class="btn btn-success">Edit</a>
                        <a href="${pageContext.request.contextPath}/person/delete?id=${person.id}" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>