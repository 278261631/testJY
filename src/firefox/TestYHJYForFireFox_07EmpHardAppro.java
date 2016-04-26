package firefox;
 


import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

 
public class TestYHJYForFireFox_07EmpHardAppro  {
    public static void main(String[] args) throws Exception {
    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
    	empHardApprove(webDriverFF.getWebDriver());
    }

	public static void empHardApprove(WebDriver driver) throws IOException, InterruptedException {
		//获取配置数据
    	String xmlString=XmlImpl.
    			readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml");
//    			readF1("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");
    	JSONObject jobj= XML.toJSONObject(xmlString).getJSONObject("peoples");
    	JSONArray jsonarr=jobj.getJSONArray("people");
    
 
        WebDriverWait webWaiter=new WebDriverWait(driver, 30);
        
        driver.switchTo().defaultContent();
        //切换一下 “培训反馈”（因为系统菜单目前是没列到自动测试的里的  用于取消掉其他菜单的展开
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.id("114967")).isDisplayed();
        		return loadcomplete;
        	}
        });
        WebElement elementNext = driver.findElement(By.id("114967"));
        elementNext.click();
        
        
        //等待一级菜单menu加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.id("114542")).isDisplayed();
        		return loadcomplete;
        	}
        });
        elementNext = driver.findElement(By.id("114542"));
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
        		WebElement elm=d.findElement(By.id("118835"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("118835"));
        elementNext.click();
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_118835").findElement(By.id("aac003")).isDisplayed();
        		return loadcomplete;
        	}
        });
        elementNext=driver.findElement(By.id("quryBtn")); //
        elementNext.click();
        Thread.sleep(1000);
        
        
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.className("slick-checkbox-header")).isEnabled();
        		return loadcomplete;
        	}
        });
        
        Thread.sleep(4000);
        elementNext=driver.findElement(By.className("slick-checkbox-header"));
        elementNext.click();
        
        elementNext=driver.findElement(By.id("approveBtn"));
        elementNext.click();
        
        
    /*
        
//        二级审核
        driver.switchTo().defaultContent();
        
        elementNext=driver.findElement(By.id("128874"));
        elementNext.click();
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_128874").findElement(By.id("aac003")).isDisplayed();
        		return loadcomplete;
        	}
        });
        elementNext=driver.findElement(By.id("quryBtn")); // 
        elementNext.click();
        Thread.sleep(1000);
        
        
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.className("slick-checkbox-header")).isEnabled();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.className("slick-checkbox-header"));
        elementNext.click();
        
        elementNext=driver.findElement(By.id("approveBtn"));
        elementNext.click();
        
        
        //elementNext=driver.findElement(By.id("addBtn")); //新增下一个
        //elementNext.click();
        
        */
        
        
//        
//        //关闭浏览器
//        Thread.sleep(3000);
//        driver.quit();
	}
    
    
}