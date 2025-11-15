package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_verify_loginDDT extends BaseClass {
	
	@Test(dataProvider="logingData" ,dataProviderClass=DataProviders.class,groups="DataDriven")// getting dataproviders from different package
	 public void verify_loginDDT(String email, String Pwd, String exp)
	 {
	 logger.info("**Starting TC003_verify_loginDDT** ");
	try {
		 
		 	//Homepage
		 	HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//Login Page
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(Pwd);
			lp.clickLogin();
			
			//My Account Page
			MyAccountPage myacc=new MyAccountPage(driver);
			boolean targetPage=myacc.isMyAccountPageExist();			
			if(exp.equalsIgnoreCase("Valid")) 
			{
				if(targetPage==true)
				{
					Assert.assertTrue(true);
					myacc.clickLogOut();

				}
				else
				{
					Assert.assertTrue(false);
				}
				
			}
			if(exp.equalsIgnoreCase("Invalid"))	
			{
				if(targetPage==true)
				{
					myacc.clickLogOut();
					Assert.assertTrue(false);

				}
				else
				{	
					Assert.assertTrue(true);

				}
				
			}
		 
	 }
            catch(Exception e) {
		Assert.fail();
	}
	logger.info("**finishing TC003_verify_loginDDT **");
	 }
	
	
}
