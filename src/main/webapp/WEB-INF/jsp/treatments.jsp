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
<fmt:message key="itemAlreadyExistsError" var="itemAlreadyExistsError" />
<fmt:message key="treatments.nameOfList" var="nameOfList" />
<fmt:message key="parkObj.id" var="id" />
<fmt:message key="parkObj.name" var="name" />
<fmt:message key="parkObj.saveChangesButton" var="saveChangesButton" />
<fmt:message key="parkObj.deleteItemButton" var="deleteItemButton" />
<fmt:message key="parkObj.cancelButton" var="cancelButton" />
<fmt:message key="treatments.addNewItemButton" var="addNewItemButton" />
<fmt:message key="treatments.addNewItemPlaceholder" var="addNewItemPlaceholder" />

<html lang="${language}">
<head>
  <title>Plants</title>
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
      document.getElementById("treatmentName" + btnId).value = name;
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

        <c:forEach var="treatment" items="${treatments}">
          <c:choose>
          <c:when test="${treatment.id==1}"/>
          <c:otherwise>
          <tr>
            <form id="${treatment.id}" method="post" action="${pageContext.request.contextPath}/treatments">
              <td><input type="hidden" name="id" value="${treatment.id}" />${treatment.id}</td>
              <td><input type="text" id="treatmentName${treatment.id}" name="treatmentName" value="${treatment.name}"
                         onfocus="showButton('saveBtn','canBtn','delItemBtn',${treatment.id})" required/></td>
              <td><input hidden type="submit" name="saveChanges" id="saveBtn${treatment.id}" form="${treatment.id}"
                         value="${saveChangesButton}" /></td>
              <td><input hidden type="submit" name="deleteButton" id="delItemBtn${treatment.id}" form="${treatment.id}"
                         value="${deleteItemButton}" /></td>
              <td><input hidden type="button" id="canBtn${treatment.id}" form="${treatment.id}"
                         onclick="hideButton('saveBtn','canBtn','delItemBtn',${treatment.id},'${treatment.name}')"
                         value="${cancelButton}" /></td>
            </form>
          </tr>
          </c:otherwise>
          </c:choose>
        </c:forEach>

        <tr>
          <form id="addPlant" method="post" action="${pageContext.request.contextPath}/treatments">
            <td>   </td>
            <td><input type="text" id="newPlant" name="treatmentName" placeholder="${addNewItemPlaceholder}"
                       required="required" onfocus="showButton('cancelButton',null,null,'')" /></td>
            <td><input type="submit" id="addNewButton" form="addPlant" value="${addNewItemButton}" /></td>
            <td><input hidden type="reset" id="cancelButton" form="addPlant" value="${cancelButton}"
                       onclick="hideButton('cancelButton',null,null,'',null)"/></td>
          </form>
        </tr>
      </c:when>
      <c:otherwise>
        <c:forEach var="treatment" items="${treatments}">
          <c:choose>
          <c:when test="${treatment.id==1}"/>
          <c:otherwise>
          <tr>
            <td>${treatment.id}</td>
            <td><input type="text" value="${treatment.name}" disabled></td>
          </tr>
          </c:otherwise>
          </c:choose>
        </c:forEach>
      </c:otherwise>
    </c:choose>

    </tbody>
  </table>

</div>
</body>
</html>