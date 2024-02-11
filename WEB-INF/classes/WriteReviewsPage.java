import java.io.*;
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

@WebServlet("/WriteReviewsPage")


public class WriteReviewsPage extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");

		String price = request.getParameter("price");

		String type=null;
		
		switch (category) 
		{
		case "Doorbells":{
			type = "doorbells";
			break;	
			}
		case "Doorlocks":{
			type = "doorlocks";
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
		String imgAdd = "images/"+type +"/"+ request.getParameter("img");
		String desc = request.getParameter("desc");

		String result; 
		System.out.println("id : "+ id + " name : " + name + " \n price: " + price + "\n img ADD: " + imgAdd + "\n desc" + desc + "\n type: " + type);
		

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

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
		//String userID = session.getAttribute("userID").toString();

		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>" +
				"<a style='font-size: 24px;color:purple;text-align:center;font-weight:bold;margin-left:35%'> Write Review for " + name +"</a></h2><br><br>" +
				"<div> <img src = '" + imgAdd +"' alt='' style='width :350px !important;height:350px !important;margin-left:35%'><h4 style='text-align :center;font-size=12px !important'>" + desc +
				"</h4> <h4 style='text-align :center;font-size=12px !important'> Price - $" + price + "</h4></div></div></div>" );
		pw.print("<form name ='WriteReview' action='ReviewConfirmPage' method='post'>");
        pw.print("<div id='content'><div class='post'>");
		pw.print("<div class='entry'>");
		pw.print("<table><tr></tr><tr></tr>");
		pw.print("<table><tr><td>");
		pw.print("</div>");
		pw.print("<b>Enter your Ratings here : <span class='tab'>       </b> </td>");
		pw.print("<td><select name='ratings' id='ratings' style = 'margin:50px !important;width : 100px !important'>"+
					"<option value='5'>5 Stars</option>"+
					"<option value='4.5'>4.5 Stars</option>"+
					"<option value='4'>4 Stars</option>"+
					"<option value='3.5'>3.5 Stars</option>"+
					"<option value='3'>3 Stars</option>"+
					"<option value='2.5'>2.5 Stars</option>"+
					"<option value='2'>2 Stars</option>"+
					"<option value='1.5'>1.5 Stars</option>"+
					"<option value='1'>1 Star</option>"+
					"<option value='0'>0 Stars</option>"+
				"</select>");
		pw.print("</td></tr></table>");
//		pw.print("<tr><td><select name='ratings' id='ratings'>");
//		for(int i=5;i<=1;i--) {
//			pw.print("<option value='"+i +"'> "+i  + " Stars "+ "</option>");
//		}
//		pw.print("</select>");
//		pw.print("</td></tr>");
		pw.print("<tr><td><b>");
     	pw.print("Enter your reviews here :</b></td></tr> <tr>");
		pw.print("<td><input type='text' name='reviews' style= 'width: 600px !important;height:50px !important' size='150'>");
		pw.print("</td></tr><br/> <br/>");
		pw.print("<input type='hidden' name='userName' value='"+userName+"'>");
		pw.print("<input type='hidden' name='prodID' value='"+id+"'>");
		pw.print("<input type='hidden' name='prodCategory' value='"+category+"'>");
		pw.print("<b style = 'color:red;font-size: 16px'> Please enter your details below </b> <br/> <br/>");
		pw.print("<tr><td><b>");
     	pw.print("Enter your age :</b></td></tr> <tr>");
		pw.print("<td><input type='text' name='age' style= 'width: 100px !important' size='100'>");
		pw.print("</td></tr><br/> <br/> <br/>");
		pw.print("<tr><td><b>");
     	pw.print("Enter your gender :</b></td></tr> <tr>");
		pw.print("<td><input type='text' name='gender' style= 'width: 100px !important' size='100'>");
		pw.print("</td></tr><br/> <br/> <br/>");
		pw.print("<tr><td><b>");
     	pw.print("Enter your occupation :</b></td></tr> <tr>");
		pw.print("<td><input type='text' name='occupation' style= 'width: 100px !important' size='100'>");
		pw.print("</td></tr><br/> <br/> <br/>"); 
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy' style= 'width: 600px !important'>");
        pw.print("</td></tr>");
        
        
        pw.print("</table></form>");
		
}

}