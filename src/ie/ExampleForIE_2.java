package ie;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ExampleForIE_2  {
    public static void main(String[] args) {
        // 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
    	System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
      System.setProperty("webdriver.ie.bin", "C:\\Program Files\\Internet Explorer\\iexplore.exe");
//      System.setProperty("webdriver.ie.driver", "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
       WebDriver driver ;
      driver = new InternetExplorerDriver();
      driver.get("http://localhost:8080/yhjy_xj/");
      System.out.println("打开浏览器");
    }
}