package automation.setup;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import java.lang.reflect.Method;

public class DriverHandler {

	public static synchronized void startDriver(String configFile, String environment, Method method, ITestContext context) throws Exception {
		WebDriver driver = null;
		///////////////////////MOBILE///////////////////////////////

		////////////////////////REMOTE DESKTOP///////////////////////////////
		if (environment.startsWith("Desktop")) {
			SeleniumDriver selenium = new SeleniumDriver();
			driver = selenium.createDriverWithCapabilities(configFile, environment, method, context);
		}
		else {
			throw new Exception(String.format("The driver environment [%s] is not supported", environment));
		}
		DriverCreator.getInstance().setDriver(driver);
	}
}




