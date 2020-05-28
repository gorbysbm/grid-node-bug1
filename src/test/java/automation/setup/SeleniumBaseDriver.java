package automation.setup;

import org.openqa.selenium.WebDriver;

public class SeleniumBaseDriver extends BaseDriver {
	protected WebDriver driver;
	private int EXPLICIT_WAIT_TIMEOUT = 6;
	public static String BASE_URL = "http://www.newtours.demoaut.com";

	public SeleniumBaseDriver() {
		super();
	}

	public SeleniumBaseDriver(WebDriver driver) {
		super(driver);
		setExplicitWaitToDefault();
		this.driver = driver;
	}

	public void setExplicitWaitToDefault() {
		//setExplicitWait(EXPLICIT_WAIT_TIMEOUT);
	}


}
