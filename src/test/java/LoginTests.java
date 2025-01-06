

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8181/");

        loginPage = new LoginPage(driver);
        loginPage.waitForPageToLoad();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldDisplayLoginScreenElements() {
        Assert.assertTrue(loginPage.isFormLoginVisible(), "Formulário de login não está visível.");
        Assert.assertTrue(loginPage.isLogoVisible(), "Logo não está visível.");
        Assert.assertTrue(loginPage.isEmailInputVisible(), "Campo de email não está visível.");
        Assert.assertTrue(loginPage.isPasswordInputVisible(), "Campo de senha não está visível.");
        Assert.assertTrue(loginPage.isLoginButtonDisabled(), "Botão de login deveria estar desabilitado.");
    }

    @Test
    public void shouldNotAllowLoginWithInvalidCredentials() {
        Assert.assertTrue(loginPage.isLoginButtonDisabled(), "Botão de login deveria estar desabilitado.");
        loginPage.fillEmail("email@example.com");
        loginPage.fillPassword("password@example.com");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Botão de login deveria estar habilitado.");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getToastContentText().contains("Não foi possivel realizar login com este usuário."));
    }

    @Test
    public void shouldNotAllowLoginWithInvalidPassword() {
        Assert.assertTrue(loginPage.isLoginButtonDisabled(), "Botão de login deveria estar desabilitado.");
        loginPage.fillEmail("generic@example.com");
        loginPage.fillPassword("password");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Botão de login deveria estar habilitado.");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getToastContentText().contains("A senha não confere."));
    }

    @Test
    public void shouldAllowLoginWithValidCredentials() {
        Assert.assertTrue(loginPage.isLoginButtonDisabled(), "Botão de login deveria estar desabilitado.");
        loginPage.fillEmail("generic@example.com");
        loginPage.fillPassword("123456");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Botão de login deveria estar habilitado.");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getToastContentText().contains("Login realizado com sucesso!"));
    }

    @Test
    public void shouldNavigateToSignupScreen() {
        loginPage.clickSignupLink();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/signup"), "URL não redirecionou corretamente para a tela de cadastro.");
    }

    @Test
    public void shouldNavigateToRecoverPasswordScreen() {
        loginPage.clickRecoverPasswordLink();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/recover-password"), "URL não redirecionou corretamente para a tela de recuperação de senha.");
    }
}
