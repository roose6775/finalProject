<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
  <title>Task #${numOfTask}</title>
</head>

<style>
fieldset{
  border: 1px solid rgb(55, 53, 76);
  width: 400px;
  margin:auto;
}
</style>

<body>
<div align="center">
  <h4><a href="${pageContext.request.contextPath}/tasks">Back to tasks</a></h4>
  <form action="${pageContext.request.contextPath}/task" method="post">
  <fieldset style="background-color: #a5d3b3">
    <legend><h1>${numOfTask}</h1></legend>
    <input hidden name="numOfTask" value="${numOfTask}">
    <table>
      <tr>
        <td>plant: </td><td style="font-size : 30px">${plantName}</td>
      </tr>
      <tr>
        <td>work: </td><td style="font-size : 30px">${workName}</td>
      </tr>
    </table>

    <table>
      <tr>
        <td style="font-size : 20px">task comment:</td>
      </tr>
      <tr>
        <td><textarea name="taskCom" onchange="submit()" rows="4" cols="40"
                      placeholder="Add here comment for task">${taskCom}</textarea></td>
      </tr>
    </table>
  </fieldset>

  <table cellspacing="10">
    <tr>
      <td style="font-size : 20px">forester comment:</td>
      <td style="font-size : 20px">owner comment:</td>
    </tr>

    <tr>
      <td><textarea name="forCom" onchange="submit()" rows="4" cols="30"
                    placeholder="Add here some description">${forestCom}</textarea></td>
      <td><textarea name="ownCom" onchange="submit()" rows="4" cols="30"
                    placeholder="Add here some description">${ownCom}</textarea></td>
    </tr>

    <tr>
      <c:choose>
        <c:when test="${isComp == 1}">
          <td>COMPLETED <input onchange="submit()" type="checkbox" value="${isComp}" name="compBox" checked/></td>
        </c:when>
        <c:otherwise>
          <td>COMPLETED <input onchange="submit()" type="checkbox" name="compBox" value="${isComp}"/></td>
        </c:otherwise>
      </c:choose>

      <c:choose>
        <c:when test="${isConf == 1}">
          <td>CONFIRMED <input onchange="submit()" type="checkbox" name="confBox" value="${isConf}" checked/></td>
        </c:when>
        <c:otherwise>
          <td>CONFIRMED <input onchange="submit()" type="checkbox" name="confBox" value="${isConf}"/></td>
        </c:otherwise>
      </c:choose>

    </tr>
  </table>
  </form>
</div>
</body>
</html>
