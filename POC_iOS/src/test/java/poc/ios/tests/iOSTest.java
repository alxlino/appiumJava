package poc.ios.tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import framework.AppiumServer;
import framework.CaptureScreenReport;
import framework.GetScreenShot;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.testng.annotations.BeforeTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class iOSTest {
	
	  //private AppiumServer appiumServer;
	  //private AppiumDriverLocalService service;
	  //private AppiumServiceBuilder builder;
		
	static ExtentReports extent;
	static ExtentTest test;
	ITestResult result; 
	
	  public static WebDriver driver;
	  
	  //private CaptureScreenReport captureScreenApp;
	  
	  //public static AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
	
	  //dispositivo real iPhone Inmetrics
	  //private String nomeDevice = "Iphone_inmstelo";
	  //private String plataformaVersao = "10.3.2";
	  //private String plataformaNome = "iOS";
	  //private String udID = "f2c33d7745857c30d2bbd841725dad921a5ae986";
	  //private String appPath = "/Users/alexandrelino/Documents/POC_Meu_Alelo_IOS";
	  //private String appNome = "Hml Alelo.ipa";
	  
	  //simulador iPhone 5s
	  private String nomeDevice = "iPhone 5s";
	  private String plataformaVersao = "11.0.1";
	  private String plataformaNome = "iOS";
	  private String udID = "1196E214-BAE7-4175-8040-F8E0ED78280C";
	  private String appPath = "/Users/alexandrelino/Downloads/iPhoneCoreDataRecipes/build/Release-iphonesimulator";
	  private String appNome = "Recipes.app";

  @BeforeTest
  public void setUpTest() throws MalformedURLException, InterruptedException {
	  
	   //appiumServer.startServer();
	    
	    //service.stop();
	    //Thread.sleep(3000);
	    //service.start(); 
	  
	    File appDir = new File(appPath);
		File app = new File(appDir, appNome);	
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		//service.stop();

		//Atribuindo as capabilities
		capabilities.setCapability("deviceName", nomeDevice);
		capabilities.setCapability("platformVersion", plataformaVersao);
		capabilities.setCapability("platformName", plataformaNome);
		capabilities.setCapability("udid", udID);
		capabilities.setCapability("app",app.getAbsolutePath());
		//capabilities.setCapability("bundleId", "com.ciandt.enterprise.alelo-meualelo-hml");
		  
		//driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	  
  }
  
  
  @Test
  public void cadastrarReceita() throws Exception {
	  
	    extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentScreenshot.html",true);
		test = extent.startTest("captureScreenshot");
	  
	  
	  WebElement screen = driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Recipes\"]"));
	  assertFalse(screen.isDisplayed());
	  //captureScreenApp.captureScreenshot(driver);
	  //captureScreenApp.endReport();
	  
	  
	    
		String screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport");
		test.log(LogStatus.PASS,"Teste Passou os titulos sao iguais: " + test.addScreenCapture(screenshotPath));
	  
  }
  
  @AfterMethod
  public void getResult(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			
			String screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport");
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Screenshot Abaixo: " + test.addScreenCapture(screenshotPath));
		}
		extent.endTest(test);
	}
  
  
  @AfterTest
  public void afterTests() {
	  
	    //extent.endTest(test);
		extent.flush();
		extent.close();
	  
	  
	  driver.quit();
	  //appiumServer.stopServer();
	  //service.stop();
	  
	    
	  
	  
  }

  

}
