<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Catalog</title>
</head>
<body>
<%@ include file = "WEB-INF/pages/productheader.jsp" %>
<!-- Product Section -->
<c:import url="/CampingProductServlet" />

<c:set var="myProducts" value="${requestScope.productList}" />

<div id="product" class="container-fluid text-center ">
  <div class="row justify-content-center">
   <c:forEach var="product" items= "${myProducts}" varStatus="i">
    <div class="card col-lg-4 col-md-4 col-sm-4 ">
      <img  src="resources/images/products/${product.productId}.jpg" alt="Product_Name" style="width:100%" height="200px">
      <div class="overlay-prod"><span class="fas fa-eye"></span>
	  <span class="fas fa-trash"onclick="document.getElementById('delprod').style.display='block'"></span>
       <span class="fas fa-arrow-up" onclick="document.getElementById('incqty').style.display='block'"></span>
      <span class="fas fa-pen"  onclick="document.getElementById('upprod').style.display='block'"></span></div>
      <h3>${product.productName}</h3>
      <p class="price">&#x20b9; ${product.price}</p>
      <p>Description : ${product.dimension}</p>
      <p>Brand : ${product.manufacturer}</p>
      <c:set var = "salary"  value = "${product.quantity}"/>
      <c:choose>
      <c:when test= "${salary > 0}" >
        <p style="color:green"> In Stock </p>
      </c:when>
    <c:otherwise>
        <p style="color:red"> Out of Stock </p>
    </c:otherwise>
</c:choose>
      <p>Colour <span class="fas fa-palette" style="color: ${product.colour}"></span></p>
    </div>
   </c:forEach>
  </div>
  </div>

  
</div>


<!-- end of Product Section -->




<%@ include file = "WEB-INF/pages/productfooter.jsp" %>
<%@ include file = "WEB-INF/pages/form.jsp" %>

</body>
</html>