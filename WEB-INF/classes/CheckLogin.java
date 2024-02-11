import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckLogin")

public class CheckLogin extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
				
		HashMap<String,UserDetail> ud = new HashMap<String,UserDetail>();
		PrintWriter out =response.getWriter();
		String dir = System.getenv("ANT_HOME");
		String usertype= request.getParameter("usertype");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try{
			File f=new File(dir+"\\webapps\\Assignment_3\\UserDetails.txt");
			FileInputStream fileinput= new FileInputStream(f);
			ObjectInputStream objIn = new ObjectInputStream(fileinput);
			ud=(HashMap)objIn.readObject();
		
		if(!ud.containsKey(username) || ud.size() == 0)
		{
			
			RequestDispatcher ris=request.getRequestDispatcher("LoginPage");  
			request.setAttribute("checkMsg","Invalid Credentials");
			ris.include(request, response);  
		}	
		else{
			UserDetail userDetail=(UserDetail)ud.get(username);
			if(userDetail.getUserType().equals(usertype) && userDetail.getPassword().equals(password) )
			{
				if(usertype.equals("storeManager"))
				{
					HttpSession session = request.getSession();
					session.setAttribute("UserDetail", userDetail);
					RequestDispatcher rd=request.getRequestDispatcher("HomeSM");  
					rd.forward(request, response);  
				}
				
				if(usertype.equals("salesManager"))
				{
					HttpSession session = request.getSession();
					session.setAttribute("UserDetail", userDetail);
					RequestDispatcher rd=request.getRequestDispatcher("HomeSAM");  
					rd.forward(request, response);  
				}
				
				if(usertype.equals("customer"))
				{
					HttpSession session = request.getSession();
					session.setAttribute("UserDetail", userDetail);
					RequestDispatcher rd=request.getRequestDispatcher("Home");  
					rd.forward(request, response);  
				}
				
			}
			else
			{
				request.setAttribute("checkMsg","Invalid");
				RequestDispatcher rd=request.getRequestDispatcher("/LoginPage");  
				rd.include(request, response);  
			}
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}