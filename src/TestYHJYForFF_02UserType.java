 

import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import org.apache.xml.utils.XMLReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
 
/**
 * 人员类别认定
 * @author 0_0
 *
 */
public class TestYHJYForFF_02UserType  {
    public static void main(String[] args) throws InterruptedException {
    	//获取数据列表
        String nameInput=XmlImpl.getStringFromXml("name").trim();
    	
    	
        // 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
//      System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
        // 创建一个 FireFox 的浏览器实例
        WebDriver driver = new FirefoxDriver();
 
        // 访问 
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
     //   driver.switchTo().frame("tab_b_138069");
        elementNext=driver.findElement(By.id("aac003")); //姓名
   
        elementNext.sendKeys("张三_残疾001");  //
        
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
        
        //选择类型
        elementNext=driver.findElement(By.id("ycc131_desc")); 
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.DOWN);
        elementNext.sendKeys(Keys.ENTER);
        
        //残疾人证编号
        elementNext.sendKeys(Keys.TAB);
        elementNext=driver.switchTo().activeElement();
        elementNext.sendKeys("10001");

        elementNext=driver.findElement(By.id("saveBtn1")); 
        elementNext.click();
        
        
        
//        driver.
        
        
        //elementNext=driver.findElement(By.id("addBtn")); //新增下一个
        //elementNext.click();
        
        
        
        
        
        //关闭浏览器
        Thread.sleep(30000);
        driver.quit();
    }
    
    
}