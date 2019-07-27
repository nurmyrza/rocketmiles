package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.Driver;
import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setup() {
        WebDriver driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        System.out.println("Hooks-->Before scenario");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
//            utilities.BrowserUtilities.getScreenShot("screen");
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        Driver.getDriver().quit();
    }
}
