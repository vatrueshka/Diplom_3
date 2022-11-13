package page.object;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BaseUrls {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public final static String LOGIN_PAGE_URL = BASE_URL + "login";
    public final static String REGISTER_PAGE_URL = BASE_URL + "register";
    public final static String FORGOT_PASSWORD_PAGE_URL = BASE_URL + "forgot-password";
    public final static String PROFILE_PAGE_URL = BASE_URL + "account/profile";

    @Step("Проверка, что открыта главная страница")
    public boolean checkMainPageOpened() {
        webdriver().shouldHave(url(BASE_URL));
        return true;
    }

    @Step("Проверка, что открыта страница логина")
    public boolean checkLoginPageOpened() {
        webdriver().shouldHave(url(LOGIN_PAGE_URL));
        return true;
    }

    @Step("Проверка, что открыта страница личного кабинета")
    public boolean checkProfilePageOpened() {
        webdriver().shouldHave(url(PROFILE_PAGE_URL));
        return true;
    }

}