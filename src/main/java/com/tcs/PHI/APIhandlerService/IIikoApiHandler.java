package com.tcs.PHI.APIhandlerService;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.PHI.APIhandlerService.ApiConfig;
import com.tcs.PHI.APIhandlerService.ServiceBean;
import com.tcs.PHI.req.ReqBean;
import com.tcs.PHI.res.ResBean;


public class IIikoApiHandler extends ServiceBean{

	
	private static final Logger log = LoggerFactory.getLogger(IIikoApiHandler.class);

	String storeCode=null;

	
	
	public IIikoApiHandler(String configFile,String storeCode){
		super(configFile,storeCode);
		this.storeCode=storeCode;
	}
	
	public void logOutOfSession(){
		logOut();
	}
	

		
/**
 * make olap responses
 * @param requestList
 * @param responseList
 * @return
 */
	public List<ResBean> makeOlapResponse(List<ReqBean> requestList){
		
		List<ResBean> responseList= new ArrayList<ResBean>();
		
		String url = getApiConfig().URL_OLAP;
		boolean isadded=false;
		//calling iiko api
		for(ReqBean iikoRequest:requestList){
			isadded=responseList.add(fetchJsonFromPostResponse(url, iikoRequest));
		}
		return responseList;
	
	}
/**
 * for inventory api 
 * @param datetime
 * @param storeCode
 * @return
 */
	public ArrayList<HashMap<String,Object>> makeInventoryBalanceResponse(String datetime,String storeCode){
		ArrayList<HashMap<String,Object>> invBalance= new ArrayList<HashMap<String,Object>>();
		String deptGUID = null;
		ArrayList<HashMap<String,Object>> departments=(ArrayList<HashMap<String,Object>>)fetchFilteredXmlFromGetResponse(ApiConfig.URL_DEPARTMENTS, "corporateItemDto");
		System.out.println("departments--->"+departments.size());
		for(HashMap<String,Object> department : departments) {
			if(String.valueOf(department.get("code")).equals(storeCode)) {
				deptGUID=department.get("id").toString();
			}
		}
		if(deptGUID!=null){
			invBalance=(ArrayList<HashMap<String, Object>>) fetchJsonFromUnnamedGetResponse(ApiConfig.makeURL_INVENTORY_BALANCE(datetime, deptGUID));
			log.debug(invBalance.toString());
		}else
			log.debug("dept guid is null");
		/*
		//picking from files
		HashMap<String,Object> suppliers= new HashMap<String,Object>();
		try {
			suppliers = (HashMap<String,Object>)this.xmlMapper.readValue(new File("D:\\workspace\\test\\src\\test\\resources\\Suppliers.xml"),
					Object.class);
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
		*/
		return invBalance;
		
	}
	
	
/**
 * supplier api
 * @param from
 * @param to
 * @param supplierName
 * @return
 */
	public HashMap<String,Object> makeSuppliersResponse(String supplierId){
		HashMap<String,Object> supplier= new HashMap<String,Object>();
		String url=ApiConfig.URL_SUPPLIERS_SEARCH+"id="+supplierId+"&key=";
		Object o=fetchFilteredXmlFromGetResponse(url,"employee");
		if(o instanceof HashMap)
			supplier=(HashMap<String, Object>) o;
		else if(o instanceof ArrayList){
			for(HashMap<String,Object> s:(ArrayList<HashMap<String, Object>>)o){
				if(s.get("id").equals(supplierId))
						supplier=s;
			}
			}	
				return supplier;
			}
		/**
		 * 
		 * @param store code
		 * @return store details
		 */
		public HashMap<String,Object> makeStoresResponse(String code){
			HashMap<String,Object> store= new HashMap<String,Object>();
			String url=ApiConfig.makeURL_PARAM_SEARCH(ApiConfig.URL_DEPARTMENT_SEARCH, "code", code);
			Object o=fetchFilteredXmlFromGetResponse(url,"corporateItemDto");
			if(o instanceof HashMap)
				store=(HashMap<String, Object>) o;
			else if(o instanceof ArrayList){
				for(HashMap<String,Object> s:(ArrayList<HashMap<String, Object>>)o){
					if(s.get("code").equals(code))
						store=s;
				}
			}
			return store;
		}
	/**
	 * product search with sku
	 * @param sku
	 * @return product having that sku
	 */
	public HashMap<String,Object> makeProductsSearchWithSku(String sku){
		HashMap<String,Object> product= new HashMap<String,Object>();
		String url=ApiConfig.makeURL_PARAM_SEARCH(ApiConfig.URL_PRODUCT_SEARCH, "num", sku);
		Object o=fetchFilteredXmlFromGetResponse(url,"productDto");
		
		if(o instanceof HashMap)
			product=(HashMap<String, Object>) o;
		else if(o instanceof ArrayList){
			for(HashMap<String,Object> s:(ArrayList<HashMap<String, Object>>)o){
				if(s.get("num").equals(sku))
					product=s;
			}
		}
		return product;
	}
	/**
	 * Search product withid
	 * @param id
	 * @return
	 */
	public HashMap<String,Object> makeProductsSearchWithId(String id){
		HashMap<String,Object> product= new HashMap<String,Object>();
		ArrayList<HashMap<String,Object>> products = (ArrayList<HashMap<String, Object>>) fetchFilteredXmlFromGetResponse(ApiConfig.URL_PRODUCTS,"productDto");
		for(HashMap<String,Object> p:products){
			if(p.get("id").equals(id)){
				product=p;
			}
		}
		return product;
	}
	/**
	 * get outer/inner/each unit details for a product
	 * @param sku
	 * @return container details for desired unit
	 */
	public HashMap<String,String> makeUnitDetailsFromProductsResponse(String productId,String whichUnitDetails){
		HashMap<String,Object> productDetails =makeProductsSearchWithId(productId);	//products api
		ArrayList<HashMap<String,String>> containersDetails=(ArrayList<HashMap<String, String>>) productDetails.get("container");  //getting containers
		HashMap<String,String> containerDetails=new HashMap<String,String>();
		
		//get outer unit/inner unit/each unit details
		for(HashMap<String,String> o:containersDetails){
			//outer
			if(o.get("num").toString().equals("1") && whichUnitDetails.equalsIgnoreCase("outer")){
				containerDetails=o;
				
			}
			//inner
			if(o.get("num").toString().equals("2") && whichUnitDetails.equalsIgnoreCase("inner")){
				containerDetails=o;
				
			}
			//each
			if(o.get("num").toString().equals("3") && whichUnitDetails.equalsIgnoreCase("each")){
				containerDetails=o;
				
			}
		}
		return containerDetails;
	}
	
/**
 * suppliers price list api
 * @param from
 * @param to
 * @param supplierCode
 * @return
 */
	public HashMap<String,Object> makeSuppliersPricelistResponse(String from,String to,String supplierCode){
		
		HashMap<String,Object> supplierPriceList= new HashMap<String,Object>();
		String url=ApiConfig.makeURL_SUPPLIER_PRICELIST(supplierCode);
		supplierPriceList=(HashMap<String, Object>) fetchFilteredXmlFromGetResponse(url,"supplierPriceListItemDto");
		/*
		//picking from files
		HashMap<String,Object> supplierPriceLists= new HashMap<String,Object>();

		try {
			supplierPriceLists = (HashMap<String,Object>)xmlMapper.readValue(new File("D:\\workspace\\IDN_StoreToWarehouse\\src\\main\\resources\\suppliersPricelist.xml"),
					Object.class);
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
		*/
	 return supplierPriceList;
	}
	}
	
