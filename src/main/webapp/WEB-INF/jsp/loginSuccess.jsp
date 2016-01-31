<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<div align="center">
        <table style="text-align:center;">
            <tr>
                <td><p>Welcome, <c:out value="${sessionScope.userName}"/></td>
            </tr>
             <tr>
                <td><a href="plants">Start working</a></td>
            </tr>
        </table>
    </div>
</body>
</html>