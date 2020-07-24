package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {

    @Test
    void shouldCheckOrderCardWithDelivery() {
        Configuration.headless = true;
        open("http://localhost:9999");
        $$("[data-test-id]").first().$("[type='text']").setValue("Самара");
        // Вместо setValue использовал getValue чтобы по умолчанию установить ближайшую доступную дату и тест не
        // пришлось бы править каждый раз как при использовании setValue
        $$("[type='tel']").first().getCssValue("value");
        $("[data-test-id='name']").$("[type='text']").setValue("Андреев Андрей");
        $$("[type='tel']").last().setValue("+79998885577");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}
