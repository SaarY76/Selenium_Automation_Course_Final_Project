package all.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotsUtils {

    private static final String SCREENSHOT_DIR = "screenshots";

    public static void takeFullPageScreenshot(WebDriver driver) throws IOException {
        // Capturing the screenshot of the entire page
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Using the driver's title as the filename (sanitize for file system safety)
        String sanitizedTitle = driver.getTitle().replaceAll("[^a-zA-Z0-9-_\\.]", "_") + ".png";

        // Ensure the directory exists
        Path directoryPath = Paths.get(SCREENSHOT_DIR);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Saving the screenshot
        Path destinationFilePath = FileSystems.getDefault().getPath(SCREENSHOT_DIR, sanitizedTitle);
        Files.copy(screenshotFile.toPath(), destinationFilePath);

        System.out.println("Screenshot saved to: " + destinationFilePath.toString());
    }


}
