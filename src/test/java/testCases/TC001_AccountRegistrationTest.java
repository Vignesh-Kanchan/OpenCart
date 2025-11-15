package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() 
	{
		logger.info( " ***Starting  TC001_AccountRegistraionTest *** ");
		HomePage hp= new HomePage(driver); 
	try {	
		hp.clickMyAccount();
		logger.info("clicked on MyAccount");
		hp.clickRegister();
		logger.info("clicked on Regtistration");

		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing the customer info....");

		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPivacyPolicy();
		regpage.clickContinue();
		
		
		logger.info("validating expected msg");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed ");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
	}	
	catch(Exception e){
		
		Assert.fail();
	}
	logger.info(" *** TC001_AccountRegistraionTest finished*** ");

	}

	

}
