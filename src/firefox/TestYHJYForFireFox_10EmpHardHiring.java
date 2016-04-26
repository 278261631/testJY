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
public class TestYHJYForFireFox_10EmpHardHiring  {
    public static void main(String[] args) throws Exception {
    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
    	String companyName = "公岗测试流程公司";
    	empHardHiring(webDriverFF.getWebDriver(),companyName);
    }

    /**
     * 公岗录用
     * @param driver
     * @throws IOException
     * @throws InterruptedException
     */
	public static void empHardHiring(WebDriver driver,String companyName) throws IOException, InterruptedException {
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
        		WebElement elm=d.findElement(By.id("117528"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("117528"));
        elementNext.click();
        //等待menu加载完毕三级菜单 
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("122642"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        Thread.sleep(1000);
        elementNext=driver.findElement(By.id("122642"));
        elementNext.click();
        //等待menu加载完毕四级菜单 
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("121767"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        Thread.sleep(1000);
        
        elementNext=driver.findElement(By.id("121767"));
        elementNext.click();
        Thread.sleep(2000);
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_121767").findElement(By.id("aac003")).isDisplayed();
        		return loadcomplete;
        	}
        });
        
        for (int i = 0; i < jsonarr.length(); i++) {
	        elementNext=driver.findElement(By.id("aac003"));
	        elementNext.sendKeys(jsonarr.getJSONObject(i).get("name").toString());;
	        Thread.sleep(1000);
	        elementNext.sendKeys(Keys.ARROW_DOWN);;
	        Thread.sleep(1000);
	        elementNext.sendKeys(Keys.ENTER);;
	        Thread.sleep(1000);

	        
	        
	        elementNext=driver.findElement(By.id("aab004"));
	        elementNext.click();
	        Thread.sleep(1000);
	        
			elementNext.sendKeys(companyName); 
	        Thread.sleep(1000);
	        elementNext=driver.findElement(By.id("ycb192"));
	        elementNext.click();
	        Thread.sleep(1000);
//	        driver.findElement(By.xpath("//div[@id='personJobRecommendGrid1']/div[4]/div/div[3]/div[3]")).click();
	        driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
	        Thread.sleep(1000);
	        elementNext=driver.findElement(By.id("aae031"));
	        elementNext.sendKeys("20160212");;
	        Thread.sleep(1000);
	        elementNext=driver.findElement(By.id("aae032"));
	        elementNext.sendKeys("20190412");;
	        Thread.sleep(1000);
	        //这里是否需要加截至日期？？？？？？？？
	        elementNext=driver.findElement(By.id("ycc115"));
	        elementNext.click();;
	        Thread.sleep(1000);
	        
	        

	        
	        Thread.sleep(1000);
	        elementNext=driver.findElement(By.id("saveBtn"));
	        elementNext.click();
	        Thread.sleep(4000);
	        
			try {
				elementNext =driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163"));
				if (driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed()) {
					driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
				}
			} catch (Exception e) {
			}
	        
	        
			Thread.sleep(5500);
			if (i < jsonarr.length()-1) {
				elementNext = driver.findElement(By.id("addBtn")); //新增下一个
				elementNext.click();
				
				Thread.sleep(2000);
//				driver.findElement(By.id("")).
		        webWaiter.until(new ExpectedCondition<Boolean>(){
		        	public Boolean apply(WebDriver d){
		        		boolean loadcomplete = d.findElement(By.id("aac003")).isDisplayed();
		        		return loadcomplete;
		        	}
		        });
			}
		}
	        
      
 
	}
    
    
}