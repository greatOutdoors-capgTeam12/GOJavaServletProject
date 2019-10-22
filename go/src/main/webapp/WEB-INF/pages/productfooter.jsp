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
</head>
<body>
<!-- Customer Say Section -->
<div id="customer" class="container-fluid">
 <h1 class="text-center blue-heading w3-animate-top">What our customers say</h1>
  <div id="myCarousel" class="carousel slide text-center" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner bg-grey" role="listbox">
      <div class="item active">
        <img src="resources/images/customer/customer1.jpg" class="img-rounded customer-img text-center" alt="Name">
        <h4>"coment"<br><span>Name, Desg, company</span></h4>
      </div>
      <div class="item">
        <img src="resources/images/customer/customer2.jpg" class="img-rounded customer-img text-center" alt="Name">
        <h4>"content"<br><span>Name, Desg, company</span></h4>
      </div>
      <div class="item">
        <img src="resources/images/customer/customer3.jpg" class="img-rounded customer-img text-center" alt="Name">
        <h4>"content"<br><span>Name, Desg, company</span></h4>
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>
</div>
<!-- end of Customer Say Section --> 

<!-- Contact Section -->

<div id="contact" class="container-fluid ">
  <h2 class="text-center blue-heading">CONTACT</h2>
  <div class="row">
    <div class="col-sm-5">
      <p>Contact us and we'll get back to you within 24 hours.</p>
      <p><span class="glyphicon glyphicon-map-marker"></span> Glassglow, UK</p>
      <p><span class="glyphicon glyphicon-phone"></span> +00 00000000</p>
      <p><span class="glyphicon glyphicon-envelope"></span> info@greatoutdoors.com</p>
    </div>
    <div class="col-sm-7">
      <div class="row">
        <div class="col-sm-6 form-group">
          <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
        </div>
        <div class="col-sm-6 form-group">
          <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
        </div>
      </div>
      <textarea class="form-control" id="comments" name="comments" placeholder="Comment" rows="5"></textarea><br>
      <div class="row">
        <div class="col-sm-12 form-group">
          <button class="btn btn-default pull-right" type="submit">Send</button>
        </div>
      </div>
    </div>
  </div>
</div>

<!--end of contact -->

<!-- Map Section -->
<div class="embed-responsive embed-responsive-100x400px">
<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d35818.719732048536!2d-4.25169!3d55.868392!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488815562056ceeb%3A0x71e683b805ef511e!2sGlasgow%2C+Glasgow+City%2C+UK!5e0!3m2!1sen!2sus!4v1448625188752" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
  </div>
  <!-- end of Map Section -->

  <!-- Footer Section -->
  <footer class="container-fluid text-center">
  <p>Copyright(2019) Reserves Great Outdoors Pvt .Ltd <br><a href="#" title="Visit www.greatoutdoors.com">www.greatoutdoors.com</a></p>
</footer>
  <!--End of Footer Section -->