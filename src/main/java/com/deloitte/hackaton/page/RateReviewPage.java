package com.deloitte.hackaton.page;

import com.deloitte.hackaton.data.product.JSONProductData;
import com.deloitte.hackaton.data.user.JSONUserData;
import io.qameta.allure.Step;
import lombok.var;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RateReviewPage extends ProductAbstract{

    @FindBy(className = "product-review-item")
    WebElement productReview;

    @FindBy(className = "product-review-helpfulness")
    List<WebElement> helpfulnessSection;

    JSONUserData userData;

    public RateReviewPage(WebDriver driver, JSONProductData productData, JSONUserData userData ) {
        super(driver, productData);
        this.userData = userData;
    }

    @Step("Find any review")
    public RateReviewPage findAnyReview(){
        assertTrue(productReview.isDisplayed());
        return this;
    }

    @Step("The review is helpfull, click yes, count of helpful vote has been change")
    public RateReviewPage clickYseAndCheckCountOfVote() throws InterruptedException {

        WebElement newItemRandom = helpfulnessSection.stream()
                .findAny()
                .get();

        var voteResult = newItemRandom.findElement(By.xpath("//span[contains(@id, 'helpfulness-vote-result')]"));
        WebElement yesVote =  newItemRandom.findElement(By.xpath("//span[contains(@id,'vote-yes')]"));
        WebElement yesVoteNumber = newItemRandom.findElement(By.xpath("//span[contains(@id, 'helpfulness-vote-yes')]"));
        int yesVoteNumberInt = Integer.parseInt(yesVoteNumber.getText());

        WebElement noVote =  newItemRandom.findElement(By.xpath("//span[contains(@id,'vote-no')]"));
        WebElement noVoteNumber = newItemRandom.findElement(By.xpath("//span[contains(@id, 'helpfulness-vote-no')]"));
        int noVoteNumberInt = Integer.parseInt(noVoteNumber.getText());

        if(voteResult.getText() == null){

            yesVote.click();
            assertEquals(yesVoteNumberInt+1, Integer.parseInt(yesVoteNumber.getText()));
            assertEquals(noVoteNumberInt-1, Integer.parseInt(noVoteNumber.getText()));

            noVote.click();
            assertEquals(noVoteNumberInt+1, Integer.parseInt(noVoteNumber.getText()));
            assertEquals(yesVoteNumberInt, Integer.parseInt(yesVoteNumber.getText()));
        }
        else{
            yesVote.click();
            sleeping();
            if((yesVoteNumberInt+1)== Integer.parseInt(yesVoteNumber.getText())){
                noVote.click();
                sleeping();
                assertEquals(noVoteNumberInt, Integer.parseInt(noVoteNumber.getText()));
                assertEquals(yesVoteNumberInt, Integer.parseInt(yesVoteNumber.getText()));
            }
            else{
                noVote.click();
                sleeping();
                assertEquals(noVoteNumberInt+1, Integer.parseInt(noVoteNumber.getText()));
                assertEquals(yesVoteNumberInt-1, Integer.parseInt(yesVoteNumber.getText()));

                yesVote.click();
                sleeping();
                assertEquals(noVoteNumberInt, Integer.parseInt(noVoteNumber.getText()));
                assertEquals(yesVoteNumberInt, Integer.parseInt(yesVoteNumber.getText()));

            }
        }
        return this;
    }

    void sleeping() throws InterruptedException {
        Thread.sleep(1000);
     }
}
