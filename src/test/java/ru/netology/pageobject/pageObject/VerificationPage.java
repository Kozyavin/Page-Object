package ru.netology.pageobject.pageObject;

import com.codeborne.selenide.Condition;
import ru.netology.pageobject.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    public VerificationPage(){ //конструктор на проверку видимости (загрузки) окна ввода кода
        $("[data-test-id = code] input").shouldBe(Condition.visible);
    }
    public void verifyCode(DataHelper.VerificationCode verificationCode){

        $("[data-test-id = code] input").setValue(verificationCode.getCode());
        $("[data-test-id = action-verify] .button__content").click();
    }
}
