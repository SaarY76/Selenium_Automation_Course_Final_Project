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

@Epic("Sign up page test Suite")
public class SignUpTests {
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
        driver = GenerateDriverAll.initDriver(BROWSER, URL);
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
     * Tests the Sign-up functionality.
     */
    @Feature("Sign up flow")
    @Story("Sign up")
    @Description("Test the Sign up functionality")
    @Link("https://automationexercise.com/login")
    @Tag("Sign up")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("Sign up form and verify data")
    @Test(dependsOnMethods = "homePageIsVisible", description = "Testing Sign up functionality", groups = {"regression", "signUp"})
    public void signUp() throws IOException {
        assert actions.doSignUp();
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}
