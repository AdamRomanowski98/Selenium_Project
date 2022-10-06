package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressesInfoPage extends UserAbstract {
    @FindBy(css = ".name")
    WebElement name;

    @FindBy(css = ".email")
    WebElement email;

    @FindBy(css = ".phone")
    WebElement phone;

    @FindBy(css = ".address1")
    WebElement address1;

    @FindBy(css = ".city-state-zip")
    WebElement city;

    @FindBy(css = ".country")
    WebElement country;

    @FindBy(xpath = "//ul[@class=\"list\"]//a[@href=\"/customer/addresses\"]")
    WebElement addressesTab;

    public AddressesInfoPage(WebDriver driver, JSONUserData userData) {
        super(driver, userData);
    }
    @Step("Open customer address page")
    public AddressesInfoPage openAddressPage() {
        driver.get(getBaseUrl()+"customer/info");
        addressesTab.click();
        return this;
    }

    @Step("Verify if user has any address saved")
    public boolean verifyIfAddressIsEmpty() {
        try {
            boolean addressBox = driver.findElements(By.xpath("//div[@class=\"address-list\"][contains(text(), \"No addresses\")]")).isEmpty();
            System.out.println(addressBox);
            return addressBox;
        } catch (NoSuchElementException e) {
            System.out.println("Such element doesn't exist");
            return false;
        }
    }

    @Step("Verify if address has been added")
    public AddressesInfoPage verifyAddedAddress() {
        assertEquals(driver.getCurrentUrl(), getBaseUrl()+"customer/addresses");
        assertEquals(userData.getFirstName() + " " + userData.getLastName(), name.getText());
        assertEquals( userData.getEmail(), email.getText().substring(7));
        assertEquals(userData.getPhoneNumber(), phone.getText().substring(14));
        assertEquals(userData.getAddress_1(), address1.getText());
        assertEquals(userData.getCity() + ", " + userData.getPostCode(), city.getText());
        assertEquals(userData.getCountry(), country.getText());
        return this;
    }

    @Step("Delete all billing addresses")
    public AddressesInfoPage deleteAllAddresses(){

        boolean isTrue = false;

        try{
            List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".delete-address-button"));
            for(int i = 0; i<deleteButtons.size(); i++){
                while (!isTrue){
                    boolean deleteButton = !driver.findElements(By.cssSelector(".delete-address-button")).isEmpty();
                    isTrue = deleteButton;
                }
                List<WebElement> deleteButton = driver.findElements(By.cssSelector(".delete-address-button"));
                new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(deleteButton.get(0)));
                deleteButton.get(0).click();
                driver.switchTo().alert().accept();
            }

            boolean isTrue2 = false;
            while (!isTrue2){
                boolean addressBox = !driver.findElements(By.xpath("//div[@class=\"address-list\"][contains(text(), \"No addresses\")]")).isEmpty();
                isTrue2 = addressBox;
            }
            assertTrue(isTrue2);

        }catch (NoSuchElementException e){
            System.out.println("No such element");
        }
        return this;
    }
}
