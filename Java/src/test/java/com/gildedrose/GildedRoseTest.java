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
            new Item("Common item", 0, 0),
            new Item("Aged Brie", 0, 0),
            new Item("Sulfuras, Hand of Ragnaros", 1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0),
            new Item("Conjured item", 0, 0)
        };
        app = new GildedRose(items);
    }

    @AfterEach
    void clearItems(){
        items = null;
        app = null;
    }

    private void setGivenItem(int position, int sellIn, int quality){
        items[position].sellIn = sellIn;
        items[position].quality = quality;

    }
    @Test
    void givenCommonItemWithNotExpiredSellDate_whenComeEndOfTheDay_thenQualityIsDegraded() {
        setGivenItem(0, 2, 2);
        int expectedQuality = app.items[0].quality - 1;
        app.updateQuality();

        assertEquals("Common item", app.items[0].name);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void givenCommonItemWithExpiredSellDate_whenComeEndOfTheDay_thenQualityIsDegradedTwice() {
        setGivenItem(0, -1, 3);
        int expectedQuality = app.items[0].quality - 2;

        app.updateQuality();

        assertEquals("Common item", app.items[0].name);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void givenCommonItemWithZeroQuality_whenComeEndOfTheDay_thenQualityIsNotNegative() {
        setGivenItem(0, 2, 0);
        int expectedQuality = 0;
        app.updateQuality();

        assertEquals("Common item", app.items[0].name);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    void givenAgedBrieWithNotPassedSellDate_whenComeEndOfTheDay_thenQualityIsIncreased() {
        setGivenItem(1, 2, 2);
        int expectedQuality = app.items[1].quality + 1;
        app.updateQuality();

        assertEquals("Aged Brie", app.items[1].name);
        assertEquals(expectedQuality, app.items[1].quality);
    }

    @Test
    void givenAgedBrieWithPassedSellDate_whenComeEndOfTheDay_thenQualityIsIncreasedTwice() {
        setGivenItem(1, -1, 2);
        int expectedQuality = app.items[1].quality + 2;
        app.updateQuality();

        assertEquals("Aged Brie", app.items[1].name);
        assertEquals(expectedQuality, app.items[1].quality);
    }

    @Test
    void givenAgedBrieWithMaxQuality_whenComeEndOfTheDay_thenQualityIsNotIncreasedThanMax() {
        setGivenItem(1, 2, 50);
        int expectedQuality = 50;

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
    void given11DaysOrMoreBackstagePasses_whenComeEndOfTheDay_thenQualityIsIncreased(){
        setGivenItem(3, 12, 2);
        int expectedQuality = app.items[3].quality + 1;

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(expectedQuality, app.items[3].quality);
    }

    @Test
    void given10DaysOrLessBackstagePasses_whenComeEndOfTheDay_thenQualityIsIncreasedTwice(){
        setGivenItem(3, 10, 2);
        int expectedQuality = app.items[3].quality + 2;

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(expectedQuality, app.items[3].quality);
    }

    @Test
    void given5DaysOrLessBackstagePasses_whenComeEndOfTheDay_thenQualityIsIncreasedThreeTimes(){
        setGivenItem(3, 5, 2);
        int expectedQuality = app.items[3].quality + 3;

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(expectedQuality, app.items[3].quality);
    }

    @Test
    void given0DaysOrLessBackstagePasses_whenComeEndOfTheDay_thenQualityIsUnderZero(){
        setGivenItem(3, -1, 2);
        int expectedQuality = 0;

        app.updateQuality();

        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[3].name);
        assertEquals(expectedQuality, app.items[3].quality);
    }

    @Test
    void givenConjuredItem_whenComeEndOfTheDay_thenQualityIsDegradedTwice(){
        setGivenItem(4, 2, 4);
        int expectedQuality = app.items[4].quality - 2;

        app.updateQuality();

        assertEquals("Conjured item", app.items[4].name);
        assertEquals(expectedQuality, app.items[4].quality);
    }

    @Test
    void givenExpiredConjuredItem_whenComeEndOfTheDay_thenQualityIsDegradedFourTimes(){
        setGivenItem(4, -1, 5);
        int expectedQuality = app.items[4].quality - 4;

        app.updateQuality();

        assertEquals("Conjured item", app.items[4].name);
        assertEquals(expectedQuality, app.items[4].quality);
    }

    @Test
    void givenConjuredItemWithZeroQuality_whenComeEndOfTheDay_thenQualityIsIsNotNegative(){
        setGivenItem(4, 2, 0);
        int expectedQuality = 0;

        app.updateQuality();

        assertEquals("Conjured item", app.items[4].name);
        assertEquals(expectedQuality, app.items[4].quality);
    }

    @Test
    void givenConjuredItemWithLessThan2Quality_whenComeEndOfTheDay_thenQualityIsIsNotNegative(){
        setGivenItem(4, 2, 1);
        int expectedQuality = 0;

        app.updateQuality();

        assertEquals("Conjured item", app.items[4].name);
        assertEquals(expectedQuality, app.items[4].quality);
    }
}
