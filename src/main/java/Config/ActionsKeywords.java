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
  
        // ���������ʱ��д����chrome�������Զ�������  
        try{  
            Log.info("����chrome�������");  
            System.setProperty("webdriver.chrome.driver","src\\libs\\chromedriver.exe");  
            driver = new ChromeDriver();  
        }catch(Exception e){  
            Log.info("�޷���������� --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
    public static void openUrl(String object, String data) {  
  
        try{  
            Log.info("�򿪲��Ի�����ַ");  
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
            driver.manage().window().maximize();  
            driver.get(Constants.URL);  
        }catch(Exception e){  
            Log.info("�޷��򿪲��Ի�����ַ --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
  
    }  
  
    public static void click(String object, String data) {  
        try{  
            Log.info("���Ԫ�أ� "+ object);  
            driver.findElement(By.xpath(OR.getProperty(object))).click();  
        }catch(Exception e){  
            Log.error("�޷����Ԫ��--- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
    public static void input(String object, String data){  
        try{  
            Log.info("��ʼ�� " + object +"�����ı�");  
            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);  
        }catch(Exception e){  
            Log.error("�޷������ı� --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
  
    }  
  
    public static void waitFor(String object, String data) throws Exception{  
        try{  
            Log.info("�ȴ�����");  
            Thread.sleep(5000);  
        }catch(Exception e){  
            Log.error("�޷��ȴ� --- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
    public static void closeBrowser(String object, String data){  
        try{  
            Log.info("�رղ��˳������");  
            driver.quit();  
        }catch(Exception e){  
            Log.error("�޷��ر������--- " + e.getMessage());  
            DriverScript.bResult = false;  
        }  
    }  
  
}   