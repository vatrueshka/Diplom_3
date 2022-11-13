package page.object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class MainPage {

    //локатор кнопки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private static SelenideElement constructorButton;

    //локатор кнопки "Лента заказов"
    @FindBy(how = How.XPATH, using = ".//p[text()='Лента Заказов']")
    private static SelenideElement orderFeedButton;

    //локатор логотипа "Stellar Burgers"
    @FindBy(how = How.XPATH, using = ".//div[starts-with(@class, 'AppHeader_header__logo__2D0X2')]")
    private static SelenideElement mainLogo;

    //локатор кнопки "Личный Кабинет"
    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private static SelenideElement yourAccountButton;

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private static SelenideElement loginButton;

    //// Раздел «Конструктор»
    //локатор вкладки Булки
    @FindBy(how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Булки']")
    public SelenideElement bunsTab;

    //локатор вкладки Соусы
    @FindBy(how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Соусы']")
    public SelenideElement saucesTab;

    //локатор вкладки Начинки
    @FindBy(how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Начинки']")
    public SelenideElement ingredientsTab;

    //локатор заголовка Булки
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    public SelenideElement bunsHeader;

    //локатор заголовка Соусы
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    public SelenideElement saucesHeader;

    //локатор заголовка Начинки
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    public SelenideElement ingredientsHeader;

    //метод клика по кнопке "Конструктор"
    public void constructorButtonClick() {
        constructorButton.click();
    }

    //метод клика по кнопке-логотипу "Stellar Burgers"
    public void mainLogoClick() {
        mainLogo.click();
    }

    //метод клика по кнопке "Личный Кабинет"
    public void yourAccountButtonClick() {
        yourAccountButton.click();
    }

    //метод клика кнопке "Войти в аккаунт"
    public void loginButtonClick() {
        loginButton.click();
    }

    ////Методы для раздела Конструктор
    //метод клика по вкладке Булки
    @Step("Клик по вкладке Булки")
    public void bunTabClick() {
        bunsTab.click();
    }

    //метод клика по вкладке Соусы
    @Step("Клик по вкладке Соусы")
    public void saucesTabClick() {
        saucesTab.click();
    }

    //метод клика по вкладке Начинки
    @Step("Клик по вкладке Начинки")
    public void ingredientsTabClick() {
        ingredientsTab.click();
    }

    //Общий метод проверки перехода к вкладкам конструктора
    @Step("Проверка перехода к разделу после клика по вкладке конструктора")
    public boolean checkConstructorTabClick(String tabName) {
        //выбор проверяемой вкладки
        SelenideElement tab = null;
        switch (tabName) {
            case "Buns":
                tab = bunsHeader;
                break;
            case "Sauces":
                tab = saucesHeader;
                break;
            case "Ingredients":
                tab = ingredientsHeader;
        }

        //проверка того, что, передаваемая в метод, вкладка выбрана
        return tab.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }
}

