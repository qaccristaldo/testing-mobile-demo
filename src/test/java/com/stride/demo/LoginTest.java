package com.stride.demo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import model.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.GetProperty.getProperties;
import static utils.Utils.forceWait;
import static utils.Utils.getUser;

import org.slf4j.Logger;
import pages.base.BasePage;
import utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class LoginTest {


    private AndroidDriver driver;
    static Logger logger = MyLogger.getLogger();
    User user;
    static BasePage basePage;

    @BeforeEach
    public void setUp() {


        String file = "local.properties";
        URL url;
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(getProperties(file, "automation_name"))
                .setUdid(getProperties(file, "udid"))
                .setAppPackage(getProperties(file, "app_package"))
                .setAppActivity(getProperties(file, "app_activity"))
                //.setNoReset(true)
                .setChromedriverExecutable(getProperties(file, "path_to_chromedriver"));
        try {
            url = new URI(getProperties(file, "appium_server_url")).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        driver = new AndroidDriver(
                url, options
        );
        driver.setSetting("enforceXPath1", true);
        logger.info(driver.toString());
        driver.startRecordingScreen(
                new AndroidStartScreenRecordingOptions()
                        .withVideoSize("1280x720")
                        .withTimeLimit(Duration.ofSeconds(1800)));
        basePage = new BasePage(driver);


    }

    @AfterEach
    public void tearDown() {
        Timestamp stamp = Timestamp.valueOf(LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String timestamp = formatter.format(stamp.toLocalDateTime())
                .replace(":", "-")
                .replace(".", "-");
        String path = String.format("evidence/video/%s.mp4", timestamp);
        forceWait(3000);
        String video = driver.stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(video);
        try {
            FileUtils.writeByteArrayToFile(new File(path), decode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        forceWait(2000);
        driver.quit();
    }


    @Test
    public void loginAsAnLC() {


    }


}
