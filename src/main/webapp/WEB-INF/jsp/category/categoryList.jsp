<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="category.list.page.title"/></h1>
<c:if test="${not empty categories}">
    <table border="1">
        <thead>
        <tr>
            <td><spring:message code="category.label.name"/></td>
            <td><spring:message code="category.label.description"/></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categories}" var="category">
            <tr>
                <td><c:out value="${category.categoryName}"/></td>
                <td><c:out value="${category.description}"/></td>
                <td><a href="/category/edit/<c:out value="${category.categoryId}"/>"><spring:message code="category.edit.link.label"/></a></td>
                <td><a href="/category/delete/<c:out value="${category.categoryId}"/>"><spring:message code="category.delete.link.label"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty categories}">
    <p>
        <spring:message code="category.list.page.label.no.categories.found"/>
    </p>
</c:if>