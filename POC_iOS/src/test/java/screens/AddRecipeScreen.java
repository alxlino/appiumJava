package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



public class AddRecipeScreen {

	final WebDriver driver;
	

	@FindBy(how = How.NAME, using = "Add Recipe")
	
	public WebElement screenName;
	
	
	@FindBy(how = How.XPATH, using = "//XCUIElementTypeApplication[@name=\"Recipes\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField")
	
	public WebElement recipeTextField;
	
	
	@FindBy(how = How.NAME, using = "Save")
	
	public WebElement saveButton;
	
	
	
	public AddRecipeScreen(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
}
