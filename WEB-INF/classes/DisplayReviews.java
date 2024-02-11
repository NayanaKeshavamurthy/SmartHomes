import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


@WebServlet("/DisplayReviews")

public class DisplayReviews extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		MongoDbDataStoreUtilities mdb = new MongoDbDataStoreUtilities();
		mdb.connect();
		
		
			//out.println("<h2 style=color:white;background-color:red;'> User Reviews</h2>");
		
		String firstname=null,usertype=null,userage=null,usergender=null,username=null;
		
		String pId=request.getParameter("id");
		String pType=request.getParameter("category");
		String pName =request.getParameter("name");

		System.out.println("DisplayReviews");
		System.out.println("ID is " + pId);
		
		Utilities util=new Utilities(request,out);
		util.printHtml("Header.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		
		HashMap<String,ArrayList<ReviewDetail>> reviews = mdb.getAllReviews();
		int i=0;
		if(!reviews.containsKey(pId))
		{
			out.println(" <h1 align=\"center\"><span style='color:red;'>"+"No Reviews Found for "+pName+"</span></h1> ");
		}
		else
		{
			out.println(" <h1 align=\"center\"><span style='color:red;'>"+pName+" Reviews</span></h1><br/><br/> ");
			ArrayList<ReviewDetail> r = (ArrayList<ReviewDetail>)reviews.get(pId);
		
			for(ReviewDetail reviewDetail : r)
			{
					i++;
					out.println("<article style='margin-left:25%'>");
					out.println("<h2 style='color:blue'>Review #"+i+" by "+ reviewDetail.getUserId()+"</h2>");
					out.println("<b>Product Name:</b> "+ reviewDetail.getProductName()+"</br>");
					out.println("<b>Product Category:</b> "+ reviewDetail.getProductCategory()+"</br>");
					out.println("<b>Product Price:</b> "+ reviewDetail.getProductPrice()+"</br>");
					out.println("<b>Product Company:</b> "+ reviewDetail.getProductCompany()+"</br>");
					out.println("<b>Retailer Zip:</b> "+ reviewDetail.getRetailerZip()+"</br>");
					out.println("<b>Retailer Name:</b> "+ reviewDetail.getRetailerName()+"</br>");
					out.println("<b>Retailer City:</b> "+ reviewDetail.getRetailerCity()+"</br>");
					out.println("<b>Retailer State:</b> "+ reviewDetail.getRetailerState()+"</br>");
					out.println("<b>Product on Sale:</b> "+ reviewDetail.getOnSale()+"</br>");
					out.println("<b>Manufacturer Rebate:</b> "+ reviewDetail.getProductRebate()+"</br>");
					out.println("<b>User:</b> "+ reviewDetail.getUserId()+"</br>");
					out.println("<b>User Age:</b> "+ reviewDetail.getUserAge()+"</br>");
					out.println("<b>User Gender:</b> "+ reviewDetail.getUserGender()+"</br>");
					out.println("<b>User Occupation:</b> "+ reviewDetail.getUserOccupation()+"</br>");
					out.println("<b>Review Rating:</b> "+ reviewDetail.getReviewRating()+"</br>");
					out.println("<b>Review Date:</b> "+ reviewDetail.getReviewDate()+"</br>");
					out.println("<b>Review Text: </b>"+ reviewDetail.getReviewText()+"</br>");
					
					
					out.println("</article>");	
			}
		}
		//out.println("<div width='50%' align='center'><a href=\"ProductsPage?catagory="+pType+"\"><button style='background-color:green;color: white;padding:14px 20px;margin:8px;width:100%;'>Back</button></a></div>");
		out.println("</section></div><div class=\"clear\"></div>");
		
		util.printHtml("Footer.html");
	
	}
}