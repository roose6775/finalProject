<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
    <div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of plants</h2></caption>
        <tr>
            <th>ID</th>
            <th>Имя</th>
        </tr>
        <c:forEach var="plant" items="${plants}">
        <tr>
            <th><c:out value = "${plant.id}"/></th>
            <th><c:out value = "${plant.name}"/></th>
        </tr>
        </c:forEach>
    </table>
    </div>
</body>
</html>
