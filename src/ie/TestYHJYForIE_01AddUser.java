package ie;
 

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.CreateIDCardNo;
import util.XmlImpl;

import com.google.gson.JsonArray;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
 
public class TestYHJYForIE_01AddUser  {
    public static void main(String[] args) throws InterruptedException, IOException {
        //获取配置数据
    	String xmlString=XmlImpl.
    			readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml");
//    			readF1("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");
    	JSONObject jobj= XML.toJSONObject(xmlString);
    	JSONArray jsonarr=jobj.getJSONObject("peoples").getJSONArray("people");
    	jsonarr.getJSONObject(1);
    	System.out.println(jsonarr.getJSONObject(0).get("name"));
        // 访问 
    	System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
        System.setProperty("webdriver.ie.bin", "C:\\Program Files\\Internet Explorer\\iexplore.exe");

    	WebDriver driver = new InternetExplorerDriver();
    	driver.manage().window().maximize();
        driver.get("http://localhost:8080/yhjy_xj/");
        // 获取 网页的 title
        System.out.println("1 Page title is: " + driver.getTitle());
 
        // 通过 id 找到 input 的 DOM
        WebElement elementUserName = driver.findElement(By.id("user_name"));
        WebElement elementPwd = driver.findElement(By.id("user_pwd"));
//        WebElement elementSub = driver.findElement(By.linkText("登录"));
        WebElement elementSub = driver.findElement(By.id("login_button"));
        
        
        // 输入关键字
//        Clipboard c= Toolkit.getDefaultToolkit().getSystemClipboard();
//        StringSelection  tradata=new StringSelection("developer");
//        c.setContents(tradata, null);
        elementUserName.sendKeys("developer");
//        Actions actions=new Actions(driver);
//        elementUserName.click();
//        Thread.sleep(2000);
//        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL) ;
//        Thread.sleep(2000);
        elementPwd.sendKeys("1");
        elementSub.click();
        
        WebDriverWait webWaiter=new WebDriverWait(driver, 15);
        //等待一级菜单menu加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.id("110385")).isDisplayed();
        		return loadcomplete;
        	}
        });
        WebElement elementNext = driver.findElement(By.id("110385"));
        elementNext.click();
 //等待menu加载完毕二级菜单
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("110386"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("110386"));
        elementNext.click();
        //等待menu加载完毕三级菜单
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("110390"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("110390"));
        elementNext.click();
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_110390").findElement(By.id("myPhoto")).isDisplayed();
        		return loadcomplete;
        	}
        });
        
        for (int i = 0; i < jsonarr.length(); i++) {
			elementNext = driver.findElement(By.id("aac003")); //姓名
//			elementNext.sendKeys("张三_残疾001");
			elementNext.sendKeys(jsonarr.getJSONObject(i).get("name").toString());
			//证件类型
			elementNext = driver.findElement(By.id("aac058_desc"));
			elementNext.click();
			elementNext = driver.findElement(By.id("1"));
			elementNext.click();
			//身份证号码
			elementNext = driver.findElement(By.id("yac002"));
			elementNext.sendKeys(jsonarr.getJSONObject(i).get("idCardNum").toString());
//			elementNext.sendKeys(CreateIDCardNo.getRandomID());
			elementNext.sendKeys(Keys.TAB);
			//        elementNext=driver.findElement(By.)
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.ENTER);
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//户口性质
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			//文化程度
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//政治面貌
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//户口登记日期
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.ENTER);
			//户口行政区划
			elementNext = driver.findElement(By.className("popWin_163"));
			elementNext.click();
			//等待行政区划加载完毕
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					d.switchTo().defaultContent();
					d.switchTo().frame("tab_b_110390");
					d.switchTo().frame(0);
					WebElement areaLink = d.findElement(By.id("650121"));
					boolean loadcomplete = areaLink.isDisplayed();
					return loadcomplete;
				}
			});
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_110390");
			driver.switchTo().frame(0);
			WebElement areaLink = driver.findElement(By.id("650121"));
			areaLink.click();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_110390");
			//联系电话
			elementNext = driver.findElement(By.id("aae005"));
			elementNext.sendKeys("13800000000");
			elementNext = driver.findElement(By.id("aae006"));
			elementNext.sendKeys("我也不知道住哪里好");
			elementNext = driver.findElement(By.id("aae006"));
			elementNext.sendKeys("我也不知道住哪里好");
			elementNext = driver.findElement(By.id("yau002"));
			elementNext.sendKeys("野鸡大学");
			elementNext = driver.findElement(By.id("saveBtn"));
			//Thread.sleep(2000);
			elementNext.click();
			//等待提示框
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement areaLink = d.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163"));
					boolean loadcomplete = areaLink.isDisplayed();
					return loadcomplete;
				}
			});
//			driver.switchTo().defaultContent();
			Thread.sleep(3500);
			driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
//			elementNext = driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163"));
//			elementNext.click();
//			List<WebElement> elementNextList = driver.findElements(By.className("sexybutton_163"));
//			for (WebElement webElement : elementNextList) {
//				if (webElement.getText().equals("确定")) {
//					webElement.click();
//					break;
//				}
//				
//			}
//			elementNext = driver.findElement(By.cssSelector("span[content*='确定']"));
//			elementNext = driver.findElement(By.xpath("/html/body/div[6]/div[2]/div[2]/button"));
//			elementNext = driver.findElement(By.cssSelector("html body.no-scrollbar div.panel.panelnomargin.window div.panel-body.panel-body-noborder.window-body div.dialog-button button.sexybutton_163"));
//			elementNext = driver.findElement(By.cssSelector("html body.no-scrollbar div.panel.panelnomargin.window div.panel-body.panel-body-noborder.window-body div.dialog-button button.sexybutton_163"));
			
//			html body.no-scrollbar div.panel.panelnomargin.window div.panel-body.panel-body-noborder.window-body div.dialog-button button.sexybutton_163
//			elementNext.click();
			Thread.sleep(3500);
			if (i < jsonarr.length()-1) {
				elementNext = driver.findElement(By.id("addBtn")); //新增下一个
				elementNext.click();
				
				Thread.sleep(2000);
//				driver.findElement(By.id("")).
		        webWaiter.until(new ExpectedCondition<Boolean>(){
		        	public Boolean apply(WebDriver d){
		        		boolean loadcomplete = d.findElement(By.id("myPhoto")).isDisplayed();
		        		return loadcomplete;
		        	}
		        });
			}
		}
		//关闭浏览器
        Thread.sleep(3000);
        driver.quit();
    }
    
   
    
}