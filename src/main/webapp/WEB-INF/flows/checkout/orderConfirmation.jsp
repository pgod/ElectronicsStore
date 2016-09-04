<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Order Confirmation</title>
</head>

<body>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Order</h1>
				<p>Order Confirmation</p>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<form:form modelAttribute="order" class="form-horizontal">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />

				<div
					class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<div class="text-center">
						<h1>Summary</h1>
					</div>
					
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<address>
								<strong>Shipping address</strong>
								 <br>
								${order.customer.name} 	${order.customer.surname}
								<br>
								${order.customer.streetName} ${order.customer.doorNo}
								<br>
								${order.customer.zipCode} ${order.customer.areaName}
								<br>
								${order.customer.state}, ${order.customer.country}
								<br> <abbr title="Phone">Phone:</abbr>
								${order.customer.phoneNumber}
							</address>
						</div>

					</div>
					<div class="row">

						<table class="table table-hover">
							<thead>
								<tr>
									<th>Product</th>
									<th>#</th>
									<th class="text-center">Price</th>
									<th class="text-center">Sum</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cartItem" items="${order.cart.cartItems}">
									<tr>
										<td class="col-md-9"><em>${cartItem.value.product.name}</em></td>
										<td class="col-md-1" style="text-align: center">
											${cartItem.value.quantity}</td>
										<td class="col-md-1 text-center">${cartItem.value.product.unitPrice} PLN</td>
										<td class="col-md-1 text-center">${cartItem.value.totalPrice} PLN</td>
									</tr>
								</c:forEach>

								<tr>
									<td> </td>
									<td> </td>
									<td class="text-right"><h4>
											<strong>Total: </strong>
										</h4></td>
									<td class="text-center text-danger"><h4>
											<strong>${order.cart.grandTotal} PLN</strong>
										</h4></td>
								</tr>
							</tbody>
						</table>
						<button id="back" class="btn btn-default"
							name="_eventId_backToCustomerInfo">back</button>

						<button type="submit" class="btn btn-success"
							name="_eventId_orderConfirmed">
							Confirm   <span class="glyphicon glyphicon-chevron-right"></span>
						</button>
						<button id="btnCancel" class="btn btn-default"
							name="_eventId_cancel">Cancel</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
