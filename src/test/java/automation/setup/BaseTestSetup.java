package automation.setup;

import com.aventstack.extentreports.AnalysisStrategy;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BaseTestSetup {
	//Total Tests Executed that received a status of PASS or FAIL
	int totalTestsExecuted = 0;

	public void beforeSuite(ITestContext ctx, AnalysisStrategy analysisStrategy) throws Exception {
		String timeStampedSuiteName = ctx.getSuite().getName() ;
		ctx.getSuite().getXmlSuite().setName(timeStampedSuiteName);
		/*********** Init Html reporter *************************/
		FilePaths.initReportFolder();
//		HtmlReporter.setReporter(FilePaths.getReportFilePath(), analysisStrategy, ctx);
	}

	public void beforeClass(ITestContext ctx) throws Exception {
	}

	public void beforeMethod(String configFile, String environment, Method method, ITestContext ctx) throws Exception {
		String sessionId = DriverCreator.getCurrentSessionId();
		try {
		} catch (Error | Exception e) {
			throw new Exception(e);
		}
	}

	public void afterMethod(WebDriver driver, String configFile, String environment, ITestResult result, ITestContext ctx) throws Exception {
		String message = "";
		String sessionId = DriverCreator.getCurrentSessionId();
		XmlTest testInfo = ctx.getCurrentXmlTest();
		String testName = result.getTestClass().getRealClass().getSimpleName()+"."+result.getMethod().getMethodName();

		//Since extent Reports doesn't yet have a "was retried" status remove the test to avoid false failure reports
		if (result.wasRetried()){
		}

		switch (result.getStatus()) {
			case ITestResult.SUCCESS:
				message = String.format(">>The test [%s] [%s]: PASSED for session: %s", testInfo.getName(), testName, sessionId);
				break;
			case ITestResult.SKIP:
				break;

			case ITestResult.FAILURE:
				if(environment.contains("BS_")){

				}
				totalTestsExecuted++;
				break;
			default:
				//Mark Test as failed if it did not get a status for some unexpected reason
				message = String.format(">>The test [%s] [%s]: did not get executed or get a status",
						testInfo.getName(), result.getMethod().getQualifiedName(), "");
				break;
			}
		}

	public void afterClass() throws Exception {
	}

	public void afterSuite() throws Exception {
	}

	public void startDriver(String configFile, String environment, Method method, ITestContext ctx) throws Exception {
		//Try to start driver and fail the test if not successful
		try {
			DriverHandler.startDriver(configFile, environment, method, ctx);
		} catch (Error | Exception e) {
			throw new Exception(e.toString());
		}
	}

	public Object[][] getDataProvider(String testName) throws IOException {
		String filePath = FilePaths.DATA_PROVIDERS_FILE_PATH + testName + ".csv";
		Reader reader = Files.newBufferedReader(Paths.get(filePath));

		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		List<String[]> found = csvReader.readAll();
		Object[][] dataProviderObj = found.toArray(new Object[found.size()][]);
		return dataProviderObj;
	}

	protected void quitDriver(WebDriver driver, ITestResult result, ITestContext ctx) {
		if (driver != null){
			driver.quit();
		}
	}
}
