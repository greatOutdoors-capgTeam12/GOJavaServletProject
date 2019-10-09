package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;

/**
 * Servlet implementation class DeleteAddressServlet
 */
public class DeleteAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		String address_ID = request.getParameter("address_ID");
		try {
			out.println("<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>\r\n"
					+ "  <title>Product Catalogue</title>\r\n" + "  <meta charset=\"utf-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\r\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n"
					+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n"
					+ "  <link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\">\r\n"
					+ "  <link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n"
					+ "  <script src='https://kit.fontawesome.com/a076d05399.js'></script>\r\n"
					+ "  <script type=\"text/javascript\" src=\"resources/js/home.js\"></script>\r\n"
					+ "  <link rel = \"stylesheet\" href=\"resources/css/home.css\" type=\"text/css\">\r\n"
					+ "  <link rel = \"stylesheet\" href=\"resources/css/product.css\" type=\"text/css\">\r\n"
					+ "  <link rel = \"stylesheet\" href=\"resources/css/forms.css\" type=\"text/css\">\r\n" + "  \r\n"
					+ "\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "<div id=\"main\">\r\n"
					+ "<button onclick=\"topFunction()\" id=\"toTopBtn\" title=\"Go to top\"><span class=\"glyphicon glyphicon-chevron-up\"></span></button>\r\n"
					+ "\r\n" + "\r\n" + "<!-- Navbar Section -->\r\n"
					+ "<nav class=\"navbar navbar-default navbar-fixed-top\" id=\"mynavbar\">\r\n"
					+ "  <div class=\"container\">\r\n" + "    <div class=\"navbar-header\">\r\n"
					+ "      <button type=\"button\" class=\"navbar-toggle navbar-toggler-icon\" data-toggle=\"collapse\" data-target=\"#myNavbar\">\r\n"
					+ "        <span class=\"icon-bar\" ></span>\r\n" + "        <span class=\"icon-bar\"></span>\r\n"
					+ "        <span class=\"icon-bar\"></span>\r\n" + "      </button>\r\n"
					+ "      <a class=\"navbar-brand\" href=\"#\">GO</a>\r\n" + "    </div>\r\n" + "\r\n"
					+ "    <div class=\"collapse navbar-collapse\" id=\"myNavbar\">\r\n"
					+ "      <ul class=\"nav navbar-nav navbar-right\">\r\n"
					+ "        <li><a href=\"index.html\"><span class=\"fas fa-home\"></span>Home</a></li>\r\n"
					+ "        \r\n" + "      <li><a href=\"#contact\">Contact</a></li>\r\n" + "      </ul>\r\n"
					+ "    </div>\r\n" + "  </div>\r\n" + "</nav>\r\n" + "\r\n" + "<!-- end of Navbar Section -->");
			AddressDTO address = new AddressDTO(address_ID, null, null, null, null, null, null, false);
			RetailerService retailer = new RetailerServiceImpl();
			boolean status = retailer.deleteAddress(address);

			if (status == true) {
				out.println("<div id = \"msg\" class=\"container-fluid \"><p><h2>Address with  Address ID : "
						+ address_ID + " has been successfully deleted</h2></p></div>");
			}
		} catch (Exception exp) {
			out.println("<div id = \"err\" class=\"container-fluid \"><p><h2>Error in deleting address >> "
					+ exp.getMessage() + "</h2></p></div>");
		} finally {
			out.println("<!-- Customer Say Section -->\r\n" + "<div id=\"customer\" class=\"container-fluid\">\r\n"
					+ " <h1 class=\"text-center blue-heading w3-animate-top\">What our customers say</h1>\r\n"
					+ "  <div id=\"myCarousel\" class=\"carousel slide text-center\" data-ride=\"carousel\">\r\n"
					+ "    <!-- Indicators -->\r\n" + "    <ol class=\"carousel-indicators\">\r\n"
					+ "      <li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>\r\n"
					+ "      <li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>\r\n"
					+ "      <li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>\r\n" + "    </ol>\r\n" + "\r\n"
					+ "    <!-- Wrapper for slides -->\r\n"
					+ "    <div class=\"carousel-inner bg-grey\" role=\"listbox\">\r\n"
					+ "      <div class=\"item active\">\r\n"
					+ "        <img src=\"resources/images/customer/customer1.jpg\" class=\"img-rounded customer-img text-center\" alt=\"Name\">\r\n"
					+ "        <h4>\"coment\"<br><span>Name, Desg, company</span></h4>\r\n" + "      </div>\r\n"
					+ "      <div class=\"item\">\r\n"
					+ "        <img src=\"resources/images/customer/customer2.jpg\" class=\"img-rounded customer-img text-center\" alt=\"Name\">\r\n"
					+ "        <h4>\"content\"<br><span>Name, Desg, company</span></h4>\r\n" + "      </div>\r\n"
					+ "      <div class=\"item\">\r\n"
					+ "        <img src=\"resources/images/customer/customer3.jpg\" class=\"img-rounded customer-img text-center\" alt=\"Name\">\r\n"
					+ "        <h4>\"content\"<br><span>Name, Desg, company</span></h4>\r\n" + "      </div>\r\n"
					+ "    </div>\r\n" + "\r\n" + "    <!-- Left and right controls -->\r\n"
					+ "    <a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">\r\n"
					+ "      <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\r\n"
					+ "      <span class=\"sr-only\">Previous</span>\r\n" + "    </a>\r\n"
					+ "    <a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">\r\n"
					+ "      <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\r\n"
					+ "      <span class=\"sr-only\">Next</span>\r\n" + "    </a>\r\n" + "  </div>\r\n" + "</div>\r\n"
					+ "</div>\r\n" + "<!-- end of Customer Say Section --> \r\n" + "\r\n"
					+ "<!-- Contact Section -->\r\n" + "\r\n" + "<div id=\"contact\" class=\"container-fluid \">\r\n"
					+ "  <h2 class=\"text-center blue-heading\">CONTACT</h2>\r\n" + "  <div class=\"row\">\r\n"
					+ "    <div class=\"col-sm-5\">\r\n"
					+ "      <p>Contact us and we'll get back to you within 24 hours.</p>\r\n"
					+ "      <p><span class=\"glyphicon glyphicon-map-marker\"></span> Glassglow, UK</p>\r\n"
					+ "      <p><span class=\"glyphicon glyphicon-phone\"></span> +00 00000000</p>\r\n"
					+ "      <p><span class=\"glyphicon glyphicon-envelope\"></span> info@greatoutdoors.com</p>\r\n"
					+ "    </div>\r\n" + "    <div class=\"col-sm-7\">\r\n" + "      <div class=\"row\">\r\n"
					+ "        <div class=\"col-sm-6 form-group\">\r\n"
					+ "          <input class=\"form-control\" id=\"name\" name=\"name\" placeholder=\"Name\" type=\"text\" required>\r\n"
					+ "        </div>\r\n" + "        <div class=\"col-sm-6 form-group\">\r\n"
					+ "          <input class=\"form-control\" id=\"email\" name=\"email\" placeholder=\"Email\" type=\"email\" required>\r\n"
					+ "        </div>\r\n" + "      </div>\r\n"
					+ "      <textarea class=\"form-control\" id=\"comments\" name=\"comments\" placeholder=\"Comment\" rows=\"5\"></textarea><br>\r\n"
					+ "      <div class=\"row\">\r\n" + "        <div class=\"col-sm-12 form-group\">\r\n"
					+ "          <button class=\"btn btn-default pull-right\" type=\"submit\">Send</button>\r\n"
					+ "        </div>\r\n" + "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n" + "</div>\r\n"
					+ "\r\n" + "<!--end of contact -->\r\n" + "\r\n" + "<!-- Map Section -->\r\n"
					+ "<div class=\"embed-responsive embed-responsive-100x400px\">\r\n"
					+ "<iframe src=\"https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d35818.719732048536!2d-4.25169!3d55.868392!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x488815562056ceeb%3A0x71e683b805ef511e!2sGlasgow%2C+Glasgow+City%2C+UK!5e0!3m2!1sen!2sus!4v1448625188752\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>\r\n"
					+ "  </div>\r\n" + "  <!-- end of Map Section -->\r\n" + "\r\n" + "  <!-- Footer Section -->\r\n"
					+ "  <footer class=\"container-fluid text-center\">\r\n"
					+ "  <p>Copyright(2019) Reserves Great Outdoors Pvt .Ltd <br><a href=\"#\" title=\"Visit www.greatoutdoors.com\">www.greatoutdoors.com</a></p>\r\n"
					+ "</footer>\r\n" + "  <!--End of Footer Section -->\r\n");
			out.close();
		}
	}

}
