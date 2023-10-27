package avactis.testcases;
/*TC3
write a test to login into the store.(positive and negative)
1. Click on SignIn
2. Enter the valid credentials
3. Login Successful*/

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class PurchasePO extends LoadableComponent<PurchasePO> {

	WebDriver driver;

	
	@FindBy(xpath = "//input[@value='Add To Cart']")
	private WebElement addtocart;
	

	// private String url = "http://localhost/Avactis/";
	// private String pageTitle = "Avactis Demo Store";

	public PurchasePO() {

		// driver = new ChromeDriver(); 
		//driver.manage().window().maximize(); get();
		PageFactory.initElements(driver, this);

	}

	public void purchase(String prodlink) {

		driver.get(prodlink);
		addtocart.click();

	}

	public void closeBrowser() {

		driver.quit();
	}


	// @Override
	protected void load() {
		// TODO Auto-generated method stub
		// driver.get(url);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		// assertEquals(driver.getTitle(), pageTitle, "Seems page is not loaded");

	}

}
