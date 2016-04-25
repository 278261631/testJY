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
public class TestYHJYForFireFox_12EmpHardMoneyApprove  {
    public static void main(String[] args) throws Exception {
    	System.out.println("需要用户有审核权限，审核权限在系统管理 人员列表最右边有个小三角号  不仔细找你是找不到的！！！！");
    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
    	empHardMoneyApprove(webDriverFF.getWebDriver());
    }

    /**
     * 公岗录用
     * @param driver
     * @throws IOException
     * @throws InterruptedException
     */
	public static void empHardMoneyApprove(WebDriver driver) throws IOException, InterruptedException {
		//获取配置数据
    	String xmlString=XmlImpl.
    			readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml");
//    			readF1("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");
    	JSONObject jobj= XML.toJSONObject(xmlString).getJSONObject("peoples");
    	JSONArray jsonarr=jobj.getJSONArray("people");
    
 
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
        		boolean loadcomplete = d.findElement(By.id("114679")).isDisplayed();
        		return loadcomplete;
        	}
        });
        elementNext = driver.findElement(By.id("114679"));
        elementNext.click();
 //等待menu加载完毕二级菜单
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("114680"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("114680"));
        elementNext.click();
//        //等待menu加载完毕三级菜单 
//        webWaiter.until(new ExpectedCondition<Boolean>(){
//        	public Boolean apply(WebDriver d){
//        		WebElement elm=d.findElement(By.id("128095"));
//        		boolean loadcomplete = elm.isDisplayed();
//        		return loadcomplete;
//        	}
//        });
//        
//        Thread.sleep(1000);
//        elementNext=driver.findElement(By.id("128095"));
//        elementNext.click();
        Thread.sleep(2000);
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_114680").findElement(By.id("yap203_desc")).isDisplayed();
        		return loadcomplete;
        	}
        });
        Thread.sleep(1000);
        
        elementNext=driver.findElement(By.id("query"));
        elementNext.click();
        Thread.sleep(1000);
        
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.xpath("/html/body/form/div[2]/div[2]/div[1]/div/div[4]/div/div[1]/div[2]")).isDisplayed();
        		return loadcomplete;
        	}
        });        
        Thread.sleep(1000);
        
        elementNext=driver.findElement(By.xpath("/html/body/form/div[2]/div[2]/div[1]/div/div[4]/div/div[1]/div[2]"));
        elementNext.click();
        Thread.sleep(1000);
        
		driver.switchTo().defaultContent();
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				d.switchTo().frame(d.findElements(By.tagName("iframe")).size()-1);
				boolean loadcomplete = d.findElement(By.id("yap203_desc")).isDisplayed();
				return loadcomplete;
			}
		});
		
		Thread.sleep(3000);
		elementNext=driver.findElement(By.className("slick-checkbox-header"));
		elementNext.click();
		Thread.sleep(1000);

		elementNext=driver.findElement(By.xpath("/html/body/div[1]/center/table/tbody/tr/td/div/button[1]"));
		elementNext.click();
		Thread.sleep(1000);
		        
		
		
		Thread.sleep(1000);
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				boolean loadcomplete = d.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed();
				return loadcomplete;
			}
		});
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
		Thread.sleep(1000);
        
		driver.findElement(By.xpath("/html/body/div[1]/center/table/tbody/tr/td/div/button[3]")).click();
		Thread.sleep(1000);
		
		
		
		
		
	        
      
 
	}
    
    
}