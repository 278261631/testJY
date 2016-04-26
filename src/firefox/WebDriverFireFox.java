package firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFireFox {

	//for test
	public static void main(String[] args) {
		

	}
	private static WebDriverFireFox firefoxDriver=null;
	private static WebDriver driver = null;
	private WebDriverFireFox(){
	      
	        
	}

	public static WebDriverFireFox getInstance(){
		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");
		if (firefoxDriver==null) {
			firefoxDriver= new WebDriverFireFox();
			driver = new FirefoxDriver();
		}
		return firefoxDriver;
	}
	
	public  WebDriver getWebDriver(){
//		WebDriverFireFox.getInstance();
		return driver;
	}
	
	public void login(String loginUser,String loginPass){
    	
		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
        // 获取 网页的 title
        System.out.println("Page title is: " + driver.getTitle());
 
        // 通过 id 找到 input 的 DOM
        WebElement elementUserName = driver.findElement(By.id("user_name"));
        WebElement elementPwd = driver.findElement(By.id("user_pwd"));
//        WebElement elementSub = driver.findElement(By.linkText("登录"));
        WebElement elementSub = driver.findElement(By.id("login_button"));
        
        
        elementUserName.sendKeys(loginUser);
        elementPwd.sendKeys(loginPass);
        elementSub.click();
        
        //等待统计图加载完
        try {
			Thread.sleep(5000);//去掉首页的图表后就不用17秒那么久了
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
