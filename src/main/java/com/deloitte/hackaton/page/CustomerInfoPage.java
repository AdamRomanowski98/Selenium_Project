package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CustomerInfoPage extends UserAbstract{

    @FindBy(xpath = "//ul[@class=\"list\"]//a[@href=\"/customer/addresses\"]")
    WebElement addressesTab;

    @FindBy(xpath = "//input[@value=\"Add new\"]")
    WebElement addNewAddressButton;

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


    public CustomerInfoPage(WebDriver driver, JSONUserData userData){
      super(driver, userData);
    }

    @Step("Verify if user has any address saved")
    public boolean verifyAddress(){
        try{
            boolean addressBox = driver.findElements(By.xpath("//div[@class=\"address-list\"][contains(text(), \"No addresses\")]")).isEmpty();
            System.out.println(addressBox);
            return addressBox;
        }catch (NoSuchElementException e){
            System.out.println("Such element doesn't exist");
            return false;
        }
    }

    @Step("Open customer address page")
    public CustomerInfoPage openAddressPage(){
        addressesTab.click();
        return this;
    }

    @Step("Click on add new address button")
    public CustomerInfoPage clickOnAddNewButton(){
        addNewAddressButton.click();
        return this;
    }

    @Step("Type first name")
    public CustomerInfoPage typeFirstName(){
        firstNameInput.sendKeys(userData.getFirstName());
        return this;
    }

    @Step("Type last name")
    public CustomerInfoPage typeLastName(){
        lastNameInput.sendKeys(userData.getLastName());
        return this;
    }

    @Step("Type email address")
    public CustomerInfoPage typeEmail(){
        emailInput.sendKeys(userData.getEmail());
        return this;
    }

    @Step("Select country")
    public CustomerInfoPage selectCountry(){
        Select selectDropDown = new Select(countryDropdown);
        selectDropDown.selectByVisibleText(userData.getCountry());
        return this;
    }

    @Step("Type city")
    public CustomerInfoPage typeCity(){
        cityInput.sendKeys(userData.getCity());
        return this;
    }

    @Step("Type address 1")
    public CustomerInfoPage typeAddress1(){
        address1Input.sendKeys(userData.getAddress_1());
        return this;
    }

    @Step("Type postal code")
    public CustomerInfoPage typePostalCode(){
        zipPostalCodeInput.sendKeys(userData.getPostCode());
        return this;
    }

    @Step("Type phone number")
    public CustomerInfoPage typePhoneNumber(){
        phoneNumberInput.sendKeys(userData.getPhoneNumber());
        return this;
    }

    @Step("Add new address")
    public CustomerInfoPage addAddress(){
        saveButton.click();
        return this;
    }

    @Step("Delete address")
    public CustomerInfoPage deleteAddress(){
        try{
            WebElement deleteButton = driver.findElement(By.xpath("//input[@value=\"Delete\"]"));
            deleteButton.click();
            driver.switchTo().alert().accept();
        }catch (NoSuchElementException e){
            System.out.println("No such element");
        }
        return this;
    }
}