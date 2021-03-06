<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<link rel="stylesheet" type="text/css" href="../../styles.css">
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<fmt:message key="backToStart" var="backToStart"/>
<fmt:message key="youSignedAs" var="youSignedAs"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="works.addNewItemButton" var="addNewItemButton" />
<fmt:message key="works.addNewItemPlaceholder" var="addNewItemPlaceholder" />
<fmt:message key="parkObj.saveChangesButton" var="saveChangesButton" />
<fmt:message key="parkObj.deleteItemButton" var="deleteItemButton" />
<fmt:message key="parkObj.cancelButton" var="cancelButton" />
<fmt:message key="works.nameOfList" var="nameOfList" />
<fmt:message key="parkObj.id" var="id" />
<fmt:message key="parkObj.name" var="name" />


<html lang="${language}">
<head>
    <title>Works</title>
</head>

<script>

  function showButton(btnName,btnName2,btnName3,btnId){
    document.getElementById(btnName+btnId).style.display='block';
    if (btnName2 != null) {
      document.getElementById(btnName2+btnId).style.display='block';
    }
    if (btnName3 != null) {
      document.getElementById(btnName3 + btnId).style.display = 'block';
    }
  }

  function hideButton(btnName,btnName2,btnName3,btnId,name){
    document.getElementById(btnName+btnId).style.display='none';
    if (btnName2 != null) {
      document.getElementById(btnName2 + btnId).style.display = 'none';
    }
    if (btnName3 != null) {
      document.getElementById(btnName3 + btnId).style.display = 'none';
    }
    if (name != null) {
      document.getElementById("plantName" + btnId).value = name;
    }
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
          <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
          <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        </select>
      </td>

      <td style="font-size: 13px;">${youSignedAs} <c:out value="${sessionScope.userName}"/></td>
      <td style="font-size: 13px;"><a href="${pageContext.request.contextPath}/start">${backToStart}</a></td>
      <td><input type="submit" value="${logout}" form="logOut"/></td>
    </tr>
  </table>

  <c:if test="${AlreadyExistsError != null}">
    <span class="error">${itemAlreadyExistsError}</span>
  </c:if>

  <table>

    <caption><h3>${nameOfList}</h3></caption>

    <thead>
    <tr>
      <th>${id}</th>
      <th>${name}</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
      <c:when test = "${(sessionScope.userRole).equals('owner')}">

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

        <tr>
          <form id="addWork" method="post" action="works">
            <td>   </td>
            <td><input type="text" id="newWork" name="workName" placeholder="${addNewItemPlaceholder}"
                       required="required" onfocus="showButton('cancelButton',null,null,'')" /></td>
            <td><input type="submit" id="addNewButton" form="addWork" value="${addNewItemButton}" /></td>
            <td><input hidden type="reset" id="cancelButton" form="addWork" value="${cancelButton}"
                       onclick="hideButton('cancelButton',null,null,'',null)"/></td>
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
