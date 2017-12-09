package com.tcs.PHI.bean;


import java.math.BigDecimal;
import java.util.ArrayList;


public interface InventoryOrderBean {
	
	public String number=null;
	
	public long orderNumber=0 ;
	
	public String orderDate=null;
	
	public String sellerCode=null;
	
	public String sellerName=null;
	
	public String sellerType =null;
	
	public String sellerAddress=null;
	
	public String sellerTelephone=null;
	
	public String sellerFAX=null;
	
	public String sellerEmail=null;
	
	public String buyerId=null;
	
	public String buyerGln=null;
	
	public String buyerCode=null;
	
	public  String buyerName=null;
	
	public String storeName=null ;
	
	public String storeCode=null ;
	
	public String deliveryDate=null;
		
	public String storeManager=null;
	
	public ArrayList<Item> items= new ArrayList<Item>();
	
	public BigDecimal itemsTotalPrice=new BigDecimal(0); 
	
	

	public static interface Item{
	
	public int sequenceNum=0;
	public String itemId=null;
	public String itemName=null;
	public BigDecimal itemUnitPrice=new BigDecimal(0);
	public BigDecimal itemTotalPrice=new BigDecimal(0);
	public String itemQuantity=null;
	public String itemUnit=null;
	public String itemUnitCode=null;
	public String itemContainerUnit=null;
	public String itemContainerUnitCode=null;
	public String itemContainerQuantity=null;
	public String itemPerContainerQuantity=null;
	
	
	
	}

}
