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
<!-- Side Nav -->
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <div data-role="main" class="ui-content"> 
  
  <form action="FilterProductServlet" method="post">
    
      <input type="text" placeholder="Product Name" name="pro-name" id="filter-prod-name">
	  
      <input type="text" placeholder="Brand" name="brand" id="filter-prod-brand"><br>
      <div data-role="rangeslider">
        <label for="price-min">Low Price:</label>
         <input type="number" placeholder="Enter Low Range"  min="0"  step="0.01" /name="qtylow" value=1 required id="filter-low-price"><br>
        <label for="price-max">High Price:</label>
         <input type="number" placeholder="Enter High Range"   max="1000000" step="0.01" /name="qtyhigh" value=1000000 required id="filter-high-price"><br>
      </div>
        <select name="category" id="filter-cat">
          <option value="0">Select Category</option>
          <option value="1">Camping</option>
          <option value="2">Golf</option>
          <option value="3">Mountaineering</option>
          <option value="4">Outdoor</option>
          <option value="5">Personal</option>
      </select>
      <button type="submit" class="btn btn-success" id="filter-prod-btn">Apply Fiter</button>
    </form>
  </div>
  </div>
<!-- end of side nav -->
</div>


<!-- Add Product Section -->
<div id="addprod" class="modal">
  <span onclick="document.getElementById('addprod').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="AddProductServlet" method="post" >
    <div class="container">
      <h1>Product Addition </h1>
      <p>Please fill in this form to add product.</p>
      <hr>
	  
      <input type="text" placeholder="Enter Product Id" name="prodid" required id="add-prod-id"><br>
	  
	  
      <input type="text" placeholder="Enter Product Name" name="prodname" required id="add-prod-name"><br>
	  
	  <input type="text" placeholder="Enter Product Brand" name="brand" id="add-prod-brand" required><br>
      <input type="text" placeholder="Enter Product Dimension" name="dim" id ="add-prod-dim" required><br>
	  <input type="text" placeholder="Enter Product Specification" name="spec" id ="add-prodspec" required><br>
       
	  <input type="number" placeholder="Enter Quantity"  min="1" max="1000" step="1" /name="qty"  id ="add-prod-qty" required><br>
	  
	  <input type="number" placeholder="Enter Price"  min="0.00" max="1000000.00" step="0.01" /name="price"  id ="add-prod-price"required><br>
     
      <select name = "prodcat" id="add-prod-cat">
          <option value="0">Select Category</option>
          <option value="1">Camping</option>
          <option value="2">Golf</option>
          <option value="3">Mountaineering</option>
          <option value="4">Outdoor</option>
          <option value="5">Personal</option>
      </select>
	  
	  <input type="color" placeholder="Enter Product Specification" name="col" required id="add-prod-col"><br>
	    <label for="pic"><b>Upload Product Images</b></label><br>
		<input type="file" name="pic" accept="image/*"><br>
		<br><img id="myImg" src="#" alt="your image" height=200 width=100 style="margin-left:80px;""><br>
        <button type="submit" class="signupbtn" id="addprodbtn" >Add Product</button>
        <button type="button" onclick="document.getElementById('addprod').style.display='none'" class="cancelbtn" id="cancel">Cancel</button>
        
      </div>
    </div>
  </form>
</div>
<!--end of Add Product Section -->


<!-- Update Product Section -->

<div id="upprod" class="modal">
  <span onclick="document.getElementById('upprod').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="UpdateProductServlet" method="post">
    <div class="container">
      <h1>Update Product </h1>
      <p>Please fill in this form to update product</p>
      <hr>
	  
      <input type="text" placeholder="Enter Product Id" name="prodid" id="up-prod-id" required><br>
	  
	  
      <input type="text" placeholder="Enter Product Name" name="prodname" id="up-prod-name" ><br>
	  
	  <input type="text" placeholder="Enter Product Brand" name="brand" id="up-prod-brand" ><br>
      <input type="text" placeholder="Enter Product Dimension" name="dim" id="up-prod-dimension"><br>
	  <input type="text" placeholder="Enter Product Specification" name="spec" id="up-prod-spec"><br>
      
	  
	  <input type="number" placeholder="Enter Price"  min="0.00" max="1000000.00" step="0.01" /name="price" id="up-prod-price" required><br>
     
      <select name="prodcat" id="up-prod-cat">
          <option value="0">Select Category</option>
          <option value="1">Camping</option>
          <option value="2">Golf</option>
          <option value="3">Mountaineering</option>
          <option value="4">Outdoor</option>
          <option value="5">Personal</option>
      </select>
	  
	  <input type="color" placeholder="Enter Product Specification" name="col" id="up-prod-col" ><br>
	    
        <button type="button" onclick="document.getElementById('upprod').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn" id="updateprodbtn">Update Product</button>
      </div>
    </div>
  </form>
</div>

<!-- end of update product Section -->



<!-- Increase Quantity -->

<div id="incqty" class="modal">
  <span onclick="document.getElementById('incqty').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="IncreaseProductQuantityServlet" method="post">
    <div class="container">
      <h1>Increase Quantity </h1>
      <p>Please fill in this form to increase Quantity</p>
      <hr>
	  
	  	<input type="text" placeholder="Enter Product Id"  name="prodid" id="inc-prod-id" required><br>
		 <input type="number" placeholder="Enter Quantity to be Increased"  min="1" max="1000" step="1" /name="qty" id="inc-prod-qty" required><br>
      
	    
        <button type="button" onclick="document.getElementById('incqty').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn" id="inc-qty-btn">Add More Product Item</button>
      </div>
    </div>
  </form>
</div>

<!-- End of Increase Quantity -->

<!-- Increase Quantity -->

<div id="delprod" class="modal">
  <span onclick="document.getElementById('delprod').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="DeleteProductServlet" method="post">
    <div class="container">
      <h1>Delete Product</h1>
      <p>Please fill in this form to Delete Product</p>
      <hr>
	  
		 <input type="text" placeholder="Enter product Id to be deleted" name="prodid" id="del-prod-id"  required><br>
      
	    
        <button type="button" onclick="document.getElementById('delprd').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn" onclick="deleteAlert()" id="delprodbtn" >Delete Product</button>
      </div>
    </div>
  </form>
</div>

<!-- End of Increase Quantity -->



<script >

function deleteAlert() {
	  alert("Are you sure you want to delete the product!");
	}

function openNav() {
  document.getElementById("mySidenav").style.width = "450px";
  document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft= "0";
  document.body.style.backgroundColor = "white";
}

var mybutton = document.getElementById("toTopBtn");
var myNavbar = document.getElementById("mynavbar");
// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "block";

  } else {
    mybutton.style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}

// Get the modal
var modal = document.getElementById('addprod');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

var upmodal = document.getElementById('upprod');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == upmodal) {
    upmodal.style.display = "none";
  }
}

var incqty = document.getElementById('incqty');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == incqty) {
    incqty.style.display = "none";
  }
}

var delprod = document.getElementById('delprod');

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
if (event.target == delprod) {
 incqty.style.display = "none";
}
}

window.addEventListener('load', function() {
	  document.querySelector('input[type="file"]').addEventListener('change', function() {
	      if (this.files && this.files[0]) {
	          var img = document.querySelector('#myImg');  // $('img')[0]
	          img.src = URL.createObjectURL(this.files[0]); // set src to file url
	          img.onload = imageIsLoaded; // optional onload event listener
	      }
	  });
	});

	function imageIsLoaded(e) { alert(e); }


	$(document).ready(function(){
		
		$('#search').keyup(function(){
	var keywords = $(this).val().trim().toLowerCase().split(' ');

	// select all list items, hide and filter then show
	$('.card').hide().filter(function() {
	    // get the lower case text for the list element
	    var text = $(this).text().toLowerCase();        

	    // determine if any keyword matches, return true on first success
	    for (var i = 0; i < keywords.length; i++) {
	        if (text.indexOf(keywords[i]) >= 0) {
	            return true;
	        }
	    }
	}).show();
	
		});
	});
	
$(document).ready(function(){
	$("#togbtn").change(function(){
	    if($(this).prop("checked") == true){
	    	
	    	$('.card').hide().filter(function() {
	    	    // get the lower case text for the list element
	    	    var text = "In Stock" ;       

	    	    // determine if any keyword matches, return true on first success
	    	    for (var i = 0; i < keywords.length; i++) {
	    	        if (text.indexOf(keywords[i]) >= 0) {
	    	            return true;
	    	        }
	    	    }
	    	}).show();
	       
	    }else{
	    	
	    	$('.card').hide().filter(function() {
	    	    // get the lower case text for the list element
	    	    var text = "In Stock" ;       

	    	    // determine if any keyword matches, return true on first success
	    	    for (var i = 0; i < keywords.length; i++) {
	    	        if (text.indexOf(keywords[i]) >= 0) {
	    	            return true;
	    	        }
	    	    }
	    	}).show();
	       
	    }
	});
	
});

	


</script>



</body>
</html>