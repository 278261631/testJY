package ie;
 

import java.io.IOException;
import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import net.sourceforge.htmlunit.corejs.javascript.ast.ThrowStatement;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

import com.google.gson.JsonArray;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
 
public class TestYHJYForIE_07EmpHard  {
    public static void main(String[] args) throws Exception {

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
        // 用下面代码也可以实现
        // driver.navigate().to("http://www.baidu.com");
 
        // 获取 网页的 title
        System.out.println("1 Page title is: " + driver.getTitle());
 
        // 通过 id 找到 input 的 DOM
        WebElement elementUserName = driver.findElement(By.id("user_name"));
        WebElement elementPwd = driver.findElement(By.id("user_pwd"));
//        WebElement elementSub = driver.findElement(By.linkText("登录"));
        WebElement elementSub = driver.findElement(By.id("login_button"));
        
        
        // 输入关键字
        elementUserName.sendKeys("developer");
        elementPwd.sendKeys("1");
        elementSub.click();
        
 
 
        // 显示搜索结果页面的 title
        System.out.println("2 Page title is: " + driver.getTitle());
        
        WebDriverWait webWaiter=new WebDriverWait(driver, 15);
        //等待一级菜单menu加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.id("114542")).isDisplayed();
        		return loadcomplete;
        	}
        });
        WebElement elementNext = driver.findElement(By.id("114542"));
        elementNext.click();
 //等待menu加载完毕二级菜单
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("118833"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("118833"));
        elementNext.click();
        //等待menu加载完毕三级菜单 
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("118834"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("118834"));
        elementNext.click();
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_118834").findElement(By.id("aac003")).isDisplayed();
        		return loadcomplete;
        	}
        });
        
        
        for (int i = 0; i < jsonarr.length(); i++) {
			elementNext = driver.findElement(By.id("aac003")); //姓名
			String nameInput = jsonarr.getJSONObject(i).get("name").toString();
			if (nameInput.length() > 1) { //如果字符比较多，就先输入最后一个字符之前的，最后再单独输入一个字符 防止rpc刷出错误的结果
				elementNext.sendKeys(nameInput.substring(0,
						nameInput.length() - 1));
				Thread.sleep(2000);
				elementNext
						.sendKeys(nameInput.substring(nameInput.length() - 1));
			} else {
				elementNext.sendKeys(nameInput);
			}
			
			
			
			
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					boolean loadcomplete = d.findElement(
							By.className("ui-multiselect-menu")).isDisplayed();
					return loadcomplete;
				}
			});
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					boolean loadcomplete = d.findElement(
							By.id("ycc131_003_radioDiv")).isDisplayed()
							|| d.findElement(By.id("ycc131_004_radioDiv"))
									.isDisplayed()
							|| d.findElement(By.id("ycc131_005_radioDiv"))
									.isDisplayed()
							|| d.findElement(By.id("ycc131_006_radioDiv"))
									.isDisplayed()
							|| d.findElement(By.id("ycc131_040_radioDiv"))
									.isDisplayed()
							|| d.findElement(By.id("ycc131_060_radioDiv"))
									.isDisplayed()
							|| d.findElement(By.id("redirectURL"))
									.isDisplayed();
					return loadcomplete;
				}
			});
			for (int j = 0; j < 6; j++) {
				if (driver.findElement(By.id("ycc131_003_radioDiv"))
						.isDisplayed()) {
					driver.findElement(By.id("ycc131_003_radioDiv")).click();
					break;
				}
				if (driver.findElement(By.id("ycc131_004_radioDiv"))
						.isDisplayed()) {
					driver.findElement(By.id("ycc131_004_radioDiv")).click();
					break;
				}
				if (driver.findElement(By.id("ycc131_005_radioDiv"))
						.isDisplayed()) {
					driver.findElement(By.id("ycc131_005_radioDiv")).click();
					break;
				}
				if (driver.findElement(By.id("ycc131_006_radioDiv"))
						.isDisplayed()) {
					driver.findElement(By.id("ycc131_006_radioDiv")).click();
					break;
				}
				if (driver.findElement(By.id("ycc131_040_radioDiv"))
						.isDisplayed()) {
					driver.findElement(By.id("ycc131_040_radioDiv")).click();
					break;
				}
				if (driver.findElement(By.id("ycc131_060_radioDiv"))
						.isDisplayed()) {
					driver.findElement(By.id("ycc131_060_radioDiv")).click();
					break;
				}
				if (driver.findElement(By.id("redirectURL")).isDisplayed()) {
					//        		driver.findElement(By.id("redirectURL")).click();

					throw new Exception("没有可见类型选项");
					//        		break;
				}
			}
			elementNext = driver.findElement(By.id("saveBtn"));
			elementNext.click();
			
			Thread.sleep(2000);
			
			if (i < jsonarr.length()-1) {
				driver.findElement(By.id("addBtn")).click();
				Thread.sleep(3000);
				webWaiter.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						boolean loadcomplete = d.findElement(By.id("aac003"))
								.isDisplayed();
						return loadcomplete;
					}
				});
			}
		}
		
        
        
        
        //elementNext=driver.findElement(By.id("addBtn")); //新增下一个
        //elementNext.click();
        
        
        
        
        
        //关闭浏览器
        Thread.sleep(3000);
        driver.quit();
    }
    
    
}