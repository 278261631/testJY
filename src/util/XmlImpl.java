package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.javascript.NamedNodeMap;
import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;
import com.google.common.io.Files;
import com.google.gson.JsonObject;
 
public class XmlImpl implements XmlInterface{
    private Document document;
 
    public void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public void createXml(String fileName) {
        Element root = this.document.createElement("scores"); 
        this.document.appendChild(root); 
        Element employee = this.document.createElement("employee"); 
        Element name = this.document.createElement("name"); 
        name.appendChild(this.document.createTextNode("wangchenyang")); 
        employee.appendChild(name); 
        Element sex = this.document.createElement("sex"); 
        sex.appendChild(this.document.createTextNode("m")); 
        employee.appendChild(sex); 
        Element age = this.document.createElement("age"); 
        age.appendChild(this.document.createTextNode("26")); 
        employee.appendChild(age); 
        root.appendChild(employee); 
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("生成XML文件成功!");
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public  void parserXml(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
             
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                NodeList employeeInfo = employee.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node = employeeInfo.item(j);
                    NodeList employeeMeta = node.getChildNodes();
                    for (int k = 0; k < employeeMeta.getLength(); k++) {
                        System.out.println(employeeMeta.item(k).getNodeName()
                                + ":" + employeeMeta.item(k).getTextContent());
                    }
                }
            }
            System.out.println("解析完毕");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("rawtypes")
	public static String getStringFromXml(String keyName) {
    	String result="";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(Class.class.getClass().getResource("/").getPath()+"SeleniumTestData.xml");
             
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                NodeList employeeInfo = employee.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node = employeeInfo.item(j);
                    NodeList employeeMeta = node.getChildNodes();
                    for (int k = 0; k < employeeMeta.getLength(); k++) {
                    	if (employeeMeta.item(k).getNodeName().equals(keyName)) {
							result=employeeMeta.item(k).getTextContent();
							break;
						}
                        //System.out.println(employeeMeta.item(k).getNodeName()
                          //      + ":" + employeeMeta.item(k).getTextContent());
                    }
                }
            }
           // System.out.println("解析完毕");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    	return result;
    }
    
//    public static void getStringFromXML(){
//    	XmlImpl dd=new XmlImpl();
//    	
//    	String str=Class.class.getClass().getResource("/").getPath()+"SeleniumTestData.xml";
//    	dd.init();
////    	dd.createXml(str);    //创建xml
//    	dd.parserXml(str);    //读取xml
////    	dd.
//    	
//    }
    public static void main(String[] args) throws Exception {
		////		getStringFromXML();
//    	 String result=getStringFromXml("name");
//    	 System.out.println(result);
//    	FileInputStream fileStream=new FileInputStream(Class.class.getClass().getResource("/").getPath()+"SeleniumTestData.xml");
//    	FileInputStream fileStream=new FileInputStream("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");

    	String xmlString=readF1("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");
    	JSONObject jobj= XML.toJSONObject(xmlString);

    	System.out.println(jobj.length());
    	
	}
    
    
    
    public static final String readF1(String filePath) throws IOException {  
    	StringBuffer sb=new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(  
                new FileInputStream(filePath)));  
  
        for (String line = br.readLine(); line != null; line = br.readLine()) {  
            sb.append(line);
        }  
        br.close();  
  
        return sb.toString();
    }
}