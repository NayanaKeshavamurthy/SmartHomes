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

@WebServlet("/ThermostatsList")

public class ThermostatsList extends HttpServlet {

	/* Trending Page Displays all the Thermostats and their Information */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Thermostats type whether it is amazon or echobee  or google or Mysa */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Thermostats> hm = new HashMap<String, Thermostats>();

		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("Thermostats");
		List<Thermostats> listThermostats = new ArrayList<>();
		int l_size = list.size();

		if (CategoryName == null)	
		{
			int i=0;
			for(AllProducts allProds : list) {
				Thermostats thermostat = allProds.getThermostat();
				hm.put("Thermostat"+ i, thermostat);
				i++;
			}
			name = "Thermostats";
		} 
		else 
		{
			int i=0;
			for(AllProducts allProds : list) 
			{
				Thermostats thermostat = allProds.getThermostat();
				String retailer = thermostat.getRetailer();
				if(thermostat.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("Thermostat"+ i, thermostat);
					i++;
					name = thermostat.getRetailer() + " Thermostats";
				}
			}
			
	    }

		/* Header, Left Navigation Bar are Printed.

		All the Thermostats and Thermostats information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + "</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Thermostats> entry : hm.entrySet()) {
			Thermostats thermostat = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + thermostat.getName() + "</h3>");
			pw.print("<strong>" + thermostat.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/thermostats/"
					+ thermostat.getImage() + "' alt='' /></li>");
			pw.print("<strong>"+thermostat.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			double totPrice = thermostat.getPrice() - ((thermostat.getPrice()*thermostat.getDiscount())/100);
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ thermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+thermostat.getId()+"'>"+
					"<input type='hidden' name='type' value='thermostats'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+thermostat.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+thermostat.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success'  value='Buy Now'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+thermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+thermostat.getId()+"'>"+
					"<input type='hidden' name='type' value='thermostats'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+Double.toString(totPrice)+"'>"+
					"<input type='hidden' name='img' value='"+thermostat.getImage()+"'>"+
					"<input type='hidden' name='des' value='"+thermostat.getDescription()+"'>"+
					"<input type='hidden' name='access' value=''>" +
					"<div class ='btn-group'>" +
						"<input type='submit' class='btn btn-default' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ thermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+thermostat.getId()+"'>"+
					"<input type='hidden' name='category' value='Thermostats'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+thermostat.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+thermostat.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-warning' value='Review Product'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ thermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+thermostat.getId()+"'>"+
					"<input type='hidden' name='category' value='Thermostats'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+thermostat.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+thermostat.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class=btn btn-default' value='View Product Reviews'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
