package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestTab {

    @Test
    void shouldCheckOrderCardWithDelivery() {
        Configuration.headless = true;
        open("http://localhost:9999");
        $("[data-test-id='city']").$("[type='text']").setValue("Ря");
        $$(".menu-item__control").find(exactText("Рязань")).click();

        // Вместо setValue использовал getValue чтобы по умолчанию установить ближайшую доступную дату и тест не
        // пришлось бы править каждый раз как при использовании setValue
        $("[type='tel']").getCssValue("value");
        $("[data-test-id='name']").$("[type='text']").setValue("Андреев Андрей");
        $$("[type='tel']").last().setValue("+79998885577");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}
