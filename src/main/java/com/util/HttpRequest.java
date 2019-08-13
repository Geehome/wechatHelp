package com.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
//	public static String sendRequest(String url,String para,String method) {
	
	public static JSONObject sendRequest(String url, String para, String method) {
		StringBuffer result = new StringBuffer();
		BufferedReader br = null;
		HttpURLConnection httpcon = null;
		JSONObject jo = null;
		try {
			URL realUrl = new URL(url);
			//打开连接
			URLConnection connect = realUrl.openConnection();
			//打开的是http连接
			httpcon = (HttpURLConnection)connect;
			httpcon.setRequestMethod(method);
			httpcon.setDoInput(true);
			httpcon.setDoOutput(true);
			httpcon.setReadTimeout(60000);
			httpcon.setReadTimeout(60000);
			httpcon.setUseCaches(false);
			
			if(method.equalsIgnoreCase("GET")) {
				//建立连接，getInputStream也会默认建立连接
				httpcon.connect();
			}
			//参数编码，方法为POST
			if(para!=null) {
				OutputStream os = httpcon.getOutputStream();
				os.write(para.getBytes("UTF-8"));
				os.close();
			}
			
			InputStreamReader isr = new InputStreamReader(httpcon.getInputStream(),"UTF-8");
			br = new BufferedReader(isr);
			
			String line;
			while((line=br.readLine())!=null) {
				result.append(line);
			}
//			ret = new String(result);
//			System.out.println(ret);
			jo = JSONObject.parseObject(new String(result));

			br.close();
			isr.close();
			isr=null;
			httpcon.disconnect();
		}catch(Exception e){
			System.out.println("发送get请求出错");
			e.printStackTrace();
		}finally {
			try {
				if(br!=null) {
					br.close();
				}
				if(httpcon!=null) {
					httpcon.disconnect();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
//		return ret;
		return jo;
	}
	
	//返回值为图片的字节流，由于不同的机器，对于二进制数据的处理有可能不一样，因此需要Base64
	//进行编码，变成字符
	public static byte[] getLogo(String avatarURL,String para,String method) {
		HttpURLConnection httpcon = null;
		try {
			URL realUrl = new URL(avatarURL);
			//打开连接
			URLConnection connect = realUrl.openConnection();
			//打开的是http连接
			httpcon = (HttpURLConnection)connect;
			httpcon.setRequestMethod(method);
			httpcon.setDoInput(true);
			httpcon.setDoOutput(true);
			httpcon.setReadTimeout(60000);
			httpcon.setReadTimeout(60000);
			httpcon.setUseCaches(false);
			
			if(method.equalsIgnoreCase("GET")) {
				//建立连接，getInputStream也会默认建立连接
				httpcon.connect();
			}
			//参数编码，方法为POST
			if(para!=null) {
				OutputStream os = httpcon.getOutputStream();
				os.write(para.getBytes("UTF-8"));
				os.close();
			}
			httpcon.disconnect();
		}catch(Exception e){
			System.out.println("发送get请求出错");
			e.printStackTrace();
		}finally {
//			try {
				if(httpcon!=null) {
					httpcon.disconnect();
				}
//			}catch(IOException e) {
//				e.printStackTrace();
//			}
		}
		
		return null;
	}
}
