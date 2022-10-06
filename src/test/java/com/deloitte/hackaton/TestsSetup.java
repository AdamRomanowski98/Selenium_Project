package com.deloitte.hackaton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

@ExtendWith(ScreenShooter.class)
public class TestsSetup {

    WebDriver driver;

    String webDriver = "chrome-headless";

    @BeforeAll
    static void setupAll(){
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    void setup(){
        if(webDriver == "chrome"){
            driver = new ChromeDriver();
        }else if(webDriver == "chrome-headless"){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }else if(webDriver == "edge"){
            driver = new EdgeDriver();
        } else if (webDriver == "edge-headless") {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");
            driver = new EdgeDriver(edgeOptions);
        }else {
            System.out.println("Invalid browser");
        }
        ScreenShooter.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        this.driver.quit();
    }
}
