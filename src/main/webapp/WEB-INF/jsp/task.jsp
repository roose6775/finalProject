<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" type="text/css" href="../../styles.css">
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<fmt:message key="task.backToTask" var="backToTask"/>
<fmt:message key="task.nameOfPage" var="nameOfPage"/>
<fmt:message key="task.plant" var="plant"/>
<fmt:message key="task.work" var="work"/>
<fmt:message key="task.taskComm" var="taskComm"/>
<fmt:message key="task.forestComm" var="forestComm"/>
<fmt:message key="task.ownerComm" var="ownerComm"/>
<fmt:message key="task.treatment" var="treatment"/>
<fmt:message key="task.taskPlaceholder" var="taskPlacehold"/>
<fmt:message key="task.forOwnPlaceholder" var="placehold"/>
<fmt:message key="task.deleteTaskButton" var="deleteTaskButton"/>
<fmt:message key="task.completed" var="completed"/>
<fmt:message key="task.confirmed" var="confirmed"/>


<html>
<head>
  <title>${nameOfPage}${numOfTask}</title>
</head>

<script>
  function clear() {
    document.body.innerHTML = '';
  }
</script>

<body>

<div align="center">

  <table>
    <tr>
      <td><a href="${pageContext.request.contextPath}/tasks">${backToTask}</a></td>
      <td>
      </td>
    </tr>
  </table>
  <form action="${pageContext.request.contextPath}/task" method="post">

    <fieldset style="background-color: #a5d3b3">
    <legend><h1>${numOfTask}</h1></legend>
    <input hidden name="numOfTask" value="${numOfTask}">

    <table>
      <tr>
        <td>${plant} </td><td style="font-size : 30px">${plantName}</td>
      </tr>
      <tr>
        <td>${work} </td><td style="font-size : 30px">${workName}</td>
      </tr>
    </table>

    <c:choose>
      <c:when test = "${(sessionScope.userRole).equals('owner')}">

    <table>
      <tr>
        <td style="font-size : 20px">${taskComm}</td>
      </tr>
      <tr>
        <td><textarea name="taskCom" onchange="submit()" rows="4" cols="40"
                      placeholder="${taskPlacehold}">${taskCom}</textarea></td>
      </tr>

      <c:if test="${workName.equals('to treat')}">
        <tr><td>${treatment}</td></tr>
        <tr>
        <td>
        <select name="treatment" onchange="submit()">
          <c:forEach var="treatment" items="${treatmentList}">
            <c:choose>
              <c:when test="${treatName.equals(treatment.name)}">
                <option selected>${treatment.name}</option>
              </c:when>
              <c:otherwise>
                <option>${treatment.name}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
        </td>
        </tr>
      </c:if>

      <tr>
        <td><input type="submit" name="deleteTask" value="${deleteTaskButton}" onclick="clear()"></td>
      </tr>

      </c:when>
      <c:otherwise>
        <tr>
          <td style="font-size : 20px">${taskComm}</td>
        </tr>
        <tr>
          <td><textarea disabled name="taskCom" rows="4" cols="40">${taskCom}</textarea></td>
        </tr>
        <tr>
          <c:if test="${workName.equals('to treat')}">
            <td>${treatment}</td>
            <td><input value=${treatName}/></td>
          </c:if>
        </tr>

      </c:otherwise>
      </c:choose>

    </table>
  </fieldset>

  <table cellspacing="10">
    <tr>
      <td style="font-size : 20px">${forestComm}</td>
      <td style="font-size : 20px">${ownerComm}</td>
    </tr>

    <tr>
      <td><textarea name="forCom" onchange="submit()" rows="4" cols="30"
                    placeholder="${placehold}">${forestCom}</textarea></td>

    <c:choose>
      <c:when test = "${(sessionScope.userRole).equals('owner')}">

      <td><textarea name="ownCom" onchange="submit()" rows="4" cols="30"
                    placeholder="${placehold}">${ownCom}</textarea></td>
    </tr>
      </c:when>
      <c:otherwise>
        <td><textarea disabled name="ownCom" rows="4" cols="30">${ownCom}</textarea></td>
      </c:otherwise>
    </c:choose>


    <tr>
      <c:choose>
        <c:when test="${isComp == 1}">
          <td>${completed} <input onchange="submit()" type="checkbox" value="${isComp}" name="compBox" checked/></td>
        </c:when>
        <c:otherwise>
          <td>${completed} <input onchange="submit()" type="checkbox" name="compBox" value="${isComp}"/></td>
        </c:otherwise>
      </c:choose>


  <c:choose>
    <c:when test = "${(sessionScope.userRole).equals('owner')}">

      <c:choose>
        <c:when test="${isConf == 1}">
          <td>${confirmed} <input onchange="submit()" type="checkbox" name="confBox" value="${isConf}" checked/></td>
        </c:when>
        <c:otherwise>
          <td>${confirmed} <input onchange="submit()" type="checkbox" name="confBox" value="${isConf}"/></td>
        </c:otherwise>
      </c:choose>

    </c:when>
    <c:otherwise>
      <c:choose>
        <c:when test="${isConf == 1}">
          <td>${confirmed} <input disabled type="checkbox" name="confBox" value="${isConf}" checked/></td>
        </c:when>
        <c:otherwise>
          <td>${confirmed} <input disabled type="checkbox" name="confBox" value="${isConf}"/></td>
        </c:otherwise>
      </c:choose>
    </c:otherwise>
    </c:choose>

    </tr>
  </table>
  </form>
</div>
</body>
</html>
