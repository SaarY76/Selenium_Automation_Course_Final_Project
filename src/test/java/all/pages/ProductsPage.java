package all.pages;

import all.utils.ScreenshotsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver, 10);
    }

    public void clickOnProductsButton() throws IOException {
        super.click(By.cssSelector("a[href='/products']"));
        ScreenshotsUtils.takeFullPageScreenshot(driver);
    }

    public boolean brandsIsVisible() {
        return super.validateElementExist(By.className("brands-name"));
    }

    public void clickOnSpecificBrand(String brandName) {
        super.click(By.cssSelector("a[href='/brand_products/" + brandName + "']"));
    }

    public boolean verifyBrandChosen(String brandName) {
        return super.validateElementExist(By.xpath("//h2[@class='title text-center' and text()='Brand - " + brandName + " Products']"));
    }

}
