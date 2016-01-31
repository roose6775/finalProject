<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Tasks</title>
</head>
<body>

<form id="plList">
</form>

  <div align="center">
  <table style="text-align:center;">
    <caption><h3>List of tasks</h3></caption>
      <tr>
        <th>id</th>
        <th>Plant</th>
        <th>Work</th>
        <th>Completed</th>
        <th>Confirmed</th>
      </tr>
    <c:forEach var="task" items="${taskList}">
      <tr>
        <td>${task.getId()}.</td>

        <td><select form="plList" onchange="">
            <c:forEach var="plant" items="${plantList}">
              <c:choose>
                <c:when test = "${task.getPlantName().equals(plant.name)}">
                  <option selected> "${plant.name}"</option>
                </c:when>
                <c:otherwise>
                  <option> "${plant.name}"</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
        </select></td>

        <td><input type="text" value="${task.getWorkName()}"/></td>

        <c:choose>
          <c:when test="${task.getIsCompleted() == true}">
            <td><input type="checkbox" value="${task.getIsCompleted()}" checked/></td>
          </c:when>
          <c:otherwise>
            <td><input type="checkbox" value="${task.getIsCompleted()}"/></td>
          </c:otherwise>
        </c:choose>


        <c:choose>
          <c:when test="${task.getIsConfirmed() == true}">
            <td><input type="checkbox" value="${task.getIsConfirmed()}" checked/></td>
          </c:when>
          <c:otherwise>
            <td><input type="checkbox" value="${task.getIsConfirmed()}"/></td>
          </c:otherwise>
        </c:choose>

      </tr>
    </c:forEach>
  </table>
  </div>
</body>
</html>
