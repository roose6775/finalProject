<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<html lang="${language}">
<head>
    <title>Tasks</title>
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

<style>
  .done{
    background-color: #b2ddfa;
  }
</style>

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
           <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
           <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
         </select>
       </td>
       <fmt:message key="youSignedAs" var="youSignedAs"/>
       <fmt:message key="backToStart" var="backToStart"/>
       <fmt:message key="logout" var="logout"/>
       <td style="font-size: 13px;">${youSignedAs} <c:out value="${sessionScope.userName}"/></td>
       <td style="font-size: 13px;"><a href="${pageContext.request.contextPath}/start">${backToStart}</a></td>
       <td><input type="submit" value="${logout}" form="logOut"/></td>
     </tr>
   </table>
  <table style="text-align:center; border-collapse: collapse;">
    <caption><h3>List of tasks</h3></caption>
      <tr>
        <th>id</th>
        <th>Plant</th>
        <th>Work</th>
        <th>Completed</th>
        <th>Confirmed</th>
      </tr>

    <c:forEach var="task" items="${taskList}">

      <tr <c:if test="${(task.getIsConfirmed() == 1) && (task.getIsCompleted() == 1)}"> class="done"</c:if>>
        <form id="list${task.getId()}" method="post" action="tasks">

        <td><input type="submit" name="goToTask" value="${task.getId()}" form="goToTask"/>
            <input name="taskId" value="${task.getId()}" hidden/>
        </td>

        <td><select name="plant" onchange="submit()">
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
        </select></td>

        <td><select name="work" onchange="submit()">
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
            <td><input onchange="submit()" type="checkbox" name="compBox" checked/></td>
          </c:when>
          <c:otherwise>
            <td><input onchange="submit()" type="checkbox" name="compBox" /></td>
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

      </form>
      </tr>
    </c:forEach>
    <tr>

      <form action="${pageContext.request.contextPath}/tasks" method="post">
      <td> </td>
      <td>
        <select name="plant" onchange="setPlant()">
          <option selected>Choose plant</option>
            <c:forEach var="plant" items="${plantList}">
             <option>${plant.name}</option>
            </c:forEach>
        </select>
      </td>

      <td>
        <select name="work" onchange="setWork()">
          <option selected>Choose work</option>
          <c:forEach var="work" items="${workList}">
            <option>${work.name}</option>
          </c:forEach>
        </select>
      </td>
      <td><input hidden type="submit" name="addNewTaskButton" id="addNewTaskButton" value="Add new task"></td>
        <td><input hidden type="reset" name="cancelButton" id="cancelButton" value="Cancel" onclick="hideButtons()"></td>
      </form>

    </tr>
  </table>
  </div>
</body>
</html>
