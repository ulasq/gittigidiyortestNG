package testSuites;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import base.Base;
import gittiGidiyorPages.ChartPage;
import gittiGidiyorPages.LoginPage;
import gittiGidiyorPages.ProductPage;

/**
 * @author ulas.dikengul
 */

public class gittiGidiyorSuite extends Base {

    Logger log = LogManager.getLogger(gittiGidiyorSuite.class);

    @Test()
    @Parameters({"username", "password", "searchValue"})
    public void UrunSepetTest(String username, String password, String searchValue) throws InterruptedException {

        LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
        ProductPage pp = PageFactory.initElements(driver, ProductPage.class);
        ChartPage cp = PageFactory.initElements(driver, ChartPage.class);

        lp.login(username, password, environment);
        log.info("Login Website");
        cp.chartProcess(pp.urunArama(searchValue));
        log.info("Test is Done");

    }

}
