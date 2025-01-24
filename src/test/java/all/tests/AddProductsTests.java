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

@Epic("Adding products test Suite")
public class AddProductsTests {
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
     * Tests the add products functionality.
     */
    @Feature("Add products flow")
    @Story("Add products")
    @Description("Test the Add products functionality")
    @Link("https://automationexercise.com/products")
    @Tag("Add products")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("Add products")
    @Test(dependsOnMethods = "homePageIsVisible", description = "Add products functionality", groups = {"regression", "add_products"})
    public void addProducts() throws IOException {
        assert actions.doAddProducts();
    }

    /**
     * Tests there are products in cart
     */
    @Feature("Add products flow")
    @Story("Add products")
    @Description("Test the Add products functionality")
    @Link("https://automationexercise.com/products")
    @Tag("Add products")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("Verifying cart is not empty")
    @Test(dependsOnMethods = "addProducts", description = "Verifying cart is not empty", groups = {"regression", "add_products"})
    public void thereAreProductsInCart() {
        assert actions.doCheckingCartIsNotEmpty();
    }

    /**
     * Tests the total prices of the products
     */
    @Feature("Add products flow")
    @Story("Add products")
    @Description("Test the Add products functionality")
    @Link("https://automationexercise.com/products")
    @Tag("Add products")
    @Owner("Saar, Alina")
    @Severity(SeverityLevel.NORMAL)
    @Step("Verifying the prices, total prices and quantity of the products in cart")
    @Test(dependsOnMethods = "thereAreProductsInCart",
            description = "Verifying the prices, total prices and quantity of the products in cart",
            groups = {"regression", "add_products"})
    public void checkingPricesTotalPricesAndQuantity() {
        assert actions.doCheckingPricesTotalPricesAndQuantity();
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}
