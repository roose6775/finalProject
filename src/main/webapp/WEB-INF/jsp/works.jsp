<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<html lang="${language}">
<head>
    <title>Works</title>
</head>

<script>
  function showButton(btnName,btnName2,btnName3,btnId){
    document.getElementById(btnName+btnId).style.display='block';
    document.getElementById(btnName2+btnId).style.display='block';
    document.getElementById(btnName3+btnId).style.display='block';
  }

  function hideButton(btnName,btnName2,btnName3,btnId,name){
    document.getElementById(btnName+btnId).style.display='none';
    document.getElementById(btnName2+btnId).style.display='none';
    document.getElementById(btnName3+btnId).style.display='none';
    document.getElementById("workName" + btnId).value = name;
  }
</script>

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
          <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
          <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        </select>
      </td>
      <fmt:message key="backToStart" var="backToStart"/>
      <fmt:message key="youSignedAs" var="youSignedAs"/>
      <fmt:message key="logout" var="logout"/>
      <td style="font-size: 13px;">${youSignedAs} <c:out value="${sessionScope.userName}"/></td>
      <td style="font-size: 13px;"><a href="${pageContext.request.contextPath}/start">${backToStart}</a></td>
      <td><input type="submit" value="${logout}" form="logOut"/></td>
    </tr>
  </table>

  <table>
    <fmt:message key="works.nameOfList" var="nameOfList" />
    <caption><h3>${nameOfList}</h3></caption>

    <fmt:message key="parkObs.id" var="id" />
    <fmt:message key="parkObs.name" var="name" />
    <thead>
    <tr>
      <th>${id}</th>
      <th>${name}</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
      <c:when test = "${(sessionScope.userRole).equals('owner')}">
        <fmt:message key="parkObj.saveChangesButton" var="saveChangesButton" />
        <fmt:message key="parkObj.deleteItemButton" var="deleteItemButton" />
        <fmt:message key="parkObj.cancelButton" var="cancelButton" />

        <c:forEach var="work" items="${works}">
          <tr>
            <form id="${work.id}" method="post" action="works">
              <td><input type="hidden" name="id" value="${work.id}" /><c:out value="${work.id}"/></td>
              <td><input type="text" id="workName${work.id}" name="workName" value="${work.name}"
                         onfocus="showButton('saveBtn','canBtn','delItemBtn',${work.id})" required/></td>
              <td><input hidden type="submit" name="saveChanges" id="saveBtn${work.id}" form="${work.id}"
                         value="${saveChangesButton}" /></td>
              <td><input hidden type="submit" name="deleteButton" id="delItemBtn${work.id}" form="${work.id}"
                         value="${deleteItemButton}" /></td>
              <td><input hidden type="button" id="canBtn${work.id}" form="${work.id}"
                         onclick="hideButton('saveBtn','canBtn','delItemBtn',${work.id},'${work.name}')"
                         value="${cancelButton}" /></td>
            </form>

          </tr>
        </c:forEach>
        <fmt:message key="works.addNewItemButton" var="addNewItemButton" />
        <fmt:message key="works.addNewItemPlaceholder" var="addNewItemPlaceholder" />
        <tr>
          <form id="addWork" method="post" action="works">
            <td>   </td>
            <td><input type="text" id="newWork" name="workName" placeholder="${addNewItemPlaceholder}"
                       required="required" /></td>
            <td><input type="submit" form="addWork" value="${addNewItemButton}" /></td>
          </form>
        </tr>
      </c:when>
      <c:otherwise>
        <c:forEach var="work" items="${works}">
          <tr>
            <td>${work.id}</td>
            <td><input type="text" value="${work.name}" disabled></td>
          </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
    </tbody>
  </table>
  </div>
</body>
</html>
