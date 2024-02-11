import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

@WebServlet("/Cart")

public class Cart extends HttpServlet {

	int coupon = 0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
;

		if(access.equals("0")){
			System.out.println("Entered access 0");
			coupon = 1;
		}

		
		System.out.print("name" + name + "type" + type + "maker" + maker + "accesee" + access);
		
		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		utility.storeProduct(id,name, type, maker, access);
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		Set<String> types = new HashSet<>();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry'>");
		// pw.print("<form id='Cart' name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			int i = 1;
			double total = 0;
			pw.print("<table class='gridable'>");
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<form name ='CartItem' action='RemovefromCart' method='post'>"+
							"<tr><td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>"+
							"<input type='hidden' name='orderItemName' value='"+oi.getName()+"'>"+
							"<td></td><td></td><td><input type='submit' name='removefromcart' value='RemoveItem'></td></tr></form>");
				total = total +oi.getPrice();
				types.add(oi.getType());
				i++;
			}
			
			double price = total;

			if(coupon==1)
			{
				price = total * (1 - 0.15);
				coupon = 0;
			}

			BigDecimal bd = BigDecimal.valueOf(price);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			

			pw.print("</div>");
			pw.print("<br></br><tr><th></th><b style='color :red'>Total:	</b><b style='color :red'>"+bd.doubleValue()+"</b>");
			
			pw.print("<br></br><br></br><p style='color:blue;font-weight:bold;font-size:18px'> Enter Promo Code here</p>");
			if(coupon ==0) 
			{
				pw.print("<tr><input class='form-control' type='text' style= 'max-width:250px'/></tr>");
				pw.print("<tr><form action='Cart' method='post'><input type='submit' class='btn btn-primary' value='Apply'><input type='hidden' name='name' value='0'><input type='hidden' name='type' value='0'><input type='hidden' name='maker' value='0'><input type='hidden' name='access' value='0'></form></tr>");
			}
			else
			{
			    pw.print("<tr><h4 style = 'color=blue'>Promotional Discount of 15% is applied on the total bill </h4></tr>");
			}

			pw.print("<form name ='ChkoutBtn' action='CheckOut' method='post'><tr><td></td><td></td><td></td>"+
			"<td><input class='form-control' type='hidden'  style='margin:60%' name='orderTotal' value='"+bd.doubleValue()+"'>"+
			"<input type='submit' class='btn btn-primary' style='margin:40%' name='CheckOut' value='CheckOut'></td></tr></table>");
			pw.print("<br><br><br><br>");

			for(String type : types)
			{
				if(type.equals("doorbells")){
					pw.print("<h2>Accessory for Door Bells");
					pw.print("<table><tr><td><div id='shop_item'>");
					pw.print("<h3> Wedge Mount </h3>");
					pw.print("<strong>$ 7.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/wedge_mount.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='wedgemount'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='blink'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td>");
		
					pw.print("<td><div id='shop_item'>");
					pw.print("<h3> Rain Cover </h3>");
					pw.print("<strong>$ 23.86 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/rain_cover.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='raincover'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='HomeAll'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td></tr></table>");
		
		
		
				}
				else if (type.equals("speakers"))
				{
		
					pw.print("<h2>Accessory for Speakers");
					pw.print("<table><tr><td><div id='shop_item'>");
					pw.print("<h3> Holder </h3>");
					pw.print("<strong>$ 12.59 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/holder.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='holder'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='holder'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td>");
		
					pw.print("<td><div id='shop_item'>");
					pw.print("<h3> Mount It </h3>");
					pw.print("<strong>$ 16.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/mountit.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='mountit'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='mountit'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td></tr></table>");
		
				}
				else if (type.equals("lightings"))
				{
		
					pw.print("<h2>Accessory for Lightings");
					pw.print("<table><tr><td><div id='shop_item'>");
					pw.print("<h3> Wall Switch Module </h3>");
					pw.print("<strong>$ 44.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/philips_2.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='switchmodule'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='philips'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td>");
		
					pw.print("<td><div id='shop_item'>");
					pw.print("<h3> Tap Dial Switch </h3>");
					pw.print("<strong>$ 49.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/philips_3.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='tapdialswitch'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='philips'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td></tr></table>");
		
				}
			}
			pw.print("</form>");

			//pw.print(carousel.carouselfeature(utility));
	
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}
