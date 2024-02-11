import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ProductChanges")

public class ProductChanges extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Enterted doPost");
		String pcategory = request.getParameter("pcategory");
		String prod_name = request.getParameter("pname");
		String operation = request.getParameter("operation");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Utilities util = new Utilities(request, out);

		util.printHtml("Header1.html");
		try {
		if(operation.equals("add")) {
			String prod_desc = request.getParameter("pdescription");
			String prod_price = request.getParameter("pprice");
			String prod_retailer = request.getParameter("pcompany");
			String prod_disc = request.getParameter("pdisc");
			String pImage = request.getParameter("pimage");
			String pcondition = request.getParameter("pcondition");
			String pRebate = request.getParameter("pRebate");
			String pSale = request.getParameter("pSale");
			System.out.println(prod_name + " " + prod_desc + " " + operation);
			MySQLDataStoreUtilities.addNewProduct(pcategory, prod_name, Double.parseDouble(prod_price), prod_desc, prod_retailer, Double.parseDouble(prod_disc), pcondition, pImage,pRebate,pSale);
			out.print("<div><h2> Product Added Succesfully!</h2></div></br></br>");
		}
		else if (operation.equals("delete")) {
			System.out.println(prod_name + " " );
			MySQLDataStoreUtilities.deleteProduct(Integer.parseInt(prod_name));
			out.print("<div><h2> Product Deleted Succesfully!</h2></div></br></br>");
			
		}
		else if(operation.equals("update")) {
		    String prod_desc = request.getParameter("pdescription");
            String prod_price = request.getParameter("pprice");
            String prod_retailer = request.getParameter("pcompany");
            String prod_disc = request.getParameter("pdisc");
            String pImage = request.getParameter("pimage");
            String pcondition = request.getParameter("pcondition");
            String prodID = request.getParameter("prodID");
			String pRebate = request.getParameter("pRebate");
            String pSale = request.getParameter("pSale");
            String pQuantity = request.getParameter("pquantity");
            System.out.println(prod_name + " " + prod_desc + " " + operation);
            MySQLDataStoreUtilities.updateProductDetails(Integer.parseInt(prodID),pcategory, prod_name, Double.parseDouble(prod_price), prod_desc, prod_retailer, Double.parseDouble(prod_disc), pcondition, pImage,pRebate,pSale,pQuantity);
            out.print("<div><h2> Product updated Succesfully!</h2></div></br></br>");
            
            System.out.println(prod_name + " " + prod_desc + " " + operation + " prodID : " + prodID);
		    
		}
		//addNewProduct(String prodCategory,String prodName,Double price,String prodDesc,String prodRetailer,Double prodDisc,String prodCondition,String prodImg)
		
		
		//test
		//util.printHtml("Footer.html");
		}catch (Exception e) {
		e.printStackTrace();
		}
		
	}
	
}