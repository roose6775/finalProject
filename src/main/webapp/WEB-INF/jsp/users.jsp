<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<fmt:message key="backToStart" var="backToStart"/>
<fmt:message key="youSignedAs" var="youSignedAs"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="users.nameOfList" var="nameOfList" />
<fmt:message key="parkObj.id" var="id" />
<fmt:message key="users.name" var="name" />
<fmt:message key="users.nickname" var="nickname" />
<fmt:message key="users.role" var="role" />

<html lang="${language}">
<head>
  <title>Plants</title>
</head>

<body>
<form id="langList">
</form>
<form id="logOut" action="${pageContext.request.contextPath}/logout" method="post">
</form>

<div align="center">
  <table>
    <tr>
      <td>
        <select id="language" name="language" form="langList" onchange="submit()">
          <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
          <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        </select>
      </td>

      <td style="font-size: 13px;">${youSignedAs} <c:out value="${sessionScope.userName}"/></td>
      <td style="font-size: 13px;"><a href="${pageContext.request.contextPath}/start">${backToStart}</a></td>
      <td><input type="submit" value="${logout}" form="logOut"/></td>
    </tr>
  </table>


  <table>
    <caption><h3>${nameOfList}</h3></caption>
    <thead>
    <tr>
      <th>${id}</th>
      <th>${name}</th>
      <th>${nickname}</th>
      <th>${role}</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
              <tr>
                <td>${user.id}</td>
                <td><input type="text" value="${user.name}" disabled></td>
                <td><input type="text" value="${user.nickname}" disabled></td>
                <td><input type="text" value="${user.role}" disabled></td>
              </tr>
        </c:forEach>
    </tbody>
  </table>

</div>
</body>
</html>
