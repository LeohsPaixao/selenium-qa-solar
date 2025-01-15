package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Classe que representa a página de login. */
public class LoginPage {
    /** Dispositivo para interar com os elementos. */
    private final WebDriver driver;
    /** Formulário de login. */
    private final WebElement formLogin;
    /** Logo da página. */
    private final WebElement logo;
    /** Campo de entrada de email. */
    private final WebElement emailInput;
    /** Campo de entrada de senha. */
    private final WebElement passwordInput;
    /** Botão de login. */
    private final WebElement loginButton;
    /** Link para a página de cadastro. */
    private final WebElement linkSignup;
    /** Link para a página de recuperação de senha. */
    private final WebElement linkRecoverPassword;

    /**
     * Construtor da classe LoginPage.
     *
     * @param driver WebDriver utilizado para interagir com a página.
     */
    public LoginPage(final WebDriver driver) {
        this.driver = driver;

        // Inicialização dos elementos
        formLogin = driver.findElement(
                By.cssSelector("[data-testid='form-login']"));
        logo = driver.findElement(
                By.cssSelector("[data-testid='logo']"));
        emailInput = driver.findElement(
                By.cssSelector("[data-testid='input-email']"));
        passwordInput = driver.findElement(
                By.cssSelector("[data-testid='input-password']"));
        loginButton = driver.findElement(
                By.cssSelector("[data-testid='btn-login']"));
        linkSignup = driver.findElement(
                By.cssSelector("[data-testid='link-signup']"));
        linkRecoverPassword = driver.findElement(
                By.cssSelector("[data-testid='link-recover-password']"));
    }

    /**
     * Aguarda até que o elemento esteja visível.
     *
     * @param element Elemento a ser aguardado.
     * @return true se o elemento estiver visível, false caso contrário.
     */
    public boolean waitForElementToBeVisible(final WebElement element) {
        long time = 10;

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(time));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * Verifica se o formulário de login está visível.
     *
     * @return true se o formulário estiver visível, false caso contrário.
     */
    public boolean isFormLoginVisible() {
        return formLogin.isDisplayed();
    }

    /**
     * Verifica se o logo está visível.
     *
     * @return true se o logo estiver visível, false caso contrário.
     */
    public boolean isLogoVisible() {
        return logo.isDisplayed();
    }

    /**
     * Verifica se o campo de email está visível.
     *
     * @return true se o campo de email estiver visível, false caso contrário.
     */
    public boolean isEmailInputVisible() {
        return emailInput.isDisplayed();
    }

    /**
     * Verifica se o campo de senha está visível.
     *
     * @return true se o campo de senha estiver visível, false caso contrário.
     */
    public boolean isPasswordInputVisible() {
        return passwordInput.isDisplayed();
    }

    /**
     * Verifica se o botão de login está desabilitado.
     *
     * @return true se o botão de login estiver desabilitado,
     * false caso contrário.
     */
    public boolean isLoginButtonDisabled() {
        return !loginButton.isEnabled();
    }

    /**
     * Verifica se o botão de login está habilitado.
     *
     * @return true se o botão de login estiver habilitado,
     * false caso contrário.
     */
    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    /**
     * Preenche o campo de email.
     *
     * @param email Email a ser preenchido.
     */
    public void fillEmail(final String email) {
        emailInput.sendKeys(email);
    }

    /**
     * Preenche o campo de senha.
     *
     * @param password Senha a ser preenchida.
     */
    public void fillPassword(final String password) {
        passwordInput.sendKeys(password);
    }

    /** Clica no botão de login. */
    public void clickLoginButton() {
        loginButton.click();
    }

    /** Clica duas vezes no botão de login. */
    public void doubleClickLoginButton() {
        Actions actions = new Actions(driver);
        actions.doubleClick(loginButton).perform();
    }

    /**
     * Obtém o texto do conteúdo do toast.
     *
     * @param message Mensagem esperada no toast.
     * @return Texto do conteúdo do toast.
     */
    public boolean getToastContentText(final String message) {
        WebElement toastContent = driver.findElement(
                By.cssSelector("[data-testid='toast-content']"));
        String.format(message, toastContent);
        return waitForElementToBeVisible(toastContent);
    }

    /** Clica no link de cadastro. */
    public void clickSignupLink() {
        linkSignup.click();
    }

    /** Clica no link de recuperação de senha. */
    public void clickRecoverPasswordLink() {
        linkRecoverPassword.click();
    }

    /** Aguarda até que a página esteja carregada. */
    public void waitForPageToLoad() {
        waitForElementToBeVisible(formLogin);
    }
}
