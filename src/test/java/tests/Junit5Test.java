package tests;


import com.codeborne.selenide.Configuration;
import tests.data.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Junit5Test {

    @BeforeEach
    void setUp() {
        open("https://nice-case.ru/");
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @ValueSource(strings = {
            "Apple", "Xiaomi"
    })
    @ParameterizedTest(name = "Поисковый запрос {0} должен отдавать не пустой список товаров")
    void searchResultsShouldNotBeEmpty(String searchQuery){
        $("#title-search-input_fixed").setValue(searchQuery).pressEnter();
        $$(".digi-products").shouldBe(sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/test_data/firstResultShouldBeCorrect.csv")
    @ParameterizedTest(name = "Поисковый запрос {0} должен отображать товар {1} первым")
    void firstResultShouldBeCorrect(String searchQuery, String expectedText){
        $("#title-search-input_fixed").setValue(searchQuery).pressEnter();
        $(".digi-products")
                .shouldHave(text(expectedText));
    }

    @EnumSource(Apple.class)
    @ParameterizedTest
    void appleCatalogShoudHaveCorrectItems(Apple appleCatalog){
        $(".menu-button").click();
        $(".catalog-card").click();
        $(".section-compact-list").shouldBe(visible).shouldHave(text(appleCatalog.name));
    }
}

