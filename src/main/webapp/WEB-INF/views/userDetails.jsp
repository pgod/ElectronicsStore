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
		<form:form  modelAttribute="customer" class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<legend>User details</legend>

				<form:errors path="*" cssClass="alert alert-danger" element="div"/>

			
				
				
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
					<label class="control-label col-lg-2" for="billingAddress.StreetName"><spring:message code="editCustomer.form.streetName.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="billingAddress.StreetName" path="billingAddress.StreetName" type="text" class="form:input-large"/>
							<form:errors path="billingAddress.StreetName" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="billingAddress.doorNo"><spring:message code="editCustomer.form.doorNo.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="billingAddress.doorNo" path="billingAddress.doorNo" type="text" class="form:input-large"/>
							<form:errors path="billingAddress.doorNo" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="billingAddress.zipCode"><spring:message code="editCustomer.form.zipCode.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="billingAddress.zipCode" path="billingAddress.zipCode" type="text" class="form:input-large"/>
							<form:errors path="billingAddress.zipCode" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="billingAddress.areaName"><spring:message code="editCustomer.form.areaName.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="billingAddress.areaName" path="billingAddress.areaName" type="text" class="form:input-large"/>
							<form:errors path="billingAddress.areaName" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="billingAddress.state"><spring:message code="editCustomer.form.state.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="billingAddress.state" path="billingAddress.state" type="text" class="form:input-large"/>
							<form:errors path="billingAddress.state" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="billingAddress.country"><spring:message code="editCustomer.form.country.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="billingAddress.country" path="billingAddress.country" type="text" class="form:input-large"/>
							<form:errors path="billingAddress.country" cssClass="text-danger"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="phoneNumber"><spring:message code="editCustomer.form.phoneNumber.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="phoneNumber" path="phoneNumber" type="text" class="form:input-large"/>
							<form:errors path="phoneNumber" cssClass="text-danger"/>
						</div>
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
