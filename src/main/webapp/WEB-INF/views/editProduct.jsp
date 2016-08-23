<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Product</title>
</head>
	<section class="container">
		<form:form  modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<legend>Edit product</legend>

				<form:errors path="*" cssClass="alert alert-danger" element="div"/>
				
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="name"><spring:message code="addProduct.form.name.label"/></label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" class="form:input-large"/> 
											${newProduct.name}
						<form:errors path="name" cssClass="text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="unitPrice"><spring:message code="addProduct.form.unitPrice.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
												${newProduct.unitPrice}
							<form:errors path="unitPrice" cssClass="text-danger"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="description"><spring:message code="addProduct.form.description.label"/></label>
					<div class="col-lg-10">
						<form:textarea id="description" path="description" rows = "2"/>
											${newProduct.description}
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="manufacturer"><spring:message code="addProduct.form.manufacturer.label"/></label>
					<div class="col-lg-10">
						<form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
											${newProduct.manufacturer}
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="category"><spring:message code="addProduct.form.category.label"/></label>
					<div class="col-lg-10">
						<form:input id="category" path="category" type="text" class="form:input-large"/>
											${newProduct.category}
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="unitsInStock"><spring:message code="addProduct.form.unitsInStock.label"/></label>
					<div class="col-lg-10">
						<form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
											${newProduct.unitsInStock}
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="productImage"><spring:message code="addProduct.form.productImage.label"/></label>
					<div class="col-lg-10">
						<form:input id="productImage" path="productImage" type="file"
							class="form:input-large" />
					</div>
				</div>


				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Submit"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>
