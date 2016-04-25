package mayong.config;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import util.XmlImpl;

public class TestConfiger {
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public JSONArray getJsonarry() {
		return jsonarry;
	}
	public void setJsonarry(JSONArray jsonarry) {
		this.jsonarry = jsonarry;
	}
	private String URL;
	private String userName;
	private String userPass;
	private JSONArray jsonarry;

	public TestConfiger() {
		super();
		String xmlString = null;
		try {
			xmlString = XmlImpl.
					readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
//    			readF1("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");
    	JSONObject jobj= XML.toJSONObject(xmlString).getJSONObject("peoples");
    	
    	JSONArray jsonarr=jobj.getJSONArray("people");
    	String loginName	=  	jobj.get("loginName").toString();
    	String loginPass	=  	jobj.get("loginPass").toString();
		this.URL =  	jobj.get("homePath").toString();
		this.userName = loginName;
		this.userPass = loginPass;
		this.jsonarry = jsonarr;
//		System.out.println(loginName+"      "+jsonarr.getJSONObject(0).get("idCardNum").toString());
	}
	
	
	
	
}
