package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages WebDriver instances per thread (thread-safe for parallel execution).
 * Supports Chrome and Firefox, headless mode for CI.
 */
public class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driverThread.get() == null) {
            initDriver();
        }
        return driverThread.get();
    }

    public static void initDriver() {
        String browser = ConfigManager.get().browser().toLowerCase();
        boolean headless = ConfigManager.get().headless();

        log.info("Initializing {} driver (headless={})", browser, headless);

        WebDriver driver = switch (browser) {
            case "firefox" -> createFirefoxDriver(headless);
            default        -> createChromeDriver(headless);
        };

        driver.manage().window().maximize();
        driverThread.set(driver);
        log.info("Driver initialized successfully");
    }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
            log.info("Driver closed");
        }
    }

    // ── Private factory methods ───────────────────────────────────────────────

    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
        if (headless) options.addArguments("--headless=new");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("--headless");
        return new FirefoxDriver(options);
    }
}
