package automation.tests.exampleSuite;

import automation.setup.DriverCreator;
import automation.setup.SeleniumBaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends SeleniumBaseDriver {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //*********Locators*********
    @FindBy(css = "table input[name='userName']")
    private WebElement txtUsername;

    @FindBy(css = "table input[name='password']")
    private WebElement txtPassword;

    @FindBy(css = "td.mouseout a[href*='mercurysignon.php']")
    private WebElement lnkSignIn;

    @FindBy(css = "table input[name='login']")
    private WebElement btnLogin ;



    //*********Page Methods*********

    public void openLoginPage() throws Exception {
        DriverCreator.getCurrentSessionId(), "Thread safety check failed");
    }

    public void enterUsername(String username){

    }

    public void enterPassword(String password){

    }

    public void clickLogin(){

    }

    public void loginUser(String userName, String password) {
        enterUsername(userName);
        enterPassword(password);
        clickLogin();
    }

    public void verifyNotLoggedIn() {
    }

    //*********Verifications*********
    public void verifyPageTitle(){

    }

}