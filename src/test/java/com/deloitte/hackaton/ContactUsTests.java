package com.deloitte.hackaton;

import com.deloitte.hackaton.data.JSONDataReader;
import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


import static com.deloitte.hackaton.utils.TestFactory.*;

public class ContactUsTests extends TestsSetup{


    @Description ("TC 31532. Test Contact Us option (unregistered user)")
    @ParameterizedTest
    @MethodSource(value = "usersDataStream")
    void contactUsNonLoginUser(JSONUserData userData){
        startNewContactUsFormNonLoginTest(driver, userData)
                .openMainPage()
//                .openContactUsPage()
                .inputFullName()
                .inputEmail()
                .inputEnquire()
                .submit()
                .verifySubmitEnquiry();
    }

    @Description ("TC 31532. Test Contact Us option (registered user)")
    @ParameterizedTest
    @MethodSource(value = "usersDataStream")
    void contactUsLoginUser(JSONUserData userData){
        startNewLoginTest(driver, userData)
                .openLoginPage()
                .typeEmail()
                .typePassword()
                .logIn();
        startNewContactUsFormLoginTest(driver, userData)
                .openContactUsPage()
                .verifyEmailInput()
                .verifyFullNameInput()
                .inputEnquire()
                .submit()
                .verifySubmitEnquiry();
    }
    private static Stream<JSONUserData> usersDataStream() {
        return JSONDataReader.readUsers().getUsers().stream();
    }

}