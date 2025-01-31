package all.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BasePage{

    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver the WebDriver instance
     */
    public CheckoutPage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * The function checks if there is at least one line of products where the products are in the cart
     * @return - True if there is at least one tr element in the tbody where the products are in the page
     */
    public boolean verifyThereAreProductsInCart() {
        return super.validateElementExist(By.cssSelector("tbody tr"));
    }

    public int numberOfProductsWithSameLocator() {
        return super.numberOfElementsWithSameLocator(By.cssSelector("tbody tr"));
    }

    /**
     * The function collects the prices of the products in the cart
     * @return - List of Doubles of the prices of the products in the cart
     */
    public List<Double> getPricesOfProductsInCart() {
        List<WebElement> pElements = super.elementsWithSameLocator(By.className("cart_price"));
        List<Double> prices = new ArrayList<>();
        for (WebElement pWebElement : pElements) {
            String priceString = pWebElement.getText().split(" ")[1]; // Extract the part after "Rs."
            priceString = priceString.replaceAll("[^0-9]", ""); // Remove non-numeric characters
            double price = Double.parseDouble(priceString);
            prices.add(price);
        }
        return prices;
    }
}
