package all.actions;

import all.pages.ContactUsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

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

    public boolean doContactUs() {
        logger.info("Starting to perform Contact Us actions");
        if (!contactUsPage.homePageIsVisible()) {
            logger.error("Starting to perform Contact Us actions");
            return false;
        }
        contactUsPage.clickOnContactUsButton();
        if (!contactUsPage.getInTouchTextIsVisible()) {
            return false;
        }
        return true;
    }
}