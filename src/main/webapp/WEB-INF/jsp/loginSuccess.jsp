<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<div align="center">
        <table>
            <tr>
                <td><p>Welcome, <c:out value="${sessionScope.sessionId}"/></td>
            </tr>
             <tr>
                <td><a href="plants">Start working</a></td>
            </tr>
        </table>
    </div>
</body>
</html>