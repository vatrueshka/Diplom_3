package page.object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {

    //локатор поля ввода "Имя"
    @FindBy(how = How.XPATH, using = ".//form//div[label[text()='Имя']]/input")
    private SelenideElement nameField;

    //локатор поля ввода "Email"
    @FindBy(how = How.XPATH, using = ".//form//div[label[text()='Email']]/input")
    private SelenideElement emailField;

    //локатор поля ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//form//div[label[text()='Пароль']]/input")
    private SelenideElement passwordField;

    //локатор "Некорректный пароль"
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement passwordError;

    //локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    //локатор ссылки "Войти"
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement loginButtonRegistrationPage;

    //метод ввода значения в поле "Имя"
    @Step("Ввод значения в поле Имя")
    public void enterNameField(String username) {
        nameField.sendKeys(username);
    }

    //метод ввода значения в поле "Email"
    @Step("Ввод значения в поле Email")
    public void enterEmailField(String email) {
        emailField.sendKeys(email);
    }

    //метод ввода значения в поле "Пароль"
    @Step("Ввод значения в поле Пароль")
    public void enterPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    //метод ввода всех значений в поля регистрации
    @Step("Ввод значений во все поля регистрации")
    public void enterRegistrationFields(String username, String email, String password) {
        enterNameField(username);
        enterEmailField(email);
        enterPasswordField(password);
    }

    //метод клика по кнопке "Зарегистрироваться"
    @Step("Клик по кнопке Зарегистрироваться")
    public void registerButtonClick() {
        registerButton.click();
    }

    //метод проверки видимости ошибки при вводе некорректного пароля
    @Step("Проверка отображения ошибки при вводе некорректного пароля")
    public void passwordErrorCheck() {
        passwordError.should(Condition.visible);
    }

    //метод клика по ссылке "Войти"
    @Step("Клик по ссылке Войти")
    public void loginButtonRegistrationPageClick() {
        loginButtonRegistrationPage.click();
    }
}
