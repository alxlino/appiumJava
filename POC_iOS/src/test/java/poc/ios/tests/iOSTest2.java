package poc.ios.tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import framework.GetScreenShot;
import screens.AddRecipeScreen;
import screens.DetailRecipeScreen;
import screens.RecipeListScreen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class iOSTest2 {
  
	  public static ExtentReports extent;
	  public static ExtentTest test;
	  public static ITestResult result; 
	
	  public static WebDriver driver;
	  
	  //simulador iPhone 5s
	  private String nomeDevice = "iPhone 5s";
	  private String plataformaVersao = "11.0.1";
	  private String plataformaNome = "iOS";
	  private String udID = "1196E214-BAE7-4175-8040-F8E0ED78280C";
	  private String appPath = "/Users/alexandrelino/Downloads/iPhoneCoreDataRecipes/build/Release-iphonesimulator";
	  private String appNome = "Recipes.app";
	  
	  

@BeforeTest
public void setUpTest() throws MalformedURLException, InterruptedException {
	  
	    File appDir = new File(appPath);
		File app = new File(appDir, appNome);	
		
		DesiredCapabilities capabilities = new DesiredCapabilities();

		//Atribuindo as capabilities
		capabilities.setCapability("deviceName", nomeDevice);
		capabilities.setCapability("platformVersion", plataformaVersao);
		capabilities.setCapability("platformName", plataformaNome);
		capabilities.setCapability("udid", udID);
		capabilities.setCapability("app",app.getAbsolutePath());
		
		driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentScreenshot.html",true);
	  
}


@Test
public void cadastrarReceita() throws Exception {
	    
		test = extent.startTest("cadastrarReceita");
		
		String novaReceita = "Receita Teste 1";
	    
	    
		RecipeListScreen recipeListScreen = PageFactory.initElements(driver, RecipeListScreen.class);
	    assertTrue(recipeListScreen.screenName.isDisplayed());
	    recipeListScreen.addButton.click();
	    String screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport_1");
		test.log(LogStatus.PASS,"Teste OK: Botao Add encontrado e clicado" + test.addScreenCapture(screenshotPath));
	    
	    
		AddRecipeScreen addRecipeScreen = PageFactory.initElements(driver, AddRecipeScreen.class);
	    assertTrue(addRecipeScreen.recipeTextField.isDisplayed());
	    addRecipeScreen.recipeTextField.sendKeys(novaReceita);
	    screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport_2");
		test.log(LogStatus.PASS,"Teste OK: Campo Recipe Name encontrado e preenchido: " + test.addScreenCapture(screenshotPath));
		
		
		assertTrue(addRecipeScreen.saveButton.isDisplayed());
		addRecipeScreen.saveButton.click();
		screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport_3");
		test.log(LogStatus.PASS,"Teste OK: Botão Save clicado: " + test.addScreenCapture(screenshotPath));

		
		DetailRecipeScreen detailRecipeScreen = PageFactory.initElements(driver, DetailRecipeScreen.class);
		assertEquals(novaReceita, detailRecipeScreen.screenName.getText());
		screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport_4");
		test.log(LogStatus.PASS,"Teste OK: Nome da tela igual ao preenchido: " + test.addScreenCapture(screenshotPath));
		
		
		assertEquals(novaReceita, detailRecipeScreen.recipeNameTextField.getText());
		screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport_5");
		test.log(LogStatus.PASS,"Teste OK: Nome do campo igual ao preenchido: " + test.addScreenCapture(screenshotPath));
			
}


@AfterMethod
public void getResult(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			
			String screenshotPath = GetScreenShot.capture(driver, "screenshotForExtentReport");
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Screenshot com Erro: " + test.addScreenCapture(screenshotPath));
		}
		extent.endTest(test);
}


@AfterTest
public void afterTests() {
	  
		extent.flush();
		extent.close();
		driver.quit();
	 
}
  
  
}
