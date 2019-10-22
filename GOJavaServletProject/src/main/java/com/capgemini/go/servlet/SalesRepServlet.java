package com.capgemini.go.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.go.dao.GoAdminDao;
import com.capgemini.go.dao.GoAdminDaoImpl;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.utility.GenerateOrderID;

/**
 * Servlet implementation class SalesRepServlet
 */
public class SalesRepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		

		
		try {
			GoAdminDao go = new GoAdminDaoImpl();
			String userId = request.getParameter("UserId");
			int n, i = 0;
			out.println("<html><head><style>\r\n" + "table, th, td {\r\n" + "  border: 1px solid black;\r\n"
					+ "  border-collapse: collapse;\r\n" + "}\r\n" + "th, td {\r\n" + "  padding: 15px;\r\n" + "}\r\n"
					+ "</style>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n" + "<title>Cancel Order</title>\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n" + "<script>\r\n"
					+ "    src = \"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\">\r\n"
					+ "</script>\r\n" + "<script src=\"js/bootstrap.min.js\"></script>\r\n"
					+ "<link rel=\"stylesheet\"\r\n"
					+ "    href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n"
					+ "<script\r\n"
					+ "    src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n"
					+ "<script\r\n"
					+ "    src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n"
					+ "<script\r\n"
					+ "    src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n"
					+ "</head>");
			List<SalesRepDTO> reports = null;
			if (userId.equalsIgnoreCase("ALL")) {

				reports = go.viewAllSalesRepData();

				n = reports.size();
				out.println(
						"<body><table><tr><td>UserId</td><td>TargetSales</td><td>Target</td><td>currentTargetSales</td><td>Bonus</td></tr>");

				while (i < n) {
					out.println("<tr><td>" + reports.get(i).getUserId() + "</td><td>" + reports.get(i).getTarget()
							+ "</td><td>" + reports.get(i).getTargetStatus() + "</td><td>"
							+ reports.get(i).getCurrentTargetStatus() + "</td><td>" + reports.get(i).getBonus()
							+ "</td></tr>");

					i++;
				}
				out.println("<button type=\"button\" class=\"btn btn-secondary custom\"\r\n"
						+ "						onclick=\"location.href='ReportType.html';\"\r\n"
						+ "						style=\"left-align: 50px\">GO BACK TO REPORT SELECTION</button>");

			} else {

				SalesRepDTO report = go.viewSalesRepData(userId);
				out.println(
						"<body><table><tr><td>UserId</td><td>TargetSales</td><td>Target</td><td>currentTargetSales</td><td>Bonus</td></tr>");

				out.println("<tr><td>" + report.getUserId() + "</td><td>" + report.getTarget() + "</td><td>"
						+ report.getTargetStatus() + "</td><td>" + report.getCurrentTargetStatus() + "</td><td>"
						+ report.getBonus() + "</td></tr></table>");
				out.println("<button type=\"button\" class=\"btn btn-secondary custom\"\r\n"
						+ "						onclick=\"location.href='setTarget.html';\"\r\n"
						+ "						style=\"left-align: 50px\">SET TARGET</button>");
				out.println("<button type=\"button\" class=\"btn btn-secondary custom\"\r\n"
						+ "						onclick=\"location.href='setBonus.html';\"\r\n"
						+ "						style=\"left-align: 50px\">SET BONUS</button>");

			}
			
		} catch (Exception exp) {
			
			out.println(exp.getMessage());
			
		} finally {
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/pages/footer.html"); 
			//rd.include(request, response); 
			out.close();
		}
	}

}
