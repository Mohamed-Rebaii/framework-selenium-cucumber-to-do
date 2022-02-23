package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.PageObjectManager;
import pageObjects.ComparePageObject;

public class ComparePricePageSteps {
	public WebDriver driver;
	TestContext testContext;
	ComparePageObject homePage;

	public ComparePricePageSteps(TestContext context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
	}

	@Given("^I navigate to myTargetUrl \"([^\"]*)\"$")
	public void iNavigateToMyTargetUrl(String url) throws Throwable {
		homePage.navigateToHomePage(url);
	}

	@When("^I search my iPhone target \"([^\"]*)\"$")
	public void iSearchMyIPhoneTarget(String phoneTarget) throws Throwable {
		homePage.searchProduct(phoneTarget);
	}

	@When("^I select my target iphone in the list$")
	public void iSelectMyTargetIphoneInTheList() throws Throwable {
		homePage.getTargetProduct();
	}

	@When("^I get the price of the selected iPhone$")
	public void iGetThePriceOfTheSelectedIPhone() throws Throwable {
		homePage.getPriceOfTargetSelected();
	}

	@Then("^I compare and deternime the lowest website for my phone target and i print the result on the console$")
	public void iGetFinalResult() {
		homePage.comparePriceMethod();
	}
}
