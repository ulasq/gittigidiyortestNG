package gittiGidiyorPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

import java.util.Random;

/**
 * @author ulas.dikengul
 */
public class BaseFunctions extends Base {

    Logger log = LogManager.getLogger(BaseFunctions.class);

    private WebDriver driver;
    private WebDriverWait wait;

    public BaseFunctions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 45);
    }


    public void clicke(WebElement element) throws InterruptedException {
        Thread.sleep(1500);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void write(WebElement element, String text) throws InterruptedException {
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void scrollDown() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void scrollUp() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,-document.body.scrollHeight)");

    }

    public void waitElement(WebElement element) throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void ScrollElement(WebElement element, WebDriver driver) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);

    }

    public void hoverElement(WebElement element, WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void moveToElement(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
    }

    public void selectOptionMethod(WebElement element, String value, WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        Select selectOption = new Select(element);
        selectOption.selectByValue(value);
        Thread.sleep(2000);

    }

    public int randomGenerator(){
        Random rand = new Random();
        int upperbound = 6;
        //generate random values from 0-6
        int int_random = rand.nextInt(upperbound);
        log.info(int_random);
        return int_random;
    }
}
