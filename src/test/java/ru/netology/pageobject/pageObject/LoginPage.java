package ru.netology.pageobject.pageObject;

import ru.netology.pageobject.data.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {   //инкапсулируем логику работы с нашим логином

    public void validLogin(DataHelper.Client client) {  //метод ввода валидного логина
        //передаём клиента (пользователя)

        $("[data-test-id = login] input").setValue(client.getLogin()); //вводим логин
        $("[data-test-id = password] input").setValue(client.getPassword()); //вводим пароль
        $("[data-test-id = action-login]").click(); //нажимаем кнопку "Продолжить"


    }


}
