import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
//import org.json.JSONObject;
import com.google.gson.Gson;

@WebServlet("/FetchDailySales")
public class FetchDailySales extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("application/json");
		PrintWriter out =response.getWriter();
		System.out.println("Entered FetchProducts get");
		Gson gson=new Gson();
		List<Product> products = MySQLDataStoreUtilities.fetchTotalDailySales();
		String prodString = gson.toJson(products);
		out.print(prodString);
	}
}