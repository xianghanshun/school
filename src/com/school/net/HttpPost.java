package com.school.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class HttpPost {
	public static void main(String args[]){
		JSONObject obj = new JSONObject();
		obj.put("password","123" );
		obj.put("subtype","" );
		obj.put("type","login" );
		obj.put("username","101" );
		obj.put("version","1" );
		String strJson = obj.toString();
		strJson = "jsonString="+strJson;
		String sr = sendPost("http://192.168.0.90/xsip/index.php", strJson);
		System.out.println(sr);
	}
	public static String sendPost(String url, String para){
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try{
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Chrome/5.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			out = new PrintWriter(conn.getOutputStream());
			out.print(para);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = in.readLine()) != null){
				result += line;
			}
		} catch(Exception e){
			System.out.println("exception occured while sending post" + e);
			e.printStackTrace();
		}
		finally{
			try{
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
}
