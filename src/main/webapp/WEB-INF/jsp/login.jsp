<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />
<%@ page isELIgnored="false" %>
<link rel="stylesheet" type="text/css" href="../../styles.css">


<html lang="${language}">
<head>
  <fmt:message key="login.nameOfPage" var="nameOfPage" />
  <title>${nameOfPage}</title>
</head>
<body>

<form id="langList">
</form>

<form method="post" action ="${pageContext.request.contextPath}/login">
<div align="center">
  <table style="text-align:center;">
    <thead>

    <c:if test="${loginError != null}">
      <fmt:message key="notFoundError" var="notFoundError" />
      <span class="error">${notFoundError}</span>
    </c:if>

    <c:if test="${successfulRegistration != null}">
      <fmt:message key="successfulReg" var="successfulReg" />
      <span class="success">${successfulReg}</span>
    </c:if>


    <fmt:message key="login.loginMessage" var="LoginMessage" />
    <th colspan="2">${LoginMessage}</th>
    </thead>
    <tbody>
    <tr>
      <td><label for="loginNickname"><fmt:message key="login.label.loginNickname" />:</label></td>
      <td><input required type="text" id="loginNickname" name="loginNickname" /></td>
      <td>   </td>
      <td><select id="language" name="language" form="langList" onchange="submit()">
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
      </select></td>
    </tr>
    <tr>
      <td><label for="loginPassword"><fmt:message key="login.label.loginPassword" />:</label></td>
      <td><input required type="password" id ="loginPassword" name="loginPassword" /></td>
    </tr>
    <tr>
      <fmt:message key="login.button.submit" var="buttonValue" />
      <td colspan="2"><input type="submit" value="${buttonValue}" /></td>
    </tr>
    <tr>
      <fmt:message key="login.messageForRegistration" var="messageForRegistration" />
      <fmt:message key="login.messageForHere" var="messageForHere" />
      <td colspan="2" style="font-size: 13px;"><c:out value = "${messageForRegistration}"/> <a href="registration"><c:out value = "${messageForHere}"/></a></td>
    </tr>
    </tbody>
  </table>
</div>
</form>
</body>
</html>