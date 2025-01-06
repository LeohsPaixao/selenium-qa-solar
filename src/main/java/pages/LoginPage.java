package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    protected WebDriver driver;

    // Declaração das variáveis dos elementos
    private WebElement formLogin;
    private WebElement logo;
    private WebElement emailInput;
    private WebElement passwordInput;
    private WebElement loginButton;
    private WebElement toastContent;
    private WebElement linkSignup;
    private WebElement linkRecoverPassword;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // Inicialização dos elementos
        formLogin = driver.findElement(By.cssSelector("[data-testid='form-login']"));
        logo = driver.findElement(By.cssSelector("[data-testid='logo']"));
        emailInput = driver.findElement(By.cssSelector("[data-testid='input-email']"));
        passwordInput = driver.findElement(By.cssSelector("[data-testid='input-password']"));
        loginButton = driver.findElement(By.cssSelector("[data-testid='btn-login']"));
        linkSignup = driver.findElement(By.cssSelector("[data-testid='link-signup']"));
        linkRecoverPassword = driver.findElement(By.cssSelector("[data-testid='link-recover-password']"));
    }

    public boolean isFormLoginVisible() {
        return formLogin.isDisplayed();
    }

    public boolean isLogoVisible() {
        return logo.isDisplayed();
    }

    public boolean isEmailInputVisible() {
        return emailInput.isDisplayed();
    }

    public boolean isPasswordInputVisible() {
        return passwordInput.isDisplayed();
    }

    public boolean isLoginButtonDisabled() {
        return !loginButton.isEnabled();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void doubleClickLoginButton() {
        Actions actions = new Actions(driver);
        actions.doubleClick(loginButton).perform();
    }

    public String getToastContentText() {
        toastContent = driver.findElement(By.cssSelector("[data-testid='toast-content']"));

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toastContent));

        String toastMessageContent = toastContent.getText();
        return toastMessageContent;
    }

    public void clickSignupLink() {
        linkSignup.click();
    }

    public void clickRecoverPasswordLink() {
        linkRecoverPassword.click();
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(formLogin));
    }
}
