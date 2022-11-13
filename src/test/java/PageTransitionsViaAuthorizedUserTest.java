import api.User;
import api.UserClientSteps;
import api.UserCredentials;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.object.BaseUrls;
import page.object.LoginPage;
import page.object.MainPage;

import static com.codeborne.selenide.Selenide.page;
import static page.object.BaseUrls.LOGIN_PAGE_URL;

public class PageTransitionsViaAuthorizedUserTest {

    BaseUrls baseUrls = page(BaseUrls.class);
    MainPage mainPage = page(MainPage.class);
    LoginPage loginPage = page(LoginPage.class);

    private UserClientSteps userClientSteps = new UserClientSteps();
    private User user;
    private ValidatableResponse validatableResponse;

    @Before
    public void setBrowser() {
        Configuration.browser = "chrome";

        //создание юзера через api
        user = User.getRandom();
        validatableResponse = userClientSteps.create(user);
        //логин под созданным юзером
        Selenide.open(LOGIN_PAGE_URL);
        loginPage.enterLoginFields(user.email, user.password);
        loginPage.loginButtonClick();

    }

    @After
    public void tearDown() {
        //закрытие браузера
        Selenide.closeWebDriver();
        //удаление созданного юзера
        validatableResponse = userClientSteps.login(UserCredentials.from(user));
        String bearerToken = validatableResponse.extract().path("accessToken");
        userClientSteps.delete(bearerToken);
    }

    @Test
    @DisplayName("Проверка перехода по клику на Личный кабинет под авторизованным юзером")
    public void YourAccountButtonTransitionViaAuthorizedUserTest() {
        mainPage.yourAccountButtonClick();
        baseUrls.checkProfilePageOpened();
    }

    @Test
    @DisplayName("Проверка перехода по клику на кнопку Конструктор под авторизованным юзером")
    public void ConstructorButtonTransitionViaAuthorizedUserTest() {
        mainPage.yourAccountButtonClick();
        mainPage.constructorButtonClick();
        baseUrls.checkMainPageOpened();
    }

    @Test
    @DisplayName("Проверка перехода по клику логотип Stellar Burgers под авторизованным юзером")
    public void MainLogoTransitionViaAuthorizedUserTest() {
        mainPage.yourAccountButtonClick();
        mainPage.mainLogoClick();
        baseUrls.checkMainPageOpened();
    }
}