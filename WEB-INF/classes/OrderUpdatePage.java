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

@WebServlet("/OrderUpdatePage")

public class OrderUpdatePage extends HttpServlet
{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		Utilities util=new Utilities(request,out);

		// check if the user is logged in
		if (!util.isLoggedin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}

		String username = util.username();
		String usertype = util.usertype();
		util.printHtml("Header2.html");
		out.print("<form name ='ViewOrder' action='OrderUpdatePage' method='get'>");
		out.print("<div id='content'><div class='post'><h2 class='title meta'>");
		out.print("<a style='font-size: 24px;'>Order</a>");
		out.print("</h2><div class='entry'>");

		/*
		 * check if the order button is clicked
		 * if order button is not clicked that means the view order page is visited
		 * freshly
		 * then user will get textbox to give order number by which he can view order
		 * if order button is clicked user will be directed to this same servlet and
		 * user has given order number
		 * then this page shows all the order details
		 */

		if (request.getParameter("Order") == null) {
			out.print("<table align='center'><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
			out.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");
		}


		/*
		 * if order button is clicked that is user provided a order number to view order
		 * order details will be fetched and displayed in a table
		 * Also user will get an button to cancel the order
		 */

		if (request.getParameter("Order") != null && request.getParameter("Order").equals("ViewOrder")) 
		{
			if (request.getParameter("orderId") != null && request.getParameter("orderId") != "") {
				int orderId = Integer.parseInt(request.getParameter("orderId"));
				out.print("<input type='hidden' name='orderId' value='" + orderId + "'>");
				
				List<OrderPayment> list = new ArrayList<>();
				list = MySQLDataStoreUtilities.fetchOrders(orderId);
				int size = list.size();

				/*
				 * get the order size and check if there exist an order with given order number
				 * if there is no order present give a message no order stored with this id
				 */

				
				// display the orders if there exist order with order id
				if (size > 0) {
					out.print("<table  class='gridtable'>");
					out.print("<tr><td></td>");
					out.print("<td>OrderId:</td>");
					out.print("<td>UserName:</td>");
					out.print("<td>productOrdered:</td>");
					out.print("<td>productPrice:</td></tr>");
					for (OrderPayment oi : list) {
						out.print("<tr>");
						out.print("<td><input type='radio' name='orderName' value='" + oi.getOrderName() + "'></td>");
						out.print("<td>" + oi.getOrderId() + ".</td><td>" + oi.getUserName() + "</td><td>"
								+ oi.getOrderName() + "</td><td>Price: " + oi.getOrderPrice() + "</td>");
						out.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						out.print("<td><input type='submit' name='Order' value='UpdateOrder' class='btnbuy'></td>");
						out.print("</tr>");

					}
					out.print("</table>");
				} else {
					out.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			} else

			{
				out.print("<h4 style='color:red'>Please enter the valid order number</h4>");
			}
		}
		// if the user presses cancel order from order details shown then process to
		// cancel the order
		
		if (request.getParameter("Order") != null && request.getParameter("Order").equals("CancelOrder")) 
		{
			if (request.getParameter("orderName") != null) 
			{
				String orderName = request.getParameter("orderName");
				int orderId = 0;
				orderId = Integer.parseInt(request.getParameter("orderId"));
				
				MySQLDataStoreUtilities.cancelOrders(orderId, orderName);
				out.print("<div> <h2> Order Cancelled. The amount will be refunded to the credit card on file </h2></div>");

			} else {
				out.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
		out.print("</form></div></div></div>");
		util.printHtml("Footer.html");

	}
}