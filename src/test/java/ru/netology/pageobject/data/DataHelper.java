package ru.netology.pageobject.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {    //класс, описывающий , в нашем случае, пользователей

    private DataHelper() {
    }

    ;   //конструктор(пустой)

    //методы, которые возвращают пльзователей (клиентов)
    public static Client getClient() {         //описываем клиента с заданными логином и паролем (метод обращения)
        Client client1 = new Client("vasya", "qwerty123");
        return client1;
    }

    public static Client getOutherClient() {   //описываем другого клиента с заданными логином и паролем (метод обращения)
        Client client2 = new Client("vasya", "querty123");
        return client2;
    }

    public static VerificationCode getVerificationCode(Client client) {//метод получения кода
        VerificationCode code = new VerificationCode("12345");
        return code;
    }

    public static int generateRandomSummary(int n) //метод генерации случайного числа
    {
        // генерируем случайное число от 0 до `n`
        return new Random().nextInt(n) + 1;
    }

    public static int generateNegativeRandomSummary(int n) //метод генерации не валидного случайного числа
    {
        // генерируем случайное число от 0 до `n`
        return new Random().nextInt(n) + 10001;
    }

    public static CardInfo getFirstCardNumber() {
        var cardNumber = new CardInfo("5559 0000 0000 0001");
        return cardNumber;
    }

    public static CardInfo getSecondCardNumber() {
        var cardNumber = new CardInfo("5559 0000 0000 0002");
        return cardNumber;
    }

    @Value
    public static class CardInfo {//экземпляр сласса CardInfo будет иметь поле cardNumber
        String cardNumber;
    }

    @Value    // создаёт посредством lombok вспомогательные методы (get,set и др.)
    public static class VerificationCode { //класс создания кода входа в ЛК. Экземпляр класса будет имеет поле code
        private String code;
    }

    @Value   // создаёт посредством lombok вспомогательные методы (get,set и др.)
    public static class Client {          //каждый клиент (экземпляр класса) имеет характеристики (поля): логин, пароль
        private String login;
        private String password;
    }


}
