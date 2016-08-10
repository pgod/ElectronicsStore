<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<li><a href="<spring:url value="/products/"/>">Products</a></li>
<li><a href="<spring:url value="/products/add"/>">Add products</a></li>
<li><a href="<spring:url value="/cart/"/>">Checkout</a></li>
<sec:authorize access="isAnonymous()">
<li><a href="<spring:url value="/login"/>">Log in</a></li>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<li><a> <sec:authentication property="principal.username" /> </a></li>
<li><a href="<spring:url value="/logout"/>" >Log out</a></li>
</sec:authorize>



