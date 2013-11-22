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
    <h1><spring:message code="category.create.page.title"/></h1>
    <div>
        <form:form action="/category/create" commandName="category" method="POST">
            <div>
                <form:label path="categoryName"><spring:message code="category.label.name"/>:</form:label>
                <form:input path="categoryName" size="15"/>
                <form:errors path="categoryName" cssClass="error" element="div"/>
            </div>
            <div>
                <form:label path="description"><spring:message code="category.label.description"/>:</form:label>
                <form:input path="description" size="20"/>
                <form:errors path="description" cssClass="error" element="div"/>
            </div>
            <div>
                <input type="submit" value="<spring:message code="category.create.page.submit.label"/>"/>
            </div>
        </form:form>
    </div>
</body>
</html>