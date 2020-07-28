package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {

    @Test
    void shouldCheckOrderCardWithDelivery() {
        open("http://localhost:9999");
        $("[data-test-id='city']").$("[type='text']").setValue("Самара");

        LocalDate today = LocalDate.now();
        LocalDate plusFourDay = today.plus(4, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String text = plusFourDay.format(formatter);

        $("[data-test-id='date']").$("[type='tel']").sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
        $("[data-test-id='date']").$("[type='tel']").setValue(text);
        $("[data-test-id='name']").$("[type='text']").setValue("Андреев Андрей");
        $("[data-test-id='phone']").$("[type='tel']").setValue("+79998885577");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText(String.format(text))).waitUntil(visible, 15000);
    }
}
