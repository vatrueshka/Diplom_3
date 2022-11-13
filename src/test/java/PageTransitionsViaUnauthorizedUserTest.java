import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.object.BaseUrls;
import page.object.MainPage;

import static com.codeborne.selenide.Selenide.page;
import static page.object.BaseUrls.BASE_URL;

public class PageTransitionsViaUnauthorizedUserTest {

    BaseUrls baseUrls = page(BaseUrls.class);
    MainPage mainPage = page(MainPage.class);

    @Before
    public void setBrowser() {
        Configuration.browser = "chrome";
        //открываем главную страницу
        Selenide.open(BASE_URL);
    }

    @Test
    @DisplayName("Проверка перехода по клику на Личный кабинет")
    public void goToProfilePageUnauthorizedUserTest() {
        mainPage.yourAccountButtonClick();
        baseUrls.checkLoginPageOpened();
    }
}
