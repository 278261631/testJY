package firefox;

import java.io.IOException;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.WebDriver;

import util.XmlImpl;

public class TestYHJYForFireFox_99All  {
    public static void main(String[] args) throws Exception {
    	for (int i = 0; i < 20; i++) {
    		try {
    			executeTest();
    		} catch (Exception e) {
    			e.printStackTrace();
    			
    		}
		}
    }

	private static void executeTest() throws IOException, InterruptedException,
			Exception {
		System.out.println("需要设置系统 “isDemo” 属性为true ，"
    			+ "根据不同的电脑  rpc 或者等待显示部分时间可能需要延长");
    	
    	
//    	String xmlString=XmlImpl.
//    			readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml");
////    			readF1("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");
//    	JSONObject jobj= XML.toJSONObject(xmlString).getJSONObject("peoples");
//    	Writer writer=null;//
    	//先写入 name 和身份证号码 再读取就不用手动修改XML了
//		XML.toJSONObject(xmlString).write(writer);
//    	JSONArray jsonarr=jobj.getJSONArray("people");
    	String xmlFilePaht=Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml";
    	XmlImpl xmlImpl=new XmlImpl();
    	xmlImpl.init();
    	xmlImpl.createXmlPeoples(xmlFilePaht,30);
    	
    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
    	WebDriver driver=webDriverFF.getWebDriver();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
//    	webDriverFF.login("noor", "1");
    	String companyName = "公岗测试流程公司";
    	//全局随机生成人员： 姓名  身份证号码 类型
    	
		TestYHJYForFireFox_01AddUser.addUser(driver);;
		TestYHJYForFireFox_02UserType.changeUserType(driver);;
		TestYHJYForFireFox_03UserType.confirmUserType(driver);;
		TestYHJYForFireFox_04LoseJob.addLoseJob(driver);
		TestYHJYForFireFox_05Paper.paperWhat(driver);
		//06突然找不到iframe了 关掉标签试试
		TestYHJYForFireFox_CloseAllTab.CloseAllTab(driver);
		TestYHJYForFireFox_06EmpHard.empHard(driver);
		TestYHJYForFireFox_07EmpHardAppro.empHardApprove(driver);
		
//		//单位新增公益性岗位
//		MyAiTestStationCreate.main(args);
//		//公益性岗位审核
//		MyAiTestStationCreate.main(args);
		
		TestYHJYForFireFox_10EmpHardHiring.empHardHiring(driver ,companyName);
		TestYHJYForFireFox_11EmpHardMoney.empHardMoneySearch(driver,companyName);
		TestYHJYForFireFox_12EmpHardMoneyApprove.empHardMoneyApprove(driver);
		
		//关闭所有标签
		TestYHJYForFireFox_CloseAllTab.CloseAllTab(driver);
		TestYHJYForFireFox_11EmpHardMoney.empHardMoneySearchApproved(driver);

		TestYHJYForFireFox_CloseAllTab.CloseAllTab(driver);
		TestYHJYForFireFox_11EmpHardMoney.empHardMoneySearchGiven(driver);
		
		Thread.sleep(20000);
		
		//关闭浏览器
//		driver.quit();
	}
}