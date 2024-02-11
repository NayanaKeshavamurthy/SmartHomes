import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeSAM")

public class HomeSAM extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
        utility.printHtml("Header2.html");
		utility.printHtml("Footer.html");
				
	}

}