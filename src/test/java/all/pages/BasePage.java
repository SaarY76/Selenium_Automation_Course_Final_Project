package all.pages;

import all.utils.ScreenshotsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;


    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver  the WebDriver instance
     * @param timeout the timeout duration in seconds
     */
    public BasePage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        actions = new Actions(driver);
    }

    /**
     * Clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be clicked
     */
    public void click(By locator) {
        logger.info("Going to click WebElement: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.click();
    }

    /**
     * Clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be clicked
     */
    public void clickOnElement(By locator) {
        logger.info("Going to click WebElement: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }


    /**
     * Types the given text into the element located by the given locator.
     *
     * @param locator the By locator of the element
     * @param text    the text to be typed
     */
    public void typeText(By locator, String text) {
        logger.info("Going to send keys to WebElement: {} {}", locator, text);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Validates if elements exist for the given locator.
     *
     * @param locator the By locator of the elements
     * @return the boolean value of elements found
     */
    public boolean validateElementExist(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }

    /**
     * Getting the number of elements with the same locator
     *
     * @param locator the By locator of the elements
     * @return the int value of elements found
     */
    public int numberOfElementsWithSameLocator(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        List<WebElement> elements = driver.findElements(locator);
        return elements.size();
    }

    /**
     * Getting the elements with the same locator
     *
     * @param locator the By locator of the elements
     * @return the List of elements found
     */
    public List<WebElement> elementsWithSameLocator(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        return driver.findElements(locator);
    }

    /**
     * Checks if the current tab title matches the given title.
     *
     * @param title the expected title of the tab
     * @return true if the tab title matches, false otherwise
     */
    public boolean isTabTitleMatch(String title) {
        return driver.getTitle().equals(title);
    }


    /**
     * Retrieves the text content of the element located by the given locator.
     *
     * @param locator the By locator of the element
     * @return the text content of the element
     */
    public String getElementText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    /**
     * Double-clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be double-clicked
     */
    public void doubleClickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        actions.doubleClick(element).perform();
    }


    /**
     * Scrolls the specified element into view using JavaScript.
     *
     * @param locator The locator used to find the element to scroll into view.
     */
    public void scrollToElement(By locator) {
        // Find the element using the provided locator
        WebElement element = driver.findElement(locator);

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls the to the bottom of the web page
     */
    public void scrollToBottom() {
        // Use JavaScript to scroll to the bottom of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scrolls the to the top of the web page
     */
    public void scrollToTop() {
        // Use JavaScript to scroll to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Uploads a file to an input element.
     *
     * @param locator  The locator used to find the file input element.
     * @param filepath The absolute path to the file to be uploaded.
     */
    public void uploadFile(By locator, String filepath) {
        logger.info("Going to upload a file to WebElement: {} {}", locator, filepath);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement fileInput = driver.findElement(locator);
        fileInput.sendKeys(filepath);
    }

    /**
     * Finds a submit button and submits the associated form.
     *
     * @param locator The locator used to find the submit button element.
     */
    public void clickSubmit(By locator) {
        logger.info("Going to click submit in WebElement: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement submitButton = driver.findElement(locator);
        submitButton.submit();
    }


    /**
     * Handles an alert popup by clicking the "OK" button when two buttons are visible.
     */
    public void clickOkButtonInAlert() {
        // Wait for the alert to be present
        logger.info("Going to click on OK button in Alert dialog");
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());

        // Accept the alert by clicking "OK"
        alert.accept();
    }

    /**
     * The function does hover on a specific web element using a locator
     * @param locator - The locator used to find the submit button element.
     */
    public void hoverOnElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement webElement = driver.findElement(locator);

        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    public void takeScreenshotOfCurrentPage() throws IOException {
        ScreenshotsUtils.takeFullPageScreenshot(driver);
    }

}