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
public class ChartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private BaseFunctions bf;
    ProductPage pp;
    Logger log = LogManager.getLogger(ChartPage.class);

    public ChartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 90);
        bf = new BaseFunctions(driver);
        this.pp = new ProductPage(driver);
    }

    @FindBy(xpath = "//*[text()='Sepete Git']")
    private WebElement chartButton;

    @FindBy(xpath = "//*[text()='Sepetim']")
    private WebElement myChart;

    @FindBy(xpath = "//*[@class='total-price']")
    private WebElement chartPrice;

    @FindBy(xpath = "//*[@class='amount']")
    private WebElement plusButton;

    @FindBy(xpath = "//*[@class='amount']")
    private WebElement quantityValue;

    @FindBy(xpath = "(//*[text()='Sil'])[1]")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[@class='btn-delete btn-update-item']")
    private WebElement trash;

    @FindBy(xpath = "//h2[text()='Sepetinizde ürün bulunmamaktadır.']")
    private WebElement chartIsEmpty;

//------------------------------------------methods----------------------------------------------------------------

    public void chartProcess(String prodPriceValue) throws InterruptedException {
        bf.hoverElement(myChart, driver);
        bf.clicke(chartButton);
        String chartPriceValue = chartPrice.getText().replaceAll(" TL", "");
        log.info("Sepet Sayfa Fiyati=" + chartPriceValue);
        assertEquals("Fiyatlar Esit Degil", prodPriceValue, chartPriceValue);
        bf.clicke(plusButton);
        bf.selectOptionMethod(plusButton, "2", driver);
        String quantity = quantityValue.getAttribute("value");
        log.info("Urun adedi=" + quantity);
        assertEquals("2 Adet Urun Bulunmamaktadir", quantity, "2");
        bf.hoverElement(trash, driver);
        bf.clicke(deleteButton);
        Thread.sleep(1000);
        String chartIsEmptyLabel = chartIsEmpty.getText();
        log.info(chartIsEmptyLabel);
        assertEquals("Sepetteki Urun Silinemedi", chartIsEmptyLabel, "Sepetinizde ürün bulunmamaktadır.");
        log.info("Chart Process is Done");
    }
}