package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public Properties p;
	public static WebDriver driver;
	public Logger logger;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","Browser"})
	public void setup(String os, String br) throws IOException {
		logger=LogManager.getLogger(this.getClass());
	 
		//loading the config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilites=new DesiredCapabilities();
		
				//os
			if(os.equalsIgnoreCase("Windows")) 
			{
				capabilites.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) 
			{
				capabilites.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching os ");
				return;
			}
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":	capabilites.setBrowserName("chrome");break;
			case "edge":	capabilites.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("No matching browser");return;			
			}
			
			driver =new RemoteWebDriver(new URL("http://10.205.179.91:4444/wd/hub"),capabilites);

		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch(br.toLowerCase())
			{
				case "chrome": driver=new ChromeDriver();break;
				case "edge": driver=new EdgeDriver();break;
				default:System.out.println("invalid browser");return;

			}
			
		}
		
	
	
		driver.manage().deleteAllCookies(); 
		driver.get(p.getProperty("Appurl")); //reading url from properties file
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown() {
			driver.quit();
	}
	
	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(7);
		return generatedString;
	}
	public String randomNumber() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	public String randomAlphaNumeric() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		String generatedString=RandomStringUtils.randomAlphabetic(7);
		return (generatedNumber+"@"+generatedString);
		
	}
	
	public String captureScreen(String tname) throws IOException {
	    String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File sourceFile = ts.getScreenshotAs(OutputType.FILE);
	    
	    String targetFilePath= System.getProperty("user.dir") + "\\Screenshots\\" + tname + timestamp + ".png";
	    File targetFile = new File(targetFilePath);
	    
	    sourceFile.renameTo(targetFile);
	    return targetFilePath;
	}
	
	
}
