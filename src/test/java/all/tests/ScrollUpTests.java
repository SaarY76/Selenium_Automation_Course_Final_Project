package all.tests;

import all.actions.Actions;
import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class ScrollUpTests {
    WebDriver driver;
    Actions actions;

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        String URL = JsonUtils.readJsonFromFile("url");
        String BROWSER = JsonUtils.readJsonFromFile("browser_chrome");
        driver = GenerateDriverAll.initDriver(BROWSER, URL);// navigating to URL
        actions = new Actions(driver);
    }

    @Description("Testing the Home page visibility")
    @Link("https://automationexercise.com")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("Home page first validation")
    @Test(description = "Testing Home page visibility")
    public void homePageIsVisible() {
        assert  actions.isHomePageVisible();
    }

    /**
     * Tests the contact us functionality.
     */
    @Feature("Scroll up flow")
    @Story("Scroll up")
    @Description("Test the Scroll up functionality")
    @Link("https://automationexercise.com")
    @Tag("Scroll up")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("Testing scroll up")
    @Test(dependsOnMethods = "homePageIsVisible", description = "Testing Scroll up functionality", groups = {"regression", "scroll_up"})
    public void scrollUp() throws IOException {
        assert actions.doScrollUp();
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}
