package automation.setup;

import automation.setup.DriverCreator;
import com.aventstack.extentreports.AnalysisStrategy;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class WebTestSetup extends BaseTestSetup {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext ctx) throws Exception {
		super.beforeSuite(ctx, AnalysisStrategy.CLASS);
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext ctx) throws Exception {
		super.beforeClass(ctx);
	}

	@BeforeMethod(alwaysRun=true)
	@Parameters(value={"config", "environment", "visualRegression"})
	public void beforeMethod(String configFile, String environment, @Optional() String visualRegression, Method method,
                             ITestContext ctx) throws Exception {
		super.startDriver(configFile, environment, method, ctx);
		super.beforeMethod(configFile, environment, method, ctx);
	}

	@AfterMethod(alwaysRun = true)
	@Parameters(value={"config", "environment","visualRegression"})
	public void afterMethod(String configFile, String environment, @Optional() String visualRegression,
                            ITestResult result, ITestContext ctx) throws Exception {
		try {
			super.afterMethod(getDriver(), configFile, environment, result, ctx);
		}

		finally {
			super.quitDriver(getDriver(),result, ctx);
		}
	}


	@AfterClass(alwaysRun = true)
	public void afterClass() throws Exception {
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws Exception {
		super.afterSuite();
	}

	public WebDriver getDriver() {
		return DriverCreator.getCurrentWebDriver();
	}

}
