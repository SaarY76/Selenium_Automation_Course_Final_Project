package all.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HomePage extends BasePage{
    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver the WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver, 10);
    }

    public boolean homePageIsVisible() {
        return super.validateElementExist(By.xpath("//a[contains(@style, 'color: orange') and contains(text(), ' Home')]"));
    }

    public void clickOnSignup_LoginButton() {
        super.click(By.cssSelector("a[href='/login']"));
    }

    public void clickOnContactUsButton() {
        super.click(By.cssSelector("a[href='/contact_us']"));
    }

    public void clickOnProductsButton() {
        super.click(By.cssSelector("a[href='/products']"));
    }

    public void scrollToTheBottomOfPage() {
        super.scrollToBottom();
    }

    public boolean verifyTextAtBottomOfPage() {
        return super.validateElementExist(By.xpath("//h2[text()='Subscription']"));
    }

    public void scrollToTheTopOfPage() {
        super.scrollToTop();
    }

    public boolean verifyTextAtTopOfPage() {
        return super.validateElementExist(By.xpath("//h2[text()='Full-Fledged practice website for Automation Engineers']"));
    }

}
