package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DetailRecipeScreen {

	static WebDriver driver;
	
	
	@FindBy(how = How.XPATH, using = "//XCUIElementTypeOther[@name!=\"\"]")
	public WebElement screenName;
	
    
    @FindBy(how = How.XPATH, using = "//XCUIElementTypeApplication[@name=\"Recipes\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeTextField[1]")
	public WebElement recipeNameTextField;
    
	
	public DetailRecipeScreen(WebDriver driver) {
		DetailRecipeScreen.driver = driver;
	}

	
	

}
