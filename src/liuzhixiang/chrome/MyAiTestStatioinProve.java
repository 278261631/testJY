package liuzhixiang.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAiTestStatioinProve {

	public static void main(String[] args) throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver",
//				"D:\\exploredriver\\chromedriver.exe");
//		System.setProperty("webdriver.ie.bin",
//				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
	   	System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
    	System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\new_chrome.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/yhjy_xj");
		// 输入用户名
		driver.findElement(By.id("user_name")).sendKeys("liuzx");
		// 输入密码
		driver.findElement(By.id("user_pwd")).sendKeys("1");
		// 点击登陆
		driver.findElement(By.id("login_button")).click();
		// 等待一级菜单加载完
		WebDriverWait waiter = new WebDriverWait(driver, 15);
		waiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean isload = d.findElement(By.id("114542")).isDisplayed();
				return isload;
			}
		});
		driver.findElement(By.id("114542")).click();
		// 等待二级菜单加载完毕
		waiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean isload = d.findElement(By.id("117528")).isDisplayed();
				return isload;
			}
		});
		driver.findElement(By.id("117528")).click();
		// 等待三级菜单加载完毕
		waiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean isload = d.findElement(By.id("122641")).isDisplayed();
				return isload;
			}
		});
		driver.findElement(By.id("122641")).click();
		// 等待四级菜单加载完毕
		waiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean isload = d.findElement(By.id("132208")).isDisplayed();
				return isload;
			}
		});
		driver.findElement(By.id("132208")).click();
		// 等待审核页面加载完毕后跳入该ifram页面
		waiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean isload = d.findElement(By.id("tab_b_132208"))
						.isDisplayed();
				return isload;
			}
		});
		driver.switchTo().frame(driver.findElement(By.id("tab_b_132208")));
		// 输入单位名称
		driver.findElement(By.id("aab004")).sendKeys("志祥公司");
		// 查询
		driver.findElement(By.id("quryBtn")).click();
		Thread.sleep(2000);
		// 全选
		driver.findElement(By.cssSelector(".slick-checkbox-header")).click();
		// 审核通过
		driver.findElement(By.id("approveBtn")).click();
		// 调回父页面
		driver.switchTo().defaultContent();
		// 点击复审
		driver.findElement(By.id("132209")).click();
		// 进入复审iframe
		waiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean isload = d.findElement(By.id("tab_b_132209")).isDisplayed();
				return isload;
			}
		});
		driver.switchTo().frame("tab_b_132209");
		//输入公司名称
		driver.findElement(By.id("aab004")).sendKeys("志祥公司");
		//点击查询
		driver.findElement(By.id("quryBtn")).click();
		Thread.sleep(4000);
		//选择要审核的数据
		driver.findElement(By.cssSelector(".slick-checkbox-header")).click(); 
		//点击审核通过
		driver.findElement(By.id("approveBtn")).click();
		//关闭浏览器
		Thread.sleep(2000);
		driver.quit();
	}
}
