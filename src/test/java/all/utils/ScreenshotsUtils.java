package all.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        // Construct the destination file path
        Path destinationFilePath = FileSystems.getDefault().getPath(SCREENSHOT_DIR, sanitizedTitle);

        // Check if the file not exists and if so it adds it to the directory
        if (!Files.exists(destinationFilePath)) {
            // Save the new screenshot
            Files.copy(screenshotFile.toPath(), destinationFilePath);
        }

        // Attach the screenshot to Allure report
        try (InputStream is = Files.newInputStream(destinationFilePath)) {
            Allure.addAttachment(sanitizedTitle, "image/png", is, "png");
        }

        System.out.println("Screenshot saved to: " + destinationFilePath.toString());
    }

}
