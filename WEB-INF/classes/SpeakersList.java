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

@WebServlet("/SpeakersList")

public class SpeakersList extends HttpServlet {

	/* SpeakersList Page Displays all the speaker and their Information in speaker Speed */
    
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Speakers type whether it is bose or apple or amazon or sonos */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Speakers> hm = new HashMap<String, Speakers>();

		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("Speakers");
		List<Speakers> listspeakers = new ArrayList<>();
		int l_size = list.size();
		
		if(CategoryName==null)
		{
			int i=0;
			for(AllProducts allProds : list) {
				Speakers speaker = allProds.getSpeaker();
				hm.put("Speaker"+ i, speaker);
				i++;
			}
			name = "Speakers";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) 
			{
				Speakers speaker = allProds.getSpeaker();
				String retailer = speaker.getRetailer();
				if(speaker.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("Speaker"+ i, speaker);
					i++;
					name = speaker.getRetailer() + " Speakers";
				}
			}	
		   
		}

		/* Header, Left Navigation Bar are Printed.

		All the speaker and speaker information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+"</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Speakers> entry : hm.entrySet()){
			Speakers speaker = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+speaker.getName()+"</h3>");
			pw.print("<strong>"+ "$" + speaker.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/speakers/"+speaker.getImage()+"' alt='' /></li>");
			pw.print("<strong>"+speaker.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			double totPrice = speaker.getPrice() - ((speaker.getPrice()*speaker.getDiscount())/100);
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+speaker.getId()+"'>"+
					"<input type='hidden' name='type' value='speakers'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+speaker.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+speaker.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success'  value='Buy Now'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+speaker.getId()+"'>"+
					"<input type='hidden' name='type' value='speakers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+Double.toString(totPrice)+"'>"+
					"<input type='hidden' name='img' value='"+speaker.getImage()+"'>"+
					"<input type='hidden' name='des' value='"+speaker.getDescription()+"'>"+
					"<input type='hidden' name='access' value=''>" +
					"<div class ='btn-group'>" +
						"<input type='submit' class='btn btn-default' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+speaker.getId()+"'>"+
					"<input type='hidden' name='category' value='Speakers'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+speaker.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+speaker.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-warning' value='Review Product'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+speaker.getId()+"'>"+
					"<input type='hidden' name='category' value='Speakers'>"+
					"<input type='hidden' name='price' value='"+totPrice+"'>"+
					"<input type='hidden' name='img' value='"+speaker.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+speaker.getDescription()+"'>"+
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
