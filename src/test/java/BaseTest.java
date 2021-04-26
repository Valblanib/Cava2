package test.java;

import Utils.Constance;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod(){
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+"automationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester","Vallerie B");

    }
    @BeforeMethod
    @Parameters(value = ("browserName"))
    public void beforeMethodMethod(@Optional String browserName, Method testMethod){
        logger =extent.createTest(testMethod.getName());
        setUpDriver(browserName);
        driver.manage().window().maximize();
        driver.get("Constance.url");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void AfterMethodMethod(ITestResult result){
        if(result.getStatus()== ITestResult.SUCCESS){
            String methodName = result.getMethod().getMethodName();
            String logText ="Test Case: " + methodName + "Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS,m);
        }
        else if(result.getStatus()== ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + "Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }
        driver.quit();

    }

    @AfterTest
    public void AfterTestMethod(){
        extent.flush();

    }

    public static void setUpDriver(String browserName) {
        if (browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator+"drivers"+File.separator+"chromedriver");
            driver = new ChromeDriver();

        }else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ File.separator+"drivers"+File.separator+"firefoxdriver");
            driver = new FirefoxDriver();
        }
    }
}
