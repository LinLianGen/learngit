/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package controller.admin.wxback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.Hashtable;
import java.util.Map;

import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pay.wxpay.web.services.XMLUtil;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
@SuppressWarnings("unused")
public class ClientCustomSSL {

    public final static void main(String[] args) throws Exception {
    	
    }

    public static boolean  start(/*List<NameValuePair>*/String xml ,String orderId) throws Exception{
    	
    	boolean flag = false;
    	String localhost = "C:/Users/admin/Desktop/";
    	String interent = "/home/";
    	//指定读取证书格式为PKCS12
    	KeyStore keyStore = KeyStore.getInstance("PKCS12");
    	//读取本机存放的PKCS12证书文件
    	FileInputStream instream = new FileInputStream(new File(interent+"apiclient_cert.p12"));
    	try {
    	//指定PKCS12的密码(商户ID)
    	keyStore.load(instream, "1494087802".toCharArray());
    	} finally {
    	instream.close();
    	}
    	SSLContext sslcontext = SSLContexts.custom()
    	.loadKeyMaterial(keyStore, "1494087802".toCharArray()).build();
    	//指定TLS版本
    
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
    	
    	sslcontext,new String[] { "TLSv1" },null,
    	
    	SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    	//设置httpclient的SSLSocketFactory
    		CloseableHttpClient httpclient = HttpClients.custom()
    				.setSSLSocketFactory(sslsf)
    				.build();
    		
    	
    	
    	    try {
    	     	HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
    	     	
    	     	StringEntity se = new StringEntity(xml.toString());
    	     	
    	     	//httppost.reset();
    	    	httppost.setEntity(se);  
    	    	
    	    	//httppost.setEntity(entity);
    	    	
    	    	System.out.println(httppost.getEntity().toString());
    	    	
    	    	System.out.println(xml.toString());
    	    	
    	        System.out.println("executing request" + httppost.getRequestLine());
    	  
    	        CloseableHttpResponse response = httpclient.execute(httppost);
    	        
    	        HttpEntity entity = response.getEntity();
    	        StringBuffer note = new StringBuffer();
    	        Map<String, String> map = new Hashtable<String, String>();
    	        try {
    	        	
    	            System.out.println("----------------------------------------");
    	            System.out.println(response.getStatusLine());
    	            if (entity != null) {
    	                System.out.println(" Response content length: " + entity.getContentLength());
    	                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
    	                String text;
    	                while ((text = bufferedReader.readLine()) != null) {
    	                    //System.out.println(text);
    	                    note.append(text);
    	                }
    	                //System.out.println("打印note："+note.toString());
    	                map = XMLUtil.doXMLParse(note.toString());
    	                String return_code = (String) map.get("return_code");
    	                System.out.println("打印return_code："+return_code);
    	                /*if(return_code.equals("SUCCESS")){	
    	                	UserRecord ur = new UserRecord();
      	                    ur.letMyBackMoney(orderId,"2");
      	                    flag = true; 
    	                }else{    	                	
    	                UserRecord ur = new UserRecord();
  	                    ur.letMyBackMoney(orderId,"3");
  	                    flag = false; 
    	                }*/
    	            }
    	           
    	            EntityUtils.consume(entity);
    	        } finally {
    	            response.close();
    	        }
    	    } finally {
    	        httpclient.close();
    	    }
    	    return flag;
    }
    
    
    /*public static void start2(String xml) throws Exception{
    	 KeyStore keyStore  = KeyStore.getInstance("PKCS12");
    	 CloseableHttpClient httpclient = HttpClients.createDefault();
         try {
             HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");

             FileBody bin = new FileBody(new File("C:/Users/admin/Desktop/apiclient_cert.p12"));
             StringBody comment = new StringBody(xml, ContentType.TEXT_PLAIN);

             HttpEntity reqEntity = MultipartEntityBuilder.create()
                     .addPart("bin", bin)
                     .addPart("comment", comment)
                     .build();


             httppost.setEntity(reqEntity);

             System.out.println("executing request " + httppost.getRequestLine());
             CloseableHttpResponse response = httpclient.execute(httppost);
            
             try {
                 System.out.println("----------------------------------------");
                 System.out.println(response.getStatusLine());
                 HttpEntity resEntity = response.getEntity();
                 if (resEntity != null) {
                     System.out.println("Response content length: " + resEntity.getContentLength());
                 }
                 EntityUtils.consume(resEntity);
             } finally {
                 response.close();
             }
         } finally {
             httpclient.close();
         }
    	    	
    }*/
    
    

}
