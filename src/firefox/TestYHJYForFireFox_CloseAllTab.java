package firefox;
 


import java.io.IOException;

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

 /**
  * 公岗录用
  * @author Administrator
  *
  */
public class TestYHJYForFireFox_CloseAllTab  {
    public static void main(String[] args) throws Exception {
    	System.out.println("关掉所有标签页");
    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
    	
    	WebDriver driver=webDriverFF.getWebDriver();

        WebDriverWait webWaiter=new WebDriverWait(driver, 15);
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
        		boolean loadcomplete = d.findElement(By.id("110385")).isDisplayed();
        		return loadcomplete;
        	}
        });
        elementNext = driver.findElement(By.id("110385"));
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
        		WebElement elm=d.findElement(By.id("138069"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("138069"));
        elementNext.click();
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_138069").findElement(By.id("aac003")).isDisplayed();
        		return loadcomplete;
        	}
        });
    	
    	CloseAllTab(webDriverFF.getWebDriver());
    	
    }

    /**
     * 公岗录用
     * @param driver
     * @throws IOException
     * @throws InterruptedException
     */
	public static void CloseAllTab(WebDriver driver) throws IOException, InterruptedException {

        
        driver.switchTo().defaultContent();
        WebElement elementNext = driver.findElement(By.id("tabListIcon"));
        elementNext.click();
        
        Thread.sleep(1000);
        elementNext = driver.findElement(By.cssSelector("li.close-all"));
        
        elementNext.click();
//        Thread.sleep(5000);
        
        
     
		
		
		
		
	        
      
 
	}
    
    
}