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

public class LoginPO extends LoadableComponent<LoginPO> {

	WebDriver driver;

	@FindBy(linkText = "Sign In")
	private WebElement signintab;

	@FindBy(name = "email")
	private WebElement email;

	@FindBy(name = "passwd")
	private WebElement password;

	@FindBy(name = "remember_me")
	private WebElement keepme;

	@FindBy(xpath = "//input[@value='Sign In']")
	private WebElement signinbutton;

	@FindBy(xpath = "//h3[text()='Manage Account and View Orders']")
	private WebElement afterloginmsg;
	
	@FindBy(xpath = "//div[text()='Account and password could not be identified. Try again or create an account.']")
	private WebElement invalidlogindatamsg;

	private String url = "http://localhost/Avactis/";
	private String pageTitle = "Avactis Demo Store";

	public LoginPO() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		get();
		PageFactory.initElements(driver, this);

	}

	public void login(String mail, String pass) {

		signintab.click();
		email.sendKeys(mail);
		password.sendKeys(pass);
		keepme.click();
		signinbutton.click();
	}

	public void closeBrowser() {

		driver.quit();
	}

	public String getLoginSuccesfullTextMsg() {
		// TODO Auto-generated method stub
		return afterloginmsg.getText();
	}
	
	public String getLoginUnsuccesfullTextMsg() {
		// TODO Auto-generated method stub
		return invalidlogindatamsg.getText();
	}
	
	

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		driver.get(url);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertEquals(driver.getTitle(), pageTitle, "Seems page is not loaded");

	}

	

}
