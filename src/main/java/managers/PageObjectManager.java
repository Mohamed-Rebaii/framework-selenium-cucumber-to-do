package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.ComparePageObject;


public class PageObjectManager {
	private WebDriver driver;
	private ComparePageObject homePage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public ComparePageObject getHomePage(){
		return (homePage == null) ? homePage = new ComparePageObject(driver) : homePage;
	}
	
}
