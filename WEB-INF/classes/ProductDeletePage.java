import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductDeletePage")

public class ProductDeletePage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		Utilities util=new Utilities(request,out);
		
		util.printHtml("Header1.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:Blue;'>"+"Delete Product</span></h1> ");
		out.println("<form  style='border:2px #f1f1f1 solid;' action='ProductChanges' method='post' ><div style='padding:12px;'>");
		out.println("<label><b>Product Name</b></label><select name='pname'>");

		HashMap<Integer,String> map = new HashMap <>();
		map = MySQLDataStoreUtilities.getAllProducts();
		for(Map.Entry<Integer,String> map1 : map.entrySet()) {
			System.out.println(map1.getKey());
			out.print("<option value = '" + map1.getKey()+ "'>" + map1.getValue() + "</option>");
		}

		out.println("</select></br></br>");
		out.println("<input type='hidden' name='operation' value='delete'>");
		out.println("<br/> <br/> <input type='submit' name='Delete Product' class='btn btn-primary btn-lg' style='width : 90%' value='Delete Product'>");
		out.println("</div></form>");
		out.println("</article></section>");
  
		// util.printHtml("Footer.html");
		
	}
}