import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccessoryList")

public class AccessoryList extends HttpServlet {

	/* Accessory Page Displays all the Accessories and their Information in Smart Home */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Tv maker whether it is microsft or sony or nintendo 
		   Add the respective product value to hashmap  */

		String CategoryName = request.getParameter("maker");


		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

			if(CategoryName.equals("doorbells"))
			{

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
			else if (CategoryName.equals("speakers"))
			{

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
			else if (CategoryName.equals("lightings"))
			{

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
			else if (CategoryName.equals("all"))
			{
				pw.print("<table><tr><td><div id='shop_item'>");
				pw.print("<h3> Wedge Mount </h3>");
				pw.print("<strong>$ 7.99 </strong><ul>");
				pw.print("<li id='item'><img src='images/accessories/wedge_mount.jpg' alt='' /></li>");
				
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='wedgemount'>"+
						"<input type='hidden' name='type' value='accessories'>"+
						"<input type='hidden' name='maker' value='wedgemount'>"+
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
						"<input type='hidden' name='maker' value='raincover'>"+
						"<input type='hidden' name='access' value=''>"+
						"<div class='btn-group'>"+
						"<input type='submit' class='btn btn-success' value='Buy Now'>"+
						"</div></form> </td></tr></table>");

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

				pw.print("<table><tr><td><div id='shop_item'>");
				pw.print("<h3> Wall Switch Module </h3>");
				pw.print("<strong>$ 44.99 </strong><ul>");
				pw.print("<li id='item'><img src='images/accessories/philips_2.jpg' alt='' /></li>");
				
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='switchmodule'>"+
						"<input type='hidden' name='type' value='accessories'>"+
						"<input type='hidden' name='maker' value='switchmodule'>"+
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
						"<input type='hidden' name='maker' value='tapdialswitch'>"+
						"<input type='hidden' name='access' value=''>"+
						"<div class='btn-group'>"+
						"<input type='submit' class='btn btn-success' value='Buy Now'>"+
						"</div></form> </td></tr></table>");


			}
					
		utility.printHtml("Footer.html");	
		}	
		
	}

