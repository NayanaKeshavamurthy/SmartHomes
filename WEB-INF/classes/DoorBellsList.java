import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DoorBellsList")

public class DoorBellsList extends HttpServlet {

	/* Doorbells Page Displays all the Doorbells and their Information in  Smart Home */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Door Bell type whether it is arlo or blink or google or ring */

		HashMap<String, DoorBells> hm = new HashMap<String, DoorBells>();
		
		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("Doorbells");
		List<DoorBells> listlDoorBells = new ArrayList<>();
		int l_size = list.size();

		if(CategoryName==null)
		{
			int i=0;
			for(AllProducts allProds : list) {
				DoorBells doorbell = allProds.getDoorbell();
				hm.put("Doorbell"+ i, doorbell);
				i++;
			}
			name = "Doorbells";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) 
			{
				DoorBells doorBell = allProds.getDoorbell();
				String retailer = doorBell.getRetailer();
				if(doorBell.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("Doorbell"+ i,doorBell);
					i++;
					name = doorBell.getRetailer() + " Doorbells";
				}
			}		
		}

		/* Header, Left Navigation Bar are Printed.

		All the Doorbell and Doorbell information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+"</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, DoorBells> entry : hm.entrySet())
		{
			DoorBells doorbell = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+doorbell.getName()+"</h3>");
			pw.print("<strong>$"+doorbell.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/doorbells/"+doorbell.getImage()+"' alt='' /></li>");
			
			pw.print("<strong>"+doorbell.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			double totPrice = doorbell.getPrice() - ((doorbell.getPrice()*doorbell.getDiscount())/100);
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='doorbells'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success'  value='Buy Now'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='doorbells'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+Double.toString(totPrice)+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='des' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='access' value=''>" +
					"<div class ='btn-group'>" +
						"<input type='submit' class='btn btn-default' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='category' value='Doorbells'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-warning' value='Review Product'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='category' value='Doorbells'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class=btn btn-default' value='View Product Reviews'></form></li>");
			
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
