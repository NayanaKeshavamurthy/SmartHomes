import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginPage")

public class LoginPage extends HttpServlet
{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
        Utilities utility=new Utilities(request,pw);
        
        utility.printHtml("Header.html");
        pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if(request.getAttribute("checkMsg") != null)
		{
			pw.println("<h3 style='color:black;background-color:blue;'>"+(String)(request.getAttribute("checkMsg"))+"</h3>");
			request.setAttribute("checkMsg","");
		}
		

		pw.print("<form method='post' action='CheckLogin'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='retailer'>Store Manager</option><option value='manager'>Salesman</option></select>"
				+ "</td></tr><tr><td></td><td>"
				+ "<input type='submit' class='btnbuy' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td><td>"
				+ "<strong><a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div>");
		utility.printHtml("Footer.html");
	}
}