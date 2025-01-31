package all.actions;

import all.pages.*;
import all.utils.ExcelUtils;
import all.utils.JsonUtils;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Actions {

    private final Logger logger;
    private final Object[][] excelData;
    // All pages
    private final HomePage homePage;
    private final ContactUsPage contactUsPage;
    private final SignUpPage signUpPage;
    private final ProductsPage productsPage;
    private final CheckoutPage checkoutPage;

    /**
     * Constructor to initialize the Actions class with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used for interacting with web elements
     */
    public Actions(WebDriver driver) {
        logger = LogManager.getLogger(Actions.class);
        excelData = ExcelUtils.readExcelData(JsonUtils.readJsonFromFile("excel_path"));

        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
        signUpPage = new SignUpPage(driver);
        productsPage = new ProductsPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    private void delay () {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isHomePageVisible() {
        if (!homePage.homePageIsVisible()) {
            logger.error("Home page is not visible");
            return false;
        }
        return true;
    }

    /**
     * The function does all the Contact us functionality
     * @return - True if all the functionality works, and else False
     * @throws IOException - If there is an issue when capturing the screenshot of the web page we are testing
     */
    public boolean doContactUs() throws IOException {
        Allure.step("Starting to perform Contact Us actions");
        logger.info("Starting to perform Contact Us actions");

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        homePage.takeScreenshotOfCurrentPage();

        Allure.step("Clicking on Contact Us button");
        logger.info("Clicking on Contact Us button");
        homePage.clickOnContactUsButton();

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        homePage.takeScreenshotOfCurrentPage();

        logger.info("Verifying that the text : Get in touch, text is visible");
        if (!contactUsPage.getInTouchTextIsVisible()) {
            logger.error("The text : Get in touch text, is not visible");
            return false;
        }

        Allure.step("Starting to collect data to from an Excel file");
        logger.info("Starting to collect data to from an Excel file");
        if (excelData == null || excelData.length == 0) {
            logger.error("Excel data read error or no data found");
            return false;
        }
        // collecting data to fill the Form from an Excel file
        String name = (String) excelData[0][0];
        String email = (String) excelData[1][0];
        String subject = (String) excelData[2][0];
        String message = (String) excelData[3][0];
        String file = (String) excelData[4][0];


        Allure.step("Filling the Form");
        logger.info("Typing name : {}", name);
        contactUsPage.typeName(name);
        logger.info("Typing email : {}", email);
        contactUsPage.typeEmail(email);
        logger.info("Typing subject : {}", subject);
        contactUsPage.typeSubject(subject);
        logger.info("Typing message : {}", message);
        contactUsPage.typeMessage(message);
        logger.info("Uploading file : {}", file);
        contactUsPage.uploadFile(file);

        Allure.step("clicking submit");
        logger.info("clicking submit");
        contactUsPage.clickSubmit();

        Allure.step("clicking OK in alert");
        logger.info("clicking OK in alert");
        contactUsPage.clickOnOkButtonInAlert();

        Allure.step("Verifying that the text : Success! Your details have been submitted successfully. is visible");
        logger.info("Verifying that the text : Success! Your details have been submitted successfully. is visible");
        if (!contactUsPage.elementWithTextSuccessIsVisible()) {
            logger.error("The text : Success! Your details have been submitted successfully. is not visible");
            return false;
        }
        Allure.step("clicking on Home page");
        logger.info("clicking on Home page");
        contactUsPage.clickOnHomePage();

        Allure.step("Verifying that Home page is visible");
        logger.info("Verifying that Home page is visible");
        return isHomePageVisible();
    }

    /**
     * The function does all the Sign-up functionality
     * @return - True if all the functionality works, and else False
     * @throws IOException - If there is an issue when capturing the screenshot of the web page we are testing
     */
    public boolean doSignUp() throws IOException {
        Allure.step("Starting to perform Sign up actions");
        logger.info("Starting to perform Sign up actions");

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        homePage.takeScreenshotOfCurrentPage();

        Allure.step("Clicking on Sign up button");
        logger.info("Clicking on Sign up button");
        homePage.clickOnSignup_LoginButton();

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        signUpPage.takeScreenshotOfCurrentPage();

        logger.info("Verifying that the text : New User Signup!, is visible");
        Allure.step("Verifying that the text : New User Signup!, is visible");
        if (!signUpPage.newUserSignupTextIsVisible()) {
            logger.error("The text : New User Signup!, is not visible");
            return false;
        }

        Allure.step("Starting to collect data from an Excel file");
        logger.info("Starting to collect data from an Excel file");
        if (excelData == null || excelData.length == 0) {
            logger.error("Excel data read error or no data found");
            return false;
        }
        // collecting data to fill the Form from an Excel file
        String name = (String) excelData[0][0];
        String email = (String) excelData[1][0];

        Allure.step("Filling the Form");
        logger.info("Typing name : {}", name);
        signUpPage.typeName(name);
        logger.info("Typing email : {}", email);
        signUpPage.typeEmail(email);

        Allure.step("clicking submit");
        logger.info("clicking submit");
        signUpPage.clickingSignUpSubmit();

        Allure.step("Verifying that the error message : Email Address already exist! is visible");
        logger.info("Verifying that the error message : Email Address already exist! is visible");
        return signUpPage.errorMessageVisible();
    }

    /**
     * The function does all the View & Cart Brand Products functionality
     * @return - True if all the functionality works, and else False
     * @throws IOException - If there is an issue when capturing the screenshot of the web page we are testing
     */
    public boolean doViewAndCartBrandProductsFunctionality() throws IOException {
        Allure.step("Starting to perform View & Cart Brand Products actions");
        logger.info("Starting to perform View & Cart Brand Products actions");

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        homePage.takeScreenshotOfCurrentPage();

        Allure.step("Clicking on Products button");
        logger.info("Clicking on Products button");
        homePage.clickOnProductsButton();

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        productsPage.takeScreenshotOfCurrentPage();

        logger.info("Verifying that the div : Brands, is visible");
        Allure.step("Verifying that the div : Brands, is visible");
        if (!productsPage.brandsIsVisible()) {
            logger.error("Brands div is not visible");
            return false;
        }

        Allure.step("Starting to collect data  from an Excel file");
        logger.info("Starting to collect data from an Excel file");
        if (excelData == null || excelData.length == 0) {
            logger.error("Excel data read error or no data found");
            return false;
        }

        String firstBrand = (String)excelData[0][1], secondBrand = (String)excelData[1][1];

        Allure.step("Clicking on "+ firstBrand +" button");
        logger.info("Clicking on "+ firstBrand +" button");
        productsPage.clickOnSpecificBrand(firstBrand);

        logger.info("Verifying that the text : " + firstBrand + ", is visible");
        Allure.step("Verifying that the text : " + firstBrand + ", is visible");
        if (!productsPage.verifyBrandChosen(firstBrand)) {
            logger.error("The text : " + firstBrand + "is not visible");
            return false;
        }

        Allure.step("Clicking on "+ secondBrand +" button");
        logger.info("Clicking on "+ secondBrand +" button");
        productsPage.clickOnSpecificBrand(secondBrand);

        logger.info("Verifying that the text : " + secondBrand + ", is visible");
        Allure.step("Verifying that the text : " + secondBrand + ", is visible");
        if (!productsPage.verifyBrandChosen(secondBrand)) {
            logger.error("The text : " + secondBrand + "is not visible");
            return false;
        }

        return true;
    }

    public boolean doAddProducts() throws IOException {
        Allure.step("Starting to perform add Products actions");
        logger.info("Starting to perform add Products actions");

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        homePage.takeScreenshotOfCurrentPage();

        Allure.step("Clicking on Products button");
        logger.info("Clicking on Products button");
        homePage.clickOnProductsButton();

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        productsPage.takeScreenshotOfCurrentPage();

        Allure.step("Scrolling to first product");
        logger.info("Scrolling to first product");
        productsPage.scrollToElement(1);

        Allure.step("Hovering on first product");
        logger.info("Hovering on first product");
        productsPage.hoverOnElement(1);

        Allure.step("Clicking on add to cart on first product");
        logger.info("Clicking on add to cart on first product");
        productsPage.clickOnAddProductToCart(1);

        productsPage.clickOnContinueShopping();

        Allure.step("Scrolling to second product");
        logger.info("Scrolling to second product");
        productsPage.scrollToElement(2);

        Allure.step("Hovering on second product");
        logger.info("Hovering on second product");
        productsPage.hoverOnElement(2);

        Allure.step("Clicking on add to cart on second product");
        logger.info("Clicking on add to cart on second product");
        productsPage.clickOnAddProductToCart(2);

        Allure.step("Clicking on view cart");
        logger.info("Clicking on view cart");
        productsPage.clickOnViewCart();

        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        checkoutPage.takeScreenshotOfCurrentPage();

        return true;
    }

    public boolean doCheckingCartIsNotEmpty() {
        Allure.step("Verifying that the cart is not empty");
        logger.info("Verifying that the cart is not empty");
        if (!checkoutPage.verifyThereAreProductsInCart()) {
            Allure.step("The cart is empty");
            logger.info("The cart is empty");
            return false;
        }
        return true;
    }

    public boolean doCheckingOfQuantity() {
        Allure.step("Starting to collect data from an Excel file");
        logger.info("Starting to collect data from an Excel file");
        if (excelData == null || excelData.length == 0) {
            logger.error("Excel data read error or no data found");
            return false;
        }

        int expectedNumberOfProducts = (int) Double.parseDouble((String) excelData[0][3]);
        Allure.step("Verifying that the two products in cart");
        logger.info("Verifying that the two products in cart");
        if (checkoutPage.numberOfProductsWithSameLocator() != expectedNumberOfProducts) {
            Allure.step("There are less products in cart then two");
            logger.info("There are less products in cart then two");
            return false;
        }
        return true;
    }

    public boolean doCheckingOfPrices() {
        Allure.step("Starting to collect data from an Excel file");
        logger.info("Starting to collect data from an Excel file");
        if (excelData == null || excelData.length == 0) {
            logger.error("Excel data read error or no data found");
            return false;
        }
        List<Double> expectedTotalPrices = new ArrayList<>();
        expectedTotalPrices.add(Double.parseDouble((String) excelData[0][2]));
        expectedTotalPrices.add(Double.parseDouble((String) excelData[1][2]));
        Allure.step("Verifying the prices of the products in cart");
        logger.info("Verifying the prices of the products in cart");
        List<Double> prices = checkoutPage.getPricesOfProductsInCart();
        for (int i=0; i< prices.size(); i++) {
            if (prices.get(i).doubleValue() != expectedTotalPrices.get(i).doubleValue()) {
                Allure.step("One of the prices "+prices.get(i)+" is not equal to the actual price " + expectedTotalPrices.get(i));
                logger.info("One of the prices "+prices.get(i)+" is not equal to the actual price " + expectedTotalPrices.get(i));
                return false;
            }
        }
        return true;
    }

    public boolean doCheckingOfTotalPrice() {
        Double firstProductPrice = Double.parseDouble((String) excelData[0][2]), secondProductPrice = Double.parseDouble((String) excelData[1][2]);
        double expectedTotalPrice = firstProductPrice + secondProductPrice, totalPrice = 0;
        Allure.step("Verifying the total price of the products in cart");
        logger.info("Verifying the total price of the products in cart");
        List<Double> prices = checkoutPage.getPricesOfProductsInCart();
        for (Double price : prices) {
            totalPrice += price;
        }
        if (expectedTotalPrice != totalPrice) {
            Allure.step("The total price "+expectedTotalPrice+" is not equal to the expected total price" + totalPrice);
            logger.info("The total price "+expectedTotalPrice+" is not equal to the expected total price" + totalPrice);
            return false;
        }
        return true;
    }

    public boolean doCheckingPricesTotalPricesAndQuantity() {
        return doCheckingOfPrices() && doCheckingOfQuantity() && doCheckingOfTotalPrice();
    }

    public boolean doScrollUp() throws IOException {
        Allure.step("Taking a screenshot of current page");
        logger.info("Taking a screenshot of current page");
        homePage.takeScreenshotOfCurrentPage();

        Allure.step("Scrolling to the bottom of the page");
        logger.info("Scrolling to the bottom of the page");
        homePage.scrollToTheBottomOfPage();
        delay();

        Allure.step("Verifying that 'SUBSCRIPTION' is visible");
        logger.info("Verifying that 'SUBSCRIPTION' is visible");
        if (!homePage.verifyTextAtBottomOfPage()) {
            Allure.step("'SUBSCRIPTION' is not visible");
            logger.info("'SUBSCRIPTION' is not visible");
            return false;
        }

        Allure.step("Scrolling to the top of the page");
        logger.info("Scrolling to the top of the page");
        homePage.scrollToTheTopOfPage();
        delay();

        Allure.step("Verifying that 'Full-Fledged practice website for Automation Engineers' is visible");
        logger.info("Verifying that 'Full-Fledged practice website for Automation Engineers' is visible");
        if (!homePage.verifyTextAtTopOfPage()) {
            Allure.step("'Full-Fledged practice website for Automation Engineers' is not visible");
            logger.info("'Full-Fledged practice website for Automation Engineers' is not visible");
            return false;
        }

        return true;
    }
}