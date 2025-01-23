package all.pages;

import all.utils.ScreenshotsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class SignUpPage extends BasePage{

    public SignUpPage(WebDriver driver) {
        super(driver, 10);
    }

    public void clickOnSignup_LoginButton() throws IOException {
        super.click(By.cssSelector("a[href='/login']"));
        ScreenshotsUtils.takeFullPageScreenshot(driver);
    }

    public boolean newUserSignupTextIsVisible() {
        return super.validateElementExist(By.xpath("//h2[text()='New User Signup!']"));
    }

    public void typeName(String name) {
        super.typeText(By.cssSelector("[data-qa='signup-name']"), name);
    }

    public void typeEmail(String email) {
        super.typeText(By.cssSelector("[data-qa='signup-email']"), email);
    }

    public void clickingSignUpSubmit() {
        super.clickSubmit(By.cssSelector("[data-qa='signup-button']"));
    }

    public boolean errorMessageVisible() {
        return super.validateElementExist(By.xpath("//p[contains(@style, 'color: red')]"));
    }

}
