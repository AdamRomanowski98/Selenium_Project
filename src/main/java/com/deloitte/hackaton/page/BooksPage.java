package com.deloitte.hackaton.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BooksPage extends ProductAbstract {

    @FindBy (css = "div.page-body > div.product-grid > div:nth-child(1) > div > div.picture > a > img")
    WebElement firstItemGrid;

    @FindBy (css = "div.page-body > div.product-grid > div:nth-child(2) > div > div.details > div.product-rating-box")
    WebElement secondItemGrid;

    @FindBy (css = "div.page-body > div.product-list > div:nth-child(1) > div > div.details > div.product-rating-box")
    WebElement firstItemList;

    @FindBy (css = " div.page-body > div.product-list > div:nth-child(2) > div > div.details > div.description")
    WebElement secondItemList;

    @FindBy (id = "products-viewmode")
    WebElement viewAs;

    @FindBy (id = "products-pagesize")
    WebElement productPageSize;

    @FindBy (id = "products-orderby")
    WebElement orderBy;

    public BooksPage(WebDriver driver) {
        super(driver);
    }

    public BooksPage getToBooksPage(){
        driver.get(getBaseUrl() + "books");
        return this;
    }


    @Step ("Test changing list view (grid/list)")
    public BooksPage changeViewGrid(){
        Select viewAsDropdown = new Select(viewAs);
        viewAsDropdown.selectByVisibleText("Grid");
        assertTrue(firstItemGrid.isDisplayed());
        assertTrue(secondItemGrid.isDisplayed());
        return this;
    }

    @Step ("Test changing list view (grid/list)")
    public BooksPage changeViewList(){
        Select viewAsDropdown = new Select(viewAs);
        viewAsDropdown.selectByVisibleText("List");
        assertTrue(firstItemList.isDisplayed());
        assertTrue(secondItemList.isDisplayed());
        return this;
    }

    @Step ("Test displaying different number of products per page - check created subpages")
    public BooksPage displayingDifferentNoOfProducts () {
        Select displayPerPage = new Select(productPageSize);
        displayPerPage.selectByVisibleText("4");
        List<WebElement> noOfProducts = driver.findElements(By.xpath("//div[@class=\"product-item\"]"));
        assertEquals(noOfProducts.size(), 4);
        return this;
    }

    @Step ("Test sorting products by different filter categories")
    public BooksPage sortAZ(){
        Select order = new Select(orderBy);
        order.selectByVisibleText("Name: A to Z");
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList= driver.findElements(By.xpath("//h2[@class=\"product-title\"]"));
        for(WebElement we:elementList){
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for(String s:obtainedList){
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        assertTrue(sortedList.equals(obtainedList));
        return this;
    }

    @Step ("Test sorting products by different filter categories")
    public BooksPage sortZA(){
        Select order = new Select(orderBy);
        order.selectByVisibleText("Name: Z to A");
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList= driver.findElements(By.xpath("//h2[@class=\"product-title\"]"));
        for(WebElement we:elementList){
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for(String s:obtainedList){
            sortedList.add(s);
        }
        Collections.sort(sortedList, Collections.reverseOrder());
        assertTrue(sortedList.equals(obtainedList));
        return this;
    }

    @Step ("Test sorting products by different filter categories")
    public BooksPage priceLH(){
        Select order = new Select(orderBy);
        order.selectByVisibleText("Price: Low to High");
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList= driver.findElements(By.xpath("//span[@class=\"price actual-price\"]"));
        for(WebElement we:elementList){
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for(String s:obtainedList){
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        assertTrue(sortedList.equals(obtainedList));
        return this;
    }


    @Step ("Test sorting products by different filter categories")
    public BooksPage priceHL(){
        Select order = new Select(orderBy);
        order.selectByVisibleText("Price: High to Low");
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList= driver.findElements(By.xpath("//span[@class=\"price actual-price\"]"));
        for(WebElement we:elementList){
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for(String s:obtainedList){
            sortedList.add(s);
        }
        Collections.sort(sortedList, Collections.reverseOrder());
        assertTrue(sortedList.equals(obtainedList));
        return this;
    }

    @Step ("Test filtering products (e.g. Books by price)")
    public BooksPage filter() {
        boolean demo = true;
        driver.findElement(By.cssSelector("div.filter-content>ul>li:nth-child(1)>a")).click();
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//span[@class=\"price actual-price\"]"));
        for (WebElement we : elementList) {
            obtainedList.add(we.getText());
        }
        ArrayList<String> stringList = new ArrayList<>();
        for (String s : obtainedList) {
            stringList.add(s);
        }


        for (int i = 0; i < stringList.size(); i++) {
            String priceStr = stringList.get(i).replaceAll("[^0-9]", "");
            int price = Integer.parseInt(priceStr);
            if (price <= 2500) {
                assertTrue(demo);
            } else {
                assertFalse(demo);
            }
        }
        return this;
    }

}