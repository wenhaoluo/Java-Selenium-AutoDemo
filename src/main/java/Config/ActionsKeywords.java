package Config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Demo.DriverScript;
import util.Log;

import static Demo.DriverScript.OR;

public class ActionsKeywords {

    public static WebDriver driver;  
  
  
    public static void openBrowser(String object, String data) {  
  
        // 这里，我们暂时都写死用chrome来进行自动化测试  
        try{  
            Log.info("启动chrome浏览器。");  
            System.setProperty("webdriver.chrome.driver","src\\libs\\chromedriver.exe");  
            driver = new ChromeDriver();  
        }catch(Exception e){  
            Log.info("无法启动浏览器 --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
    public static void openUrl(String object, String data) {  
  
        try{  
            Log.info("打开测试环境地址");  
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
            driver.manage().window().maximize();  
            driver.get(Constants.URL);  
        }catch(Exception e){  
            Log.info("无法打开测试环境地址 --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
  
    }  
  
    public static void click(String object, String data) {  
        try{  
            Log.info("点击元素： "+ object);  
            driver.findElement(By.xpath(OR.getProperty(object))).click();  
        }catch(Exception e){  
            Log.error("无法点击元素--- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
    public static void input(String object, String data){  
        try{  
            Log.info("开始在 " + object +"输入文本");  
            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);  
        }catch(Exception e){  
            Log.error("无法输入文本 --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
  
    }  
  
    public static void waitFor(String object, String data) throws Exception{  
        try{  
            Log.info("等待五秒");  
            Thread.sleep(5000);  
        }catch(Exception e){  
            Log.error("无法等待 --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
    public static void closeBrowser(String object, String data){  
        try{  
            Log.info("关闭并退出浏览器");  
            driver.quit();  
        }catch(Exception e){  
            Log.error("无法关闭浏览器--- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
}   