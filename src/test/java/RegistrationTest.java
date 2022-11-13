import api.User;
import api.UserClientSteps;
import api.UserCredentials;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import page.object.BaseUrls;
import page.object.RegisterPage;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;
import static page.object.BaseUrls.REGISTER_PAGE_URL;

public class RegistrationTest {
    BaseUrls baseUrls = page(BaseUrls.class);
    RegisterPage registerPage = page(RegisterPage.class);

    private UserClientSteps userClientSteps = new UserClientSteps();

    private User user;

    @Before
    public void setBrowser() {
        Configuration.browser = "chrome";
        //создание данных для юзера (имя, email, пароль)
        user = User.getRandom();
        //открытие страницы регистрации
        Selenide.open(REGISTER_PAGE_URL);
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void successRegistrationTest() {
        //регистрация на фронте
        registerPage.enterRegistrationFields(user.name, user.email, user.password);
        registerPage.registerButtonClick();

        //проверка, что после успешной регистрации открывается страница логина
        assertTrue("Login via registered user failed", baseUrls.checkLoginPageOpened());

        //проверка логина под созданным юзером с помощью api
        ValidatableResponse validatableResponse = userClientSteps.login(UserCredentials.from(user));
        validatableResponse.assertThat().statusCode(200);
        validatableResponse.assertThat().body("success", equalTo(true));

        //удаление юзера
        String bearerToken = validatableResponse.extract().path("accessToken");
        userClientSteps.delete(bearerToken);
    }

    @Test
    @DisplayName("Проверка отображения ошибки для некорректного пароля")
    public void incorrectPasswordRegistrationTest() {
        //создание случайного пароля с максимальной длинной в 6 символов
        Faker faker = new Faker();
        String password = faker.internet().password(1, 6);

        //регистрация на фронте
        registerPage.enterRegistrationFields(user.name, user.email, password);
        registerPage.registerButtonClick();

        //проверка отображения ошибки
        registerPage.passwordErrorCheck();
//        assertTrue("", registerPage.passwordErrorCheck()); //мб сделать булин чтобы ассерт написать
    }

}
