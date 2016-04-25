package firefox;
 


import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

 /**
  * 公岗录用
  * @author Administrator
  *
  */
public class TestYHJYForFireFox_11EmpHardMoney  {
    public static void main(String[] args) throws Exception {
    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
    	WebDriver driver=webDriverFF.getWebDriver();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
//    	empHardMoneySearch(webDriverFF.getWebDriver());
//    	empHardMoneySearchApproved(driver);
    	String companyName = "公岗测试流程公司";
    	empHardMoneySearchGiven(driver );
    }

    /**
     * 
     * @param driver
     * @throws IOException
     * @throws InterruptedException
     */
	public static void empHardMoneySearch(WebDriver driver,String companyName) throws IOException, InterruptedException {
		System.out.println("从此以后的操作 查询都有可能有延迟。。。不知道为什么");
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
        		boolean loadcomplete = d.findElement(By.id("113270")).isDisplayed();
        		return loadcomplete;
        	}
        });
        elementNext = driver.findElement(By.id("113270"));
        elementNext.click();
 //等待menu加载完毕二级菜单
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("115077"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("115077"));
        elementNext.click();
        //等待menu加载完毕三级菜单 
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id("128095"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        Thread.sleep(1000);
        elementNext=driver.findElement(By.id("128095"));
        elementNext.click();
        Thread.sleep(2000);
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_128095").findElement(By.id("startDate")).isDisplayed();
        		return loadcomplete;
        	}
        });
        
        

        
        for (int i = 0; i < jsonarr.length(); ) {
        	driver.switchTo().defaultContent();
        	driver.switchTo().frame("tab_b_128095");//切换到菜单相关窗口
        	
	        elementNext=driver.findElement(By.id("startDate"));
	        elementNext.clear();
	        elementNext.sendKeys("201604");
	        Thread.sleep(1000);
	        elementNext=driver.findElement(By.id("endDate"));
	        elementNext.clear();
	        elementNext.sendKeys("201606");
	        Thread.sleep(1000);
        
	        
	        
	        elementNext=driver.findElement(By.id("aab004"));
	        elementNext.click();
	        Thread.sleep(1000);
	        elementNext.sendKeys(companyName); 
	        Thread.sleep(1000);
	        
	        
	        
	        Thread.sleep(1000);
	        elementNext=driver.findElement(By.id("queryBtn"));
	        elementNext.click();
	        
	        Thread.sleep(3000);
	        webWaiter.until(new ExpectedCondition<Boolean>(){
	        	public Boolean apply(WebDriver d){
	        		boolean loadcomplete = d.findElement(By.cssSelector("input[type=\"radio\"]")).isDisplayed();
	        		return loadcomplete;
	        	}
	        });
	        
	        Thread.sleep(1000);
//	        driver.findElement(By.xpath("//input[@type='radio'])[2]")).click();
	        driver.findElement(By.cssSelector("input[type=\"radio\"]")).click();

	        
	        Thread.sleep(1000);
	        elementNext=driver.findElement(By.id("batchBtn"));
	        elementNext.click();
	        
			try {
				elementNext =driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163"));
				if (driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed()) {
					driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
				}
			} catch (Exception e) {
			}
	        	        
			
			Thread.sleep(5000);//等待打开申请详细页面
			String windowHandle=driver.getWindowHandle();//存储当前window的handle
			System.out.println(windowHandle);
			System.out.println(driver.getWindowHandles().size());
			driver.switchTo().defaultContent();
			webWaiter.until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d){
//					d.switchTo().window(d.getWindowHandle()); //切换回顶级窗体，因为这个弹出窗口是属于顶级的 
					//事实证明 这个顶级窗口独自存在  不依托于原始 defaultContent
					d.switchTo().frame(d.findElements(By.tagName("iframe")).size()-1);
//					System.out.println(d.getPageSource());
//					System.out.println(d.getWindowHandles().size()+"\t"+driver.getWindowHandles().size());
					boolean loadcomplete = d.findElement(By.id("yae107_desc")).isDisplayed();
//					System.out.println(d.get);
					return loadcomplete;
				}
			});
			
			Thread.sleep(1000);
			for (String stringHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(stringHandle)) {
					driver.switchTo().window(stringHandle);
					System.out.println(">>>>>"+stringHandle);
				}
			}
			Thread.sleep(1000);
			
			driver.findElement(By.cssSelector("input.slick-checkbox-header")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("saveBtn")).click();
			Thread.sleep(1000);
			webWaiter.until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d){
					boolean loadcomplete = d.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed();
					return loadcomplete;
				}
			});
			driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("closeBtn")).click();
			Thread.sleep(1000);
			
			driver.switchTo().window(windowHandle);//切换回正常window
			
			
			
			
			break; //我就只提交一次了
			

		}
	        
      
 
	}
	/**
	 * 已经审核通过的
	 * @param driver
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void empHardMoneySearchApproved(WebDriver driver) throws IOException, InterruptedException {
		System.out.println("不知道为什么会有延迟 要等好久才出现  难道是因为table是属于YHJY 而我用的sunbin登录的吗？");
		//获取配置数据
//		String xmlString=XmlImpl.
//				readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml");
//		JSONObject jobj= XML.toJSONObject(xmlString).getJSONObject("peoples");
//		JSONArray jsonarr=jobj.getJSONArray("people");
		
		
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
				boolean loadcomplete = d.findElement(By.id("113270")).isDisplayed();
				return loadcomplete;
			}
		});
		elementNext = driver.findElement(By.id("113270"));
		elementNext.click();
		//等待menu加载完毕二级菜单
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				WebElement elm=d.findElement(By.id("115077"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});
		
		elementNext=driver.findElement(By.id("115077"));
		elementNext.click();
		//等待menu加载完毕三级菜单 
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				WebElement elm=d.findElement(By.id("128095"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});
		
		Thread.sleep(1000);
		elementNext=driver.findElement(By.id("128095"));
		elementNext.click();
		Thread.sleep(2000);
		//等待iframe加载完毕
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				boolean loadcomplete = d.switchTo().frame("tab_b_128095").findElement(By.id("startDate")).isDisplayed();
				return loadcomplete;
			}
		});
		
		
		
		
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_128095");//切换到菜单相关窗口
			
			elementNext=driver.findElement(By.xpath("/html/body/form/div[2]/div[1]/div[3]/ul/li[2]/a"));
			elementNext.click();
			Thread.sleep(1000);
			
	        elementNext=driver.findElement(By.id("startDate"));
	        elementNext.clear();
	        elementNext.sendKeys("201601");
	        Thread.sleep(1000);
	        
			elementNext=driver.findElement(By.id("endDate"));
			elementNext.clear();
			elementNext.sendKeys("201607");
			Thread.sleep(1000);
			
//			elementNext=driver.findElement(By.id("aab004"));
//			elementNext.click();
//			Thread.sleep(1000);
//			elementNext.sendKeys("测试"); 
			Thread.sleep(1000);
			
			
			
			Thread.sleep(1000);
			elementNext=driver.findElement(By.id("queryBtn"));
			elementNext.click();
			
			Thread.sleep(2000);
			webWaiter.until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d){
					boolean loadcomplete = d.findElement(By.xpath("/html/body/form/div[2]/div[2]/div[2]/div/div[4]/div/div/div[2]")).isDisplayed();
					return loadcomplete;
				}
			});
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/form/div[2]/div[2]/div[2]/div/div[4]/div/div/div[2]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("issueBtn")).click(); //发放
			Thread.sleep(1000);
			
	
			webWaiter.until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d){
					boolean loadcomplete = d.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed();
					return loadcomplete;
				}
			});
			driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
			Thread.sleep(1000);
			
		
		
	}
	/**
	 * 已经发放的
	 * @param driver
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void empHardMoneySearchGiven(WebDriver driver) throws IOException, InterruptedException {
		
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
				boolean loadcomplete = d.findElement(By.id("113270")).isDisplayed();
				return loadcomplete;
			}
		});
		elementNext = driver.findElement(By.id("113270"));
		elementNext.click();
		//等待menu加载完毕二级菜单
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				WebElement elm=d.findElement(By.id("115077"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});
		
		elementNext=driver.findElement(By.id("115077"));
		elementNext.click();
		//等待menu加载完毕三级菜单 
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				WebElement elm=d.findElement(By.id("128095"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});
		
		Thread.sleep(1000);
		elementNext=driver.findElement(By.id("128095"));
		elementNext.click();
		Thread.sleep(2000);
		//等待iframe加载完毕
		webWaiter.until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				boolean loadcomplete = d.switchTo().frame("tab_b_128095").findElement(By.id("startDate")).isDisplayed();
				return loadcomplete;
			}
		});
		
		
		
		
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_128095");//切换到菜单相关窗口
			
			elementNext=driver.findElement(By.xpath("/html/body/form/div[2]/div[1]/div[3]/ul/li[3]/a"));
			elementNext.click();
			Thread.sleep(1000);
			
	        elementNext=driver.findElement(By.id("startDate"));
	        elementNext.clear();
	        elementNext.sendKeys("201601");
	        Thread.sleep(1000);
	        
			elementNext=driver.findElement(By.id("endDate"));
			elementNext.clear();
			elementNext.sendKeys("201607");
			Thread.sleep(1000);
			
//			elementNext=driver.findElement(By.id("aab004"));
//			elementNext.click();
//			Thread.sleep(1000);
//			elementNext.sendKeys("测试"); 
			Thread.sleep(1000);
			
			
			
			Thread.sleep(1000);
			elementNext=driver.findElement(By.id("queryBtn"));
			elementNext.click();
			
			Thread.sleep(2000);
			webWaiter.until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d){
					boolean loadcomplete = d.findElement(By.xpath("/html/body/form/div[2]/div[2]/div[3]/div/div[4]/div/div[1]")).isDisplayed();
					return loadcomplete;
				}
			});
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/form/div[2]/div[2]/div[3]/div/div[4]/div/div[1]")).click();
			Actions doubleClickAction=new Actions(driver);
//			doubleClickAction.doubleClick(driver.findElement(By.xpath("/html/body/form/div[2]/div[2]/div[3]/div/div[4]/div/div[1]")));
			doubleClickAction.doubleClick().perform();;
			//双击操作。。。。。
			Thread.sleep(1000);
//			doubleClickAction.contextClick().perform();;
//			driver.findElement(By.id("issueBtn")).click(); //发放
			Thread.sleep(1000);
			
	
//			webWaiter.until(new ExpectedCondition<Boolean>(){
//				public Boolean apply(WebDriver d){
//					boolean loadcomplete = d.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).isDisplayed();
//					return loadcomplete;
//				}
//			});
//			driver.findElement(By.cssSelector("div.dialog-button > button.sexybutton_163")).click();
//			Thread.sleep(1000);
			
		
		
		
	}
    
    
}