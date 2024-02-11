import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySQLDataStoreUtilities {	
	public static Connection conn;
	public static void getConnection() {
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url = "jdbc:mysql://localhost:3306/smarthomes";
		String username = "root";
		String password = "root";
		conn=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		}
		// catch( ClassNotFoundException e) {
		// 	e.printStackTrace();
		// }
	}
	public static List <AllProducts> getAllWearable(String prodCategory) {
		getConnection();
		String query = "Select * from allprods where prod_cat = ? and quantity > 0";
		List <AllProducts> list = new ArrayList<>();
		try {
			PreparedStatement pm = conn.prepareStatement(query);
			pm.setString(1, prodCategory);
			ResultSet rs = pm.executeQuery();
			while(rs.next()) {
				String category = rs.getString("prod_cat");
				AllProducts allProducts  = new AllProducts();
				switch(category) 
				{
					case "Doorbells":{
							DoorBells doorbell = new DoorBells();
							doorbell.setId(rs.getString("prod_id"));
							doorbell.setName(rs.getString("prod_name"));
							doorbell.setPrice(rs.getDouble("prod_price"));
							doorbell.setImage(rs.getString("prod_img"));
							doorbell.setDescription(rs.getString("prod_Description"));
							doorbell.setRetailer(rs.getString("prod_Retailer"));
							doorbell.setCondition(rs.getString("prod_condition"));
							doorbell.setDiscount(rs.getDouble("prod_disc"));
							allProducts.setDoorbell(doorbell);
							list.add(allProducts);
							break;	
							}
					case "Doorlocks":{
						DoorLocks doorlock = new DoorLocks();
						doorlock.setId(rs.getString("prod_id"));
						doorlock.setName(rs.getString("prod_name"));
						doorlock.setPrice(rs.getDouble("prod_price"));
						doorlock.setImage(rs.getString("prod_img"));
						doorlock.setDescription(rs.getString("prod_Description"));
						doorlock.setRetailer(rs.getString("prod_Retailer"));
						doorlock.setCondition(rs.getString("prod_condition"));
						doorlock.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setDoorLock(doorlock);
						list.add(allProducts);
						break;	
							}
					case "Lightings":{
						Lightings lighting = new Lightings();
						lighting.setId(rs.getString("prod_id"));
						lighting.setName(rs.getString("prod_name"));
						lighting.setPrice(rs.getDouble("prod_price"));
						lighting.setImage(rs.getString("prod_img"));
						lighting.setDescription(rs.getString("prod_Description"));
						lighting.setRetailer(rs.getString("prod_Retailer"));
						lighting.setCondition(rs.getString("prod_condition"));
						lighting.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setLighting(lighting);
						list.add(allProducts);
						break;	
							}
					case "Speakers":{
						Speakers speaker = new Speakers();
						speaker.setId(rs.getString("prod_id"));
						speaker.setName(rs.getString("prod_name"));
						speaker.setPrice(rs.getDouble("prod_price"));
						speaker.setImage(rs.getString("prod_img"));
						speaker.setDescription(rs.getString("prod_Description"));
						speaker.setRetailer(rs.getString("prod_Retailer"));
						speaker.setCondition(rs.getString("prod_condition"));
						speaker.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setSpeaker(speaker);
						list.add(allProducts);
						break;	
							}
					case "Thermostats":{
						Thermostats thermostat = new Thermostats();
						thermostat.setId(rs.getString("prod_id"));
						thermostat.setName(rs.getString("prod_name"));
						thermostat.setPrice(rs.getDouble("prod_price"));
						thermostat.setImage(rs.getString("prod_img"));
						thermostat.setDescription(rs.getString("prod_Description"));
						thermostat.setRetailer(rs.getString("prod_Retailer"));
						thermostat.setCondition(rs.getString("prod_condition"));
						thermostat.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setThermostat(thermostat);
						list.add(allProducts);
						break;	
							}
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}
	public static AllProducts getproduct(int productCode) {
		getConnection();
		String query = "Select * from allprods where prod_id = ?";
		AllProducts allProducts = new AllProducts();
		try {
			PreparedStatement pm = conn.prepareStatement(query);
			pm.setInt(1, productCode);
			ResultSet rs = pm.executeQuery();
			while(rs.next()) {
				String category = rs.getString("prod_cat");
				switch(category) 
				{
					case "Doorbells":{
							DoorBells doorbell = new DoorBells();
							doorbell.setId(rs.getString("prod_id"));
							doorbell.setName(rs.getString("prod_name"));
							doorbell.setPrice(rs.getDouble("prod_price"));
							doorbell.setImage(rs.getString("prod_img"));
							doorbell.setDescription(rs.getString("prod_Description"));
							doorbell.setRetailer(rs.getString("prod_Retailer"));
							doorbell.setCondition(rs.getString("prod_condition"));
							doorbell.setDiscount(rs.getDouble("prod_disc"));
							allProducts.setDoorbell(doorbell);
							break;	
							}
					case "Doorlocks":{
						DoorLocks doorlock = new DoorLocks();
						doorlock.setId(rs.getString("prod_id"));
						doorlock.setName(rs.getString("prod_name"));
						doorlock.setPrice(rs.getDouble("prod_price"));
						doorlock.setImage(rs.getString("prod_img"));
						doorlock.setDescription(rs.getString("prod_Description"));
						doorlock.setRetailer(rs.getString("prod_Retailer"));
						doorlock.setCondition(rs.getString("prod_condition"));
						doorlock.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setDoorLock(doorlock);
						break;	
							}
					case "Lightings":{
						Lightings lighting = new Lightings();
						lighting.setId(rs.getString("prod_id"));
						lighting.setName(rs.getString("prod_name"));
						lighting.setPrice(rs.getDouble("prod_price"));
						lighting.setImage(rs.getString("prod_img"));
						lighting.setDescription(rs.getString("prod_Description"));
						lighting.setRetailer(rs.getString("prod_Retailer"));
						lighting.setCondition(rs.getString("prod_condition"));
						lighting.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setLighting(lighting);
						break;	
							}
					case "Speakers":{
						Speakers speaker = new Speakers();
						speaker.setId(rs.getString("prod_id"));
						speaker.setName(rs.getString("prod_name"));
						speaker.setPrice(rs.getDouble("prod_price"));
						speaker.setImage(rs.getString("prod_img"));
						speaker.setDescription(rs.getString("prod_Description"));
						speaker.setRetailer(rs.getString("prod_Retailer"));
						speaker.setCondition(rs.getString("prod_condition"));
						speaker.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setSpeaker(speaker);
						break;	
							}
					case "Thermostats":{
						Thermostats thermostat = new Thermostats();
						thermostat.setId(rs.getString("prod_id"));
						thermostat.setName(rs.getString("prod_name"));
						thermostat.setPrice(rs.getDouble("prod_price"));
						thermostat.setImage(rs.getString("prod_img"));
						thermostat.setDescription(rs.getString("prod_Description"));
						thermostat.setRetailer(rs.getString("prod_Retailer"));
						thermostat.setCondition(rs.getString("prod_condition"));
						thermostat.setDiscount(rs.getDouble("prod_disc"));
						allProducts.setThermostat(thermostat);
						break;	
							}
					
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allProducts;
		
	}
	public static HashMap<Integer,String> getAllProducts(){
		
		HashMap<Integer,String> map = new HashMap<>();
		getConnection();
		String query = "Select prod_id,prod_name from allprods";
		try {
			PreparedStatement pm = conn.prepareStatement(query);
			ResultSet rs = pm.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt("prod_id"), rs.getString("prod_name"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	public static boolean isUserNamePresent(String userName) {
		
		getConnection();
		int res = 0;
		String query = "Select count(1) as count from users where userName = ?";
		try {
			PreparedStatement pm = conn.prepareStatement(query);
			pm.setString(1, userName);
			ResultSet rs = pm.executeQuery();
			while(rs.next()) {
				res = rs.getInt("count");
			}
			conn.close();
			if(res !=0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public static void createNewUser(User us) {
		getConnection();
		int res = 1;
		String query = "insert into users (userType,userName ,password,address,city,state,zip,storeId,CustName) values (?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pm = conn.prepareStatement(query);
			pm.setString(1, us.getUsertype());
			pm.setString(2, us.getName());
			pm.setString(3, us.getPassword());
			pm.setString(4, us.getAddress());
			pm.setString(5, us.getCity());
			pm.setString(6, us.getState());	
			pm.setString(7, us.getZip());
			pm.setInt(8, us.getStoreID());
			pm.setString(9, us.getFullName());
			int rs = pm.executeUpdate();
			//conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public static boolean checkUserCredentails(String userName,String password,String userType) {
		
		getConnection();
		int res = 0;
		String query = "Select count(1) as count from users where userName = ? and password = ? and userType=?";
		try {
			PreparedStatement pm = conn.prepareStatement(query);
			pm.setString(1, userName);
			pm.setString(2, password);
			pm.setString(3, userType);
			ResultSet rs = pm.executeQuery();
			while(rs.next()) {
				res = rs.getInt("count");
			}
			conn.close();
			System.out.println("User check count is :"+res);
			if(res !=0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
public static User fetchUserDteails(String userName,String password,String userType) {
	
	getConnection();
	int res = 0;
	User us = null;
	String query = "Select * from users where userName = ? and password = ? and userType=?";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setString(1, userName);
		pm.setString(2, password);
		pm.setString(3, userType);
		ResultSet rs = pm.executeQuery();
		while(rs.next()) {
			us = new User(rs.getString("userName"),rs.getString("password"),rs.getString("userType"));
		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return us;
	
}

public static int getOrderID() {
	
	getConnection();
	int res = 0;
	String query = "SELECT MAX(orderID) as orderID FROM orders";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		ResultSet rs = pm.executeQuery();

		//res=rs.getInt("orderID");
		while(rs.next()) {
			res = rs.getInt("orderID") + 1;
		}
		conn.close();
		System.out.println("User check count is :"+res);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return res;
	
}

public static void storeCustomerDetails(int OrderID,String customerName,String address,String city,String state,Long zip,Long phoneNumber,Long creditCardNo,String deliveryType,String orderDate,String shippingDate,String Total,String storeLocation) {
	
	getConnection();
	String query = "Insert into orders (orderID,customerName,address,city,state,zip,creditCardNumber,deilveryOption,phoneNumber,order_Date,shippping_date,total,storeId) values ?,?,?,?,?,?,?,?,?,?,?,?,?)";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setInt(1, OrderID);
		pm.setString(2, customerName);
		pm.setString(3, address);
		pm.setString(4, city);
		pm.setString(5, state);
		pm.setLong(6, zip);
		pm.setLong(7, creditCardNo);
		pm.setString(8,deliveryType);
		pm.setLong(9, phoneNumber);
		pm.setString(10,orderDate);
		pm.setString(11,shippingDate);
		pm.setString(12,Total);
		pm.setString(13,storeLocation);
		int rs = pm.executeUpdate();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void addOrderDetails(int OrderID,String productName,double orderPrice,String userName,Long creditCardNo,String orderName) {
	
	getConnection();
	String query = "Insert into order_details (orderID,productName,productPrice,UserName,creditCardNo,orderName) values (?,?,?,?,?,?)";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setInt(1, OrderID);
		pm.setString(2, productName);
		pm.setDouble(3, orderPrice);
		pm.setString(4, userName);
		pm.setLong(5, creditCardNo);
		pm.setString(6, orderName);
		int rs = pm.executeUpdate();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
                                                                                                    

public static List<OrderPayment> fetchUserOrders(String userName){
	
	getConnection();
	String query = "Select * from order_details where userName = ? ";
	List<OrderPayment> list = new ArrayList<>();
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setString(1, userName);
		ResultSet rs = pm.executeQuery();
		while(rs.next()) {
			//OrderPayment(int orderId,String userName,String firsntame,String orderName,double orderPrice,String creditCardNo)
			OrderPayment od = new OrderPayment(rs.getInt("orderID"),rs.getString("UserName"),rs.getString("orderName"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("creditCardNo"));
			list.add(od);
		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
}
public static void addNewProduct(String prodCategory,String prodName,Double price,String prodDesc,String prodRetailer,Double prodDisc,String prodCondition,String prodImg,String isRebate,String isSale) {
	
	getConnection();
	String query = "Insert into allprods (prod_cat,prod_name,prod_price,prod_Description,prod_Retailer,prod_disc,prod_condition,prod_img,isRebate,isSale) values (?,?,?,?,?,?,?,?,?,?)";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setString(1, prodCategory);
		pm.setString(2, prodName);
		pm.setDouble(3, price);
		pm.setString(4, prodDesc);
		pm.setString(5, prodRetailer);
		pm.setDouble(6, prodDisc);
		pm.setString(7, prodCondition);
		pm.setString(8, prodImg);
		pm.setString(9, isRebate);
		pm.setString(10, isSale);
		int rs = pm.executeUpdate();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static void deleteProduct(int productID) {
	getConnection();
	String query = "Delete from allprods where prod_id  = ? ";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setInt(1, productID);
		int rs = pm.executeUpdate();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static List<OrderPayment> fetchOrders(int orderID){
	
	getConnection();
	String query = "Select * from order_details where orderID = ? ";
	List<OrderPayment> list = new ArrayList<>();
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setInt(1, orderID);
		ResultSet rs = pm.executeQuery();
		while(rs.next()) {
			//OrderPayment(int orderId,String userName,String firsntame,String orderName,double orderPrice,String creditCardNo)
			OrderPayment od = new OrderPayment(rs.getInt("orderID"),rs.getString("UserName"),rs.getString("orderName"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("creditCardNo"));
			list.add(od);
		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
}
public static void cancelOrders(int orderID,String productName) {
	getConnection();
	String query = "Delete from order_details where orderID = ? and productName = ? ";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		pm.setInt(1, orderID);
		pm.setString(2, productName);
		int rs = pm.executeUpdate();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static List<AllProducts> getMostTrending(){
	List<AllProducts> list= new ArrayList<>();
	
	getConnection();
	String query = "select ap.* from allprods ap  inner join "
			+ "(SELECT a.prod_id FROM `order_details` o , allprods a where o.productName = a.prod_name group by a.prod_id order by count(1) desc limit 5) as ap2 "
			+ "where ap.prod_id = ap2.prod_id limit 5";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		ResultSet rs = pm.executeQuery();
		while(rs.next()) {
			//OrderPayment(int orderId,String userName,String firsntame,String orderName,double orderPrice,String creditCardNo)
			DoorBells doorbell = new DoorBells();
			AllProducts prods = new AllProducts();
			doorbell.setId(rs.getString("prod_id"));
			doorbell.setName(rs.getString("prod_name"));
			doorbell.setPrice(rs.getDouble("prod_price"));
			doorbell.setImage(rs.getString("prod_img"));
			doorbell.setDescription(rs.getString("prod_Description"));
			doorbell.setRetailer(rs.getString("prod_Retailer"));
			doorbell.setCondition(rs.getString("prod_condition"));
			doorbell.setDiscount(rs.getDouble("prod_disc"));
			prods.setDoorbell(doorbell);
			prods.setCategory(rs.getString("prod_cat"));
			list.add(prods);
			
		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
	
}
public static List<StoreDetails> getMostSalesStore(){
	
	List<StoreDetails> list= new ArrayList<>();
	getConnection();
	//String query = "SELECT s.* FROM orders o , store_details s where o.zip = s.zipcode GROUP by s.zipcode ORDER by count(1) desc limit 1";
	String query = "SELECT s.storeId, s.streetAddress, s.city, s.state, s.zipcode FROM store_details s JOIN orders o ON s.zipcode = o.zip GROUP BY s.storeId, s.streetAddress, s.city, s.state,s.zipcode ORDER BY COUNT(o.orderId) DESC LIMIT 5;";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		ResultSet rs = pm.executeQuery();
		while(rs.next()) {
			//OrderPayment(int orderId,String userName,String firsntame,String orderName,double orderPrice,String creditCardNo)
			StoreDetails StoreDetails = new StoreDetails(rs.getInt("storeID"),rs.getString("streetAddress"),rs.getString("city"),rs.getString("state"),rs.getLong("zipcode"));
			list.add(StoreDetails);
		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
	
}

public static List<AllProducts> getMostLiked(){
	List<AllProducts> list= new ArrayList<>();
	
	getConnection();
	String query = "select ap.* from allprods ap  inner join "
			+ "(SELECT a.prod_id FROM `order_details` o , allprods a where o.productName = a.prod_name group by a.prod_id order by count(1) desc limit 5) as ap2 "
			+ "where ap.prod_id = ap2.prod_id limit 5";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		ResultSet rs = pm.executeQuery();
		while(rs.next()) {
			//OrderPayment(int orderId,String userName,String firsntame,String orderName,double orderPrice,String creditCardNo)
			DoorBells doorbell = new DoorBells();
			AllProducts prods = new AllProducts();
			doorbell.setId(rs.getString("prod_id"));
			doorbell.setName(rs.getString("prod_name"));
			doorbell.setPrice(rs.getDouble("prod_price"));
			doorbell.setImage(rs.getString("prod_img"));
			doorbell.setDescription(rs.getString("prod_Description"));
			doorbell.setRetailer(rs.getString("prod_Retailer"));
			doorbell.setCondition(rs.getString("prod_condition"));
			doorbell.setDiscount(rs.getDouble("prod_disc"));
			prods.setDoorbell(doorbell);
			prods.setCategory(rs.getString("prod_cat"));
			list.add(prods);
			
		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
	
}

public static List<StoreDetails> getAllStoreDetails(){
	
	List<StoreDetails> list= new ArrayList<>();
	getConnection();
	String query = "SELECT s.* FROM store_details s";
	try {
		PreparedStatement pm = conn.prepareStatement(query);
		ResultSet rs = pm.executeQuery();
		while(rs.next()) {
			//OrderPayment(int orderId,String userName,String firsntame,String orderName,double orderPrice,String creditCardNo)
			StoreDetails StoreDetails = new StoreDetails(rs.getInt("storeID"),rs.getString("streetAddress"),rs.getString("city"),rs.getString("state"),rs.getLong("zipcode"));
			list.add(StoreDetails);
		}
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
	
}
public static void updateProductDetails(int prodId,String prodCategory,String prodName,Double price,String prodDesc,String prodRetailer,Double prodDisc,String prodCondition,String prodImg,String isRebate,String isSale,String quantity) {
    
    getConnection();
    String query = "update allprods set prod_cat = ? ,prod_name = ? ,prod_price = ?, prod_Description =? ,prod_Retailer = ?,prod_disc =? ,prod_condition =?,prod_img = ?,isRebate = ?,isSale = ?,quantity = ?  where prod_id = ?";
    try {
        PreparedStatement pm = conn.prepareStatement(query);
        pm.setString(1, prodCategory);
        pm.setString(2, prodName);
        pm.setDouble(3, price);
        pm.setString(4, prodDesc);
        pm.setString(5, prodRetailer);
        pm.setDouble(6, prodDisc);
        pm.setString(7, prodCondition);
        pm.setString(8, prodImg);
		pm.setString(9, isRebate);
        pm.setString(10, isSale);
        pm.setString(11, quantity);
        pm.setInt(12, prodId);
        int rs = pm.executeUpdate();
        conn.close();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
}
public static StoreDetails fetchUserStore(String userName) {
    
    getConnection();
    int res = 0;
    StoreDetails storeDetails = null;
    String query = "SELECT s.* FROM users u , store_details s where u.storeId = s.storeID and u.userName = ? ";
    try {
        PreparedStatement pm = conn.prepareStatement(query);
        pm.setString(1, userName);
        ResultSet rs = pm.executeQuery();
        while(rs.next()) {
            //int id, String name, String city, String state, Long zipcode
            storeDetails = new StoreDetails(rs.getInt("storeID"),rs.getString("streetAddress"),rs.getString("city"),rs.getString("state"),rs.getLong("zipcode"));
        }
        conn.close();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return storeDetails;
    
}

public static User fetchUserDetails(String userName) {
    
    getConnection();
    int res = 0;
    User user = null;
    String query = "SELECT u.* FROM users u  where u.userName = ? ";
    try {
        PreparedStatement pm = conn.prepareStatement(query);
        pm.setString(1, userName);
        ResultSet rs = pm.executeQuery();
        while(rs.next()) {
            //String name, String password, String usertype, String address, String city,String state, String zip,int storeID,String fullName
            user = new User(rs.getString("userName"),rs.getString("password"),rs.getString("userType"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getString("zip")
                    ,rs.getInt("storeId"),rs.getString("CustName"));
        }
        conn.close();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return user;
    
}
public static List<Product> fetchAllProducts() {
    
    getConnection();
    List<Product> list = new ArrayList<>();
    int res = 0;
    StoreDetails storeDetails = null;
    String query = "SELECT * from allprods ";
    try {
        
        PreparedStatement pm = conn.prepareStatement(query);
        ResultSet rs = pm.executeQuery();
        while(rs.next()) {
            Product product = new Product();
            product.setId(rs.getString("prod_id"));
            product.setName(rs.getString("prod_name"));
            product.setPrice(rs.getDouble("prod_price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setDiscounAmt(rs.getDouble("prod_disc"));
            if(rs.getInt("isRebate") ==1)
                product.setIsRebate(true);
            else
                product.setIsRebate(false);
            if(rs.getInt("isSale") ==1)
                product.setIsSale(true);
            else
                product.setIsSale(false);
            list.add(product);
            
        }
        conn.close();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return list;
    
}
public static List<Product> fetchAllProductsSales() {
    
    getConnection();
    List<Product> list = new ArrayList<>();
    int res = 0;
    StoreDetails storeDetails = null;
    //String query = "select a.*,count(1) as total_quantity_sold,a.productPrice*count(1) as total_produc_Sale from order_details a group by productName order by count(1) desc";
	String query = "SELECT productName, SUM(1) AS total_quantity_sold, SUM(productPrice) AS total_produc_Sale , productPrice FROM order_details GROUP BY productName,productPrice ORDER BY total_quantity_sold DESC";
    try {
        
        PreparedStatement pm = conn.prepareStatement(query);
        ResultSet rs = pm.executeQuery();
        while(rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("productName"));
            product.setPrice(rs.getDouble("productPrice"));
            product.setQuantity(rs.getInt("total_quantity_sold"));
            product.setDiscounAmt(rs.getDouble("total_produc_Sale"));
            list.add(product);
            
        }
        conn.close();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return list;
    
}
public static List<Product> fetchTotalDailySales() {
    
    getConnection();
    List<Product> list = new ArrayList<>();
    String query = "SELECT sum(total) as total,date_format(order_Date, '%Y/%m/%d') as order_Date FROM `orders` group by order_Date order by order_Date";
    try {
        
        PreparedStatement pm = conn.prepareStatement(query);
        ResultSet rs = pm.executeQuery();
        while(rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("order_Date"));
            product.setPrice(rs.getDouble("total"));
            
            list.add(product);
            
        }
        conn.close();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return list;
    
}
public static void updateProductQuantity(String id) {
    
    getConnection();
    String query = "update allprods set quantity = quantity-1 where prod_id = ?";
    try {
        PreparedStatement pm = conn.prepareStatement(query);
        pm.setString(1, id);
        int rs = pm.executeUpdate();
        conn.close();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
}
}