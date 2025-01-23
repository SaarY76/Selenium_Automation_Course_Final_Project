package all.tests;

import all.actions.Actions;
import all.pages.ContactUsPage;
import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContactUsTests {
    private WebDriver driver;
    private Actions actions;

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        String URL = JsonUtils.readJsonFromFile("url");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        driver = GenerateDriverAll.initDriver(BROWSER, URL);// navigating to URL
        actions = new Actions(driver);
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
    @Test(description = "testing the contact us functionality", groups = {"regression", "contactUs"})
    public void contactUs() throws IOException {
        assert actions.doContactUs();
    }

    /**
     * Tests that home page is visible
     */
    @Test(description = "testing that home page is visible", groups = "contactUs")
    public void homePageIsVisible() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        assert contactUsPage.homePageIsVisible();
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}
