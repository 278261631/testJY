package mayong.tasks;

import mayong.config.TestConfiger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestTasks {

	public void   doLogin(WebDriver driver,String userName,String userPass ){
        // 通过 id 找到 input 的 DOM
        WebElement elementUserName = driver.findElement(By.id("user_name"));
        WebElement elementPwd = driver.findElement(By.id("user_pwd"));
//        WebElement elementSub = driver.findElement(By.linkText("登录"));
        WebElement elementSub = driver.findElement(By.id("login_button"));
        
        
        // 输入关键字
        elementUserName.sendKeys(userName);
        elementPwd.sendKeys(userPass);
        elementSub.click();
        
        WebDriverWait webWaiter=new WebDriverWait(driver, 15);
        //等待一级菜单menu加载完毕
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		boolean loadcomplete = d.findElement(By.id("110385")).isDisplayed();
        		return loadcomplete;
        	}
        });
	}
	
	/***
	 * 切换菜单
	 */
	public void doSwitchToTopMenu(String lvl_1_id,WebDriver driver){
        WebElement elementNext = driver.findElement(By.id("110385"));
        elementNext.click();
	}
	public void doSwitchToSubMenu(final String lvl_2_id,WebDriver driver){
		 //等待menu加载完毕二级菜单
		WebDriverWait webWaiter=new WebDriverWait(driver, 15);
        webWaiter.until(new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver d){
        		WebElement elm=d.findElement(By.id(lvl_2_id));
        		boolean loadcomplete = elm.isDisplayed();
        		return loadcomplete;
        	}
        });
        
        WebElement elementNext=driver.findElement(By.id(lvl_2_id));
        elementNext.click();
	}
	public void doSwitchToTab(final String lvl_3_id,final String testElementID,WebDriver driver){
			WebDriverWait webWaiter=new WebDriverWait(driver, 15);   
			webWaiter.until(new ExpectedCondition<Boolean>(){
	        	public Boolean apply(WebDriver d){
	        		WebElement elm=d.findElement(By.id(lvl_3_id));
	        		boolean loadcomplete = elm.isDisplayed();
	        		return loadcomplete;
	        	}
	        });
	        
			WebElement elementNext=driver.findElement(By.id(lvl_3_id));
			elementNext.click();
	        //等待iframe加载完毕
	        webWaiter.until(new ExpectedCondition<Boolean>(){
	        	public Boolean apply(WebDriver d){
	        		boolean loadcomplete = d.switchTo().frame("tab_b_"+lvl_3_id).findElement(By.id(testElementID)).isDisplayed();
	        		return loadcomplete;
	        	}
	        });
	}
	
	public void doFillNewPersonFormOneByOne(WebDriver driver ,TestConfiger testConfiger) throws InterruptedException{
		
		  for (int i = 0; i < testConfiger.getJsonarry().length(); i++) {
			  JSONObject jsonObject=testConfiger.getJsonarry().getJSONObject(i);
			WebElement elementNext = driver.findElement(By.id("aac003")); //姓名
			//		elementNext.sendKeys("张三_残疾001");
			elementNext.sendKeys(jsonObject.get("name").toString());
			//证件类型
			elementNext = driver.findElement(By.id("aac058_desc"));
			elementNext.click();
			elementNext = driver.findElement(By.id("1"));
			elementNext.click();
			//身份证号码
			elementNext = driver.findElement(By.id("yac002"));
			elementNext.sendKeys(jsonObject.get("idCardNum").toString());
			elementNext.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebDriverWait webWaiter = new WebDriverWait(driver, 15);
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement areaLink = d.findElement(By.id("myPhoto"));
					boolean loadcomplete = areaLink.isEnabled();
					return loadcomplete;
				}
			});
			//		elementNext.sendKeys(CreateIDCardNo.getRandomID());
			//        elementNext=driver.findElement(By.)
//			elementNext = driver.switchTo().activeElement();
//			elementNext.sendKeys(Keys.TAB);
//			elementNext = driver.switchTo().activeElement();
//			elementNext.sendKeys(Keys.ENTER);
//			Thread.sleep(1000);
//			elementNext.sendKeys(Keys.TAB);
//			elementNext = driver.switchTo().activeElement();
//			elementNext.sendKeys(Keys.TAB);
//			elementNext = driver.switchTo().activeElement();
			//民族
			elementNext = driver.findElement(By.id("aac005_desc"));
			elementNext.click();
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			//户口性质
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			//文化程度
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.DOWN);
			Thread.sleep(1000);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//政治面貌
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//户口登记日期
			elementNext.sendKeys(Keys.TAB);
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.ENTER);
			//户口行政区划
			elementNext = driver.findElement(By.className("popWin_163"));
			elementNext.click();
			//等待行政区划加载完毕
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					d.switchTo().defaultContent();
					d.switchTo().frame("tab_b_110390");
					d.switchTo().frame(0);
					WebElement areaLink = d.findElement(By.id("650121"));
					boolean loadcomplete = areaLink.isDisplayed();
					return loadcomplete;
				}
			});
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_110390");
			driver.switchTo().frame(0);
			WebElement areaLink = driver.findElement(By.id("650121"));
			areaLink.click();
				Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_110390");
			//联系电话
			elementNext = driver.findElement(By.id("aae005"));
			elementNext.sendKeys("13800000000");
			elementNext = driver.findElement(By.id("aae006"));
			elementNext.sendKeys("我也不知道住哪里好");
			elementNext = driver.findElement(By.id("aae006"));
			elementNext.sendKeys("我也不知道住哪里好");
			elementNext = driver.findElement(By.id("yau002"));
			elementNext.sendKeys("新疆大学");
				Thread.sleep(2000);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			elementNext = driver.findElement(By.id("saveBtn"));
			//Thread.sleep(2000);
			elementNext.click();
			//等待提示框
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement areaLink = d.findElement(By
							.cssSelector("div.dialog-button > button.sexybutton_163"));
					boolean loadcomplete = areaLink.isDisplayed();
					return loadcomplete;
				}
			});
			//		driver.switchTo().defaultContent();
				Thread.sleep(3500);
			driver.findElement(
					By.cssSelector("div.dialog-button > button.sexybutton_163"))
					.click();
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i < testConfiger.getJsonarry().length() - 1) {
				elementNext = driver.findElement(By.id("addBtn")); //新增下一个
				elementNext.click();

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//			driver.findElement(By.id("")).
				webWaiter.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						boolean loadcomplete = d.findElement(By.id("myPhoto"))
								.isDisplayed();
						return loadcomplete;
					}
				});
			}
		}
		
	}
	
	//click addNext when add new person  ,Sorry,My IME Error ,Could not use Chinese.	
	public void doClickNextButton(WebDriver driver ){
		WebElement elementNext = driver.findElement(By.id("addBtn")); //新增下一个
		elementNext.click();
	}
	
}
