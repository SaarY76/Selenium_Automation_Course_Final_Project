package all.actions;

import all.pages.ContactUsPage;
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

    public boolean isHomePageVisible(String text) {
        logger.info("Verifying that " + text + " is visible");
        if (!contactUsPage.homePageIsVisible()) {
            logger.error(text + " is not visible");
            return false;
        }
        return true;
    }

    public boolean doContactUs() throws IOException {
        //Allure.step("Starting to perform Contact Us actions");
        Object[][] excelData = ExcelUtils.readExcelData(JsonUtils.readJsonFromFile("excel_path"));
        if (excelData == null || excelData.length == 0) {
            logger.error("Excel data read error or no data found");
            return false;
        }
        logger.info("Starting to perform Contact Us actions");
        if (isHomePageVisible("Home page")) {
            contactUsPage.clickOnContactUsButton();
            logger.info("Verifying that the text : Get in touch, text is visible");
            if (!contactUsPage.getInTouchTextIsVisible()) {
                logger.error("The text : Get in touch text, is not visible");
                return false;
            }
            // collecting data to fill the Form from an Excel file
            String name = (String) excelData[0][0];
            System.out.println(name);
            String email = (String) excelData[1][0];
            System.out.println(email);
            String subject = (String) excelData[2][0];
            System.out.println(subject);
            String message = (String) excelData[3][0];
            System.out.println(message);
            String file = (String) excelData[4][0];
            System.out.println(file);


            logger.info("Typing name : {}", name);
            contactUsPage.typeName(name);
            logger.info("Typing email : {}", email);
            delay();
            contactUsPage.typeEmail(email);
            logger.info("Typing subject : {}", subject);
            contactUsPage.typeSubject(subject);
            logger.info("Typing message : {}", message);
            contactUsPage.typeMessage(message);
            logger.info("Uploading file : {}", file);
            contactUsPage.uploadFile(file);
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