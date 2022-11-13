package page.object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {

    //локатор ссылке "Войти"
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private static SelenideElement loginButton;

    //метод клика по ссылке "Войти"
    @Step("Клик по ссылке Войти")
    public void loginButtonClick() {
        loginButton.click();
    }
}
