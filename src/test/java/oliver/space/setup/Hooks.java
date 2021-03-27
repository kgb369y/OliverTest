package oliver.space.setup;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import oliver.space.methods.CommonsMethods;

public class Hooks extends CommonsMethods {

	@Before
	public void setUp(Scenario scenario) {
		startSystemProperties();
		initDriver("chrome");
		Driver.driver.get(Constants.OLIVERSITE);
	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		LogEntries logs = Driver.driver.manage().logs().get(LogType.BROWSER);
		
		if (scenario.isFailed()) {
			takeScreenshot(scenario);
			scenario.embed(logs.toString().getBytes(), "webConsoleLog");
		}
		quitDriverAndGenerateReport();
	}

	public static void quitDriverAndGenerateReport() throws Exception {
		if (Driver.driver != null) {
			Driver.driver.close();
			Driver.driver.quit();
		}
	}

	public void startSystemProperties() {
		WebDriverManager.chromedriver().setup();
	}

	public static void initDriver(String browser) {
		if (Driver.driver == null) {
			switch (browser) {
			case "chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.addArguments("--start-maximized");
				//options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");

				options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
				options.addArguments("--disable-infobars"); // https://stackoverflow.com/a/43840128/1689770
				options.addArguments("--disable-browser-side-navigation"); // https://stackoverflow.com/a/49123152/1689770
				options.addArguments("--disable-gpu"); // https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // to use if we want to evaluate performance from
																		// ui

				options.setAcceptInsecureCerts(true); // apply if is needed access to wp without certs

				Driver.driver = new ChromeDriver(options);
				Driver.driver.manage().window().maximize();
				break;
			default:
				break;
			}

			initImplictWaits();
		}
	}

	// default implicitWaits... is recommended to have a properties related to
	// timeout implicit and explicit by environment but for the purpose of this
	// tests will be static
	public static void initImplictWaits() {
		if (Driver.driver != null) {
			implicitWaitsTo(Constants.IMPLICITWAITDEF);
		}
	}

	private void takeScreenshot(Scenario scenario) {
		TakesScreenshot ts = (TakesScreenshot) Driver.driver;
		byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

}
