import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
    
    public static void getConnection() {
        try {
        Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/smarthomes";
            String username = "root";
            String password = "root";
        conn=DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }
	
	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,Product> data;
		data=getData();
		
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Product p=(Product)pi.getValue(); 
                if (p.getName().toLowerCase().startsWith(searchId))
                {
                        sb.append("<product>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<productName>" + p.getName() + "</productName>");
                        sb.append("</product>");
                }
			}
       }
	   
	   return sb;
	}
	
	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
		    getConnection();
			
		    String selectproduct="select * from  allprods";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	Product p = new Product();
			    p.setId(rs.getString("prod_id"));
			    p.setName(rs.getString("prod_name"));
			    p.setDescription(rs.getString("prod_Description"));
			    p.setImage(rs.getString("prod_img"));
			    p.setPrice(rs.getDouble("prod_price"));
			    p.setCategory(rs.getString("prod_cat"));
			    p.setRetailer(rs.getString("prod_Retailer"));
				hm.put(rs.getString("prod_id"), p);
			}
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}
	
	public static void storeData(HashMap<String,Product> productdata)
	{
		try
		{
		
		    MySQLDataStoreUtilities.getConnection();
				
			String insertIntoProductQuery = "INSERT INTO allprods(productId,productName,image,retailer,productCondition,productPrice,productType,discount) "
			+ "VALUES (?,?,?,?,?,?,?,?);";	
			for(Map.Entry<String, Product> entry : productdata.entrySet())
			{	

				PreparedStatement pst = conn.prepareStatement(insertIntoProductQuery);
				//set the parameter for each column and execute the prepared statement
				pst.setString(1,entry.getValue().getId());
				pst.setString(2,entry.getValue().getName());
				pst.setString(3,entry.getValue().getImage());
				pst.setString(4,entry.getValue().getRetailer());
				pst.setString(5,entry.getValue().getCondition());
				pst.setDouble(6,entry.getValue().getPrice());
				pst.setString(7,entry.getValue().getImage());
				pst.setDouble(8,entry.getValue().getDiscounAmt());
				pst.execute();
			}
		}
		catch(Exception e)
		{	
	
		}		
	}

}
