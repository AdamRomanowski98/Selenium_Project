package com.deloitte.hackaton;

import com.deloitte.hackaton.data.JSONDataReader;
import com.deloitte.hackaton.data.product.JSONProductData;
import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.deloitte.hackaton.utils.TestFactory.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsDataDrivenTests extends TestsSetup{


    @Description ("TC 31537. Negative scenario: try to checkout without agreeing to terms of service")
    @ParameterizedTest
    @MethodSource("productsDataStream")
    @Order(1)
    void testCheckoutWithoutAgreeing(JSONProductData productData) throws InterruptedException {
        mainPage(driver, productData).navigateToMainPage();
        boolean isEmpty = startNewCartTest(driver, productData).checkIfCartEmpty();
        if(isEmpty){
            startNewCartTest(driver, productData).deleteIfNotEmpty();
        }
        startNewProductTest(driver, productData)
                .openProductPage()
                .verifyProductName()
                .verifyAvailability()
                .selectQuantity()
                .orderProduct()
                .verifyNotification()
                .goToCartPage()
                .clickCheckoutButton()
                .validateCheckout();
    }

    @Description ("TC 31529. Buy a product as a registered user")
    @ParameterizedTest
    @MethodSource("productsDataStream")
    @Order(6)
    void buyAProductAsRegistered(JSONProductData productData) throws InterruptedException {
        JSONUserData userData = JSONDataReader.readUsers().getUsers().get(0);
        startNewProductTest(driver, productData, userData)
                .login()
                .openLoginPage()
                .typeEmail()
                .typePassword()
                .logIn()
                .verifyLogin();
        startNewAddressesInfoTest(driver, userData).openAddressPage().deleteAllAddresses();
            startNewAddressAddTest(driver, userData)
                    .clickOnAddNewButton()
                    .typeFirstName()
                    .typeLastName()
                    .typeEmail()
                    .selectCountry()
                    .typeCity()
                    .typeAddress1()
                    .typePostalCode()
                    .typePhoneNumber()
                    .addAddress();

        boolean isEmpty = startNewCartTest(driver, productData, userData).checkIfCartEmpty();
        if(isEmpty){
            startNewCartTest(driver, productData, userData).deleteIfNotEmpty();
        }
        startNewProductTest(driver, productData, userData)
                .openProductPage()
                .verifyProductName()
                .verifyAvailability()
                .selectQuantity()
                .orderProduct()
                .verifyNotification();
        mainPage(driver, userData)
                .getElementsFromProductDetails()
                .navigateToMainPage()
                .searchFor14laptop()
                .laptop14inchAddToCart()
                .goToCart()
                .deleteProduct()
                .updateCart()
                .selectCountry()
                .selectState()
                .acceptTerms()
                .goToCheckout()
                .chooseNewBillingAddress()
                .addNewAddressData()
                .selectShippingAddress()
                .selectShippingMethod()
                .selectPaymentMethod()
                .typePurchaseOrderNumber()
                .validateShippingInfo()
                .validateBillingInfoOnChange()
                .confirm();
        startNewLoginTest(driver, userData)
                .logOut()
                .verifyIfLoggedOut();
    }


    @Description ("TC 31527. Buy physical gift card")
    @ParameterizedTest
    @MethodSource(value = "usersDataStream")
    @Order(2)
    void buyAPhysicalGiftCard(JSONUserData userData) throws InterruptedException {
        startNewLoginTest(driver, userData)
                .openLoginPage()
                .typeEmail()
                .typePassword()
                .logIn()
                .verifyLogin();
        startNewAddressesInfoTest(driver, userData).openAddressPage().deleteAllAddresses();
        startNewAddressAddTest(driver, userData)
                .clickOnAddNewButton()
                .typeFirstName()
                .typeLastName()
                .typeEmail()
                .selectCountry()
                .typeCity()
                .typeAddress1()
                .typePostalCode()
                .typePhoneNumber()
                .addAddress();
        startNewAddressesInfoTest(driver,userData)
                .verifyAddedAddress();

        boolean isEmpty = startNewCartTest(driver, userData).checkIfCartEmpty();
        if(isEmpty){
            startNewCartTest(driver, userData).deleteIfNotEmpty();
        }
        mainPage(driver, userData)
                .navigateToMainPage()
                .goToGiftCards()
                .locatePhysicalGiftCard100$();
        mainPage(driver, userData)
                .getElementsFromProductDetails();
        startGiftCardTest(driver, userData)
                .provideInfoAndAddToCart();
        startNewCartTest(driver, userData)
                .selectUsersCountry()
                .acceptTerms()
                .goToCheckout()
                .clickThroughPaymentMethods()
                .confirm()
                .validateBillingInfo()
                .validateShippingInfo()
                .validateTotalCostsInDetailsIfCOD();
        startNewLoginTest(driver, userData)
                .logOut()
                .verifyIfLoggedOut();
    }

    @Description ("TC 31526. Buy a customizable product - happy path")
    @ParameterizedTest
    @MethodSource(value = "usersDataStream")
    @Order(3)
    void buyACustomizableProduct(JSONUserData userData) throws InterruptedException {
        startNewLoginTest(driver, userData)
                .openLoginPage()
                .typeEmail()
                .typePassword()
                .logIn()
                .verifyLogin();

        startNewAddressesInfoTest(driver, userData).openAddressPage().deleteAllAddresses();
        startNewAddressAddTest(driver, userData)
                .clickOnAddNewButton()
                .typeFirstName()
                .typeLastName()
                .typeEmail()
                .selectCountry()
                .typeCity()
                .typeAddress1()
                .typePostalCode()
                .typePhoneNumber()
                .addAddress();
        startNewAddressesInfoTest(driver,userData)
                .verifyAddedAddress();

        boolean isEmpty = startNewCartTest(driver, userData).checkIfCartEmpty();
        if(isEmpty){
            startNewCartTest(driver, userData).deleteIfNotEmpty();
        }
        mainPage(driver, userData)
                .navigateToMainPage()
                .searchForBlueAndGreenSneaker();
        mainPage(driver, userData)
                .getElementsFromProductDetails();
        startSneakersProductTest(driver, userData)
                .selectColor()
                .selectSize()
                .addToCart()
                .acceptTerms()
                .goToCheckout()
                .clickThroughPaymentMethods()
                .confirm()
                .validateTotalCostsInDetailsIfCOD()
                .validateShoesDetails()
                .validateBillingInfo()
                .validateShippingInfo();
        startNewLoginTest(driver, userData)
                .logOut()
                .verifyIfLoggedOut();
    }

    @Description ("TC 31525. Buy non-customizable product - happy path")
    @ParameterizedTest
    @MethodSource(value = "usersDataStream")
    @Order(4)
    void buyANonCustomizableProduct(JSONUserData userData) throws InterruptedException {
        startNewLoginTest(driver, userData)
                .openLoginPage()
                .typeEmail()
                .typePassword()
                .logIn()
                .verifyLogin();
        startNewAddressesInfoTest(driver, userData).openAddressPage().deleteAllAddresses();
            startNewAddressAddTest(driver, userData)
                    .clickOnAddNewButton()
                    .typeFirstName()
                    .typeLastName()
                    .typeEmail()
                    .selectCountry()
                    .typeCity()
                    .typeAddress1()
                    .typePostalCode()
                    .typePhoneNumber()
                    .addAddress();
            startNewAddressesInfoTest(driver, userData)
                    .verifyAddedAddress();

        boolean isEmpty = startNewCartTest(driver, userData).checkIfCartEmpty();
        if(isEmpty){
            startNewCartTest(driver, userData).deleteIfNotEmpty();
        }
        mainPage(driver, userData)
                .navigateToMainPage()
                .searchFor14laptop()
                .getElementsFromProductDetails()
                .laptop14inchAddToCart()
                .acceptTerms()
                .goToCheckout()
                .clickThroughPaymentMethods()
                .confirm()
                .validateBillingInfo()
                .validateShippingInfo()
                .checkProductDetails()
                .validateTotalCostsInDetailsIfCOD();
    }

    @Description ("TC 31531. Test Reorder button in Order Details")
    @ParameterizedTest
    @MethodSource("productsDataStream")
    @Order(5)
    void reOrder(JSONProductData productData) throws InterruptedException {
        JSONUserData userData = JSONDataReader.readUsers().getUsers().get(0);

        startNewLoginTest(driver, userData)
                .openLoginPage()
                .typeEmail()
                .typePassword()
                .logIn()
                .verifyLogin();
        startNewAddressesInfoTest(driver, userData).openAddressPage().deleteAllAddresses();

        startNewAddressAddTest(driver, userData)
                .clickOnAddNewButton()
                .typeFirstName()
                .typeLastName()
                .typeEmail()
                .selectCountry()
                .typeCity()
                .typeAddress1()
                .typePostalCode()
                .typePhoneNumber()
                .addAddress();

        startNewAddressesInfoTest(driver,userData)
                .verifyAddedAddress();

        boolean isEmpty = startNewCartTest(driver, productData, userData).checkIfCartEmpty();
        if(isEmpty){
            startNewCartTest(driver, productData, userData).deleteIfNotEmpty();
        }
        startNewProductTest(driver, productData, userData)
                .openProductPage()
                .verifyProductName()
                .verifyAvailability();
        mainPage(driver, userData)
                .getElementsFromProductDetails();
        startNewProductTest(driver, productData, userData)
                .selectQuantity()
                .orderProduct()
                .verifyNotification();
        startNewCartTest(driver, productData, userData)
                .goToCart()
                .saveProductData()
                .selectCountry()
                .selectState()
                .acceptTerms()
                .goToCheckout()
                .clickThroughPaymentMethods()
                .confirm()
                .validateTotalCostsInDetailsIfCOD()
                .validateShippingInfo()
                .validateBillingInfo();
        startNewReOrderTest(driver, productData, userData)
                .openOrdersInfo()
                .detailsButton()
                .clickOnReOrderButton()
                .reOrderCheckData();
        startNewCartTest(driver, productData, userData)
                .acceptTerms()
                .goToCheckout()
                .clickThroughPaymentMethods()
                .confirm()
                .validateBillingInfo()
                .validateShippingInfo();
        startNewLoginTest(driver,userData)
                .verifyLogin();
        startNewReOrderTest(driver, productData, userData)
                .openOrdersInfo()
                .checkOrdersNumberForFirstItem();
    }

    private static Stream<JSONUserData> usersDataStream () {
        return JSONDataReader.readUsers().getUsers().stream();
    }
    private static Stream<JSONProductData> productsDataStream() {
        return JSONDataReader.readProducts().getProducts().stream();
    }
}
