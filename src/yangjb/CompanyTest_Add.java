package yangjb;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.XmlImpl;

public class CompanyTest_Add {
	public static void main(String args[]) throws InterruptedException,
			IOException {
		/* 设置个浏览器路径 */
//		System.setProperty("webdriver.firefox.bin",
//				"D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		// 获取配置数据
		String xmlString = XmlImpl.readF1(Class.class.getClass()
				.getResource("/").getPath().replace("%20", " ")
				+ "CompanyTestData.xml");
		JSONObject jobj = XML.toJSONObject(xmlString);
		JSONArray jsonarr = jobj.getJSONObject("companys").getJSONArray(
				"company");
		jsonarr.getJSONObject(1);
		System.out.println(jsonarr.getJSONObject(0).get("CompanyName"));

		// 访问
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/yhjy_xj/");
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

		WebDriverWait webWaiter = new WebDriverWait(driver, 15);
		// 等待一级菜单menu加载完毕
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.findElement(By.id("110385"))
						.isDisplayed();
				return loadcomplete;
			}
		});
		WebElement elementNext = driver.findElement(By.id("110385"));
		elementNext.click();
		// 等待menu加载完毕二级菜单
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement elm = d.findElement(By.id("110396"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});
		elementNext = driver.findElement(By.id("110396"));
		elementNext.click();
		// 等待menu加载完毕三级菜单
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement elm = d.findElement(By.id("110397"));
				boolean loadcomplete = elm.isDisplayed();
				return loadcomplete;
			}
		});

		elementNext = driver.findElement(By.id("110397"));
		elementNext.click();
		// 等待iframe加载完毕否则找不到元素
		webWaiter.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean loadcomplete = d.switchTo().frame("tab_b_110397")  // <iframe id="tab_b_110397" />
						.findElement(By.id("aab004")).isDisplayed(); // 是否加载完成
				return loadcomplete;
			}
		});

		/*
		 * elementNext = driver.findElement(By.id("aab004")); //获得页面单位名id
		 * elementNext
		 * .sendKeys(jsonarr.getJSONObject(0).get("CompanyName").toString());
		 * //设置页面单位名数据 数据来源CompanyTestData.xml //
		 * elementNext.sendKeys(jsonarr.getJSONObject
		 * (1).get("name").toString()); //选择单位类型 elementNext =
		 * driver.findElement(By.id("aab019_div_arrow")); //获得页面id
		 * elementNext.click(); elementNext = driver.findElement(By.id("50"));
		 * elementNext.click();
		 */

		for (int i = 0; i < jsonarr.length(); i++) {
			elementNext = driver.findElement(By.id("aab004")); // 获得页面单位名id
			elementNext.sendKeys(jsonarr.getJSONObject(i).get("CompanyName")
					.toString()); // 设置页面单位名数据 数据来源CompanyTestData.xml
			// 选择单位类型
			elementNext = driver.findElement(By.id("aab019_desc")); // 获得页面id
			elementNext.click();
			elementNext = driver.findElement(By.id("50"));
			elementNext.click();
			// 录入社保编号
			elementNext = driver.findElement(By.id("aab002"));
			elementNext.sendKeys(jsonarr.getJSONObject(i)
					.get("SocialsecurityNO").toString());
			// 组织机构代码
			elementNext = driver.findElement(By.id("aab003"));
			elementNext.sendKeys(jsonarr.getJSONObject(i)
					.get("OrganizationCode").toString());
			// 法人代表
			elementNext = driver.findElement(By.id("aab013"));
			elementNext.sendKeys(jsonarr.getJSONObject(i).get("LayName")
					.toString());
			// 事业单位类型
			elementNext = driver.findElement(By.id("aab057_div_arrow")); // 获得页面id
			elementNext.click();
			elementNext = driver.findElement(By.id("1"));
			elementNext.click();
			// 隶属关系
			elementNext = driver.findElement(By.id("aab021_div_arrow")); // 获得页面id
			elementNext.click();
			elementNext = driver.findElement(By.id("40"));
			elementNext.click();

			// 行政区划
			// elementNext =
			// driver.findElement(By.className("innerIcon popWin_163"));
			elementNext = driver.findElement(By.className("popWin_163"));
			elementNext.click();

			// 等待行政区划加载完毕
			webWaiter.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					d.switchTo().defaultContent(); // 到默认web
					d.switchTo().frame("tab_b_110397"); // 到frame
					d.switchTo().frame(0);
					System.out.println(d.getPageSource());
					WebElement areaLink = d.findElement(By.id("650105"));
					boolean loadcomplete = areaLink.isDisplayed(); // 是否加载完成
					return loadcomplete;
				}
			});
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_110397");
			driver.switchTo().frame(0);
			WebElement areaLink = driver.findElement(By.id("650105"));
			areaLink.click();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tab_b_110397");

			// 通讯地址
			elementNext = driver.findElement(By.id("aae006"));
			elementNext.sendKeys(jsonarr.getJSONObject(i).get("Addr")
					.toString());
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
			if (i < jsonarr.length() - 1) {
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
		System.out.println("end!");
		Thread.sleep(5000);
		driver.quit();
	}

}