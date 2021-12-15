package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpAndLoginTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
       driver.quit();
       driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void unauthorizedAccess() {
        driver.get("http://localhost:" + port + "/result");
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    private void accommodateForPageLoadTime() throws InterruptedException {
        Thread.sleep(2000);
    }



    @Test
    public void signupUser() throws InterruptedException {

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

        Assertions.assertEquals("Home", driver.getTitle());
        accommodateForPageLoadTime();

        homePage.logout();
        accommodateForPageLoadTime();

        driver.get("http://localhost:" + port + "/home");
        accommodateForPageLoadTime();

        Assertions.assertEquals("Login", driver.getTitle());

    }

}
