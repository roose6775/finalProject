<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Plants</title>
</head>
<body>
    <div align="center">
    <table style="text-align:center;">
        <caption><h3>List of plants</h3></caption>
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="plant" items="${plants}">
        <tr>
            <td><c:out value = "${plant.id}"/></td>
            <td><input type="text" value="${plant.name}"/></td>
            <td><input type="submit" value="ыц" /></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html>