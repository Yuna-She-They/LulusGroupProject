<%-- 
    Document   : order
    Created on : Mar 21, 2019, 5:54:04 PM
    Author     : Scott Simpson
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
    
    
    

    <!--<link rel="stylesheet" href="CSS_SCSS/MainStyletest.css" />-->
    <link rel="stylesheet" href="CSS_SCSS/OrderJSPStyle.css" />
    <!-- Adds an icon library to the website -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

    <!--date only
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
      $( function() {
        $( "#datepicker" ).datepicker();
      } );
      </script>
      -->
      
<!--number2 works but css is messed up   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

    <script>
      $(function () {
        $('#datetimepicker1').datetimepicker();
     });
    </script>
-->

<!--number 3 https://www.jqueryscript.net/time-clock/Datetime-Picker-jQuery-Moment.html-->
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" 
        integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" 
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.0/moment-with-locales.min.js"></script>
    <link href="CSS_SCSS/datetimepicker.css" rel="stylesheet">
    <script src="js/datetimepicker.js"></script>

<div class="pickerholder">
    <script type="text/javascript">
    $(document).ready( function () {
        $('#picker').dateTimePicker({title: "Select Order Ready Date and Time"});
       
    })
    </script>
</div>


		<title>Order - Lulu's Local Eatery</title>
	</head>
	<body id="page-top">
		<div class="containera">
			<div class="containera2">
				<header>
					<div>Lulu's Local Eatery</div>
					<nav>
						<a href="index.html">Home</a> -
						<a href="menu.html">Menu</a> -
						<a href="#order">Order</a> - 
						<a href="ping_pong.html">Ping Pong Bar</a> -
						<a href="contact_us.html">Contact Us</a>
					</nav>
				</header>
				<article id="order">
                                    <div class="message">
                                        ${msg}
                                    </div>
                                    <c:if test = "${param.readytime == 'later'}">
                                        <div class="calendar">
                                            When would you like your order to be ready?
                                            <!--date only
                                            <p>Date: <input type="text" id="datepicker" readonly></p>
                                            -->
                                            <!--need to disallow past dates-->
                                            
                                          <!--number2
                                          <div class='input-group date' id='datetimepicker1'>
                                             <input type='text' class="form-control" />
                                             <span class="input-group-addon">
                                             <span class="glyphicon glyphicon-calendar"></span>
                                             </span>
                                          </div>
                                          -->
                                          
                                          <!--number 3-->
                                          <!--<div style="width: 250px; margin: 50px auto;">-->
                                                <div id="picker"> </div>
                                                <input type="hidden" id="result" value="" />
                                            <!--</div>-->
                                          
                                            
                                            
                                        </div>
                                    </c:if>
					<p>
                                            Select the menu items you would like to order:
					</p>
                                        <br>
                                        <form action="Cart" method="post">
                                            <div class="ordertitle">
                                                    Starters, Salads, &amp; Sides
                                            </div>
                                            <table>
                                                <tr>
                                                    <td><span class=tableheader>Add</span></td>
                                                    <td><span class=tableheader>Quantity</span></td>
                                                    <td><span class=tableheader>Name</span></td>
                                                    <td><span class=tableheader>Price</span></td>
                                                </tr>
                                                <tr>
                                                    <td><input type="checkbox" name="Name price" value="Name"></td>
                                                    <td><input type="text" name="quantity" id="quantity" value="1"/></td>
                                                    <td>Buffalo Cauliflower Bites</td>
                                                    <td>$5.95</td>
                                                </tr>
                                                <tr>
                                                    <td><input type="checkbox" name="Name price" value="Name"></td>
                                                    <td><input type="text" name="quantity" id="quantity" value="1"/></td>
                                                    <td>Kale Salad</td>
                                                    <td>$6.95</td>
                                                </tr>
                                                <tr>
                                                    <td><input type="checkbox" name="Name price" value="Name"></td>
                                                    <td><input type="text" name="quantity" id="quantity" value="1"/></td>
                                                    <td>Kale and Brussels Sprouts Caesar Salad</td>
                                                    <td>$8.95</td>
                                                </tr>
                                            </table>
                                            <table>
                                                <tr>
                                                    <td><span class=tableheader>Add</span></td>
                                                    <td><span class=tableheader>Quantity</span></td>
                                                    <td><span class=tableheader>Name</span></td>
                                                    <td><span class=tableheader>Price</span></td>
                                                </tr>
                                            <c:forEach var="item" items="${items}">
                                                <c:if test="item.category == side">
                                                    
                                                        <tr>
                                                            <td><input type="checkbox" name="${item.name}" value="${item.name}"></td>
                                                            <td><input type="text" name="quantity${item.name}" id="quantity${item.name}" value="1"/></td>
                                                            <td>${item.name}</td>
                                                            <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                                                        </tr>
                                                    
                                                </c:if>
                                            </c:forEach>
                                            </table>
                                            <br />
                                            <div class="ordertitle">
                                                    Wraps &amp; Sandwiches
                                            </div>
                                            <table>
                                                <tr>
                                                    <td><span class=tableheader>Add</span></td>
                                                    <td><span class=tableheader>Quantity</span></td>
                                                    <td><span class=tableheader>Name</span></td>
                                                    <td><span class=tableheader>Price</span></td>
                                                </tr>
                                            <c:forEach var="item" items="${items}">
                                                <c:if test="item.category == wrap">
                                                    
                                                        <tr>
                                                            <td><input type="checkbox" name="${item.name}" value="${item.name}"></td>
                                                            <td><input type="text" name="quantity${item.name}" id="quantity${item.name}" value="1"/></td>
                                                            <td>${item.name}</td>
                                                            <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                                                        </tr>
                                                    
                                                </c:if>
                                            </c:forEach>
                                            </table>
                                            <br>
                                            <div class="ordertitle">
                                                    Entrees
                                            </div>
                                            <table>
                                                <tr>
                                                    <td><span class=tableheader>Add</span></td>
                                                    <td><span class=tableheader>Quantity</span></td>
                                                    <td><span class=tableheader>Name</span></td>
                                                    <td><span class=tableheader>Price</span></td>
                                                </tr>
                                            <c:forEach var="item" items="${items}">
                                                <c:if test="item.category == entree">
                                                    
                                                        <tr>
                                                            <td><input type="checkbox" name="${item.name}" value="${item.name}"></td>
                                                            <td><input type="text" name="quantity${item.name}" id="quantity${item.name}" value="1"/></td>
                                                            <td>${item.name}</td>
                                                            <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                                                        </tr>
                                                    
                                                </c:if>
                                            </c:forEach>
                                            </table>
                                            <br>
                                            <div class="ordertitle">
                                                    Kids
                                            </div>
                                            <table>
                                                <tr>
                                                    <td><span class=tableheader>Add</span></td>
                                                    <td><span class=tableheader>Quantity</span></td>
                                                    <td><span class=tableheader>Name</span></td>
                                                    <td><span class=tableheader>Price</span></td>
                                                </tr>
                                            <c:forEach var="item" items="${items}">
                                                <c:if test="item.category == kids">
                                                    
                                                        <tr>
                                                            <td><input type="checkbox" name="${item.name}" value="${item.name}"></td>
                                                            <td><input type="text" name="quantity${item.name}" id="quantity${item.name}" value="1"/></td>
                                                            <td>${item.name}</td>
                                                            <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                                                        </tr>
                                                    
                                                </c:if>
                                            </c:forEach>
                                            </table>
                                            <br>
                                            <div class="buttonholder">
						<input type="submit" id="submit" value="Review Cart">
                                            </div>
                                        </form>
				</article>
			</div>
		</div>
	</body>
</html>

