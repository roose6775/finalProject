<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />
<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<html lang="${language}">
<head>
  <title>Login</title>
</head>
<body>

<form id="langList">
</form>

<form method="post" action ="login">
<div align="center">
  <table style="text-align:center;">
    <thead>
    <fmt:message key="login.LoginMessage" var="LoginMessage" />
    <th colspan="2">${LoginMessage}</th>
    </thead>
    <tbody>
    <tr>
      <td><label for="loginNickname"><fmt:message key="login.label.loginNickname" />:</label></td>
      <td><input type="text" id="loginNickname" name="loginNickname" /></td>
      <td/>
      <td><select id="language" name="language" form="langList" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
      </select></td>
    </tr>
    <tr>
      <td><label for="loginPassword"><fmt:message key="login.label.loginPassword" />:</label></td>
      <td><input type="password" id ="loginPassword" name="loginPassword" /></td>
    </tr>
    <tr>
      <fmt:message key="login.button.submit" var="buttonValue" />
      <td colspan="2"><input type="submit" value="${buttonValue}" /></td>
    </tr>
    <tr>
      <fmt:message key="login.message" var="RegisterMessage" />
      <fmt:message key="login.messageHere" var="RegisterMessageHere" />
      <td colspan="2" style="font-size: 13px;"><c:out value = "${RegisterMessage}"/> <a href="registration"><c:out value = "${RegisterMessageHere}"/></a></td>
    </tr>
    </tbody>
  </table>
</div>
</form>
</body>
</html>