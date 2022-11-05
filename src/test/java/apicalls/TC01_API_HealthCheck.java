package apicalls;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC01_API_HealthCheck {
	
	
	String AuthToken=null;
	String param_id=null;
	String Btoken;
	

	//Using data Provider to check both scenarios

	
	@DataProvider (name="keys")
    public String[] key() {
		String info[] = {"testApiKey", "untestApiKey"}; 
		
		return info ;
		}
	
	
	
	
	@Test (priority=1 , dataProvider="keys")
	public void TestForAPIhealth(String value) {
		
		
     //Specify Base URI
	       	RestAssured.baseURI="http://localhost:3000";
	       	
	 // Request object 
     RequestSpecification	httprequest= RestAssured.given();
                            httprequest.header("x-api-key" ,value);
     // Response object
     Response response = httprequest.request(Method.GET , "/");
            	
	 int Rcode_body=	response.statusCode();
	System.out.println("Status code for TestForAPIhealth---------->"+Rcode_body);
	
	System.out.println("");
	
	//Lets Check if Valid key and Invalid key Return response as expected
	
	 String responseBody  =  response.getBody().asString();

     System.out.println("Key you have entered is --------> "+responseBody);

    if(!(value.equals("testApiKey"))){
    	Assert.assertEquals(401, Rcode_body);
 }
    
    else { 
    	Assert.assertEquals(200, Rcode_body);
 }
    
	}
	
	@Test(priority=2)
	public void  Register_User() {
		 //Specify Base URI
    RestAssured.baseURI="http://localhost:3000";
       	
 // Request object 
     RequestSpecification	httprequest= RestAssured.given();
  
 // Request Payload with Request
     
     JSONObject JsonBody = new JSONObject();
     JsonBody.put("email", "aaa@aaa.com");
     JsonBody.put("password", "123456");
     
  //   httprequest.header("x-api-key" ,"testApiKey");
     httprequest.header("Content-type" ,"Application/Json");
                            
     httprequest.body(JsonBody.toJSONString());
                             
  // Response object
     Response response = httprequest.request(Method.POST , "/api/users/register");
     
     int Rcode_body=	response.statusCode();
     System.out.println("");
     System.out.println("Status code for test Register User---->"+Rcode_body);
	
	
	Assert.assertEquals(201, Rcode_body);
	
	
	
	}
	
	@Test (priority=3)
	public void getToken() {
	 //Specify Base URI
	    RestAssured.baseURI="http://localhost:3000";
	       	
	 // Request object 
	     RequestSpecification	httprequest= RestAssured.given();
	  
	 // Request Payload with Request
	     
	     JSONObject JsonBody = new JSONObject();
	     JsonBody.put("email", "aaa@aaa.com");
	     JsonBody.put("password", "123456");
	     
//	     httprequest.header("x-api-key" ,"testApiKey");
	     httprequest.header("Content-type" ,"Application/Json");
	                            
	     httprequest.body(JsonBody.toJSONString());
	                             
	  // Response object
	     Response response = httprequest.request(Method.POST , "/api/users/tokens");
	   
	   //Validation  
	     int Rcode_body=	response.statusCode();
	     System.out.println("");
	     System.out.println("Status code for get Token Test Case-------->" +Rcode_body);
		 
		
		 String responseBody  =  response.getBody().asString();
		
		 
		// Line of code will feed bearer token to global variable which can be use for other test cases 
		 Btoken =  JsonPath.from(responseBody).get("data.token");
		 System.out.println("");
		 System.out.println(Btoken);
	     
         // This will assign Token to global variable
         AuthToken = Btoken;
         
         Assert.assertEquals(200, Rcode_body);
	}
	
	@Test(priority=4)
	 public void Add_New_GiftCard() {
		System.out.println("");
		
	//Confirming if I am receiving the Token or not	
		System.out.println("Received from previous test  -----------------> "+AuthToken);
		
		
		//Specify Base URI
	    RestAssured.baseURI="http://localhost:3000/";
	       	
	 // Request object 
	     RequestSpecification	httprequest= RestAssured.given();
	    
	   
	 // Request Payload 
	     
	     JSONObject JsonBody = new JSONObject();
	     JsonBody.put("_id", "2");
	     JsonBody.put("vendor", "bestbuy");
	     JsonBody.put("amount", "2000");
	
	     // Headers Along request   
	     httprequest.header("Authorization" ,"Bearer "+AuthToken); 
//	     httprequest.header("x-api-key" ,"testApiKey");
	     httprequest.header("Content-type" ,"Application/Json");
	  
	     
	  
	     httprequest.body(JsonBody.toJSONString());      
	     
	     
	     
	     //Response Object
	                     
	      Response response  =  httprequest.request(Method.POST, "/api/giftcards");
	     
	      
	      
	   //Validation   
	      int Rcode_body=	response.statusCode();
	      System.out.println("");
	      System.out.println("Status code for Test Case Add_New_GiftCard---->"+Rcode_body);
			 
		  String responseBody  =  response.getBody().asString();
		  System.out.println("");
		  System.out.println(responseBody);
		
	 // below line of code will feed id to global which can be use for other test cases
		   String id =  JsonPath.from(responseBody).get("data._id");
	       param_id =id;
	       
	       Assert.assertEquals(201, Rcode_body);
	}
	
	
	@Test(priority=5)
	public void GetSpecific_Giftcard() {
		
	
	//	Specified base URI
          RestAssured.baseURI="http://localhost:3000/api/giftcards";
	//      RestAssured.basePath="/:id";
	
        	
    // Request object with all the headers 
     	     RequestSpecification	httprequest= RestAssured.given();
     	     httprequest.header("Authorization" ,"Bearer "+AuthToken); 
   	//         httprequest.header("x-api-key" ,"testApiKey");
   	         httprequest.header("Content-type" ,"Application/Json");
   	         
	
	         
	
	//Response Object with pathparameters
             
   	      Response response  = httprequest.pathParams(":id",param_id).request(Method.GET, "/{:id}");
		     
   	          
   	 //Validation        
   	          int Rcode_body=	response.statusCode();
			  System.out.println(Rcode_body);
				 
			  String responseBody  =  response.getBody().asString();
			  System.out.println("");
			  System.out.println("Status code for Test Case GetSpecific_GiftCard---->"+Rcode_body);
			  System.out.println("");
			  Assert.assertEquals(200, Rcode_body);
			  
			  String Actual = JsonPath.from(responseBody).get("data._id");
				 Assert.assertEquals(param_id, Actual);
	}
	
	

	@Test(priority=6)
	public void GetAllGiftcard() {
		
	
	//	Specified base URI
          RestAssured.baseURI="http://localhost:3000/api";
	//      RestAssured.basePath="/:id";
	
        	
    // Request object with all the headers 
     	     RequestSpecification	httprequest= RestAssured.given();
     	     httprequest.header("Authorization" ,"Bearer "+AuthToken); 
   	//         httprequest.header("x-api-key" ,"testApiKey");
   	         httprequest.header("Content-type" ,"Application/Json");
   	         
	
	         
	
	//Response Object with pathparameters
             
   	      Response response  = httprequest.request(Method.GET,"/giftcards");
		     
   	          
   	 //Validation        
   	          int Rcode_body=	response.statusCode();
   	       System.out.println("Status code for Test Case Get All Gift Cards---->"+Rcode_body);
				 
			  String responseBody  =  response.getBody().asString();
			  
			  System.out.println("");
			  System.out.println(responseBody);
			  String Actual = JsonPath.from(responseBody).get("data[0].vendor");
			  String Actual1 = JsonPath.from(responseBody).get("data[0].amount");
		
			  Assert.assertEquals("bestbuy", Actual);
			  Assert.assertEquals("2000", Actual1);
	}
	
	
	@Test(priority=7)
	public void Delete_A_Giftcard() {
		
	
	//	Specified base URI
          RestAssured.baseURI="http://localhost:3000/api/giftcards";
	//      RestAssured.basePath="/:id";
	
        	
    // Request object with all the headers 
     	     RequestSpecification	httprequest= RestAssured.given();
     	     httprequest.header("Authorization" ,"Bearer "+AuthToken); 
   	//         httprequest.header("x-api-key" ,"testApiKey");
   	         httprequest.header("Content-type" ,"Application/Json");
   	         
	
	         
	
	//Response Object with pathparameters
             
   	      Response response  = httprequest.pathParams(":id",param_id).request(Method.DELETE, "/{:id}");
		     
   	          
   	 //Validation        
   	          int Rcode_body=	response.statusCode();
			  System.out.println(Rcode_body);
				 
			  String responseBody  =  response.getBody().asString();
			  System.out.println("Status code for Test Case Delete_A_GiftCard---->"+Rcode_body);
			
			 String Actual = JsonPath.from(responseBody).get("data._id");
			 Assert.assertEquals(param_id, Actual);  // Validate if right is Deleted
			
			 System.out.println(responseBody);
			 System.out.println("");
			 String message = JsonPath.from(responseBody).get("data.message");
		
			 
			 // As per Requirement Delete should return only ID
			 System.out.println("Message getting from delete command--------->"+message);
			 Assert.assertNull(message);
			  
			  
			  
	}
	
	
	
	
	
	
	
	
	}
	
	


