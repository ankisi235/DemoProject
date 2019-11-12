package com.demo.utilities;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.relevantcodes.extentreports.LogStatus;
import org.hamcrest.Condition;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtils {

    public static WebDriver driver;
    public static String currentDirectory = System.getProperty("user.dir");

    public static void initDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", currentDirectory + "\\SeleniumWebDrivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            System.out.println("Driver not found or init failed" + e.getMessage() + e.getStackTrace());
        }
    }

    /**
     * This method initializes webdriver
     */
    public static void initMobileDriver(String agent, int width, int height) {
        try {
            System.setProperty("webdriver.chrome.driver", currentDirectory + "\\SeleniumWebDrivers\\chromedriver.exe");
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", width);
            deviceMetrics.put("height", height);
            // deviceMetrics.put("pixelRatio", 3.0);
            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            //mobileEmulation.put("deviceName", "Nexus 5");
            mobileEmulation.put("userAgent", agent);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
            driver = new ChromeDriver(options);
        } catch (
                Exception e) {
            System.out.println("Driver not found or init failed" + e.getMessage() + e.getStackTrace());
        }

    }

    /**
     * Select option by visible text from Select dropdown
     *
     * @param element dropdown webelement
     * @param option  visible text to be selected
     */
    public static void selectFromDropdown(WebElement element, String option) {
        Select select = new Select(element);
        select.selectByVisibleText(option);
    }

    public static void selectFromRadioGroup(By locator, String option, By childLocator) {
        List<WebElement> list = driver.findElements(locator);
        for (WebElement item : list) {
            if (item.getText() != "" && item.getText().contains(option)) {
                if (childLocator != null) {
                    click(item.findElement(childLocator));
                }
                click(item);
            }
        }
    }

    public static void selectCheckBox(By chkBox) {
        try {
            driver.findElement(chkBox).click();
        } catch (Exception e) {
            System.out.println("checkbox click failed");
        }

    }

    /**
     * This method launches url and maximizes the screen
     *
     * @param url      - page url
     * @param pageName - name of the page to be loaded
     * @return title of the page
     */
    public static String launchURL(String url, String pageName) {
        try {
            driver.get(url);
            driver.manage().window().maximize();
            //ExtentReportNG.logTest(LogStatus.INFO, "application launch", pageName);
            return driver.getTitle();
        } catch (Exception e) {
            // ExtentReportNG.logTest(LogStatus.FAIL, "Issue in launching the application", "LaunchError");
            return null;
        }
    }

    /**
     * wait and finds webelement of the web page by locators
     *
     * @param locator: locator of the element to be clicked
     * @return WebElement
     */
    public static WebElement find(By locator) {
        try {
            Wait wait = new FluentWait(driver).withTimeout(20, SECONDS)
                    .pollingEvery(2, SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NullPointerException.class)
                    .ignoring(ElementNotVisibleException.class);

            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator);
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in finding the element" + e.getMessage());
        }
        return null;
    }

    public static void selectFirstItemFromList(By listLocator) {
        try {
            List<WebElement> list = driver.findElements(listLocator);
            click(list.get(0));
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in the list" + e.getMessage());
        }
    }

    /**
     * clicks on the element and handles exceptions
     *
     * @param webElement: webelement to be clicked
     * @return same webElement
     */
    public static WebElement click(WebElement webElement) {
        try {
            Wait wait = new FluentWait(driver).withTimeout(20, SECONDS)
                    .pollingEvery(2, SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(WebDriverException.class)
                    .ignoring(NullPointerException.class);

            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            return webElement;
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in clicking the element" + e.getMessage());
        }
        return null;
    }

    /**
     * gets text of the webelement
     *
     * @return Text of the webelement
     * @param- locator: of the webelement which text has to be extracted
     */
    public static String getText(By locator) {
        try {
            Wait wait = new FluentWait(driver).withTimeout(10, SECONDS)
                    .pollingEvery(2, SECONDS)
                    .ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return find(locator).getText();
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in getting text " + e.getMessage());
            return null;
        }
    }

    /**
     * clears text of the webelement and then enters input
     *
     * @param webElement webElement in which value has to be entered
     * @param value      text to be entered
     * @return same webElement
     */
    public static WebElement input(WebElement webElement, String value) {
        try {
            webElement.clear();
            webElement.sendKeys(value);
            return webElement;
        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in inserting text" + e.getMessage());
            return null;
        }
    }

    /**
     * mouse hovers over the element
     *
     * @param webElement
     */
    public static void mouseHover(WebElement webElement) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(webElement).build().perform();

        } catch (Exception e) {
            ExtentReportNG.logTest(LogStatus.INFO, "Exception caught in mouse hover" + e.getMessage());
        }
    }

    /**
     * generate random string for data
     *
     * @param length
     * @param includeLetters
     * @param includeNumbers
     */
    public static String genRandomly(int length, boolean includeLetters, boolean includeNumbers) {
        return RandomStringUtils.random(length, includeLetters, includeNumbers);
    }

    /**
     * closes browser window
     */
    public static void closeDriver() {
        driver.close();

    }
    /**
     * closes browser windows
     */
    public static void quitDriver() {
        driver.close();
        driver.quit();
    }

    /**
     * kills driver instance in task manager
     *
     * @author Ankita Singh
     */
    public static void killDriver() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static void scrollUp(){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
}
