package page.object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {

    //локатор кнопки "Выход"
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement exitButton;

    //метод клика по кнопке "Выход"
    @Step("Клик по кнопке Выйти")
    public void exitButtonClick() {
        exitButton.click();
    }
}
