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
                <th> Product Name</th>
                <th> Product Note</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td><c:out value="${product.name}"/></td>
                    <td><c:out value="${product.note}"/></td>
                    <td>
                        <a href="#" class="btn btn-success">Edit</a>
                        <a href="${pageContext.request.contextPath}/product/delete?id=${product.id}" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>