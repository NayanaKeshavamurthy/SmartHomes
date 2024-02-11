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

@WebServlet("/AddProductPage")

public class AddProductPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		Utilities util=new Utilities(request,out);
		
		util.printHtml("Header1.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:red;'>" + "Add Product</span></h1> ");
		out.println("<form  style='border:2px #f1f1f1 solid;' action='ProductChanges' method='post' ><div style='padding:12px;'>");
		
		out.println(
				"<label><b>Product Name</b></label><input style='width:100%' type='text' placeholder='Enter Product Name' name='pname' required \"></br></br>");
		out.println(
				"<label><b>Product Description</b></label></br><input style='width:100%;' placeholder='Enter Product Description' name='pdescription' required></input></br></br>");
		out.println(
				"<label><b>Product Discount</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Discount' name='pdisc' required \"></br></br>");
		out.println(
				"<label><b>Product Price</b></label><input style='width:100%' type='text' placeholder='Enter Product Price' name='pprice' \"></br></br>");
		out.println(
				"<label><b>Enter Product Image Name</b></label></br><input style='width:100%;' type='text'  placeholder='Enter Product Image Name(with Extension)' name='pimage' required ></br></br>");
		out.println(
				"<label><b>Product Condition</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Condition' name='pcondition' required \"></br></br>");
		out.println(
				"<label><b>Product Company</b></label></br><input style='width:100%;' type='text' placeholder='Enter Product Company' name='pcompany' required \"></br></br>");
		out.println("<input type='hidden' name='operation' value='add'>");
		out.println(
                "<label><b>Is there any rebate on the product?</b></label></br><select name='pRebate'><option value='1' selected>Yes</option><option value='0'>No</option></select></br></br>");
		out.println(
                "<label><b>Is there any sale on the product?</b></label></br><select name='pSale'><option value='1' selected>Yes</option><option value='0'>No</option></select></br></br>");
		out.println("<label><b>Select Category</b></label></br><select name='pcategory'><option value='Doorbells' selected>Smart Door Bells</option><option value='Doorlocks'>Smart Door Locks</option><option value='Speakers'>Smart Speakers</option><option value='Lightings'>Smart Lightings</option><option value='Thermostats'>Smart Thermostats</option></select>");

		out.println("<br/> <br/> <input type='submit' name='Add Product' class='btn btn-primary btn-lg' style='width : 90%' value='Add Product'>");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("Enterted doPost");
		String prod_name = request.getParameter("pname");
		String prod_desc = request.getParameter("pdescription");
		String prod_col = request.getParameter("pcolor");
		String prod_price = request.getParameter("pprice");
		String pcategory = request.getParameter("pcategory");
		System.out.println(prod_name + " " + prod_desc);
		
	}
}