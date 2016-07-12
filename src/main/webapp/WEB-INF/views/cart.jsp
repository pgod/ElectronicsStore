<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script src="/ElectronicsStore/resource/js/controllers.js"></script>

<title>Cart</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Cart</h1>
				<p>Products in your cart</p>
			</div>
		</div>
	</section>

	<section class="container" ng-app="cartApp">
		<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">

			<div>
				<a class="btn btn-danger pull-left"
					ng-click="clearCart()"> <span
					class="glyphicon glyphicon-remove-sign"></span> erase cart
				</a> <a href="#" class="btn btn-success pull-right"> <span
					class="glyphicon-shopping-cart glyphicon"></span> Buy
				</a>
			</div>
			<table class="table table-hover">
				<tr>
					<th>Product</th>
          <th>Unit price</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Action</th>

				</tr>
				<tr ng-repeat="item in cart.cartItems">
					<td>{{item.product.productId}}-{{item.product.name}}</td>
					<td>{{item.product.unitPrice}} PLN
					</td>
					<td>{{item.quantity}}</td>
					<td>{{item.totalPrice}} PLN</td>
					<td><a href="#" class="label label-danger" ng-click="removeFromCart(item.product.productId)"> <span
							class="glyphicon glyphicon-remove" /></span> Delete
					</a></td>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th>Total price</th>
					<th>{{cart.grandTotal}} PLN</th>
					<th></th>
				</tr>
			</table>
			
			<a href="<spring:url value="/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Go back to shopping
			</a>
		</div>
	</section>
</body>
</html>
