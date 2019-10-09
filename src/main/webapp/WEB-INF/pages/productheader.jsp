<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Catalog</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src='https://kit.fontawesome.com/a076d05399.js'></script>
  <script type="text/javascript" src="resources/js/home.js"></script>
  <link rel = "stylesheet" href="resources/css/home.css" type="text/css">
  <link rel = "stylesheet" href="resources/css/product.css" type="text/css">
  <link rel = "stylesheet" href="resources/css/forms.css" type="text/css">

  
  <style>
.dropdown-submenu {
  position: relative;
}

.dropdown-submenu .dropdown-menu {
  top: 0;
  right: 100%;
  margin-top: -1px;
}
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
  </style>
  
</head>
<body>
<div id="main">
<button onclick="topFunction()" id="toTopBtn" title="Go to top"><span class="glyphicon glyphicon-chevron-up"></span></button>


<!-- Navbar Section -->
<nav class="navbar navbar-default navbar-fixed-top" id="mynavbar">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle navbar-toggler-icon" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar" ></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.html">GO</a>
    </div>

    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="index.html"><span class="fas fa-home"></span>Home</a></li>
        
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" >Products
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="allprod.jsp">All Categories<span class="glyphicon glyphicon-globe"></span></a></li>
          <li><a href="camping.jsp">Camping<span class="glyphicon glyphicon-tent"></span></a></li>
          <li><a href="mountaineering.jsp">Mountaineering<span class="fas fa-hiking"></span></a></li>
          <li><a href="outdoor.jsp">Outside <span class="glyphicon glyphicon-leaf "></span></a></li>
          <li><a href="personal.jsp">Personal<span class="glyphicon glyphicon-sunglasses"></span></a></li>
          <li><a href="golf.jsp">Golf<span class="fas fa-golf-ball "></span></a></li>
        </ul>
      </li>
      <li><a href="#contact">Contact</a></li>
      </ul>
    </div>
  </div>
</nav>

<!-- end of Navbar Section -->

<div class="dropdown">
  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example
  <span class="caret"></span></button>
  <ul class="dropdown-menu" style="background-color:white; margin-left:80%">
    <li><a href="#">Sort by Name</a></li>
    <li><a href="#">Low to High Price</a></li>
    <li><a href="#">High to Low Price</a></li>
  </ul>
</div>
<!-- Serach Bar Section -->
<div class="searchbar">
<span class="dropdown">

  <span class="glyphicon glyphicon-filter" onclick="openNav()" id="filter-product">Filter</span>
</label>
  <div class="search-container">
      <input type="text" placeholder="Search ...." id="search" >
      
    </form>
  </div>
  <button class="btn btn-info"   onclick="document.getElementById('addprod').style.display='block'" id="add-product">Add Product</button>
  <button class="btn btn-info"   onclick="document.getElementById('upprod').style.display='block'"  id="update-product">Update Product</button>
  <button class="btn btn-info"   onclick="document.getElementById('delprod').style.display='block'" id="delete-product">Delete Product</button>
  <button class="btn btn-info"   onclick="document.getElementById('incqty').style.display='block'" id="increase-qty">Increase Product Quantity</button>
</div>
<!-- end of Search Bar Section -->