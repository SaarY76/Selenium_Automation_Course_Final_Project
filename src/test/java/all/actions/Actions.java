package all.actions;

import all.pages.ContactUsPage;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Actions {

    private static final Logger logger = LogManager.getLogger(Actions.class);

    // All pages
    ContactUsPage contactUsPage;
    /**
     * Constructor to initialize the Actions class with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used for interacting with web elements
     */
    public Actions(WebDriver driver) {
        contactUsPage = new ContactUsPage(driver);
    }

    private void delay () {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isHomePageVisible(String text) {
        logger.info("Verifying that " + text + " is visible");
        if (!contactUsPage.homePageIsVisible()) {
            logger.error(text + " is not visible");
            return false;
        }
        return true;
    }

    public boolean doContactUs() throws IOException {
        //Allure.step("Starting to perform Contact Us actions");
        logger.info("Starting to perform Contact Us actions");
        if (isHomePageVisible("Home page")) {
            contactUsPage.clickOnContactUsButton();
            logger.info("Verifying that the text : Get in touch, text is visible");
            if (!contactUsPage.getInTouchTextIsVisible()) {
                logger.error("The text : Get in touch text, is not visible");
                return false;
            }
            logger.info("Typing name");
            contactUsPage.typeName("Saar");
            logger.info("Typing email");
            contactUsPage.typeEmail("saar@gmail.com");
            logger.info("Typing subject");
            contactUsPage.typeSubject("Help");
            logger.info("Typing message");
            contactUsPage.typeMessage("Add to cart is not working");
            logger.info("Uploading file");
            contactUsPage.uploadFile("C:\\Users\\סער\\Personal\\IntelliJ_Workspace\\FinalProject\\src\\main\\resources\\example.txt");
            logger.info("clicking submit");
            contactUsPage.clickSubmit();
            delay();
            contactUsPage.clickOnOkButtonInAlert();
            delay();
            logger.info("Verifying that the text : Success! Your details have been submitted successfully. is visible");
            if (!contactUsPage.elementWithTextSuccessIsVisible()) {
                logger.error("The text : Success! Your details have been submitted successfully. is not visible");
                return false;
            }
            logger.info("clicking on Home page");
            contactUsPage.clickOnHomePage();
            delay();
        }
        return isHomePageVisible("Home page");
    }
}