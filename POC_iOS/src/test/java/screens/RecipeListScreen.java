package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.How;


public class RecipeListScreen {

	final WebDriver driver;
	
	
	@FindBy(how = How.XPATH, using = "//XCUIElementTypeOther[@name=\"Recipes\"]") 
	public WebElement screenName;
	
	
	@FindBy(how = How.NAME, using = "Add")
	public WebElement addButton;
	
	
	public RecipeListScreen(WebDriver driver) {
		this.driver = driver;
	}



}
