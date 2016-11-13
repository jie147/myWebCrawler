package com.jie.downloader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.examples.HtmlToPlainText;

public class HtmlUtil 
{
	
	public static HttpClient getClient() 
	{  
	     HttpClient client = new HttpClient();  
	     return client;  
	 }  
	   
	 public static String getHtml(String url) 
	 {  
		 try 
		 {
			 return getHtml(url, 80, "utf8","114.92.236.142", 8118, "Mozilla/5.0");
	     } catch (IOException e) {
			// TODO Auto-generated catch block
	     	e.printStackTrace();
	     }  
		  return null;
	  }  
	   
	   public static String getHtml(String url, String cookie) throws HttpException, IOException 
	   {  
	     return getHtml(url, 80, null, null, 0, cookie);  
	   }  
	   
	   public static String getHtml(String url, int port, String cookie) throws HttpException, IOException 
	   {  
	     return getHtml(url, port, null, null, 0, cookie);  
	   }  
	   
	   public static String getHtml(String url, int port, String encoding, String proxyHost, int proxyPort, String cookie)  
	       throws HttpException, IOException 
	   {  
		   HttpClient httpClient = getClient();  
		   System.out.println(httpClient.getClass().getName());
		   String rest = null;  
		   if(proxyHost != null && proxyPort != 0)
			   httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);  
		     
		   HttpMethod method = new GetMethod(url);  
		     
		   if(!StringUtils.isBlank(cookie)) 
			   method.addRequestHeader("Cookie", cookie);  
		 

		   method.addRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36");  
		   //Mozilla/5.0 (Windows NT 6.1; rv:7.0.1) Gecko/20100101 Firefox/7.0.1  
		   httpClient.executeMethod(method);  
		  System.out.println(method.getRequestHeaders());
		   
		   String header = method.getResponseHeader("Content-Type").getValue();  
		   header=header.toLowerCase();
		   
		   if(header.contains("charset=")) 
			   encoding = header.substring(header.indexOf("charset=") + "charset=".length()); 
		   
		   if(encoding == null) 
			   encoding = "UTF-8";  
		   
		   rest = new String(method.getResponseBody(), encoding);  
		   method.releaseConnection();  
		     
		   return rest;  
	  }  
	   
	  public  static void saveImageByURL(String image_url,String save_path,int num)
	  {
		  
		  
		  
		try {
			 URL url = new URL(image_url);
			 DataInputStream dis = new DataInputStream(url.openStream()); 

			 String new_image_path=save_path+num+".jpg";
			  
			 FileOutputStream fos=new FileOutputStream(new File(new_image_path));
			 byte[] buffer=new byte[4096];
			 int length;
			  
			 while((length=dis.read(buffer))>0)
				 fos.write(buffer,0,length);
			  
			  dis.close();
			  fos.close();
			  System.out.println(new_image_path+" has been downloaded");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
	 }
	  
	 
	  public  static void saveMP3ByURL(String mp3_url,String save_path,int num)
	  {
		  
		  
		try {
			 URL url = new URL(mp3_url);
			 DataInputStream dis = new DataInputStream(url.openStream());

			 String new_image_path=save_path+num+".mp3";
			  
			 FileOutputStream fos=new FileOutputStream(new File(new_image_path));
			 byte[] buffer=new byte[4096];
			 int length;
			  
			 while((length=dis.read(buffer))>0)
				 fos.write(buffer,0,length);
			  
			  dis.close();
			  fos.close();
			  System.out.println(new_image_path+" has been downloaded");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
	 }
	

}
