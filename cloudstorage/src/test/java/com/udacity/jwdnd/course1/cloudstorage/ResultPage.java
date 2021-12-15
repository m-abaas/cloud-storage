package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {


    @FindBy(id = "click-here")
    private WebElement clickHereButton;


    public ResultPage(WebDriver webDriver)
    {
        PageFactory.initElements(webDriver, this);
    }
    public void clickHereToGoBack()
    {
        clickHereButton.click();
    }

}
