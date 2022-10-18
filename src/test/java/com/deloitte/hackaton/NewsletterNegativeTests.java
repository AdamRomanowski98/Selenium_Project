package com.deloitte.hackaton;

import com.deloitte.hackaton.data.JSONDataReader;
import com.deloitte.hackaton.data.user.JSONInvalidEmails;
import io.qameta.allure.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static com.deloitte.hackaton.utils.TestFactory.startNewNewsletterNegativeTest;

public class NewsletterNegativeTests extends TestsSetup{


    @Description ("TC 31535. Negative scenario: Incorrect e-mail when signing to newsletter")
    @ParameterizedTest
    @MethodSource(value = "invalidEmailStream")
    void newsletterNegative(JSONInvalidEmails invalidEmails) throws InterruptedException {
        startNewNewsletterNegativeTest(driver, invalidEmails)
                .openMainPage()
                .inputInvalidMail()
                .subscribe()
                .errorMessage();
    }

    private static Stream<JSONInvalidEmails> invalidEmailStream() {
        return JSONDataReader.readInvalidEmail().getInvalidEmails().stream();
    }
}

