package all.tests;

import all.actions.Actions;
import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

@Epic("Contact us page test Suite")
public class ContactUsTests {
    private WebDriver driver;
    private Actions actions;

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
    @Feature("Contact us flow")
    @Story("Contact us")
    @Description("Test the Contact us functionality")
    @Link("https://automationexercise.com/contact_us")
    @Tag("Contact us")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("Contact us form and verify data")
    @Test(dependsOnMethods = "homePageIsVisible", description = "Testing Contact us functionality", groups = {"regression", "contactUs"})
    public void contactUs() throws IOException {
        assert actions.doContactUs();
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}
