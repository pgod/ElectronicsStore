<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Customer</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Customer</h1>
				<p>Customer details</p>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="order.customer" class="form-horizontal">
			<fieldset>
				<legend>Customer Details</legend>

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
					<label class="control-label col-lg-2" for="streetName"><spring:message code="editCustomer.form.streetName.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="streetName" path="streetName" type="text" class="form:input-large"/>
							<form:errors path="streetName" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="doorNo"><spring:message code="editCustomer.form.doorNo.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="doorNo" path="doorNo" type="text" class="form:input-large"/>
							<form:errors path="doorNo" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="zipCode"><spring:message code="editCustomer.form.zipCode.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="zipCode" path="zipCode" type="text" class="form:input-large"/>
							<form:errors path="zipCode" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="areaName"><spring:message code="editCustomer.form.areaName.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="areaName" path="areaName" type="text" class="form:input-large"/>
							<form:errors path="areaName" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="state"><spring:message code="editCustomer.form.state.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="state" path="state" type="text" class="form:input-large"/>
							<form:errors path="state" cssClass="text-danger"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="country"><spring:message code="editCustomer.form.country.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="country" path="country" type="text" class="form:input-large"/>
							<form:errors path="country" cssClass="text-danger"/>
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
				
				

				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="Add" name="_eventId_customerInfoCollected" />
						<button id="btnCancel" class="btn btn-default" name="_eventId_cancel">Cancel</button>
					</div>
				</div>

			</fieldset>
		</form:form>
	</section>
</body>
</html>
