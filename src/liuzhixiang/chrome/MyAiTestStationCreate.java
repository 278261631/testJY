package liuzhixiang.chrome;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAiTestStationCreate {
	public static void main(String[] args) throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver",
//				"D:\\exploredriver\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.bin",
//				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
	   	System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
    	System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\new_chrome.exe");

		WebDriver driver = new ChromeDriver();
		// 浏览器访问就业项目
		driver.get("http://localhost:8080/yhjy_xj");
		// 通过id获取输入框
		WebElement username = driver.findElement(By.id("user_name"));
		// 输入值
		username.sendKeys("liuzx");
		WebElement pwd = driver.findElement(By.id("user_pwd"));
		pwd.sendKeys("1");
		// 点击登陆按钮
		WebElement login = driver.findElement(By.id("login_button"));
		login.click();
		// 等待一级菜单加载完毕
		WebDriverWait webWaiter = new WebDriverWait(driver, 15);
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.findElement(By.id("114542"))
						.isDisplayed();
				return loadcomplete;
			}
		});
		// 点击就业援助功能
		WebElement personInfoMana = driver.findElement(By.id("114542"));
		// 点击
		personInfoMana.click();
		// 等待二级菜单加载完毕
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.findElement(By.id("117528"))
						.isDisplayed();
				return loadcomplete;
			}
		});
		// 点击公益性岗位及人员管理
		WebElement psMana = driver.findElement(By.id("117528"));
		// 点击
		psMana.click();
		// 等待三级菜单加载完毕
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.findElement(By.id("122641"))
						.isDisplayed();
				return loadcomplete;
			}
		});
		// 点击公益性岗位单位管理
		WebElement unitStationCreate = driver.findElement(By.id("122641"));
		unitStationCreate.click();
		// 等待四级菜单加载完毕
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.findElement(By.id("117529"))
						.isDisplayed();
				return loadcomplete;
			}
		});
		// 点击单位公益性岗位开发
		WebElement createStation = driver.findElement(By.id("117529"));
		createStation.click();
		// 跳入iframe打开的岗位开发窗口
		driver.switchTo().frame("tab_b_117529");
		// 点击新增按钮
		WebElement newStation = driver.findElement(By.id("addBtn"));
		newStation.click();
			 //输入用人单位
	        WebElement unit = driver.findElement(By.id("aab004"));
	        unit.sendKeys("志祥公司");
	        //等待rpc加载完毕
	        webWaiter.until(new ExpectedCondition<Boolean>(){
	        	public Boolean apply(WebDriver d){
	        		boolean loadcomplete = d.findElement(By.className("ui-multiselect-menu")).isDisplayed();
	        		return loadcomplete;
	        	}
	        });
	        Thread.sleep(5000);
	        //按下键盘下键
	        unit.sendKeys(Keys.DOWN);
	        //按下回车键
	        unit.sendKeys(Keys.ENTER); 
	        Thread.sleep(5000);
	        //点击新增岗位按钮弹出新窗口
	        WebElement addStation = driver.findElement(By.id("addPositionBtn"));
	        addStation.click();
	        driver.switchTo().defaultContent();
	        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
	        //跳转到新增岗位页面
	        driver.switchTo().frame(iframeList.get(2));
	        //填写岗位类别(选取第一个)
	        WebElement stationClass = driver.findElement(By.id("ycb197_desc"));
	        stationClass.click();
	        stationClass.sendKeys(Keys.DOWN);
	        stationClass.sendKeys(Keys.ENTER);
	        Thread.sleep(2000);
	        //选择岗位名称
	        WebElement stationName = driver.findElement(By.id("ycb192_desc"));
	        stationName.click();
	        Thread.sleep(2000);
	        stationName.sendKeys(Keys.DOWN);
	        stationName.sendKeys(Keys.ENTER);
	        //岗位性质选择
	        WebElement stationProperty = driver.findElement(By.id("ycb191_desc"));
	        stationProperty.sendKeys(Keys.DOWN);
	        stationProperty.sendKeys(Keys.ENTER);
	        //岗位数量
	        WebElement stationNum = driver.findElement(By.id("ycb193"));
	        stationNum.sendKeys("20");
	        //劳动报酬
	        WebElement stationMoney = driver.findElement(By.id("ycb194"));
	        stationMoney.sendKeys("1500");
	        //岗位提供日期
	        WebElement stationDate = driver.findElement(By.id("ycb195"));
	        stationDate.sendKeys("20150723");
	        //岗位截止日期
	        WebElement stationDeadline = driver.findElement(By.id("ycb199"));
	        stationDeadline.sendKeys("20160723");
	        //劳务派遣公司名称
	        WebElement comName = driver.findElement(By.id("eaa003"));
	        comName.sendKeys("志祥");
	        //等待rpc加载
	        Thread.sleep(2000);
	        comName.sendKeys(Keys.DOWN);
	        comName.sendKeys(Keys.ENTER);
	        //工作地点
	        WebElement stationTarget = driver.findElement(By.id("ycb198"));
	        stationTarget.sendKeys("新疆维吾尔自治区昌吉市");
	        //备注
	        WebElement tips = driver.findElement(By.id("aae013"));
	        tips.sendKeys("ajsdlfjsaodifsndksjafjoidsjoflsdknkslafj");
	        //保存
	        WebElement saveStation = driver.findElement(By.id("saveBtn"));
	        saveStation.click();
	        Thread.sleep(3000);
	        //点击弹出框的确定按钮
	    	List<WebElement> buttonList = driver.findElements(By.tagName("button"));
	    	for(int a = 0 ; a < buttonList.size();a++){
	    		if("确定".equals(buttonList.get(a).getText().toString())){
	    			buttonList.get(a).click();
	    			break;
	    		}
	    	}
	        //点击关闭按钮
	        WebElement closeStationCreate = driver.findElement(By.id("closeBtn"));
	        closeStationCreate.click();
	        //关闭浏览器
	        driver.quit();
		}
	}

