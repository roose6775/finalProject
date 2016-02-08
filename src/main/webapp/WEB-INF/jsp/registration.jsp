<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />
<link rel="stylesheet" type="text/css" href="../../styles.css">
<%@ page isELIgnored="false" %>

<fmt:message key="registration.nameOfPage" var="nameOfPage"/>
<fmt:message key="registration.nameOfList" var="nameOfList"/>
<fmt:message key="registration.name" var="name"/>
<fmt:message key="registration.nickname" var="nickname"/>
<fmt:message key="registration.password" var="password"/>
<fmt:message key="registration.repeatPassword" var="repeatPassword"/>
<fmt:message key="registration.required" var="required"/>
<fmt:message key="registration.submitButton" var="submitButton"/>
<fmt:message key="registration.message" var="message"/>
<fmt:message key="registration.hereMessage" var="hereMessage"/>


<html lang="${language}">
<head>
  <title>${nameOfPage}</title>
</head>
<body>

<form id="langList">
</form>

<form method="post" action="registration">
  <div style="width: 20%; height: 20%; margin: 0 auto;">
    <table>
      <tr>
        <td><select id="language" name="language" form="langList" onchange="submit()">
          <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
          <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        </select></td>
      </tr>
    </table>

    <table border="0" style="text-align:center;">
      <thead>

      <c:if test="${existedName != null}">
        <fmt:message key="existedNameErr" var="existedNameErr" />
        <span class="error">${existedNameErr}</span>
      </c:if>

      <c:if test="${dontMatchPasswords != null}">
        <fmt:message key="dontMatchPasswordsErr" var="dontMatchPasswordsErr" />
        <span class="error">${dontMatchPasswordsErr}</span>
      </c:if>

      <tr>
        <th colspan="2">${nameOfList}</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>${name}</td>
        <td><input type="text" name="regName" value="" /></td>
      </tr>
      <tr>
        <td>${nickname}</td>
        <td><input type="text" name="regNickname" value="" required/></td>
      </tr>
      <tr>
        <td>${password}</td>
        <td><input type="password" name="regPassword" value="" required/></td>
      </tr>
      <tr>
        <td>${repeatPassword}</td>
        <td><input type="password" name="regPasswordAgain" value="" required/></td>
      </tr>
      <tr>
        <td colspan="2">${required}</td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="${submitButton}" /></td>
      </tr>
      <tr>
        <td colspan="2" style="font-size: 13px;">${message} <a href="login">${hereMessage}</a></td>
      </tr>
      </tbody>
    </table>
  </div>
</form>
</body>
</html>
