package UI_Testing;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	
	
		
	public static WebDriver driver;

	public static void initialization() {
	
		
    System.setProperty("WebDriver.Chrome.driver","chromedriver.exe");
	driver =  new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	driver.get("http://localhost:3001");
		
		
	}

}
