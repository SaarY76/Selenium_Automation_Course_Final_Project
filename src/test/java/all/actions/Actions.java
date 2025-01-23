package all.actions;

import all.pages.ContactUsPage;
import all.pages.ProductsPage;
import all.pages.SignUpPage;
import all.utils.ExcelUtils;
import all.utils.JsonUtils;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Actions {

    private static final Logger logger = LogManager.getLogger(Actions.class);

    // All pages
    ContactUsPage contactUsPage;
    SignUpPage signUpPage;
    ProductsPage productsPage;
    /**
     * Constructor to initialize the Actions class with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used for interacting with web elements
     */
    public Actions(WebDriver driver) {
        contactUsPage = new ContactUsPage(driver);
        signUpPage = new SignUpPage(driver);
        productsPage = new ProductsPage(driver);
    }

    private void delay () {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isHomePageVisible(String text) {
        if (!contactUsPage.homePageIsVisible()) {
            logger.error("{} is not visible", text);
            return false;
        }
        return true;
    }

    public boolean doContactUs() throws IOException {
        Allure.step("Starting to perform Contact Us actions");
        logger.info("Starting to perform Contact Us actions");

        Allure.step("Clicking on Contact Us button");
        logger.info("Clicking on Contact Us button");
        contactUsPage.clickOnContactUsButton();

        logger.info("Verifying that the text : Get in touch, text is visible");
        if (!contactUsPage.getInTouchTextIsVisible()) {
            logger.error("The text : Get in touch text, is not visible");
            return false;
        }

        Allure.step("Starting to collect data to fill Form from an Excel file");
        logger.info("Starting to collect data to fill Form from an Excel file");
        Object[][] excelData = ExcelUtils.readExcelData(JsonUtils.readJsonFromFile("excel_path"));
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
        return isHomePageVisible("Home page");
    }

    public boolean doSignUp() throws IOException {
        Allure.step("Starting to perform Sign up actions");
        logger.info("Starting to perform Sign up actions");

        Allure.step("Clicking on Sign up button");
        logger.info("Clicking on Sign up button");
        signUpPage.clickOnSignup_LoginButton();

        logger.info("Verifying that the text : New User Signup!, is visible");
        Allure.step("Verifying that the text : New User Signup!, is visible");
        if (!signUpPage.newUserSignupTextIsVisible()) {
            logger.error("The text : New User Signup!, is not visible");
            return false;
        }

        Allure.step("Starting to collect data to fill Form from an Excel file");
        logger.info("Starting to collect data to fill Form from an Excel file");
        Object[][] excelData = ExcelUtils.readExcelData(JsonUtils.readJsonFromFile("excel_path"));
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

    public boolean doViewAndCartBrandProductsFunctionality() throws IOException {
        Allure.step("Starting to perform View & Cart Brand Products actions");
        logger.info("Starting to perform View & Cart Brand Products actions");

        Allure.step("Clicking on Products button");
        logger.info("Clicking on Products button");
        productsPage.clickOnProductsButton();

        logger.info("Verifying that the div : Brands, is visible");
        Allure.step("Verifying that the div : Brands, is visible");
        if (!productsPage.brandsIsVisible()) {
            logger.error("Brands div is not visible");
            return false;
        }

        String firstBrand = "Polo", secondBrand = "Babyhug";

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
}