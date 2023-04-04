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
            new Item("Common item", 5, 7),
            new Item("Aged Brie", 2, 0),
            new Item("Sulfuras, Hand of Ragnaros", 1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        };
        app = new GildedRose(items);
    }

    @AfterEach
    void clearItems(){
        items = null;
        app = null;
    }
    @Test
    void givenCommonItemWithNotExpiredSellDate_whenComeEndOfTheDay_thenQualityIsAltered() {
        int expectedQuality = app.items[0].quality - 1;
        app.updateQuality();

        assertEquals("Common item", app.items[0].name);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void givenCommonItemWithExpiredSellDate_whenComeEndOfTheDay_thenQualityIsAlteredTwice() {
        int expectedQuality = app.items[0].quality - 2;
        app.items[0].sellIn = 0;

        app.updateQuality();

        assertEquals("Common item", app.items[0].name);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void givenCommonItemWithZeroQuality_whenComeEndOfTheDay_thenQualityIsNotNegative() {
        int expectedQuality = 0;
        app.items[0].quality = 0;
        app.updateQuality();

        assertEquals("Common item", app.items[0].name);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void givenAgedBrieWithNotPassedSellDate_whenComeEndOfTheDay_thenQualityIsAltered() {
        int expectedQuality = app.items[1].quality + 1;
        app.updateQuality();

        assertEquals("Aged Brie", app.items[1].name);
        assertEquals(expectedQuality, app.items[1].quality);
    }

    @Test
    void givenAgedBrieWithPassedSellDate_whenComeEndOfTheDay_thenQualityIsAlteredTwice() {
        app.items[1].sellIn = 0;
        int expectedQuality = app.items[1].quality + 2;
        app.updateQuality();

        assertEquals("Aged Brie", app.items[1].name);
        assertEquals(expectedQuality, app.items[1].quality);
    }

    @Test
    void givenAgedBrieWithMaxQuality_whenComeEndOfTheDay_thenQualityIsNotUpperThanMax() {
        int expectedQuality = 50;
        app.items[1].quality = 50;

        app.updateQuality();

        assertEquals("Aged Brie", app.items[1].name);
        assertEquals(expectedQuality, app.items[1].quality);
    }

    @Test
    void givenSulfuras_whenComeEndOfTheDay_thenQualityIsNotAltered(){
        int sulfurasExpectedQuality = 80;

        app.updateQuality();

        assertEquals("Sulfuras, Hand of Ragnaros", app.items[2].name);
        assertEquals(sulfurasExpectedQuality, app.items[2].quality);
    }

    @Test
    void givenExpiredSulfuras_whenComeEndOfTheDay_thenQualityIsNotAltered(){
        int sulfurasExpectedQuality = 80;
        app.items[2].sellIn = 0;

        app.updateQuality();

        assertEquals("Sulfuras, Hand of Ragnaros", app.items[2].name);
        assertEquals(sulfurasExpectedQuality, app.items[2].quality);
    }

    @Test
    void given11DaysOrMoreBackstagePasses_whenComeEndOfTheDay_thenQualityIsAltered(){
        int expectedQuality = app.items[3].quality + 1;

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(expectedQuality, app.items[3].quality);
    }

    @Test
    void given10DaysOrLessBackstagePasses_whenComeEndOfTheDay_thenQualityIsAlteredTwice(){
        app.items[3].sellIn = 10;
        int expectedQuality = app.items[3].quality + 2;

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(expectedQuality, app.items[3].quality);
    }

}
