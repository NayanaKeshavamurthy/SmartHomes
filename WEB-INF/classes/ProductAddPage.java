import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductAddPage")
public class ProductAddPage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		Utilities util=new Utilities(request,out);
		
		String pname=request.getParameter("pname");
		String pcondition=request.getParameter("pcondition");
		String pcategory=request.getParameter("pcategory");
		String pdescription=request.getParameter("pdescription");
		String pprice=request.getParameter("pprice");
		String pcompany=request.getParameter("pcompany");
		String pimage=request.getParameter("pimage");
		String dir = System.getenv("ANT_HOME");
		SaxParserDataStore s1= new SaxParserDataStore(dir+"\\webapps\\Assignment_3\\ProductCatalog.xml");
		int pSize = s1.getProductsSize();
		
		//SaxParserDataStore s= new SaxParserDataStore(dir+"\\webapps\\Assignment_2\\ProductCatalog.xml",pname,pprice,pcondition,pdescription,pcompany,pimage,pcategory,pSize);
        request.setAttribute("checkMsg","Product Added");
        response.sendRedirect("HomeSM");

	}   
}