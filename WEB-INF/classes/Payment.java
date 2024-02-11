import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.time.*;


@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		LocalDate orderDate = LocalDate.now();
		System.out.println("Today's date is: "+orderDate);
		System.out.println("2 weeks from now: "+orderDate.plusDays(14));
		

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zipcode=request.getParameter("zipcode");
		String phone=request.getParameter("phone");
		String creditCardNo=request.getParameter("creditCardNo");
		String deliveryType = request.getParameter("order");		
		String total = request.getParameter("orderTotal");
		String storelocation = request.getParameter("storelocation");
		System.out.print("the user address is" +address1);
		System.out.print(creditCardNo);
		if(!firstname.isEmpty() && !lastname.isEmpty() && !address1.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zipcode.isEmpty() && !phone.isEmpty() && !creditCardNo.isEmpty())
		{
			// Random rand = new Random(); 
			// int orderId = rand.nextInt(100);
			int orderId= MySQLDataStoreUtilities.getOrderID();

			//iterate through each order
			MySQLDataStoreUtilities.storeCustomerDetails(orderId,firstname+lastname,address1+address2,city,state,Long.parseLong(zipcode),Long.parseLong(phone),Long.parseLong(creditCardNo),deliveryType,orderDate.toString(),orderDate.plusDays(14).toString(),total,storelocation);
			
			for (OrderItem oi : utility.getCustomerOrders())
			{

				//set the parameter for each column and execute the prepared statement

				utility.storePayment(orderId,oi.getName(),oi.getPrice(),firstname,creditCardNo);
				MySQLDataStoreUtilities.updateProductQuantity(oi.getId());
			}

			//remove the order details from cart after processing
			
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2>Your Order is placed succesfully");
			pw.print("&nbsp&nbsp");  
			pw.print("<br>Your Order No is "+(orderId));
			pw.print("<br>Delivery/pickup date is "+(orderDate.plusDays(14)));
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
