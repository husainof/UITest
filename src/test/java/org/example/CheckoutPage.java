package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getDriver;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//*[contains(@id, 'first-name')]")
    private WebElement firstName;
    @FindBy(xpath = "//*[contains(@id, 'last-name')]")
    private WebElement lastName;
    @FindBy(xpath = "//*[contains(@id, 'postal-code')]")
    private WebElement zipCode;
    @FindBy(xpath = "//*[contains(@id, 'continue')]")
    private WebElement continueBtn;
    @FindBy(xpath = "//*[contains(@id, 'finish')]")
    private WebElement finishBtn;
    @FindBy(xpath = "//*[contains(text(), 'Thank you for your order')]")
    private WebElement successText;

    public void inputFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }
    public void inputLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }
    public void inputZipCode(String code) {
        this.zipCode.sendKeys(code);
    }
    public void pressContinue() {
        continueBtn.click();
    }
    public void pressFinish() {
        finishBtn.click();
    }
    public String getUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }
    public String getThankMessage() {
        return driver.findElement(By.className("complete-header")).getText();
    }
}
