package ru.netology.pageobject.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByText;
import ru.netology.pageobject.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ReplenishmentCard {


    public ReplenishmentCard() {
        $(byText("Пополнение карты")).shouldBe(visible);
    }


    public void replenishmentCard(Integer amountToTransfer, String cardNumber) {
        $("[data-test-id = amount] input").setValue(String.valueOf(amountToTransfer));
        $("[data-test-id = from] input").setValue(String.valueOf(cardNumber));
        $("[data-test-id = action-transfer]").click();

    }

    public void invalidReplenishmentCard(Integer amountToTransfer, String cardNumber) {
        replenishmentCard(amountToTransfer, cardNumber);
        $("[data-test-id = error-notification] input").shouldBe(visible);  //проверка на появление сообщения о недопустимости операции
    }

}
