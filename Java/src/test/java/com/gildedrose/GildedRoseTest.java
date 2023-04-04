package com.gildedrose;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item[] items;
    private GildedRose app;
    @BeforeEach
    void initItems(){
        items = new Item[] {
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 1, 80)
        };
        app = new GildedRose(items);
    }

    @AfterEach
    void clearItems(){
        items = null;
        app = null;
    }
    @Test
    void givenCommonItemWithNotPassedSellDate_whenComeEndOfTheDay_thenQualityIsAltered() {
        int expectedQuality = app.items[0].quality - 1;
        app.updateQuality();
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void givenSulfuras_whenComeEndOfTheDay_thenQualityIsNotAltered(){
        int sulfurasExpectedQuality = 80;

        app.updateQuality();

        assertEquals("Sulfuras, Hand of Ragnaros", app.items[1].name);
        assertEquals(sulfurasExpectedQuality, app.items[1].quality);
    }

}
