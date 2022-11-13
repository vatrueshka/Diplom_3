import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.object.MainPage;

import static com.codeborne.selenide.Selenide.page;
import static page.object.BaseUrls.BASE_URL;

public class ConstructorTabsTest {
    MainPage mainPage = page(MainPage.class);

    @Before
    public void setBrowser() {
        Configuration.browser = "chrome";
        //открываем главную страницу
        Selenide.open(BASE_URL);
    }

    @Test
    @DisplayName("Проверка перехода по клику на вкладку Булки")
    public void bunsTabClickTest() {
        mainPage.saucesTabClick();
        mainPage.bunTabClick();
        mainPage.checkConstructorTabClick("Buns");
    }

    @Test
    @DisplayName("Проверка перехода по клику на вкладку Соусы")
    public void saucesTabClickTest() {
        mainPage.saucesTabClick();
        mainPage.checkConstructorTabClick("Sauces");
    }

    @Test
    @DisplayName("Проверка перехода по клику на вкладку Начинки")
    public void ingredientsTabClickTest() {
        mainPage.ingredientsTabClick();
        mainPage.checkConstructorTabClick("Ingredients");
    }

}
