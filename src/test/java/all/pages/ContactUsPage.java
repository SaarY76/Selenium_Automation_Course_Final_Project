package all.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage{

    public ContactUsPage(WebDriver driver) {
        super(driver, 10);
    }

    public boolean homePageIsVisible() {
        return super.isTabTitleMatch("Automation Exercise");
    }

    public void clickOnContactUsButton() {
        super.click(By.cssSelector("a[href='/contact_us']"));
    }


    public boolean getInTouchTextIsVisible() {
        return super.validateElementExist(By.xpath("//h2[@class='title text-center' and text()='Get In Touch']"));
    }

    public void typeName() {
        super.typeText(By.cssSelector("[data-qa='name']"),"Saar");
    }

    public void typeEmail() {

    }

    public void typeSubject() {

    }

    public void typeMessage() {

    }

}
