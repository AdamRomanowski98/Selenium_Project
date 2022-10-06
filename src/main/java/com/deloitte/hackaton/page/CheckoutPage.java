package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.product.JSONProductData;
import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CheckoutPage extends ProductAbstract{

    @FindBy(xpath = "//*[@id=\"billing-address-select\"]")
    WebElement billingAddressSelect;

    @FindBy(xpath = "//*[@id=\"BillingNewAddress_CountryId\"]")
    WebElement billingAddressCountry;

    @FindBy(xpath = "//*[@id=\"BillingNewAddress_City\"]")
    WebElement city;

    @FindBy(xpath = "//*[@id=\"BillingNewAddress_Address1\"]")
    WebElement address_1;

    @FindBy(xpath = "//*[@id=\"BillingNewAddress_ZipPostalCode\"]")
    WebElement postCode;

    @FindBy(xpath = "//*[@id=\"BillingNewAddress_PhoneNumber\"]")
    WebElement phoneNumber;

    @FindBy(xpath = "//*[@id=\"billing-buttons-container\"]/input")
    WebElement billingContinueButton;

    @FindBy(xpath = "//*[@id=\"shipping-buttons-container\"]/input")
    WebElement shippingContinueButton;

    @FindBy(xpath = "//*[@id=\"shipping-address-select\"]/option[1]")
    WebElement shippingAddress;

    @FindBy(xpath = "//input[@value=\"Ground___Shipping.FixedRate\"]")
    WebElement groundShippingMethod;

    @FindBy(xpath = "//*[@id=\"shipping-method-buttons-container\"]/input")
    WebElement shippingMethodContinueButton;

    @FindBy(xpath = "//input[@value=\"Payments.PurchaseOrder\"]")
    WebElement paymentMethod;

    @FindBy(xpath = "//*[@id=\"payment-method-buttons-container\"]/input")
    WebElement paymentMethodContinueButton;

    @FindBy(xpath = "//*[@id=\"PurchaseOrderNumber\"]")
    WebElement purchaseOrderNumber;

    @FindBy(xpath = "//*[@id=\"payment-info-buttons-container\"]/input")
    WebElement purchaseOrderNumberContinueButton;

    @FindBy(xpath = "//input[@onclick=\"Billing.save()\"]")
    WebElement clickBillingAddress;

    @FindBy(xpath = "//input[@onclick=\"Shipping.save()\"]")
    WebElement clickShippingAddress;

    @FindBy(xpath = "//input[@onclick=\"ShippingMethod.save()\"]")
    WebElement clickShippingMethod;

    @FindBy (xpath = "//input[@value=\"Payments.CashOnDelivery\"]")
    WebElement paymentMethodCOD;

    @FindBy(xpath = "//input[@onclick=\"PaymentMethod.save()\"]")
    WebElement clickPaymentMethod;
    @FindBy(xpath = "//input[@onclick=\"PaymentInfo.save()\"]")
    WebElement clickPaymentInfo;

    @FindBy(xpath = "//input[@onclick=\"ConfirmOrder.save()\"]")
    WebElement clickConfirmOrder;

    @FindBy(xpath = "//li/a[contains(@href, \"/orderdetails\")]")
    WebElement orderDetails;

    @FindBy(xpath = "//ul[@class=\"billing-info\"]/li[@class=\"name\"]")
    WebElement nameDetailsBilling;

    @FindBy (xpath = "//ul[@class=\"billing-info\"]/li[@class=\"email\"]")
    WebElement emailDetailsBilling;

    @FindBy (xpath = "//ul[@class=\"billing-info\"]/li[@class=\"phone\"]")
    WebElement phoneDetailsBilling;

    @FindBy (xpath = "//ul[@class=\"billing-info\"]/li[@class=\"city-state-zip\"]")
    WebElement cityStateZipDetailsBilling;

    @FindBy (xpath = "//ul[@class=\"billing-info\"]/li[@class=\"country\"]")
    WebElement countryDetailsBilling;

    @FindBy (xpath = "//ul[@class=\"billing-info\"]/li[@class=\"payment-method\"]")
    WebElement paymentDetailsBilling;

    @FindBy(xpath = "//ul[@class=\"shipping-info\"]/li[@class=\"name\"]")
    WebElement nameDetailsShipping;

    @FindBy (xpath = "//ul[@class=\"shipping-info\"]/li[@class=\"email\"]")
    WebElement emailDetailsShipping;

    @FindBy (xpath = "//ul[@class=\"shipping-info\"]/li[@class=\"phone\"]")
    WebElement phoneDetailsShipping;

    @FindBy (xpath = "//ul[@class=\"shipping-info\"]/li[@class=\"city-state-zip\"]")
    WebElement cityStateZipDetailsShipping;

    @FindBy (xpath = "//ul[@class=\"shipping-info\"]/li[@class=\"country\"]")
    WebElement countryDetailsShipping;

    @FindBy (xpath = "//ul[@class=\"shipping-info\"]/li[@class=\"shipping-method\"]")
    WebElement methodDetailsShipping;

    @FindBy(xpath = "//div[@class=\"attributes\"][contains(text(), \"Size\")]")
    WebElement sizeColorField;

    @FindBy(xpath = "//em//a")
    WebElement productNameField;

    @FindBy (xpath = "//div[@class=\"payment-details\"]/label[@for=\"paymentmethod_0\"]")
    WebElement paymentMethodCODLabel;

    @FindBy (xpath = "//td[@class=\"a-right price\"]")
    WebElement productPrice;

    @FindBy (xpath = "//td[@class=\"a-center quantity\"]")
    WebElement productQuantity;

    @FindBy (xpath = "//td[@class=\"a-right total\"]")
    WebElement totalPriceInDetails;

    @FindBy (css = "tr:nth-child(1)>td.cart-total-right>span")
    WebElement subTotal;

    @FindBy (css = "tbody>tr:nth-child(2)>td.cart-total-right>span")
    WebElement shippingCostInDetails;

    @FindBy (css = "tr:nth-child(3)>td.cart-total-right>span")
    WebElement paymentMethodAddFee;

    @FindBy (css = "tr:nth-child(4)>td.cart-total-right>span")
    WebElement taxDetails;


    @FindBy (css = "tr:nth-child(3)>td.cart-total-right>span")
    WebElement taxDetailsPOD;

    @FindBy (xpath = "//table[@class=\"cart-total\"]/tbody/tr/td[@class=\"cart-total-right\"]/span[@class=\"nobr\"]//strong")
    WebElement orderTotal;

    public List<String> sneakersElements = SneakersProductPage.getList();

    public List<String> productDetailsElements = MainPage.getList();

    List<String> checkoutInfoList = new ArrayList<>();

    List<String> billingDataList = new ArrayList<>();

    public CheckoutPage(WebDriver driver, JSONProductData productData, JSONUserData userData) {
        super(driver, productData);
        this.userData = userData;
    }

    @Step("Click Order details and validate order")
    public CheckoutPage clickThroughPaymentMethods() {
        clickBillingAddress.click();
        clickShippingAddress.click();
        groundShippingMethod.click();
        checkoutInfoList.add(groundShippingMethod.getText());
        clickShippingMethod.click();
        paymentMethodCOD.click();
        checkoutInfoList.add(paymentMethodCODLabel.getText());
        clickPaymentMethod.click();
        clickPaymentInfo.click();
        return this;
    }

    @Step
    public CheckoutPage chooseNewBillingAddress(){
        Select billingAddressSelectDropdown = new Select(billingAddressSelect);
        billingAddressSelectDropdown.selectByVisibleText("New Address");
        WebElement newAddress = driver.findElement(By.xpath("//*[@id=\"billing-address-select\"]/option[@value=\"\"]"));
        assertTrue(newAddress.isSelected());
        return this;
    }

    @Step
    public CheckoutPage addNewAddressData() throws InterruptedException {
        Select billingAddressCountrySelectDropdown = new Select(billingAddressCountry);
        billingAddressCountrySelectDropdown.selectByVisibleText("Canada");
        city.sendKeys("Toronto");
        address_1.sendKeys("address1");
        postCode.sendKeys("93-120");
        phoneNumber.sendKeys("123321123");
        billingDataList.add(city.getText());
        billingDataList.add(address_1.getText());
        billingDataList.add(postCode.getText());
        billingDataList.add(phoneNumber.getText());
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(billingContinueButton));
        billingContinueButton.click();
        return this;
    }

    @Step
    public CheckoutPage selectShippingAddress(){
        shippingAddress.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(shippingContinueButton));
        shippingContinueButton.click();
        return this;
    }

    @Step
    public CheckoutPage selectShippingMethod(){
        groundShippingMethod.click();
        checkoutInfoList.add(groundShippingMethod.getText());
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(shippingMethodContinueButton));
        shippingMethodContinueButton.click();
        return this;
    }

    @Step
    public CheckoutPage selectPaymentMethod(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(paymentMethod));
        paymentMethod.click();
        checkoutInfoList.add(paymentMethod.getText());
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(paymentMethodContinueButton));
        paymentMethodContinueButton.click();
        return this;
    }

    @Step
    public CheckoutPage typePurchaseOrderNumber(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(purchaseOrderNumber));
        purchaseOrderNumber.sendKeys("0001");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(purchaseOrderNumberContinueButton));
        purchaseOrderNumberContinueButton.click();
        return this;
    }

    @Step("Click Order details and validate order")
    public CheckoutPage validateBillingInfo() {
        assertTrue(nameDetailsBilling.getText().contains(getUserData().getFirstName()));
        assertTrue(nameDetailsBilling.getText().contains(getUserData().getLastName()));
        assertTrue(emailDetailsBilling.getText().contains(getUserData().getEmail()));
        assertEquals(phoneDetailsBilling.getText().replaceAll("[^0-9]", ""), getUserData().getPhoneNumber());
        assertTrue(cityStateZipDetailsBilling.getText().contains(getUserData().getCity()));
        assertTrue(cityStateZipDetailsBilling.getText().contains(getUserData().getPostCode()));
        assertTrue(countryDetailsBilling.getText().contains(getUserData().getCountry()));
        String paymentCODFromArray = checkoutInfoList.get(1);
        String paymentCOD[] = paymentCODFromArray.split(" ");
        assertTrue(paymentDetailsBilling.getText().contains(paymentCOD[0]));
        return this;
    }

    @Step("Validate billing details when changed")
    public CheckoutPage validateBillingInfoOnChange(){
        assertTrue(nameDetailsBilling.getText().contains(getUserData().getFirstName()));
        assertTrue(nameDetailsBilling.getText().contains(getUserData().getLastName()));
        assertTrue(emailDetailsBilling.getText().contains(getUserData().getEmail()));
        assertTrue(cityStateZipDetailsBilling.getText().contains(billingDataList.get(0)));
        assertTrue(cityStateZipDetailsBilling.getText().contains(billingDataList.get(1)));
        assertTrue(cityStateZipDetailsBilling.getText().contains(billingDataList.get(2)));
        return this;
    }

    @Step("Click Order details and validate order")
    public CheckoutPage validateShippingInfo() {
        assertTrue(nameDetailsShipping.getText().contains(getUserData().getFirstName()));
        assertTrue(nameDetailsShipping.getText().contains(getUserData().getLastName()));
        assertTrue(emailDetailsShipping.getText().contains(getUserData().getEmail()));
        assertEquals(phoneDetailsShipping.getText().replaceAll("[^0-9]", ""), getUserData().getPhoneNumber());
        assertTrue(cityStateZipDetailsShipping.getText().contains(getUserData().getCity()));
        assertTrue(cityStateZipDetailsShipping.getText().contains(getUserData().getPostCode()));
        assertTrue(countryDetailsShipping.getText().contains(getUserData().getCountry()));
        String groundLabelFromArray = checkoutInfoList.get(0);
        String groundLabel[] = groundLabelFromArray.split(" ");
        assertTrue(methodDetailsShipping.getText().contains(groundLabel[0]));
        return this;
    }

    @Step
    public CheckoutPage validateShoesDetails(){
        assertTrue(sizeColorField.getText().contains(sneakersElements.get(0)));
        assertTrue(sizeColorField.getText().contains(sneakersElements.get(2)));
        return this;
    }


    @Step("Click Order details and validate order")
    public CheckoutPage confirm() {
        clickConfirmOrder.click();
        orderDetails.click();
        return this;
    }

    @Step
    public CheckoutPage checkProductDetails(){
        assertEquals(productNameField.getText(), productDetailsElements.get(0));
        assertEquals(productPrice.getText(), productDetailsElements.get(1));
        assertEquals(productQuantity.getText(), productDetailsElements.get(2));
        return this;
    }



    public CheckoutPage validateTotalCostsInDetailsIfCOD(){
        String CODlabel =  checkoutInfoList.get(1);
        String priceinCODStr = CODlabel.replaceAll("[^0-9]", "");
        int priceInCOD = Integer.valueOf(priceinCODStr);
        String totalPriceStr = totalPriceInDetails.getText().replaceAll("[^0-9]", "");
        int totalPrice = Integer.valueOf(totalPriceStr);
        String subTotalStr = subTotal.getText().replaceAll("[^0-9]", "");
        int subTotal = Integer.valueOf(subTotalStr);
        String shippingStr = shippingCostInDetails.getText().replaceAll("[^0-9]", "");
        int shippingCost = Integer.valueOf(shippingStr);
        String paymentMethodStr = paymentMethodAddFee.getText().replaceAll("[^0-9]", "");
        int paymentMethod = Integer.valueOf(paymentMethodStr);
        String taxStr = taxDetails.getText().replaceAll("[^0-9]", "");
        int tax = Integer.valueOf(taxStr);
        String orderTotalStr = orderTotal.getText().replaceAll("[^0-9]", "");
        int orderTotal = Integer.valueOf(orderTotalStr);
        assertEquals(priceInCOD, paymentMethod);
        assertEquals(totalPrice, subTotal);
        assertEquals(orderTotal, subTotal+shippingCost+priceInCOD+tax);
        return this;
    }

    public CheckoutPage validateTotalCostsInDetailsIfPO(){
        String totalPriceStr = totalPriceInDetails.getText().replaceAll("[^0-9]", "");
        int totalPrice = Integer.valueOf(totalPriceStr);
        String subTotalStr = subTotal.getText().replaceAll("[^0-9]", "");
        int subTotal = Integer.valueOf(subTotalStr);
        String shippingStr = shippingCostInDetails.getText().replaceAll("[^0-9]", "");
        int shippingCost = Integer.valueOf(shippingStr);
        String taxStr = taxDetailsPOD.getText().replaceAll("[^0-9]", "");
        int tax = Integer.valueOf(taxStr);
        String orderTotalStr = orderTotal.getText().replaceAll("[^0-9]", "");
        int orderTotal = Integer.valueOf(orderTotalStr);
        assertEquals(totalPrice, subTotal);
        assertEquals(orderTotal, subTotal+shippingCost+tax);
        return this;
    }


}