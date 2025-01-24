package all.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver) {
        super(driver, 10);
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

    public void scrollToElement(int number) {
        super.scrollToElement(By.xpath("//img[@src='/get_product_picture/" + number + "']"));
    }

    public void hoverOnElement(int number) {
        super.hoverOnElement(By.xpath("//img[@src='/get_product_picture/" + number + "']"));
    }

    public void clickOnAddProductToCart(int number) {
        super.clickOnElement(By.cssSelector("a[data-product-id='" + number + "']"));
    }

    public void clickOnContinueShopping() {
        super.click(By.xpath("//button[text()='Continue Shopping']"));
    }

    public void clickOnViewCart() {
        super.clickOnElement(By.linkText("View Cart"));
    }

}
