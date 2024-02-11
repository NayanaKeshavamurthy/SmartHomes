import java.io.IOException;
import java.io.*;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String orderName;
	private String userName;
	private String firstname;
	private String lastname;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String phone;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	
	public OrderPayment(int orderId,String userName,String firsntame,String orderName,double orderPrice,String creditCardNo){
		this.orderId=orderId;
		this.userName=userName;
		this.firstname=firstname;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.creditCardNo=creditCardNo;

		}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	

}
