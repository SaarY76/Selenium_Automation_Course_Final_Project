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

@Epic("View & Cart Brand Products test Suite")
public class ViewCartBrandsProductsTests {
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
        assert  actions.isHomePageVisible(" Home");
    }

    /**
     * Tests the View & Cart Brand Products functionality.
     */
    @Feature("View & Cart Brand Products flow")
    @Story("View & Cart Brand Products")
    @Description("Test the View & Cart Brand Products functionality")
    @Link("https://automationexercise.com/products")
    @Tag("View & Cart Brand Products")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("View & Cart Brand Products")
    @Test(dependsOnMethods = "homePageIsVisible", description = "View & Cart Brand Products functionality", groups = {"regression", "view_and_cart_brand_products"})
    public void viewAndCartBrandProducts() throws IOException {
        assert actions.doViewAndCartBrandProductsFunctionality();
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}
