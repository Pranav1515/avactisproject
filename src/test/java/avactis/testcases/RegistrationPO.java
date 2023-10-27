/*TC1
Positive scenario Steps are
1. Click on "My Account" Link
2. Click on "Register" Button
3. Enter the valid data
4. Click on Register Button
5. And Registration should be successful*/

package avactis.testcases;

import static org.testng.Assert.assertEquals;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class RegistrationPO extends LoadableComponent<RegistrationPO> { // manually extends or inherit LoadableComponent class

	@FindBy(linkText = "My Account")
	private WebElement myaccounttab;

	@FindBy(xpath = "//button[text()='Register']")
	private WebElement registerbutton;

	@FindBy(name = "customer_info[Customer][Email]")
	private WebElement email;

	@FindBy(name = "customer_info[Customer][Password]")
	private WebElement password;

	@FindBy(name = "customer_info[Customer][RePassword]")
	private WebElement repassword;

	@FindBy(name = "customer_info[Customer][FirstName]")
	private WebElement firstname;

	@FindBy(name = "customer_info[Customer][LastName]")
	private WebElement lastname;

	@FindBy(xpath = "//select[@name='customer_info[Customer][Country]']/option[@value='100']")
	private WebElement country;

	@FindBy(xpath = "//select[@name='customer_info[Customer][State]']/option[@value='636']")
	private WebElement state;

	@FindBy(name = "customer_info[Customer][ZipCode]")
	private WebElement zipcode;

	@FindBy(name = "customer_info[Customer][City]")
	private WebElement city;

	@FindBy(name = "customer_info[Customer][Streetline1]")
	private WebElement streetline1;

	@FindBy(name = "customer_info[Customer][Streetline2]")
	private WebElement streetline2;

	@FindBy(name = "customer_info[Customer][Phone]")
	private WebElement phone;

	@FindBy(xpath = "//input[@value='Register']")
	private WebElement registersubmit;

	@FindBy(xpath = "//div[@class='myaccount_home']/div[1]")
	private WebElement regisuccesfullmsgtext;
	
	@FindBy(xpath = "//div[@class='registration_form']/div[1]")
	private WebElement invalidmailmsgtext;
	
	

	WebDriver driver;

	private String url = "http://localhost/Avactis/";
	private String pageTitle = "Avactis Demo Store";

	public RegistrationPO() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// instead of load() and isLoaded() we called get() method
		get(); // call both methods load() and isLoaded()
		PageFactory.initElements(driver, this);
	}
	public void registration(String mail, String mailhost, String pass, String repass, String fname, String lname,
			String zip, String cit, String street1, String street2, String pno) {

		myaccounttab.click();

		registerbutton.click();

		email.sendKeys(mail + getRandomMail() + mailhost);

		password.sendKeys(pass);

		repassword.sendKeys(repass);

		firstname.sendKeys(fname);

		lastname.sendKeys(lname);

		country.click();

		state.click();

		zipcode.sendKeys(zip);

		city.sendKeys(cit);

		streetline1.sendKeys(street1);

		streetline2.sendKeys(street2);

		phone.sendKeys(pno);

		registersubmit.click();

	}

	public void closeBrowser() {
		driver.quit();
	}

	protected String getRandomMail() {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890"; // random string may be char and numbers
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

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

	public String getRegiSuccesfullTextMsg() {
		// TODO Auto-generated method stub
		return regisuccesfullmsgtext.getText();
	}
	
	public String getInvalidMailTextMsg() {
		// TODO Auto-generated method stub
		return invalidmailmsgtext.getText();
	}

}
