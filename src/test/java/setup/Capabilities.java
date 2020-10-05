package setup;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.sukgu.Shadow;

public class Capabilities {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static WebDriverWait wait;
	public static Properties PROPS = new LeitorProperties("config.properties").getProperties();
	public static Shadow shadow;

	/**
	 * Metodo para retornar o driver conforme o browser informado na suite
	 * @param platform
	 */
	@BeforeClass()
	@Parameters({ "platforma"})
	protected void defineBrowser(String platforma) {
		DesiredCapabilities cap = new DesiredCapabilities();
		if (driver.get() == null) {
			switch (platforma) {
			case "Firefox":
				System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
				cap = DesiredCapabilities.firefox();
				FirefoxOptions fireOptions = new FirefoxOptions();
				fireOptions.addArguments("-private");
				fireOptions.setAcceptInsecureCerts(true);
				cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fireOptions);
				driver.set(new FirefoxDriver(fireOptions));
				break;
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
				cap = DesiredCapabilities.chrome();
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				ChromeOptions chOptions = new ChromeOptions();
				chOptions.addArguments("--incognito");
				chOptions.addArguments("--ignore-certificate-errors");
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chOptions.setExperimentalOption("prefs", chromePrefs);
				cap.setCapability(ChromeOptions.CAPABILITY, chOptions);
				driver.set(new ChromeDriver(chOptions));
				break;
			}
		}

		wait = new WebDriverWait(driver.get(), 30);
		shadow = new Shadow(getDriver());
		shadow.setImplicitWait(15);
		
	}
	
	/**
	 * Finalizar browser ao terminar teste de uma classe
	 */
	@AfterClass
	protected void finalizar() {
		driver.get().quit();
		driver.set(null);
	}

	/**
	 * Acessa a aplicacao
	 */
	public static void acessaAplicacao(String url) {
		driver.get().manage().window().maximize();
		driver.get().get(url);
	}

	/**
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}

	/**
	 * @return the wait
	 */
	public static WebDriverWait getWait() {
		return wait;
	}

}