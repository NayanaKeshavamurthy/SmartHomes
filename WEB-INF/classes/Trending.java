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

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	/* Trending Page Displays all the Doorbells and their Information in Smart Home*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Doorbells type whether it is microsft or sony or nintendo then add products to hashmap*/

		String name = "Trending";
		String CategoryName = request.getParameter("maker");
		
		HashMap<String, AllProducts> hm = new HashMap<String, AllProducts>();
		HashMap<String, AllProducts> hm2 = new HashMap<String, AllProducts>();
		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getMostTrending();
		
		int l_size = list.size();
		System.out.println(l_size);
		int i=0;
		for(AllProducts allProds : list) {
			hm.put("Trending"+ i, allProds);
			i++;
		}
		

		/* Header, Left Navigation Bar are Printed.

		All the tvs and Tv information are dispalyed in the Content Section

		and then Footer is Printed*/

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
			pw.print("<li id='item'><img src='images/"+type+"/"+doorbell.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='"+type+"'>"+
					"<input type='hidden' name='price' value='"+doorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+ doorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+doorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='"+ type +"'>"+
					"<input type='hidden' name='price' value='"+doorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+doorbell.getImage()+"'>"+
					"<input type='hidden' name='des' value='"+doorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-default' value='View Product'></form></li>");
			pw.print("</ul></div></td>");
			
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		i = 0;
		pw.print("</table></div></div></div>");	
		
		
		try {
            
	        name = "Most liked";
	        MongoDbDataStoreUtilities.connect();
	        HashMap<String,ArrayList<ReviewDetail>> rs = MongoDbDataStoreUtilities.getAllReviews();
	        pw.print("<div id='content'><div class='post'><h2 class='title meta' style = 'text-align:center'>");
	        pw.print("<a style='font-size: 24px;'>"+name+" Products</a>");
	        pw.print("</h2><div class='entry'><table id='bestseller'>");
	        HashMap<String,Float> avgMap = new HashMap<String,Float>();
	        for(Map.Entry<String, ArrayList<ReviewDetail>> e : rs.entrySet())
	        {
	            ArrayList<ReviewDetail> rsList = e.getValue();          
	            String pName = e.getKey();
	            if(!rsList.isEmpty())
	            {   
	                int jj=0;
	                float sum=0;
	                float [] avgRating = new float[rsList.size()];
	                
	                for(ReviewDetail reviewDetail : rsList)
	                {
	                        avgRating[jj]= Float.parseFloat(reviewDetail.getReviewRating());
	                        jj++;
	                }
	                
	                for(int j=0; j<jj; j++)
	                {
	                    sum+= avgRating[j];
	                }
	                float a1=sum/jj;
	                avgMap.put(pName,a1);
	            }   
	        }
	        int m=5;
	        Map<String, Float> sortAvg = sortByAvg(avgMap,false);
	        System.out.println("Liked prods avgMap " + avgMap.size());
	        
	        for(Map.Entry<String, Float> ee : sortAvg.entrySet())
	        {
	            if(m > 0)
	            {   
	                    String pName = ee.getKey();
	                    AllProducts AllProducts= MySQLDataStoreUtilities.getproduct(Integer.parseInt(pName));
	                    String productID = null,productName = null,productPrice = null,prodImgUrl = null,productCategory = null,prodDesc = null,prodImg=null;
	                    
	                    
	                    if(AllProducts.getDoorbell() != null) {
	                        DoorBells doorbell = AllProducts.getDoorbell();
	                        productID = doorbell.getId();
	                        productName = doorbell.getName();
	                        productPrice = String.valueOf(doorbell.getPrice());
	                        prodImgUrl = "doorbells/" + doorbell.getImage();
	                        productCategory = "doorbells";
	                        prodDesc = doorbell.getDescription();
	                        prodImg = doorbell.getImage();
	                        
	                    }
	                    else if(AllProducts.getDoorLock() != null) {
	                        DoorLocks dLocks= AllProducts.getDoorLock();
	                        productID = dLocks.getId();
	                        productName = dLocks.getName();  
	                        productPrice = String.valueOf(dLocks.getPrice());
	                        prodImgUrl = "doorlocks/" + dLocks.getImage();
	                        productCategory = "doorlocks";
	                        prodDesc = dLocks.getDescription();
	                        prodImg = dLocks.getImage();
	                    } 
	                    else if(AllProducts.getLighting() != null) {
	                        Lightings lighting= AllProducts.getLighting();
	                        productID = lighting.getId();
	                        productName = lighting.getName();
	                        productPrice = String.valueOf(lighting.getPrice());
	                        prodImgUrl = "lightings/" + lighting.getImage();
	                        productCategory = "lightings";
	                        prodDesc = lighting.getDescription();
	                        prodImg = lighting.getImage();
	                    } 
	                    else if(AllProducts.getSpeaker() != null) {
	                        Speakers speaker= AllProducts.getSpeaker();
	                        productID = speaker.getId();
	                        productName = speaker.getName();
	                        productPrice = String.valueOf(speaker.getPrice());
	                        prodImgUrl = "speakers/" + speaker.getImage();
	                        productCategory = "speakers";
	                        prodDesc = speaker.getDescription();
	                        prodImg = speaker.getImage();
	                        
	                    } 
						else if(AllProducts.getThermostat() != null) {
	                        Thermostats thermostat= AllProducts.getThermostat();
	                        productID = thermostat.getId();
	                        productName = thermostat.getName();
	                        productPrice = String.valueOf(thermostat.getPrice());
	                        prodImgUrl = "thermostats/" + thermostat.getImage();
	                        productCategory = "thermostats";
	                        prodDesc = thermostat.getDescription();
	                        prodImg = thermostat.getImage();
	                        
	                    } 
	                    System.out.println("Liked prods prodID " + productID + " productName : " + productName + " productPrice : " + productPrice + " prodImg " + prodImgUrl);
	                    
	                    if(i%3==1) pw.print("<tr>");
	                    pw.print("<td><div id='shop_item'>");
	                    pw.print("<h3>"+productName+"</h3>");
	                    pw.print("<strong>$"+productPrice+"</strong><ul>");
	                    pw.print("<li id='item'><img src='images/"+prodImgUrl +"' alt='' /></li>");
	                    pw.print("<li><form method='post' action='Cart'>" +
	                            "<input type='hidden' name='name' value='"+ productName+"'>"+
	                            "<input type='hidden' name='id' value='"+productID+"'>"+
	                            "<input type='hidden' name='type' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='price' value='"+productPrice+"'>"+
	                            "<input type='hidden' name='img' value='"+prodImg+"'>"+
	                            "<input type='hidden' name='desc' value='"+prodDesc+"'>"+
	                            "<input type='hidden' name='maker' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='access' value=''>"+
	                            "<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
	                    pw.print("<li><form method='post' action='ProductDetailsPage'>" +
	                            "<input type='hidden' name='name' value='"+ productName+"'>"+
	                            "<input type='hidden' name='id' value='"+productID+"'>"+
	                            "<input type='hidden' name='type' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='price' value='"+productPrice+"'>"+
	                            "<input type='hidden' name='img' value='"+prodImg+"'>"+
	                            "<input type='hidden' name='des' value='"+prodDesc+"'>"+
	                            "<input type='hidden' name='maker' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='access' value=''>"+
	                            "<input type='submit' class='btnbuy' value='View Product'></form></li>");
	                    pw.print("</ul></div></td>");
	                    
	                    if(i%3==0 || i == size) pw.print("</tr>");
	                    i++; 
	                    
	            }
	            m--;
	        }
	        pw.print("</table></div></div></div>"); 
	        ;
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	        //pw.print(" </div></div></div></div>");
		
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta' style = 'text-align:center'>");
		pw.print("<a style='font-size: 24px;'> Most Trending stores</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		List<StoreDetails> list2 = new ArrayList<>();
		list2 = MySQLDataStoreUtilities.getMostSalesStore();
		for(StoreDetails storeDetails : list2) {
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<strong style = 'color : red'>"+storeDetails.getName()+"</strong>");
			pw.print("<strong>"+storeDetails.getCity()+" , "+ storeDetails.getState() +"</strong>");
			pw.print("<strong>"+storeDetails.getZipcode() +"</strong>");
			pw.print("</div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div>");
		//utility.printHtml("Footer.html");
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
