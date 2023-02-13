package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Property {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String DRIVER_PATH = "C:\\WebDriver\\bin\\yandexdriver.exe";
    public static final String PATH_REGISTER = "/register";
    public static final String PATH_LOGIN = "/login";
    public static final String PATH_MAIN = "/";
    public static final String PATH_FOGOT_PASS = "/forgot-password";
    public static final String PATH_PERSONAL_ACCOUNT = "/account/profile";
    public static final String NAME = RandomStringUtils.randomAlphabetic(3, 5);
    public static final String EMAIL = RandomStringUtils.randomAlphanumeric(3, 9) + emailRandom();
    public static final String PASSWORD = RandomStringUtils.randomAlphanumeric(6, 9);
    public static String emailRandom() {
        String[] mails = new String[]{"@yandex.ru", "@ya.ru", "@gmail.com", "@mail.ru"};
        int randomMails = new Random().nextInt(mails.length);
        return mails[randomMails];
    }
}
