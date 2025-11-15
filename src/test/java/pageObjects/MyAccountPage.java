package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//div[@id=\"content\"]//h2[1]")WebElement msgHeading;
	@FindBy(xpath="//div[@class='list-group']//a[13]")WebElement lnkLogOut;
	
	public  boolean isMyAccountPageExist() {
	
		try {
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
			
		}		
	
	}
	public void clickLogOut() {
		lnkLogOut.click();
		
	}
	
}
