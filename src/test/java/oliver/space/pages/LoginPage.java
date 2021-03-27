package oliver.space.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import oliver.space.setup.Driver;

public class LoginPage {


	@FindBy(css = "[data-qa='button-account-create']")
	public WebElement goLogin;
	
	@FindBy(css = "[data-qa='signin-button']")
	public WebElement alreadyRegistered;

	@FindBy(id = "input-email")
	public WebElement user;

	@FindBy(id = "input-password")
	public WebElement password;

	@FindBy(css = ".modal-content [type='submit']")
	public WebElement submit;

	private WebDriver driver;

	public LoginPage() {
		driver = Driver.driver;
		PageFactory.initElements(driver, this);
	}

	public By alreadyLoggd() {
		return By.cssSelector(".side-bar .my-account .top-row a");
	}

}
