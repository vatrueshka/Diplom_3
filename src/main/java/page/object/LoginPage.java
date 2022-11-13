package page.object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    //локатор поля ввода "Email"
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private static SelenideElement emailField;

    //локатор поля ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private static SelenideElement passwordField;

    //локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private static SelenideElement loginButton;

    //метод ввода значения в поле "Email"
    @Step("Ввод значений в поля Email и Пароль")
    public void enterLoginFields(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    //метод клика по кнопке "Войти"
    @Step("Клик по ссылке Войти")
    public void loginButtonClick() {
        loginButton.click();
    }
}
