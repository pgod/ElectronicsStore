<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
	<script src="/ElectronicsStore/resource/js/controllers.js"></script>
<title>Products</title>
</head>

	<section class="container" ng-app="cartApp">
		<div class="row">
		<div class="col-md-5">
	<img src="<c:url value="/resource/images/${product.productId}.png"></c:url>" alt="image"  style = "width:100%"/>
</div>
		
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong>Product ID: </strong><span class="label label-warning">${product.productId}</span>
				</p>
				<p>
					<strong>Manufacturer</strong>: ${product.manufacturer}
				</p>
				<p>
					<strong>Category</strong>: ${product.category}
				</p>
				<p>
					<strong>Units in stock</strong>: ${product.unitsInStock}
				</p>
				<h4>${product.unitPrice} PLN</h4>
				<p ng-controller="cartCtrl">
					<a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')"> 
<span class="glyphicon-shopping-cart glyphicon"></span> Order now </a>
<a href="<spring:url value="/cart" />" class="btn btn-default">
	<span class="glyphicon-hand-right glyphicon"></span> Cart
</a>

 <a href="<spring:url value="/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Back
					</a>

				</p>

			</div>
		</div>
	</section>
</html>
