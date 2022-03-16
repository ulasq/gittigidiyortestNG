package gittiGidiyorPages;

import static org.testng.AssertJUnit.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author ulas.dikengul
 */
public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private BaseFunctions bf;
    Logger log = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 90);
        bf = new BaseFunctions(driver);
    }

    @FindBy(xpath = "//*[text()='BUL']/preceding::input[1]")
    private WebElement productSearch;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "(//*[text()='Çerez Politikası']/following::span[1])[1]")
    private WebElement closePrivacy;

    @FindBy(xpath = "//*[@title='2. sayfa']")
    private WebElement secondPage;

    @FindBy(xpath = "(//ul[@class='clearfix'])[2]//following::li[2]")
    private WebElement secondPageClick;

    @FindBy(xpath = "//*[text()='Akıllı Sıralama']/following::li[1]")
    private WebElement firstProduct;

    @FindBy(id = "sp-addbasket-button")
    private WebElement addChart;

    @FindBy(xpath = "//span[text()='Kapat']")
    private WebElement closedPrivacy;

    @FindBy(xpath = "//*[text()='Öne Çıkan Taksitler']/preceding::div[3]")
    private WebElement prodPrice;

//------------------------------------------methods----------------------------------------------------------------

    public String urunArama(String searchValue) throws InterruptedException {
        bf.write(productSearch, searchValue);
        bf.clicke(searchButton);
        bf.clicke(closePrivacy);
        bf.scrollDown();
        //bf.moveToElement(secondPage, driver);
        bf.clicke(secondPage);
        String currentStatus = secondPage.getAttribute("class");
        log.info(currentStatus);
        assertEquals("2. Sayfa Acilamadi", currentStatus, "sc-12aj18f-0 glRpjV");
        bf.clicke(firstProduct);
        String prodPriceValue = prodPrice.getText().replaceAll(" TL", "");
        log.info("Urunlerim Sayfa Fiyati=" + prodPriceValue);
        bf.clicke(closedPrivacy);
        bf.clicke(addChart);
        log.info("Product Process is Done");
        return prodPriceValue;

    }
}