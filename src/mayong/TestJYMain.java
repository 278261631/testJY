package mayong;

import java.io.IOException;

import mayong.config.TestConfiger;
import mayong.tasks.TestTasks;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.omg.CORBA.TCKind;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

public class TestJYMain {

	public static void main(String[] args) {

	      //获取配置数据
			TestConfiger testConfiger =new TestConfiger();
	        // 访问 
	    	WebDriver driver = new FirefoxDriver();
	    	driver.manage().window().maximize();
//	        driver.get("http://localhost:8080/yhjy_xj/");
	        driver.get(testConfiger.getURL());
	        // 获取 网页的 title
	        System.out.println("\t\t\tPage title is: " + driver.getTitle());
	        TestTasks ttask=  new TestTasks();
	        ttask.doLogin(driver,testConfiger.getUserName(),testConfiger.getUserPass());
	        
//	    人员管理?    
	        ttask.doSwitchToTopMenu("110385",driver);
	        ttask.doSwitchToSubMenu("110386",driver);
//	        ttask.doSwitchToSubMenu("110390",driver);
	        ttask.doSwitchToTab("110390","myPhoto", driver);
	        
	        
//        菜单点击
	        

	        try {
				ttask.doFillNewPersonFormOneByOne(driver, testConfiger);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	        
			//关闭浏览器
	        try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        driver.quit();
	}

}
