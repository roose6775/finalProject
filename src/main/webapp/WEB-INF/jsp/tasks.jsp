<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />
<link rel="stylesheet" type="text/css" href="../../styles.css">

<fmt:message key="tasks.nameOfPage" var="nameOfPage" />
<fmt:message key="youSignedAs" var="youSignedAs"/>
<fmt:message key="backToStart" var="backToStart"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="tasks.nameOfList" var="nameOfList"/>
<fmt:message key="parkObj.id" var="id"/>
<fmt:message key="tasks.dateColumn" var="dateColumn"/>
<fmt:message key="tasks.plantColumn" var="plantColumn"/>
<fmt:message key="tasks.workColumn" var="workColumn"/>
<fmt:message key="tasks.compColumn" var="compColumn"/>
<fmt:message key="tasks.confColumn" var="confColumn"/>
<fmt:message key="tasks.addNewTaskButton" var="addNewTaskButton"/>
<fmt:message key="parkObj.cancelButton" var="cancelButton"/>
<fmt:message key="tasks.choosePlant" var="choosePlant"/>
<fmt:message key="tasks.chooseWork" var="chooseWork"/>
<fmt:message key="tasks.itemIsDeleted" var="itemIsDeleted"/>

<html lang="${language}">
<head>
  <title>${nameOfPage}</title>
</head>

<script>

  var isPlantSet = false;
  var isWorkSet = false;

  function setPlant(){
    isPlantSet = true;
    if (isWorkSet){
      document.getElementById("addNewTaskButton").style.display = 'block';
      document.getElementById("cancelButton").style.display = 'block';
    }
  }
  function setWork(){
    isWorkSet = true;
    if (isPlantSet){
      document.getElementById("addNewTaskButton").style.display = 'block';
      document.getElementById("cancelButton").style.display = 'block';
    }
  }
  function hideButtons(){
    document.getElementById("addNewTaskButton").style.display = 'none';
    document.getElementById("cancelButton").style.display = 'none';

  }
</script>

<body>

<form id="langList">
</form>
<form id="goToTask" action="${pageContext.request.contextPath}/task" target="_blank" method="get">
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

  <c:if test="${deleted != null}">
    <span class="success">${itemIsDeleted}</span>
  </c:if>

  <table style="text-align:center; border-collapse: collapse;">
    <caption><h3>${nameOfList}</h3></caption>
      <tr>
        <th>${id}</th>
        <th>${dateColumn}</th>
        <th>${plantColumn}</th>
        <th>${workColumn}</th>
        <th>${compColumn}</th>
        <th>${confColumn}</th>
      </tr>

    <c:forEach var="task" items="${taskList}">

       <tr <c:if test="${(task.getIsConfirmed() == 1) && (task.getIsCompleted() == 1)}"> class="done"</c:if> >
        <form id="list${task.getId()}" method="post" action="tasks">

        <td>
          <input type="submit" name="goToTask" value="${task.getId()}" form="goToTask"/>
          <input name="taskId" value="${task.getId()}" hidden/>
        </td>

          <td style="font-size: 13px;">${task.getTaskDate()}</td>

          <c:choose>
          <c:when test = "${(sessionScope.userRole).equals('owner')}">

        <td>
          <select name="plant" onchange="submit()">
            <c:forEach var="plant" items="${plantList}">
              <c:choose>
                <c:when test="${task.getPlantName().equals(plant.name)}">
                  <option selected>${plant.name}</option>
                </c:when>
                <c:otherwise>
                  <option>${plant.name}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </td>

        <td>
          <select name="work" onchange="submit()">
          <c:forEach var="work" items="${workList}">
            <c:choose>
              <c:when test = "${task.getWorkName().equals(work.name)}">
                <option selected>${work.name}</option>
              </c:when>
              <c:otherwise>
                <option>${work.name}</option>
              </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </td>

        <c:choose>
          <c:when test="${task.getIsCompleted() == 1}">
            <td style="width: 100px"><input onchange="submit()" type="checkbox" name="compBox" checked/></td>
          </c:when>
          <c:otherwise>
            <td style="width: 100px"><input onchange="submit()" type="checkbox" name="compBox" /></td>
          </c:otherwise>
        </c:choose>


        <c:choose>
          <c:when test="${task.getIsConfirmed() == 1}">
            <td><input onchange="submit()" type="checkbox" name="confBox" checked/></td>
          </c:when>
          <c:otherwise>
            <td><input onchange="submit()" type="checkbox" name="confBox" /></td>
          </c:otherwise>
        </c:choose>

          </c:when>
            <c:otherwise>
              <td><input type="text" value="${task.getPlantName()}" disabled></td>
              <td><input type="text" value="${task.getWorkName()}" disabled></td>
              <c:choose>
                <c:when test="${task.getIsCompleted() == 1}">
                  <td style="width: 100px"><input disabled  type="checkbox" name="compBox" checked/></td>
                </c:when>
                <c:otherwise>
                  <td style="width: 100px"><input disabled type="checkbox" name="compBox" /></td>
                </c:otherwise>
              </c:choose>

              <c:choose>
                <c:when test="${task.getIsConfirmed() == 1}">
                  <td><input disabled type="checkbox" name="confBox" checked/></td>
                </c:when>
                <c:otherwise>
                  <td><input disabled type="checkbox" name="confBox" /></td>
                </c:otherwise>
              </c:choose>
            </c:otherwise>
          </c:choose>

        </form>
      </tr>
    </c:forEach>

    <c:if test="${(sessionScope.userRole).equals('owner')}">
    <tr>
      <td>+</td>
      <form action="${pageContext.request.contextPath}/tasks" method="post">
        <td>
        <select name="plant" onchange="setPlant()">
          <option selected>${choosePlant}</option>
            <c:forEach var="plant" items="${plantList}">
             <option>${plant.name}</option>
            </c:forEach>
        </select>
      </td>
      <td>
        <select name="work" onchange="setWork()">
          <option selected>${chooseWork}</option>
          <c:forEach var="work" items="${workList}">
            <option>${work.name}</option>
          </c:forEach>
        </select>
      </td>
        <td><input hidden type="submit" name="addNewTaskButton" id="addNewTaskButton" value="${addNewTaskButton}"/></td>
        <td><input hidden type="reset" name="cancelButton" id="cancelButton" value="${cancelButton}"
                   onclick="hideButtons()"/></td>
      </form>
      </c:if>
    </tr>
  </table>
  </div>
</body>
</html>
