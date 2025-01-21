package all.pages;

import all.utils.ScreenshotsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ContactUsPage extends BasePage{

    public ContactUsPage(WebDriver driver) {
        super(driver, 10);
    }

    public boolean homePageIsVisible() {
        return super.isTabTitleMatch("Automation Exercise");
    }

    public void clickOnContactUsButton() throws IOException {
        super.click(By.cssSelector("a[href='/contact_us']"));
        ScreenshotsUtils.takeFullPageScreenshot(driver);
    }


    public boolean getInTouchTextIsVisible() {
        return super.validateElementExist(By.xpath("//h2[@class='title text-center' and text()='Get In Touch']"));
    }

    public void typeName(String name) {
        super.typeText(By.cssSelector("[data-qa='name']"), name);
    }

    public void typeEmail(String email) {
        super.typeText(By.cssSelector("[data-qa='email']"), email);
    }

    public void typeSubject(String subject) {
        super.typeText(By.cssSelector("[data-qa='subject']"), subject);
    }

    public void typeMessage(String message) {
        super.typeText(By.cssSelector("[data-qa='message']"), message);
    }

    public void uploadFile(String filePath) {
        super.uploadFile(By.name("upload_file"), filePath);
    }

    public void clickSubmit() {
        super.clickSubmit(By.cssSelector("[data-qa='submit-button']"));
    }

    public void clickOnOkButtonInAlert() {
        super.clickOkButtonInAlert();
    }

    public boolean elementWithTextSuccessIsVisible() {
        return super.validateElementExist(By.className("status"));
    }

    public void clickOnHomePage() {
        super.click(By.xpath("//a[text()=' Home']"));
    }

}
