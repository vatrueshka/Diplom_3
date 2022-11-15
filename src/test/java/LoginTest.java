import api.User;
import api.UserClientSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.object.*;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;
import static page.object.BaseUrls.*;

public class LoginTest {

    BaseUrls baseUrls = page(BaseUrls.class);
    MainPage mainPage = page(MainPage.class);
    LoginPage loginPage = page(LoginPage.class);
    RegisterPage registerPage = page(RegisterPage.class);
    ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);

    private UserClientSteps userClientSteps = new UserClientSteps();
    private User user;
    private ValidatableResponse validatableResponse;

    @Before
    public void setBrowser() {
        Configuration.browser = "chrome";
        //создание рандомного юзера через api
        user = User.getRandom();
        validatableResponse = userClientSteps.create(user);
    }


    @After
    public void tearDown() {
        String bearerToken = validatableResponse.extract().path("accessToken");
        userClientSteps.delete(bearerToken);
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void loginUsingLoginButtonOnMainPage() {
        Selenide.open(BASE_URL);

        mainPage.loginButtonClick();
        loginPage.enterLoginFields(user.getEmail(), user.getPassword());
        loginPage.loginButtonClick();

        assertTrue("Login failed", baseUrls.checkMainPageOpened());
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void loginUsingYourAccountButton() {
        Selenide.open(BASE_URL);

        mainPage.yourAccountButtonClick();
        loginPage.enterLoginFields(user.getEmail(), user.getPassword());
        loginPage.loginButtonClick();

        assertTrue("Login failed", baseUrls.checkMainPageOpened());
    }


    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void loginUsingLoginButtonOnRegistrationPage() {
        Selenide.open(REGISTER_PAGE_URL);

        registerPage.loginButtonRegistrationPageClick();
        loginPage.enterLoginFields(user.getEmail(), user.getPassword());
        loginPage.loginButtonClick();

        assertTrue("Login failed", baseUrls.checkMainPageOpened());
    }


    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void loginUsingLoginButtonOnForgotPassword() {
        Selenide.open(FORGOT_PASSWORD_PAGE_URL);

        forgotPasswordPage.loginButtonClick();
        loginPage.enterLoginFields(user.getEmail(), user.getPassword());
        loginPage.loginButtonClick();

        assertTrue("Login failed", baseUrls.checkMainPageOpened());
    }
}
