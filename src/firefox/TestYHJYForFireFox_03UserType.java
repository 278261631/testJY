package firefox;
 


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

 
/**
 * 人员类别认定
 * @author 0_0
 *
 */
public class TestYHJYForFireFox_03UserType  {
    public static void main(String[] args) throws InterruptedException {
    	WebDriverFireFox webDriverFF=WebDriverFireFox.getInstance();
//    	webDriverFF.login("developer", "superxjjy903");
    	webDriverFF.login("mayong", "mayong");
        confirmUserType(webDriverFF.getWebDriver());
    }

    
    
    
	public static void confirmUserType(WebDriver driver) throws InterruptedException {
	
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
        		WebElement elm=d.findElement(By.id("138509"));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        elementNext=driver.findElement(By.id("138509"));
        elementNext.click();
        
        Thread.sleep(2000);
        //等待iframe加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.switchTo().frame("tab_b_138509").findElement(By.id("queryBtn")).isDisplayed();
        		return loadcomplete;
        	}
        });
        Thread.sleep(1000);
        elementNext=driver.findElement(By.id("queryBtn")); //查询按钮
        elementNext.click();
        Thread.sleep(1000);
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.className("slick-checkbox-header")).isEnabled();
        		return loadcomplete;
        	}
        });

        Thread.sleep(1000);
        //全选
        elementNext=driver.findElement(By.className("slick-checkbox-header")); 
        elementNext.click();
        
        Thread.sleep(1000);
        //审核通过

        elementNext=driver.findElement(By.id("examOkBtn")); 
        elementNext.click();
        


        
        //elementNext=driver.findElement(By.id("addBtn")); //新增下一个
        //elementNext.click();
        
        
        
        
        
//        //关闭浏览器
//        Thread.sleep(3000);
//        driver.quit();
	}
    
    
}