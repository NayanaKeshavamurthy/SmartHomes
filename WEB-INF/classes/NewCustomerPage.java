import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/NewCustomerPage")

public class NewCustomerPage extends HttpServlet
{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);
		if(request.getAttribute("checkMsg") != null)
		{
			out.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}	
		util.printHtml("Header2.html");
		out.print("<div class='post' style='float: none; width: 100%'>");
		out.print("<h2 class='title meta'><a style='font-size: 24px;'>Add New Customer</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		out.print("<form method='post' action='Registration'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Name</h3></td><td><input type='text' name='name' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Re-Password</h3></td><td><input type='password' name='repassword' value='' class='input' required></input>"	
				+ "</td></tr><tr><td>"
				+ "<h3>Street Address</h3></td><td><input type='text' name='address' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
                + "<h3>City</h3></td><td><input type='text' name='city' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
                + "<h3>State</h3></td><td><input type='text' name='state' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
                + "<h3>Zip code</h3></td><td><input type='text' name='zip' value='' class='input' required></input>"
                + "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer'>Customer</option><option value='storeManager'>Store Manager</option><option value='salesManager'>Salesman</option></select>"	
				+ "</td></tr>");
		out.print("<table><tr><td>");
		out.print("</div>");
		out.print("<h3>Select nearest store</h3> </td>");
				List<StoreDetails> list2 = new ArrayList<>();
				list2 = MySQLDataStoreUtilities.getAllStoreDetails();
				out.print("<td><select name='storelocation' id='storelocation'>");
				for(StoreDetails storeDetails : list2) {
					out.print("<option value='"+storeDetails.getId()+"'> "+storeDetails.getName() + " - "+ storeDetails.getZipcode() +"</option>");
				}
				out.print("</select>");
				out.print("</td></tr></table>");
				out.print("<input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
	}
}