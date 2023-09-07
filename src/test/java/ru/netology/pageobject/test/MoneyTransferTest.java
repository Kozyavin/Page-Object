package ru.netology.pageobject.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pageobject.data.DataHelper;
import ru.netology.pageobject.pageObject.DashboardPage;
import ru.netology.pageobject.pageObject.LoginPage;
import ru.netology.pageobject.pageObject.ReplenishmentCard;
import ru.netology.pageobject.pageObject.VerificationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var client1 = DataHelper.getClient();
        loginPage.validLogin(client1);

        var verificationPage = new VerificationPage();
        var code = DataHelper.getVerificationCode(client1);
        verificationPage.verifyCode(code);

    }

    @Test
    void positiveTransferBetweenCard() {         //позитивный тест на операцию перевода денег между картами

        var dashboardPage = new DashboardPage();
        var balanceFirstCard = dashboardPage.getCardBalance(0);//получение баланса карты №1
        var balanceSecondCard = dashboardPage.getCardBalance(1);//получение баланса карты №2
        var summary = DataHelper.generateRandomSummary(balanceFirstCard);//генерация случайного числа для перевода
        var expectedFirstBalance = balanceFirstCard - summary;//получение проверяемого баланса после перевода без методов, карта №1
        var expectedSecondBalance = balanceSecondCard + summary;//получение проверяемого баланса после перевода без методов, карта №2
        dashboardPage.selectCardToTransfer(1);//выбор карты для перевода из двух(выбираем пополнить 2-ю)

        var replenishmentCard = new ReplenishmentCard();
        replenishmentCard.replenishmentCard(summary, String.valueOf(DataHelper.getFirstCardNumber()));//применяем метод перевода

        var actualBalanceFirstCard = dashboardPage.getCardBalance(0);//баланс после перевода, карта №1
        var actualBalanceSecondCard = dashboardPage.getCardBalance(1);//баланс после перевода, карта №2

        assertEquals(expectedFirstBalance, actualBalanceFirstCard);  //проверка соответствия по карте №1
        assertEquals(expectedSecondBalance, actualBalanceSecondCard);// проверка соответствия по карте №2

    }

    @Test
    void negativeTransferBetweenCard() {         //негативный тест на операцию перевода денег между картами

        var dashboardPage = new DashboardPage();
        var balanceFirstCard = dashboardPage.getCardBalance(0);//получение баланса карты №1
        var balanceSecondCard = dashboardPage.getCardBalance(1);//получение баланса карты №2
        var summary = DataHelper.generateNegativeRandomSummary(balanceFirstCard);//генерация случайного не валидного числа для перевода
        var expectedFirstBalance = balanceFirstCard - summary;//получение проверяемого баланса после перевода без методов, карта №1
        var expectedSecondBalance = balanceSecondCard + summary;//получение проверяемого баланса после перевода без методов, карта №2
        dashboardPage.selectCardToTransfer(1);//выбор карты для перевода из двух(выбираем пополнить 2-ю)

        var replenishmentCard = new ReplenishmentCard();
        replenishmentCard.invalidReplenishmentCard(summary, String.valueOf(DataHelper.getFirstCardNumber()));//применяем спец метод для не валидного перевода

        var actualBalanceFirstCard = dashboardPage.getCardBalance(0);//получение баланса карты №1 после попытки перевода(останется тем же)
        var actualBalanceSecondCard = dashboardPage.getCardBalance(1);//получение баланса карты №2 после попытки перевода(останется тем же)

        assertEquals(expectedFirstBalance, actualBalanceFirstCard);  //проверка соответствия по карте №1
        assertEquals(expectedSecondBalance, actualBalanceSecondCard);// проверка соответствия по карте №2

    }
}
