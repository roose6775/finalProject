<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />
<%@ page isELIgnored="false" %>

<fmt:message key="youSignedAs" var="youSignedAs"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="start.nameOfList" var="nameOfList"/>
<fmt:message key="start.nameOfPage" var="nameOfPage"/>
<fmt:message key="start.toPlants" var="toPlants"/>
<fmt:message key="start.toWorks" var="toWorks"/>
<fmt:message key="start.toTreatment" var="toTreatments"/>
<fmt:message key="start.toTasks" var="toTasks"/>
<fmt:message key="start.toUsers" var="toUsers"/>
<fmt:message key="start.plantsDescription" var="plantDescr"/>
<fmt:message key="start.tasksDescription" var="taskDescr"/>
<fmt:message key="start.usersDescription" var="userDescr"/>
<fmt:message key="start.treatmentsDescription" var="treatDescr"/>
<fmt:message key="start.worksDescription" var="worksDescr"/>

<html lang="${language}">
<html>
<head>
    <title>${nameOfPage}</title>
</head>
<body>

<form id="langList">
</form>
<form id="logOut" action="${pageContext.request.contextPath}/logout" method="post">
</form>

<div align="center">
    <table>
        <td>
            <select id="language" name="language" form="langList" onchange="submit()">
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
             </select>
        </td>

        <td style="font-size: 13px;">${youSignedAs} <c:out value="${sessionScope.userName}"/></td>
        <td><input type="submit" value="${logout}" form="logOut"/></td>
    </table>

    <table style="text-align:left;">
        <tr>
            <th style="text-align:center;">${nameOfList}</th>
        </tr>
        <tr><td>      </td></tr>

                <ul>
                    <tr><td><li><a href="${pageContext.request.contextPath}/plants">${toPlants}</a> ${plantDescr}</li></td></tr>
                    <tr><td><li><a href="${pageContext.request.contextPath}/works">${toWorks}</a> ${worksDescr}</li></td></tr>
                    <tr><td><li><a href="${pageContext.request.contextPath}/treatments">${toTreatments}</a> ${treatDescr}</li></td></tr>
                    <tr><td><li><a href="${pageContext.request.contextPath}/tasks">${toTasks}</a> ${taskDescr}</li></td></tr>
                    <c:if test="${(sessionScope.userRole).equals('owner')}"><tr><td><li><a href="${pageContext.request.contextPath}/users">${toUsers}</a> ${userDescr}</li></td></tr></c:if>
                </ul>
    </table>
</div>
</body>
</html>