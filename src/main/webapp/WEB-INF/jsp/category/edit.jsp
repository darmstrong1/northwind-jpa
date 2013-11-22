<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title><spring:message code="spring.data.jpa.example.title"/></title>
    <link rel="stylesheet" href="/static/css/styles.css" type="text/css"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<h1><spring:message code="category.edit.page.title"/></h1>
<div>
    <form:form action="/category/edit" commandName="category" method="POST">
        <form:hidden path="categoryId"/>
        <table>
            <tr>
                <td><form:label path="categoryName"><spring:message code="category.label.name"/>:</form:label></td>
                <td><form:input path="categoryName" size="15"/></td>
                <td><form:errors path="categoryName" cssClass="error" element="div"/></td>
            </tr>
            <tr>
                <td><form:label path="description"><spring:message code="category.label.description"/>:</form:label></td>
                <td><form:input path="description" size="60"/></td>
                <td><form:errors path="description" cssClass="error" element="div"/></td>
            </tr>
        </table>
        <div>
            <input type="submit" value="<spring:message code="category.edit.page.submit.label"/>"/>
        </div>
    </form:form>
</div>
</body>
</html>