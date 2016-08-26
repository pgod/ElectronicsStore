<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>User</title>
</head>
	<section class="container">
		<form:form  modelAttribute="newCustomer" class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<legend>User details</legend>

				<form:errors path="*" cssClass="alert alert-danger" element="div"/>

			
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="email"><spring:message code="editCustomer.form.email.label"/></label>
					<div class="col-lg-10">
						<form:input id="email" path="email" type="text" class="form:input-large"/>
						<form:errors path="email" cssClass="text-danger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="password"><spring:message code="editCustomer.form.password.label"/></label>
					<div class="col-lg-10">
						<form:input id="password" path="password" type="password" class="form:input-large"/>
						<form:errors path="password" cssClass="text-danger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="name"><spring:message code="editCustomer.form.name.label"/></label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" class="form:input-large"/>
						<form:errors path="name" cssClass="text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="surname"><spring:message code="editCustomer.form.surname.label"/></label>
					<div class="col-lg-10">
						<form:input id="surname" path="surname" type="text" class="form:input-large"/>
						<form:errors path="surname" cssClass="text-danger"/>
					</div>
				</div>
				

				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Submit changes"/>
						<a href="<spring:url value="/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Back
					</a>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>

</html>
