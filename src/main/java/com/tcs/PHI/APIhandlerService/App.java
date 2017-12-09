package com.tcs.PHI.APIhandlerService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.PHI.req.ReqBean;
import com.tcs.PHI.res.ResBean;

public class App {

	public static void main(String[] args) throws JsonProcessingException {
		List<ReqBean> requestList= new ArrayList<ReqBean>();
		List<ResBean> responseList=new ArrayList<ResBean>();
		
		
		//req 1
		ReqBean request= new ReqBean ();
		request.setReportType("SALES");
		request.addGroupByRowField( "Department","OpenDate.Typed");
		request.addAggregateField("UniqOrderId","DishReturnSum","DishSumInt","DishDiscountSumInt.withoutVAT");
		try {
			request.addDefaultFilterByRange("2017-01-01T00:00:00.000","2017-09-05T00:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.addDefaultFilterByValues();
		request.setReportType("SALES");
		
		requestList.add(request);
		
		//req2
		ReqBean request2= new ReqBean ();
    	try{ 
    		request2.addGroupByRowField( "Department","OpenDate.Typed","OrderDiscount.Type");
    		request2.addAggregateField("UniqOrderId");
    		request2.addAggregateField("VAT.Sum");
			request2.addAggregateField("DishSumInt");
			request2.addAggregateField("DiscountSum");
			request2.addDefaultFilterByRange("2017-01-01T00:00:00.000","2017-09-05T00:00:00.000");
			request2.addDefaultFilterByValues();
			request2.setReportType("SALES");
		}catch(ParseException pe){
			System.out.println(pe.getMessage());
		}
    	requestList.add(request2);
		
		
		//req3
    	ReqBean request3= new ReqBean ();
    	try{ 
    		request3.addGroupByRowField( "Department","OpenDate.Typed","Delivery.IsDelivery","Delivery.ServiceType","Banquet","NonCashPaymentType");
    		request3.addAggregateField("UniqOrderId");
			request3.addAggregateField("DiscountSum");
			request3.addAggregateField("DishDiscountSumInt");
			request3.addDefaultFilterByRange("2017-01-01T00:00:00.000","2017-09-05T00:00:00.000");
			request3.addDefaultFilterByValues();
			request3.setReportType("SALES");
		}catch(ParseException pe){
			System.out.println(pe.getMessage());
		}
    	requestList.add(request3);
    	
    	

		ServiceBean service= new ServiceBean("application.properties","");
/*		String token= service.getToken(service.getApiConfig().URL_AUTH);
		String url=service.getApiConfig().URL_OLAP+token;
		String url2= service.getApiConfig().URL_EMPS+token;
		System.out.println(url);
		System.out.println(url2);
    	//loop runs
		for(ReqBean req:requestList){
			//writing req
			String json = null;
			json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(req);
			//System.out.println(json);
		
		ResBean response=service.fetchJsonFromPostResponse(url, req); //fetching response
		responseList.add(response);
		
		//writing res
		for(HashMap<String,String> data:response.getData()){
			System.out.println(data);
		}
		
		}
		//String json1 = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(responseList.get(0).getData());
		//String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(responseList.get(1).getData());
		//System.out.println(json1);
		//System.out.println(json);
		//new DCRWriter().writeToDCR("16701",responseList);
		
		
		//HashMap<String,Object> empList=service.fetchXmlFromGetResponse(url2);
		//System.out.println(empList);
		//ArrayList<HashMap<String,Object>> hm=service.fetchEmployeeXmlFromGetResponse()
		//if(c831367e-778f-e80f-18f7-bd0843cd10c6)
		//String employeeUrl="https://uat-16703-asia-idn.iiko.it/resto/api/employees?key="+service.getToken("https://uat-16703-asia-idn.iiko.it/resto/api/auth?login=admin&pass=2155245b2c002a1986d3f384af93be813537a476");
		HashMap<String,Object> hm=service.fetchXmlFromGetResponse(url2);
		service.logOut(token);
		*/
}}
