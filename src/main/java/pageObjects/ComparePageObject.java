package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import managers.FileReaderManager;
import selenium.Wait;

public class ComparePageObject {
	WebDriver driver;
	/* Locators */
	final static String AMAZONE_SEARCH_FIELD_ID = "twotabsearchtextbox";
	final static String AMAZONE_TARGET_PHONE_CSS = ".a-link-normal.s-underline-text.s-underline-link-text.s-link-style.a-text-normal";
	final static String FLIPKART_SEARCH_FIELD_CLASS_NAME = "_3704LK";
	final static String AMAZONE_TARGET_PHONE_PRICE_XPATH = "//*[@id=\"in_olp_feature_div\"]/div[2]/span/a/span[2]";
	final static String FLIPKART_TARGET_PHONE_CLASS_NAME = "_1fQZEK";
	final static String FLIPKART_TARGET_PHONE_PRICE_XPATH = "//div[@class='_30jeq3 _16Jk6d']";

	/* @FindBy */
	@FindBy(how = How.ID, using = AMAZONE_SEARCH_FIELD_ID)
	public static WebElement searchFiledAmazone;
	@FindBy(how = How.CSS, using = AMAZONE_TARGET_PHONE_CSS)
	public static WebElement phoneTagertAmazone;
	@FindBy(how = How.CLASS_NAME, using = FLIPKART_SEARCH_FIELD_CLASS_NAME)
	public static WebElement searchFiledFlipkart;
	@FindBy(how = How.XPATH, using = AMAZONE_TARGET_PHONE_PRICE_XPATH)
	public static WebElement amazonePhonePrice;
	@FindBy(how = How.CLASS_NAME, using = FLIPKART_TARGET_PHONE_CLASS_NAME)
	public static WebElement phoneTagertFlipkart;
	@FindBy(how = How.XPATH, using = FLIPKART_TARGET_PHONE_PRICE_XPATH)
	public static WebElement flipkartPhonePrice;

	/* Variables */
	public static String amazonePrice;
	public static String flipkartPrice;
	public static String tagerPhoneSelectedAmazoneUrl;
	public static String targetPhoneSelectedFlipkartUrl;

	/* Methods */
	public ComparePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToHomePage(String url) {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl(url));
	}

	public void searchProduct(String product) {
		String searchProduct = FileReaderManager.getInstance().getJsonReader()
				.getAllProductInformation(FileReaderManager.getInstance().getJsonReader().getProductByName(product));
		if (driver.getCurrentUrl().contains("amazon")) {
			searchFiledAmazone.sendKeys(searchProduct, Keys.ENTER);
		} else {
			Wait.untilPageLoadComplete(driver);
			searchFiledFlipkart.sendKeys(searchProduct, Keys.ENTER);
		}
	}

	public void getTargetProduct() throws InterruptedException {
		if (driver.getCurrentUrl().contains("amazon")) {
			tagerPhoneSelectedAmazoneUrl = phoneTagertAmazone.getAttribute("href");
			driver.get(tagerPhoneSelectedAmazoneUrl);
		} else {
			Thread.sleep(5000);
			System.out.println("get target product url flipkart" + driver.getCurrentUrl());
			targetPhoneSelectedFlipkartUrl = phoneTagertFlipkart.getAttribute("href");
			driver.get(targetPhoneSelectedFlipkartUrl);
		}
	}

	public void getPriceOfTargetSelected() {
		if (driver.getCurrentUrl().contains("amazon")) {
			amazonePrice = amazonePhonePrice.getText();

		} else {
			flipkartPrice = flipkartPhonePrice.getText();
		}
	}

	public void comparePriceMethod() {
		String iphone = FileReaderManager.getInstance().getJsonReader()
				.getAllProductInformation(FileReaderManager.getInstance().getJsonReader().getProductByName("iPhone"));
		String amazone_price = amazonePrice.replace("₹", "").replace(",", "").replace(".", "");
		String flipkart_price = flipkartPrice.replace("₹", "").replace(",", "").replace(".", "").concat("00");

		if (Double.parseDouble(amazone_price) > Double.parseDouble(flipkart_price)) {
			System.out.println("Flipkart website price of" + iphone + "is" + flipkartPrice
					+ "lower then amazon price is" + amazonePrice + " purchasing from flipkart is better");

		} else if (Double.parseDouble(amazone_price) == (Double.parseDouble(flipkart_price))) {
			System.out.println("Flipkart website price of" + iphone + " is: " + flipkartPrice + "same of amazon price: "
					+ amazonePrice + " purchasing from faster delivery from this two website");

		} else {
			System.out.println("Flipkart website price of" + iphone + " is: " + flipkartPrice
					+ " highiest then amazon price is :" + amazonePrice + " purchasing from amazon is better");
		}
	}
}
