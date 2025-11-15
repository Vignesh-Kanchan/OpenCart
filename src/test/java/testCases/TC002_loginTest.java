package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_loginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login() 
	{
		logger.info("**Starting TC002_loginTest** ");
		try {
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//My Account Page
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetPage=myacc.isMyAccountPageExist();
		Assert.assertEquals(targetPage, true);
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("**Finished TC002_loginTest** ");

		
		
	}
	
}
