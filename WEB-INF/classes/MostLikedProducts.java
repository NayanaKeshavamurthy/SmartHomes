import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MostLikedProducts")

public class MostLikedProducts extends HttpServlet {

	/* Trending Page Displays all the Tvs and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Tvs type whether it is microsft or sony or nintendo then add products to hashmap*/

		String name = "Most Liked";
		String CategoryName = request.getParameter("maker");
		HashMap<String, AllProducts> hm = new HashMap<String, AllProducts>();
		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getMostTrending();
		
		int l_size = list.size();
		System.out.println(l_size);
			int i=0;
			for(AllProducts allProds : list) {
				hm.put("Trending"+ i, allProds);
				i++;
			}

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Products</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		i = 1; int size= hm.size();
		for(Map.Entry<String, AllProducts> entry : hm.entrySet()){
			DoorBells doorbell = entry.getValue().getDoorbell();
			String category = entry.getValue().getCategory();
			String type = null;
			switch (category) 
			{
			case "Doorlocks":{
				type = "doorlocks";
				break;	
				}
			case "Doorbells":{
				type = "doorbells";
			break;	
				}
			case "Lightings":{
				type = "lightings";	
				break;	
			}
			case "Speakers":{
				type = "speakers";
				break;	
			}
			case "Thermostats":{
				type = "thermostats";
				break;	
			}
			}
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+doorbell.getName()+"</h3>");
			pw.print("<strong>$"+doorbell.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/"+ type +"/"+doorbell.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='"+type+"'>"+
					"<input type='hidden' name='price' value='"+doorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+ doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='"+ type +"'>"+
					"<input type='hidden' name='price' value='"+doorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='des' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product'></form></li>");
			pw.print("</ul></div></td>");
			
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		i=0;
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
	}
	
	private static Map sortByAvg(Map<String, Float> temp,final boolean o)
    {

        LinkedList<Entry<String, Float>> ll = new LinkedList<Entry<String, Float>>(temp.entrySet());

        Collections.sort(ll, new Comparator<Entry<String, Float>>()
        {
            public int compare(Entry<String, Float> o1,Entry<String, Float> o2)
            {
                if (o)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        Map<String, Float> mainMap = new LinkedHashMap<String, Float>();
        for (Entry<String, Float> eee : ll)
        {
            mainMap.put(eee.getKey(), eee.getValue());
        }

        return mainMap;
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
	

}
