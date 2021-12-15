package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    @FindBy(id = "logout-submit-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "add-new-note-button")
    private WebElement addNewNoteButton;

    @FindBy(id = "add-new-credential-button")
    private WebElement addNewCredentialButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

   @FindBy(id = "credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement credentialUserNameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "saveNote")
    private WebElement noteSubmitButton;

    @FindBy(id = "saveCredential")
    private WebElement credentialSubmitButton;

    @FindBy(id = "edit-note")
    private WebElement editNoteButton;

    @FindBy(name = "edit-credential")
    private List<WebElement> listedCredentialEditButtons;

    @FindBy(id = "delete-note")
    private WebElement deleteNoteButton;

    @FindBy(id = "delete-credential")
    private WebElement deleteCredentialButton;

    @FindBy(id = "noteTitle")
    private WebElement listedNoteTitleField;


    @FindBy(id = "noteDescription")
    private WebElement listedDescriptionField;


    @FindBy(name = "displayed-credential-url")
    private List<WebElement> displayedCredentialsUrlField;

    @FindBy(name = "displayed-credential-username")
    private List<WebElement> displayedCredentialsUserNameField;

    @FindBy(name = "displayed-credential-password")
    private List<WebElement> displayedCredentialsPasswordField;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void goToNotesTab() {
        this.notesTab.click();
    }

    public void goToCredentialsTab() {
        this.credentialsTab.click();
    }

    public void clickOnAddNewNote() {
        this.addNewNoteButton.click();
    }

    public void clickOnAddNewCredential() {
        this.addNewCredentialButton.click();
    }


    public void fillInNoteForm(String title, String description) {
        this.noteTitleField.sendKeys(title);
        this.noteDescriptionField.sendKeys(description);

    }

    public void fillInCredentialForm(String url, String userName, String password)
    {
        this.credentialUrlField.clear();
        this.credentialUrlField.sendKeys(url);

        this.credentialUserNameField.clear();
        this.credentialUserNameField.sendKeys(userName);

        this.credentialPasswordField.clear();
        this.credentialPasswordField.sendKeys(password);
    }

    public void clickOnSubmitNoteButton()
    {
        this.noteSubmitButton.click();
    }
    public void clickOnSubmitDescriptionButton()
    {
        this.credentialSubmitButton.click();
    }

    public void clickOnEditNote()
    {
        this.editNoteButton.click();
    }

    public String getListedNoteTitle()
    {
        return this.listedNoteTitleField.getText();
    }

    public String getListedDescription()
    {
        return this.listedDescriptionField.getText();
    }

    public void editNoteTitle(String newTitle)
    {
        this.noteTitleField.clear();
        this.noteTitleField.sendKeys(newTitle);
    }

    public void editNoteDescription(String newDescription)
    {
        this.noteDescriptionField.clear();
        this.noteDescriptionField.sendKeys(newDescription);
    }

    public void clickOnDeleteNoteButton()
    {
        this.deleteNoteButton.click();
    }

    public void clickOnDeleteCredentialButton()
    {
        this.deleteCredentialButton.click();
    }


    public List<String> getDisplayedCredentialsUrls()
    {
        List<String> urls;
        urls = new ArrayList<>();
        for (WebElement webElement: this.displayedCredentialsUrlField)
        {
            urls.add(webElement.getText());
        }
        return urls;
    }

    public List<String> getDisplayedCredentialsUserName()
    {
        List<String> userNames;
        userNames = new ArrayList<>();
        for (WebElement webElement: this.displayedCredentialsUserNameField)
        {
            userNames.add(webElement.getText());
        }
        return userNames;
    }


    public List<String> getDisplayedCredentialsPassword()
    {
        List<String> encryptedPasswords;
        encryptedPasswords = new ArrayList<>();
        for (WebElement webElement: this.displayedCredentialsPasswordField)
        {
            encryptedPasswords.add(webElement.getText());
        }
        return encryptedPasswords;
    }

    public void clickOnEditCredential(Integer index)
    {
        this.listedCredentialEditButtons.get(index).click();
    }

    public void clearTheCredentialForm()
    {
        this.credentialUrlField.clear();
        this.credentialUserNameField.clear();
        this.credentialPasswordField.clear();
    }

    public String getCurrentUrl()
    {
        return this.credentialUrlField.getText();
    }

}
