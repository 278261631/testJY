package yangjb;

import java.io.IOException;
import java.util.List;

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


/*
 * EmploymentRegistration.java 就业登记
 */
public class EmploymentRegistration {
	public static void main(String[] args) throws InterruptedException,IOException{
		//设置浏览器路径
//		System.setProperty("webdriver.firefox.bin", "D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		// 获取配置数据  读取XX.xml 获得数据
		String xmlMessage = XmlImpl.readF1(Class.class.getClass().getResource("/").getPath().replace("%20", " ")+ "EmploymentRegistration.xml");
		JSONObject jobj = XML.toJSONObject(xmlMessage);
		JSONArray jsonarry = jobj.getJSONObject("peoples").getJSONArray("people");
		jsonarry.getJSONObject(1);
	  //System.out.println(jsonarr.getJSONObject(0).get("name"));
		System.out.println(jsonarry.getJSONObject(0).get("name")); //输出读取的内容
		//访问web
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize(); //调整窗口大小
		driver.get("http://localhost:8080/yhjy_xj/");	//连接web页面
		
		// 获得网页标题
		System.out.println("1 Page title is: " + driver.getTitle());

		// 通过 id 找到 input 的 DOM 取得页面ID 并对要控制的控件进行设置
		WebElement elementUserName = driver.findElement(By.id("user_name"));
		WebElement elementPwd = driver.findElement(By.id("user_pwd"));
		// WebElement elementSub = driver.findElement(By.linkText("登录"));
		WebElement elementSub = driver.findElement(By.id("login_button"));
		
		// 输入关键字
		elementUserName.sendKeys("mars");
		elementPwd.sendKeys("mars");
		elementSub.click();

		WebDriverWait webWaiter = new WebDriverWait(driver, 5);
		// 等待一级菜单menu加载完毕
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.findElement(By.id("111368"))
						.isDisplayed();
				return loadcomplete;
			}
		});
		WebElement elementNext = driver.findElement(By.id("111368"));
		elementNext.click();
		// 等待menu加载完毕二级菜单
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement elm = d.findElement(By.id("111369"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});
		elementNext = driver.findElement(By.id("111369"));
		elementNext.click();
		// 等待menu加载完毕三级菜单
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement elm = d.findElement(By.id("111371"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});

		elementNext = driver.findElement(By.id("111371"));
		elementNext.click();
		// 等待iframe加载完毕否则找不到元素
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.switchTo().frame("tab_b_111371")  //  <iframe id="tab_b_111371" />
						.findElement(By.id("ycc035")).isDisplayed(); // 是否加载完成
				return loadcomplete;
			}
		});
		
		for(int i=0; i<jsonarry.length(); i++){
/*			//输入姓名  Rpc
			elementNext = driver.findElement(By.id("aac003")); //姓名
			elementNext.sendKeys(jsonarry.getJSONObject(i).get("name").toString());
			//选择一个
*/	
			elementNext = driver.findElement(By.id("aac003")); //姓名
			String nameInput=jsonarry.getJSONObject(i).get("name").toString();
			if (nameInput.length()>1) { //如果字符比较多，就先输入最后一个字符之前的，最后再单独输入一个字符 防止rpc刷出错误的结果
				elementNext.sendKeys(nameInput.substring(0, nameInput.length()-1));
				Thread.sleep(2000);
				elementNext.sendKeys(nameInput.substring(nameInput.length()-1));
			}else {
				elementNext.sendKeys(nameInput);
			}
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					boolean loadcomplete = d.findElement(
							By.className("ui-multiselect-menu")).isDisplayed();
					return loadcomplete;
				}
			});
			Thread.sleep(3000);
			elementNext.sendKeys(Keys.DOWN);		//模仿键盘下键选择rpc
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.DOWN);
			elementNext.sendKeys(Keys.ENTER);
			//就业方式
			elementNext = driver.findElement(By.id("yhc407_div_arrow")); // 获得页面id
			elementNext.click();
			elementNext = driver.findElement(By.id("021"));
			elementNext.click();
			//就业日期
			elementNext = driver.findElement(By.id("ycc030"));
			elementNext.click();
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.ENTER);


/*			List<WebElement> div=driver.findElements(By.className("navImg NavImgll"));		//通过By.className("navImg NavImgll")找到tradebox-items里面的所有元素
			List<WebElement> as= div.get(0).findElements(By.tagName("a"));				//通过By.tagName("a")从div中找出第一个li 元素   （ divs.get(0).findElements(By.tagName("li")); ）
			as.get(0).click();
			elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.ENTER);
*/				
			//就业登记日期
			elementNext = driver.findElement(By.id("aae043"));
			elementNext.click();
			//elementNext = driver.switchTo().activeElement();
			elementNext.sendKeys(Keys.ENTER);


			
			//行业
			elementNext = driver.findElement(By.id("aca112"));
			elementNext.click();
			//等待页面加载并跳转到该页面选择
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					d.switchTo().defaultContent();		//默认页面
					d.switchTo().frame("tab_b_111371");	//选择行业页面
					/*d.switchTo().frame(0);*/
					WebElement areaLink = d.findElement(By.id("_tradeBox_A01"));	//获取元素
					boolean loadcomplete = areaLink.isDisplayed();
					return loadcomplete;
				}
			});
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_111371");
			WebElement areaLink = driver.findElement(By.id("_tradeBox_A02"));
			
			List<WebElement> divs=driver.findElements(By.className("tradebox-items"));		//通过By.className("tradebox-items")找到tradebox-items里面的所有元素
			List<WebElement> lis= divs.get(0).findElements(By.tagName("li"));				//通过By.tagName("li")从div中找出第一个li 元素   （ divs.get(0).findElements(By.tagName("li")); ）
			lis.get(0).click();
					
//			areaLink.click();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_111371");
			
			elementNext = driver.findElement(By.className("tradeBox-btn-ok"));
			elementNext.click();
			
			
			// 保存
			elementNext = driver.findElement(By.id("saveBtn"));
			elementNext.click();
			// //等待提示框
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement areaLink = d.findElement(By
							.cssSelector("div.dialog-button > button.sexybutton_163"));
					boolean loadcomplete = areaLink.isDisplayed();
					return loadcomplete;
				}
			});

			Thread.sleep(3500);
			driver.findElement(
					By.cssSelector("div.dialog-button > button.sexybutton_163"))
					.click();

			Thread.sleep(3500);
			if (i < jsonarry.length() - 1) {
				elementNext = driver.findElement(By.id("addBtn")); // 新增下一个
				elementNext.click();

				Thread.sleep(2000);
				webWaiter.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						boolean loadcomplete = d.findElement(By.id("aab004"))
								.isDisplayed();
						return loadcomplete;
					}
				});
			}
			
		}

		//关闭浏览器
        Thread.sleep(5000);
//        driver.quit();
        System.out.println("ended");
		
	}

}