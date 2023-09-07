package ru.netology.pageobject.pageObject;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    ElementsCollection cards = $$(".list__item div");

    public DashboardPage() {    //конструктор на проверку видимости (загрузки) окна Dashboard при создании экземпляра
        $("[data-test-id = dashboard]").shouldBe(visible);
    }

    public int getCardBalance(int i) {

        var text = cards.get(i).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {

        var value = text.substring(text.indexOf(": ") + 2, text.indexOf(" р"));
        return Integer.parseInt(value);
    }

    public void selectCardToTransfer(int i) {
        ElementsCollection cards = $$("[data-test-id = action-deposit]");
        cards.get(i).click();

    }


}


