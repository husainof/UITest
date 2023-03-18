package org.example;

import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
public class TestCase {
    public static LoginPage loginPage;
    public static CartPage cartPage;
    public static InventoryPage inventoryPage;
    public static CheckoutPage checkoutPage;
    public static WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));
    }
    /**
     * тестовый метод для осуществления аутентификации
     */
    @Test
    public void loginTest() {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //вводим пароль
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();

        inventoryPage.addToCart();
        inventoryPage.toCart();

        cartPage.pressCheckout();

        checkoutPage.inputFirstName("test");
        checkoutPage.inputLastName("test");
        checkoutPage.inputZipCode("test");

        checkoutPage.pressContinue();
        checkoutPage.pressFinish();

        String url = checkoutPage.getUrl();
        String thanks = checkoutPage.getThankMessage();

        Assert.assertEquals(ConfProperties.getProperty("successpage"), url);
        Assert.assertEquals(ConfProperties.getProperty("thanks"), thanks);

    }
    @Test
    public void invalidLogin() {
        driver.get(ConfProperties.getProperty("loginpage"));
        loginPage.inputLogin(ConfProperties.getProperty("invalidlogin"));
        loginPage.inputPassword(ConfProperties.getProperty("invalidpassword"));
        loginPage.clickLoginBtn();
        Assert.assertEquals(ConfProperties.getProperty("errormessage"), loginPage.getErrorMessage());
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
