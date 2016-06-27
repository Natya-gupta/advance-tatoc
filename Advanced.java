package com.selenium.example;

//import java.awt.List;
import java.util.List;
import java.beans.Statement;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Advanced {

	
	public static void main(String[] args) throws SQLException, InterruptedException, ClientProtocolException, IOException, JSONException 
	{//private Selenium selenium;
	//private FlashSelenium flashApp;
	WebDriver driver=new FirefoxDriver();
	driver.get("http://10.0.1.86/tatoc");
	driver.findElement(By.cssSelector(".page a")).click();
	driver.get("http://10.0.1.86/tatoc/advanced/hover/menu");
	
	
	
	WebElement menu = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/span[1]"));
	
	Actions builder = new Actions(driver);
	builder.moveToElement(menu);
	WebElement submenu = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/span[5]"));
	builder.moveToElement(submenu);
	builder.click().build().perform();
	
//	DriverManager.getConnection(jdbc:mysql:10.0.1.86,"tatocuser","tatoc01");
	

	//Load the mysql driver dynamically
	/*Class.forName("com.mysql.jdbc.Driver");
	//Establish connection
	Connection con = DriverManager.getConnection("jdbc:mysql:wombat", "myLogin", "myPassword");
	//Create statement Object
	Statement stmt = (Statement) con.createStatement();
	//Execute the query and store the results in the ResultSet object
	ResultSet rs =  stmt.executeQuery("SELECT id, name, salary FROM employee");
	//Printing the column values of ResultSet
	while (rs.next()) {
	 int x = rs.getInt("id");
	 String s = rs.getString("name");
	 float f = rs.getFloat("salary");
	}*/
	Thread.sleep(2000);
	 String symbol= driver.findElement(By.cssSelector("#symboldisplay")).getText();
			System.out.println(symbol);
			Connection con=null;
			PreparedStatement pstmt= null;
			ResultSet rs=null;
			String id=null;
			String name=null;
			String passkey= null;
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				  
				con=DriverManager.getConnection("jdbc:mysql://10.0.1.86:3306/tatoc","tatocuser","tatoc01"); 
			
				pstmt= con.prepareStatement("select id from identity where symbol=?;");
				pstmt.setString(1, symbol);
				rs= pstmt.executeQuery();
				if(rs.next()){
					id= rs.getString("id");
				}
				System.out.println(id);
				
			

				
				 int identity= Integer.parseInt(id);
				rs.close();
				pstmt.close();
				pstmt= con.prepareStatement("select name,passkey from credentials where id=?;");
				pstmt.setInt(1, identity);
				rs= pstmt.executeQuery();
				
				if(rs.next()){
					name= rs.getString("name");
					passkey= rs.getString("passkey");
				}
				rs.close();
				pstmt.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			finally {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(con!=null){
					con.close();
				}
			}
			
			driver.findElement(By.cssSelector("#name")).sendKeys(name);
			driver.findElement(By.cssSelector("#passkey")).sendKeys(passkey);
			driver.findElement(By.cssSelector("#submit")).click();
			
			//assignment 3
			//private Selenium selenium;
			//private FlashSelenium flashApp;
			//flashApp = new FlashSelenium(selenium, “movie_player”);
			//Object flashApp;
			//while (Integer.parseInt(flashApp.call(“getPlayerState”)) == 3){
			//	Thread.sleep(1000);
//Thread.sleep(18000);

JavascriptExecutor js = (JavascriptExecutor)driver; 
Thread.sleep(1000);
js.executeScript("player.play()");
Thread.sleep(25000);

driver.findElement(By.cssSelector("a")).click();
//driver.get("http://10.0.1.86/tatoc/advanced/rest");
//String s1=driver.findElement(By.cssSelector("span#session_id")).getText();
//System.out.println("s1") 
/*String s2=s1.substring(12);
System.out.println(s2);
String s="http://10.0.1.86/tatoc/advanced/rest/service/token/";
String s3=s.concat(s2);

driver.get(s3);

String s5=driver.findElement(By.cssSelector("html>body>pre")).getText();
String result = s5.substring(10,42);
System.out.println(result);*/




	/*String url = "http://10.0.1.86/tatoc/advanced/rest/service/register";
	HttpClient client = HttpClients.createDefault();
	//HttpClient client = new DefaultHttpClient();
	HttpPost post = new HttpPost(url);

	// add header
post.setHeader("User-Agent",s5);

    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	urlParameters.add(new BasicNameValuePair("id", "s2"));
	urlParameters.add(new BasicNameValuePair("signature", "s5"));
	urlParameters.add(new BasicNameValuePair("allow access", "1"));

	post.setEntity(new UrlEncodedFormEntity(urlParameters));

	HttpResponse response = (HttpResponse) client.execute(post);
	System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + post.getEntity());
	System.out.println("Response Code : " + 
                                response.getStatus().getCode());

	

	BufferedReader rd = new BufferedReader(
            new InputStreamReader(((HttpEntityEnclosingRequestBase) response).getEntity().getContent()));

StringBuffer result1= new StringBuffer();
String line = "";
while ((line = rd.readLine()) != null) {
result1.append(line);
}
*/

/*ClientResponse response = webResource.header("Cookie", "JSESSIONID=" + sessionId)
.type("application/json")
.accept("application/json")
.post(ClientResponse.class, json);
	
//Map sessionMap=ActionContext.getSession();
//String sessionId = ((String) sessionMap.get("ASESSIONID")).replaceAll("[0-9a-z]*-", "");
/*@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public Response getSomething(@Context HttpHeaders headers, @QueryParam("username") String username)
{
    ...
    Map<String, Cookie> existingCookies = headers.getCookies();
}*/
	/*HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost("http://10.0.1.86/tatoc/advanced/rest/service/register");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(3);
	params.add(new BasicNameValuePair("id", "s2"));
	params.add(new BasicNameValuePair("signature", "s5"));
	params.add(new BasicNameValuePair("allow access", "1"));
	
	httppost.setEntity(new UrlEncodedFormEntity(params, "s5"));

	//Execute and get the response.
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity entity = response.getEntity();

	if (entity != null) {
	    InputStream instream = entity.getContent();
	    try {
	        // do something useful
	    } finally {
	        instream.close();*/
	
	
	
	/* URL url = new URL("http://10.0.1.86/7tatoc/advanced/rest/service/register");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
	
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		String string= "id="+s3+"& signature="+result+"&allow_access=1";
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(string);
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + string);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) 
		{
			response.linkTextappend(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
	
		conn.disconnect();
		driver.navigate().back();
	    driver.findElement(By.cssSelector(".page a")).click();
	    driver.findElement(By.linkText("Download File")).click();*/
	    
	    
		 driver.get("http://10.0.1.86/tatoc/advanced/rest");
		    String string=driver.findElement(By.id("session_id")).getText();
		    string=string.substring(12, string.length());
		   
		   
		    System.out.println(string);
		    
		    URL url = new URL("http://10.0.1.86/tatoc/advanced/rest/service/token/"+string);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			/*if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}*/


			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			String ssss=new String(response);
			
			JSONObject obj=new JSONObject(ssss);
			ssss=(String) obj.get("token");

			System.out.println(ssss);
			
			//post
			
			URL url1 = new URL("http://10.0.1.86/tatoc/advanced/rest/service/register");
			HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
			

			conn1.setRequestMethod("POST");
			
			conn1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "id="+string+"&signature="+ssss+"&allow_access=1";
			
			// Send post request
			conn1.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn1.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = conn1.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			
			conn1.disconnect();
			driver.findElement(By.cssSelector(".page a")).click();
			//driver.findElement(By.linkText("Download File")).click();
			
			
			driver.findElement(By.linkText("Download File")).click();
	        Thread.sleep(5000);
	        BufferedReader br = null;
	        String strng=null, sCurrentLine;
	        try 
	        {
	            int i=0;
	            br = new BufferedReader(new FileReader("file_handle_test.dat"));
	            while ((sCurrentLine = br.readLine()) != null) 
	            {
	                if(i==2)
	                    strng = sCurrentLine;
	                i++;
	            }
	        }
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        } 
	        strng = strng.substring(11,strng.length());
	        driver.findElement(By.id("signature")).sendKeys(strng);
	        driver.findElement(By.className("submit")).click();

	}
}
		
	


