import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import java.io.*;

@WebServlet("/ReviewConfirmPage")


public class ReviewConfirmPage extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		
		String ratings = request.getParameter("ratings");
		String reviews = request.getParameter("reviews");
		String userName = request.getParameter("userName");
		String prodID = request.getParameter("prodID");
		String age = request.getParameter("age");
		String prodCategory = request.getParameter("prodCategory");

		String gender = request.getParameter("gender");
		String occupation = request.getParameter("occupation");

		String result; 
		System.out.println("ReviewConfirmPage");
		System.out.println(" Ratings : " + ratings + " reviews : " + reviews + " userName :" + userName + " prodID : "+ prodID+ " age : " + age + " gender : " + gender + " occupation : "+ occupation);
		

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 
        
        AllProducts allProducts   = MySQLDataStoreUtilities.getproduct(Integer.parseInt(prodID));
        StoreDetails storeDetails = MySQLDataStoreUtilities.fetchUserStore(userName);

        ReviewDetail reviewDetail = new ReviewDetail();
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = dateObj.format(formatter);
		reviewDetail.setReviewDate(date);
		reviewDetail.setReviewRating(ratings);
		reviewDetail.setReviewText(reviews);
		reviewDetail.setUserId(userName);
		reviewDetail.setUserOccupation(occupation);
		reviewDetail.setUserGender(gender);
		reviewDetail.setUserAge(age);
		reviewDetail.setRetailerName(storeDetails.getName());
		reviewDetail.setRetailerState(storeDetails.getState());
		reviewDetail.setRetailerZip(String.valueOf(storeDetails.getZipcode()));
		reviewDetail.setRetailerCity(storeDetails.getCity());
		
		reviewDetail.setProductRebate("Yes");
		
		switch(prodCategory) {
		case "Doorbells":{
				DoorBells doorbell = allProducts.getDoorbell();
				if(doorbell.getDiscount() > 0)
					reviewDetail.setOnSale("Yes");
				else 
					reviewDetail.setOnSale("No");
				reviewDetail.setProductCompany(doorbell.getRetailer());
				reviewDetail.setProductName(doorbell.getName());
				reviewDetail.setProductID(doorbell.getId());
				reviewDetail.setProductPrice(String.valueOf(doorbell.getPrice()));
				
				break;	
				}
		case "Doorlocks":{
			DoorLocks doorlock = allProducts.getDoorLock();
			if(doorlock.getDiscount() > 0)
				reviewDetail.setOnSale("Yes");
			else 
				reviewDetail.setOnSale("No");
			reviewDetail.setProductCompany(doorlock.getRetailer());
			reviewDetail.setProductName(doorlock.getName());
			reviewDetail.setProductID(doorlock.getId());
			reviewDetail.setProductPrice(String.valueOf(doorlock.getPrice()));
			break;	
				}
	case "Lightings":{
		Lightings lighting = allProducts.getLighting();
		if(lighting.getDiscount() > 0)
			reviewDetail.setOnSale("Yes");
		else 
			reviewDetail.setOnSale("No");
		reviewDetail.setProductCompany(lighting.getRetailer());
		reviewDetail.setProductName(lighting.getName());
		reviewDetail.setProductID(lighting.getId());
		reviewDetail.setProductPrice(String.valueOf(lighting.getPrice()));
		break;	
			}
	case "Speakers":{
		Speakers speaker = allProducts.getSpeaker();
		if(speaker.getDiscount() > 0)
			reviewDetail.setOnSale("Yes");
		else 
			reviewDetail.setOnSale("No");
		reviewDetail.setProductCompany(speaker.getRetailer());
		reviewDetail.setProductName(speaker.getName());
		reviewDetail.setProductID(speaker.getId());
		reviewDetail.setProductPrice(String.valueOf(speaker.getPrice()));
		break;	
			}

	case "Thernostats":{
		Thermostats thermostat = allProducts.getThermostat();
		if(thermostat.getDiscount() > 0)
			reviewDetail.setOnSale("Yes");
		else 
			reviewDetail.setOnSale("No");
		reviewDetail.setProductCompany(thermostat.getRetailer());
		reviewDetail.setProductName(thermostat.getName());
		reviewDetail.setProductID(thermostat.getId());
		reviewDetail.setProductPrice(String.valueOf(thermostat.getPrice()));
		break;	
			}

		}
		reviewDetail.setProductCategory(prodCategory);
		
		MongoDbDataStoreUtilities.connect();
		MongoDbDataStoreUtilities.addReview(reviewDetail);
		MongoDbDataStoreUtilities.closeConnection();

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<b> Review Added succesfully. Go to products to view the reviews </b>");
		utility.printHtml("Footer.html");
		
}

}