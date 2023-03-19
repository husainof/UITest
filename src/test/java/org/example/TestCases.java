package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
public class TestCases {
    public static LoginPage loginPage;
    public static CartPage cartPage;
    public static InventoryPage inventoryPage;
    public static CheckoutPage checkoutPage;
    public static WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeAll
    public static void setup() {

        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",path+"\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    @Test
    public void successOrderTest() {

        driver.get(ConfProperties.getProperty("login-page"));

        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));

        loginPage.clickLoginBtn();

        inventoryPage.addToCart();
        inventoryPage.toCart();

        cartPage.pressCheckout();

        checkoutPage.inputFirstName(ConfProperties.getProperty("first-name"));
        checkoutPage.inputLastName(ConfProperties.getProperty("last-name"));
        checkoutPage.inputZipCode(ConfProperties.getProperty("zip-code"));

        checkoutPage.pressContinue();
        checkoutPage.pressFinish();

        String url = checkoutPage.getUrl();
        String thanks = checkoutPage.getThankMessage();

        Assertions.assertEquals(ConfProperties.getProperty("success-page"), url);
        Assertions.assertEquals(ConfProperties.getProperty("thanks-message"), thanks);

    }
    @Test
    public void invalidLoginTest() {
        driver.get(ConfProperties.getProperty("login-page"));

        loginPage.inputLogin(ConfProperties.getProperty("invalid-login"));
        loginPage.inputPassword(ConfProperties.getProperty("invalid-password"));
        loginPage.clickLoginBtn();

        Assertions.assertEquals(ConfProperties.getProperty("error-message"), loginPage.getErrorMessage());
    }
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
