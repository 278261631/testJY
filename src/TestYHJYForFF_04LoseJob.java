 

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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

import com.google.gson.JsonArray;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
 
public class TestYHJYForFF_04LoseJob  {
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
    	WebDriver driver = new FirefoxDriver();
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
        		boolean loadcomplete = d.findElement(By.id("111368")).isDisplayed();
        		return loadcomplete;
        	}
        });
        WebElement elementNext = driver.findElement(By.id("111368"));
        elementNext.click();
 //等待menu加载完毕二级菜单
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("111370"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("111370"));
        elementNext.click();
        //等待menu加载完毕三级菜单
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("111386"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("111386"));
        elementNext.click();
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_111386").findElement(By.id("aac003")).isDisplayed();
        		return loadcomplete;
        	}
        });
        elementNext=driver.findElement(By.id("aac003")); //姓名
        elementNext.sendKeys(jsonarr.getJSONObject(0).get("name").toString());
        
        
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.className("ui-multiselect-menu")).isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);
        
        
        //选择失业原因
        elementNext=driver.findElement(By.id("ycc028_desc")); 
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);

        //选择失业类型
        elementNext=driver.findElement(By.id("ycc027_desc")); 
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);
        
        //输入失业日期
        elementNext=driver.findElement(By.id("ycc024")); 
        elementNext.click();
        elementNext.sendKeys("20110701");
        
//        Thread.sleep(1000);
        //输入失业日期
        elementNext.click();
        elementNext=driver.findElement(By.id("aae043")); 
        elementNext.sendKeys("20110701");
//        Thread.sleep(1000);

        elementNext=driver.findElement(By.id("saveBtn")); 
        elementNext.click();
        
        
        
        //elementNext=driver.findElement(By.id("addBtn")); //新增下一个
        //elementNext.click();
        
        
        
        
        
        //关闭浏览器
        Thread.sleep(30000);
        driver.quit();
    }
    
    
}