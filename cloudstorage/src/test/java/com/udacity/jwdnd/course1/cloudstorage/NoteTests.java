package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private HomePage homePage;
    private ResultPage resultPage;

    String title = "Project Note";
    String description = "This was really one of the best projects, I ever did!";

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
        resultPage = new ResultPage(driver);


        String firstName = "Mustafa";
        String lastName = "Abbas";
        String userName = "m.abbas";
        String password = "dummyPassword";

        WebDriverWait wait = new WebDriverWait(driver, 5);

        // Go to SignUp page
        loginPage.goToSignup();
        accommodateForPageLoadTime();

        signupPage.signup(firstName, lastName, userName, password);
        accommodateForPageLoadTime();

        driver.get("http://localhost:" + port + "/login");
        accommodateForPageLoadTime();

        loginPage.login(userName, password);
        accommodateForPageLoadTime();

        // Also, creating a note

        homePage.goToNotesTab();
        accommodateForPageLoadTime();

        homePage.clickOnAddNewNote();
        accommodateForPageLoadTime();

        homePage.fillInNoteForm(title, description);
        accommodateForPageLoadTime();

        homePage.clickOnSubmitNoteButton();
        accommodateForPageLoadTime();

        resultPage.clickHereToGoBack();
        accommodateForPageLoadTime();

        homePage.goToNotesTab();
        accommodateForPageLoadTime();


    }

    @AfterEach
    public void afterEach() throws InterruptedException {

        driver.get("http://localhost:" + port + "/home");
        accommodateForPageLoadTime();

        homePage.goToNotesTab();
        accommodateForPageLoadTime();

        // This is in a try - catch block because the delete test won't have any tests to delete
        try {
            homePage.clickOnDeleteNoteButton();
            accommodateForPageLoadTime();

            resultPage.clickHereToGoBack();
            accommodateForPageLoadTime();

        } catch (Exception ignored) {
            ;
        }
    }


    private void accommodateForPageLoadTime() throws InterruptedException {
        Thread.sleep(1000);
    }


    @Test
    public void createNewNote() throws InterruptedException {

        homePage.clickOnEditNote();
        accommodateForPageLoadTime();

        assertEquals(title, homePage.getListedNoteTitle());
        assertEquals(description, homePage.getListedDescription());

    }

    @Test
    public void editExistingNote() throws InterruptedException {

        String newTitle = "Sprint boot is fun";
        String newDescription = "What a project!";

        homePage.clickOnEditNote();
        accommodateForPageLoadTime();

        homePage.editNoteTitle(newTitle);
        accommodateForPageLoadTime();

        homePage.editNoteDescription(newDescription);
        accommodateForPageLoadTime();

        homePage.clickOnSubmitNoteButton();
        accommodateForPageLoadTime();

        resultPage.clickHereToGoBack();
        accommodateForPageLoadTime();

        homePage.goToNotesTab();
        accommodateForPageLoadTime();

        homePage.clickOnEditNote();
        accommodateForPageLoadTime();

        assertEquals(newTitle, homePage.getListedNoteTitle());
        assertEquals(newDescription, homePage.getListedDescription());

    }

    @Test
    public void deleteExistingNote() throws InterruptedException {

        homePage.clickOnDeleteNoteButton();
        accommodateForPageLoadTime();

        resultPage.clickHereToGoBack();
        accommodateForPageLoadTime();

        homePage.goToNotesTab();
        accommodateForPageLoadTime();

        // Clicking on EditNote - that doesn't exist - will throw an error
        assertThrows(NoSuchElementException.class, () -> homePage.clickOnEditNote());

    }

}
