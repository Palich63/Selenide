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

public class TestTab {

    @Test
    void shouldCheckEveryStepForOrder() {
        open("http://localhost:9999");
        $("[data-test-id='city']").$("[type='text']").setValue("Ря");
        $$(".menu-item__control").find(exactText("Рязань")).click();

        LocalDate today = LocalDate.now();
        LocalDate plusWeek = today.plus(7, ChronoUnit.DAYS);
        int dayToday = today.getDayOfMonth();
        int day = plusWeek.getDayOfMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String text = plusWeek.format(formatter);

        $("[data-test-id='date']").$("[class='icon-button__content']").click();

        if (dayToday > day) {
            $("[data-step='1']").click();
            $("[class='popup__container']").$$("td.calendar__day").find(exactText(String.valueOf(day))).click();
        } else {
            $("[class='popup__container']").$$("td.calendar__day").find(exactText(String.valueOf(day))).click();
        }


        $("[data-test-id='date']").$("[type='tel']").sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        $("[data-test-id='date']").$("[type='tel']").setValue(text);
        $("[data-test-id='name']").$("[type='text']").setValue("Андреев Андрей");
        $("[data-test-id='phone']").$("[type='tel']").setValue("+79998885577");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText(String.format("%02d", plusWeek.getDayOfMonth()) + "." + String.format("%02d", plusWeek.getMonthValue()) + "." + plusWeek.getYear())).waitUntil(visible, 15000);
    }
}
