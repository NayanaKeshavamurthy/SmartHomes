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

@WebServlet("/LightingsList")

public class LightingsList extends HttpServlet {

	/* Trending Page Displays all the Lightings and their Information in Smart Home */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Lightings type whether it is et2 or philips or gowing */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Lightings> hm = new HashMap<String, Lightings>();

		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("Lightings");
		List<Lightings> listlLightings = new ArrayList<>();
		int l_size = list.size();

		if(CategoryName==null)
		{
			int i=0;
			for(AllProducts allProds : list) {
				Lightings lighting = allProds.getLighting();
				hm.put("Lighting"+ i, lighting);
				i++;
			}
			name = "Lightings";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) 
			{
				Lightings lighting = allProds.getLighting();
				String retailer = lighting.getRetailer();
				if(lighting.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("Lighting"+ i, lighting);
					i++;
					name = lighting.getRetailer() + " Lightings";
				}
			}		
		}

		/* Header, Left Navigation Bar are Printed.

		All the Lightings and Lighting information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + "</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Lightings> entry : hm.entrySet()) {
			Lightings lighting = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + lighting.getName() + "</h3>");
			pw.print("<strong>" + lighting.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/lightings/"
					+ lighting.getImage() + "' alt='' /></li>");
			pw.print("<strong>"+lighting.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			double totPrice = lighting.getPrice() - ((lighting.getPrice()*lighting.getDiscount())/100);
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ lighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+lighting.getId()+"'>"+
					"<input type='hidden' name='type' value='lightings'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+lighting.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+lighting.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success'  value='Buy Now'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+lighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+lighting.getId()+"'>"+
					"<input type='hidden' name='type' value='lightings'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+Double.toString(totPrice)+"'>"+
					"<input type='hidden' name='img' value='"+lighting.getImage()+"'>"+
					"<input type='hidden' name='des' value='"+lighting.getDescription()+"'>"+
					"<input type='hidden' name='access' value=''>" +
					"<div class ='btn-group'>" +
						"<input type='submit' class='btn btn-default' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ lighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+lighting.getId()+"'>"+
					"<input type='hidden' name='category' value='Lightings'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+lighting.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+lighting.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-warning' value='Review Product'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ lighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+lighting.getId()+"'>"+
					"<input type='hidden' name='category' value='Lightings'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+lighting.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+lighting.getDescription()+"'>"+
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
