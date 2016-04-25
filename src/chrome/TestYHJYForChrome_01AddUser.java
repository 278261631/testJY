package chrome;
 

import java.io.File;
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
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

import com.google.gson.JsonArray;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
 
public class TestYHJYForChrome_01AddUser  {
    public static void main(String[] args) throws InterruptedException, IOException {
        // 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
//      System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
        // 创建一个 FireFox 的浏览器实例
        //获取配置数据
    	String xmlString=XmlImpl.
    			readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+"SeleniumTestData.xml");
//    			readF1("C:\\Workspaces\\MyEclipse 10_debug\\testJY\\src\\SeleniumTestData.xml");
    	JSONObject jobj= XML.toJSONObject(xmlString);
    	JSONArray jsonarr=jobj.getJSONObject("peoples").getJSONArray("people");
    	jsonarr.getJSONObject(1);
    	System.out.println(jsonarr.getJSONObject(0).get("name"));
        // 访问 


    	System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
    	System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\new_chrome.exe");
        // 创建一个 FireFox 的浏览器实例
//        WebDriver driver = new ChromeDriver();
 
        // 创建一个 ChromeDriver 的接口，用于连接 Chrome
        @SuppressWarnings("deprecation")
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("D:\\chromedriver.exe"))
//                .usingChromeDriverExecutable(new File("D:\\chromedriver.exe"))
                .usingAnyFreePort().build();
        service.start();
        // 创建一个 Chrome 的浏览器实例
        WebDriver driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
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
        
 
        // 提交 input 所在的  form
//        element.submit();
//        WebElement submitBtn=driver.findElement(By.id("su"));
//        submitBtn.click();
         
//        // 通过判断 title 内容等待搜索页面加载完毕，间隔10秒
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
////                return d.getTitle().toLowerCase().endsWith("ztree");
//                return d.getTitle().toLowerCase().startsWith("ztree");
//            }
//        });
        
        
//        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
//        WebElement controlPanelFrame = null;
//        
//        for (WebElement frameItem : frames) {
//        	//src="../../../common/xzqh/xzqhAction.do?keyName=aac010&valueName=aab301&keyName_1=aac010_1&_r=0.4598737241703953"
//        	if (frameItem.getAttribute("src").startsWith("../../../common/xzqh/xzqhAction.do?keyName=aac010&valueName=aab301&keyName_1"))
//            {
//                controlPanelFrame = frameItem;
//                System.out.println(controlPanelFrame);
//                break;
//            }
//		}
 
        // 显示搜索结果页面的 title
        System.out.println("2 Page title is: " + driver.getTitle());
        
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
        elementNext=driver.findElement(By.id("aac003")); //姓名
//        elementNext.sendKeys("张三_残疾001");
        elementNext.sendKeys(jsonarr.getJSONObject(0).get("name").toString());
        
        //证件类型
        elementNext=driver.findElement(By.id("aac058_desc")); 
        elementNext.click();
        

        
        elementNext=driver.findElement(By.id("1")); 
        elementNext.click();

        //身份证号码
        elementNext=driver.findElement(By.id("yac002")); 
//        elementNext.sendKeys("65010219840602755x");
        elementNext.sendKeys(jsonarr.getJSONObject(0).get("idCardNum").toString());

        elementNext.sendKeys(Keys.TAB);
//        elementNext=driver.findElement(By.)
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.ENTER);
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);
        //户口性质
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);
        //
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        //文化程度
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);
        
        //政治面貌
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);
        
        //户口登记日期
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys(Keys.ENTER);
        
        //户口行政区划
        elementNext=driver.findElement(By.className("popWin_163"));
        elementNext.click();
        //等待行政区划加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
//        		boolean loadcomplete = d.switchTo().frame("tab_b_110390").findElement(By.id("650121")).isDisplayed();
//        		boolean loadcomplete = d.switchTo().frame("tab_b_110390").switchTo().frame(0).findElement(By.id("650121")).isDisplayed();
//        		System.out.println();
        		//WebElement frame=d.findElement(By.xpath(""));
//        		boolean loadcomplete = d.switchTo().frame(1).findElement(By.id("650121")).isDisplayed();
//        		boolean loadcomplete = d.findElement(By.xpath("//*[@id='650121']")).isDisplayed();
//        		d.switchTo().defaultContent(); 
//        		boolean testBoolean = d.findElement(By.xpath("/html/body/div[4]/table/tbody/tr[4]/td[2]/a")).isDisplayed();
//        		System.out.println(testBoolean);
        		
//        		boolean loadcomplete = d.findElement(By.id("650121")).isDisplayed();
//        		System.out.println(loadcomplete);
        		
        		
        		

        		            // all frames
				d.switchTo().defaultContent();
//				List<WebElement> frameList=d.switchTo().frame("tab_b_110390").findElements(By.tagName("iframe"));//.switchTo().frame(d.findElement(By.xpath("/html/body/div[2]/div[2]/iframe"))).findElement(By.id("650121")).isDisplayed();
//				System.out.println(frameList.size());
				d.switchTo().defaultContent();
//				WebElement areaLink=d.switchTo().frame(frameList.get(0)).findElement(By.id("650121"));
				d.switchTo().frame("tab_b_110390");
//				System.out.println(d.getPageSource());
				d.switchTo().frame(0);
				WebElement areaLink=d.findElement(By.id("650121"));
//				System.out.println(areaLink.isDisplayed());
				boolean loadcomplete=areaLink.isDisplayed();
        		
        		return loadcomplete;
        	}
        });
		driver.switchTo().defaultContent();
		driver.switchTo().frame("tab_b_110390");
		driver.switchTo().frame(0);
		WebElement areaLink=driver.findElement(By.id("650121"));
        areaLink.click();
//        elementNext=driver.findElement(By.id("650121"));
//        elementNext.click();
        
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("tab_b_110390");
        //联系电话
        elementNext=driver.findElement(By.id("aae005"));
        elementNext.sendKeys("13800000000");
        
        elementNext=driver.findElement(By.id("aae006"));
        elementNext.sendKeys("我也不知道住哪里好");
        
        elementNext=driver.findElement(By.id("aae006"));
        elementNext.sendKeys("我也不知道住哪里好");
        
        elementNext=driver.findElement(By.id("yau002"));
        elementNext.sendKeys("野鸡大学");
        
        elementNext=driver.findElement(By.id("saveBtn"));
        elementNext.click();
        
        
//        driver.
        
        
        //elementNext=driver.findElement(By.id("addBtn")); //新增下一个
        //elementNext.click();
        
        
        
        
        
        //关闭浏览器
        Thread.sleep(30000);
        driver.quit();
    }
    
    
}