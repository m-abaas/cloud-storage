package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement userNameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signupButton;


    public LoginPage(WebDriver webDriver)
    {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String userName, String password)
    {
        this.userNameField.sendKeys(userName);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public void goToSignup()
    {
        this.signupButton.click();
    }
}


