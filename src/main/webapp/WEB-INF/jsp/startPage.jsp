<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<div align="center">
    <p style="font-size: 13px;">You signed in as <c:out value="${sessionScope.userName}"/></p>
    <table style="text-align:left;">
        <tr>
            <th style="text-align:center;">Tables</th>
        </tr>
        <tr><td>      </td></tr>

                <ul>
                    <tr><td><li><a href="${pageContext.request.contextPath}/plants">Plants</a> (table with names of plants)</li></td></tr>
                    <tr><td><li><a href="${pageContext.request.contextPath}/works">Works</a></li></td></tr>
                    <tr><td><li><a href="">Treatments</a></li></td></tr>
                    <tr><td><li><a href="${pageContext.request.contextPath}/tasks">Tasks</a></li></td></tr>
                    <tr><td><li><a href="">Users</a></li></td></tr>
                </ul>
    </table>
</div>
</body>
</html>