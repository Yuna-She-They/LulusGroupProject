<%-- 
    Document   : checkout
    Created on : Apr 25, 2019, 7:49:34 PM
    Author     : wmscottsimpsonjr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
﻿<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<meta name="description" content="" />
		<meta name="author" content="" />
		<link rel="stylesheet" href="CSS_SCSS/MainStyle.css" />
		<link rel="stylesheet" href="CSS_SCSS/OrderJSPStyle.css" />
		<link rel="stylesheet" href="CSS_SCSS/CheckoutStyle.css" />
		<!-- Adds an icon library to the website -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,600" rel="stylesheet"> 
                <script src="js/LuhnAlt.js"></script>
		<title>Cart - Lulu's Local Eatery</title>
	</head>
	<body id="page-top">
		<div class="container">
			<div class="container2">
				<header>
					<div class="logo"><img src="images/logogreen.jpg" width="250"></div>
					<nav>
						<a href="index.html">Home</a> -
						<a href="menu.html">Menu</a> -
						<a href="order.html">Order</a> - 
						<a href="ping_pong.html">Ping Pong Bar</a> -
						<a href="contact_us.html">Contact Us</a>
					</nav>
				</header>
				<article id="checkout">
                                    <div class="message">
                                        ${msg}
                                    </div>
					<h1>Check Out</h1>
                                        Pickup time: ${invoice.weekdaypickupdate}
                                        <br>
                                        (Pickup available Monday&ndash;Saturday, Noon&ndash;8:30 PM)
                                        <br>
                                        Total: <fmt:formatNumber value="${total}" type="currency"/>
                                        <br>
					<form action="PlaceOrder" method="post">
                                            <table>
                                                <tr>
                                                    <td>
                                                        First name
                                                    </td>
                                                    <td>
                                                        <input type="text" name="firstname" required="required" autofocus="autofocus">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Last name
                                                    </td>
                                                    <td>
                                                       <input type="text" name="lastname" required="required"> 
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Phone
                                                    </td>
                                                    <td>
                                                        <input type="text" name="phone" required="required">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Email
                                                    </td>
                                                    <td>
                                                        <input type="email" name="email" required="required">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Credit Card
                                                    </td>
                                                    <td>
                                                        <input type="text" id="ccnumber" required="required"/>
                                                    </td>
                                                    <td>
                                                        <input type="button" id="testCC" value="Verify" />
                                                        <span id="ccRes"></span>
                                                    </td>
                                                </tr>
                                            </table>
                                            Please bring your card when you come to pick up your food. It will not be charged until such time.

                                            <div class="buttonholder">
                                                    <input type="hidden" id="submit" name="placeorder" value="Place Order"/>
                                            </div>
					</form>
				</article>
				<footer id="info">
					<a href="https://www.google.com/maps/place/Lulu's+Local+Eatery,+LLC/@38.5989912,-90.2430857,15z/data=!4m8!1m2!3m1!2sLulu's+Local+Eatery,+LLC!3m4!1s0x0:0x5e32f72323d673d7!8m2!3d38.5989918!4d-90.2430859" target="_blank">3201 S Grand Ave St. Louis, MO 63118</a><br />
					314-300-8215 // Monday&ndash;Saturday 11:30am&ndash;9pm<br />
					Ping-pong bar above Lulu's open all day // full bar open 5pm&ndash;12am<br />
					<a href="https://squareup.com/gift/M0H6A3WHHA1NG/order" target="_blank">PURCHASE A GIFT CARD</a> <br/>
					Holiday Hours: Closed December 24<sup>th</sup> &amp; December 25<sup>th</sup>, Closed December 31<sup>st</sup> &amp; January 1<sup>st</sup> <br />
					<a href="https://www.facebook.com/Lulus-Local-Eatery-132285260224330/" class="fa fa-facebook" target="_blank"></a>
					<a href="https://www.instagram.com/luluslocaleatery/" class="fa fa-instagram" target="_blank"></a>
				</footer>
			</div>
		</div>
	</body>
</html>

