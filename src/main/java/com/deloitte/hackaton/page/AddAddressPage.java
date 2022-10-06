package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddAddressPage extends UserAbstract {

    @FindBy(xpath = "//*[@id=\"Address_FirstName\"]")
    WebElement firstNameInput;

    @FindBy(xpath = "//*[@id=\"Address_LastName\"]")
    WebElement lastNameInput;

    @FindBy(xpath = "//*[@id=\"Address_Email\"]")
    WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"Address_CountryId\"]")
    WebElement countryDropdown;

    @FindBy(xpath = "//*[@id=\"Address_City\"]")
    WebElement cityInput;

    @FindBy(xpath = "//*[@id=\"Address_Address1\"]")
    WebElement address1Input;

    @FindBy(xpath = "//*[@id=\"Address_ZipPostalCode\"]")
    WebElement zipPostalCodeInput;

    @FindBy(xpath = "//*[@id=\"Address_PhoneNumber\"]")
    WebElement phoneNumberInput;

    @FindBy(xpath = "//input[@value =\"Save\"]")
    WebElement saveButton;

    public AddAddressPage(WebDriver driver, JSONUserData userData) {
        super(driver, userData);
    }

    @Step("Click on add new address button")
    public AddAddressPage clickOnAddNewButton() {

        boolean isTrue = false;
        while (!isTrue){
            try{
                boolean addNewAddressButton = !driver.findElements(By.xpath("//input[@value=\"Add new\"]")).isEmpty();
                isTrue = addNewAddressButton;
            }catch (NoSuchElementException e){
                System.out.println("No Such element");
            }
        }
        WebElement addNewAddressButton = driver.findElement(By.xpath("//input[@value=\"Add new\"]"));
        addNewAddressButton.click();
        return this;
    }

    @Step("Type first name")
    public AddAddressPage typeFirstName() {
        firstNameInput.sendKeys(userData.getFirstName());
        return this;
    }

    @Step("Type last name")
    public AddAddressPage typeLastName() {
        lastNameInput.sendKeys(userData.getLastName());
        return this;
    }

    @Step("Type email address")
    public AddAddressPage typeEmail() {
        emailInput.sendKeys(userData.getEmail());
        return this;
    }

    @Step("Select country")
    public AddAddressPage selectCountry() {
        Select selectDropDown = new Select(countryDropdown);
        selectDropDown.selectByVisibleText(userData.getCountry());
        return this;
    }

    @Step("Type city")
    public AddAddressPage typeCity() {
        cityInput.sendKeys(userData.getCity());
        return this;
    }

    @Step("Type address 1")
    public AddAddressPage typeAddress1() {
        address1Input.sendKeys(userData.getAddress_1());
        return this;
    }

    @Step("Type postal code")
    public AddAddressPage typePostalCode() {
        zipPostalCodeInput.sendKeys(userData.getPostCode());
        return this;
    }

    @Step("Type phone number")
    public AddAddressPage typePhoneNumber() {
        phoneNumberInput.sendKeys(userData.getPhoneNumber());
        return this;
    }

    @Step("Add new address")
    public AddAddressPage addAddress() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        return this;
    }

}