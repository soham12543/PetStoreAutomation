package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    String repName;

    @Override
    public void onStart(ITestContext context)  //Will Execute only once before starting all the tests
    {
        // Initializing Extent Reports
    	String timestamp=new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());
        repName="Test-Report"+timestamp+".html";
        sparkReporter=new ExtentSparkReporter("./Report/"+repName);
        sparkReporter.config().setDocumentTitle("Rest Assured Automation Project");
        sparkReporter.config().setReportName("Pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

     // Create ExtentReports first
        extent = new ExtentReports();

        // Attach reporter
        extent.attachReporter(sparkReporter);
        //Common Details
        extent.setSystemInfo("Application", "Pet Store API");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Soham Chakraborty");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Creating a new test in the report
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	    test = extent.createTest(result.getName());
    	    test.createNode(result.getName());
    	    test.assignCategory(result.getMethod().getGroups());
    	    test.log(Status.FAIL, "Test Failed");
    	    test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Writing everything to the report
    }
    

}
