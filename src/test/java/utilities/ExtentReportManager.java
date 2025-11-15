package utilities;

	import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

	public class ExtentReportManager implements ITestListener {

	    public ExtentTest test;
	    public ExtentReports extent;
	    ExtentSparkReporter sparkReporter;

	   
	    public void onStart(ITestContext testContext) {
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        String repName = "Test-Report-" + timeStamp + ".html";
	        sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);//specify the location of the report
	        
	        sparkReporter.config().setDocumentTitle("OpenCart Automation Test");
	        sparkReporter.config().setReportName("OpenCart Functional Testing");
	        sparkReporter.config().setTheme(Theme.DARK);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Application", "OpenCart");
	        extent.setSystemInfo("Module", "Admin");
	        extent.setSystemInfo("Sub Module", "Customers");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));

	        String os = testContext.getCurrentXmlTest().getParameter("os");
	        extent.setSystemInfo("Operating System", os);
	        String browser = testContext.getCurrentXmlTest().getParameter("browser");
	        extent.setSystemInfo("Borwser", browser);
	        
	        List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	        if(!includeGroups.isEmpty()) {
	        	 extent.setSystemInfo("Groups", includeGroups.toString());
	        }
	    
	    }

	  
	    public void onTestSuccess(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.log(Status.PASS, result.getMethod().getMethodName() + " is passed");
	        System.out.println(result.getMethod().getMethodName() + " is successfully executed");
	    }

	 
	    public void onTestFailure(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.log(Status.FAIL, result.getMethod().getMethodName() + " is failed");
	        test.log(Status.FAIL, result.getThrowable());

	        try {
	            String imgPath = new BaseClass().captureScreen(result.getName());
	            test.log(Status.FAIL, result.getName() + " Screenshot: " + test.addScreenCaptureFromPath(imgPath));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }

	  
	    public void onTestSkipped(ITestResult result) {
	        test = extent.createTest(result.getTestClass().getName());
	        test.log(Status.SKIP, result.getMethod().getMethodName() + " is skipped");
	    }

	    public void onFinish(ITestContext testContext) {
	    	extent.flush();
	    }
	}
	
