package firefox;
 


import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

 
public class TestYHJYForFireFox_06EmpHard  {
    public static void main(String[] args) throws Exception {

    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
    	empHard(webDriverFF.getWebDriver());
        
//        //关闭浏览器
//        Thread.sleep(3000);
//        driver.quit();
    }

	public static void empHard(WebDriver driver) throws IOException, InterruptedException, Exception {
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
			Thread.sleep(2000);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			if (driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed()) {
				driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
			}
			Thread.sleep(2000);
			
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
			Thread.sleep(2000);
			elementNext = driver.findElement(By.id("saveBtn"));
			elementNext.click();
			Thread.sleep(2000);
			
			try {
				elementNext =driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163"));
				if (driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed()) {
					driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
				}
			} catch (Exception e) {
//				e.printStackTrace();
			}
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
        

	}
    
    
}