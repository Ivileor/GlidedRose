package com.gildedrose.constants;

public final class ItemConstants {

    // Item name
    public static final String CONJURED = "Conjured";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";

    // Quality factor
    public static final int MAX_COMMON_ITEM_QUALITY_ALLOWED = 50;
    public static final int MIN_COMMON_ITEM_QUALITY_ALLOWED = 0;
    public static final int EXPIRED_ITEM_QUALITY_CHANGE_FACTOR = 2;
    public static final int COMMON_QUALITY_CHANGE = 1;
    public static final int CONJURED_QUALITY_DECREASING = 2;
    public static final int BACKSTAGE_PASSES_10_DAYS_INCREASE = 2;
    public static final int BACKSTAGE_PASSES_5_DAYS_INCREASE = 3;

    // Sell in factor
    public static final int SELL_IN_DECREASING_FACTOR = 1;

    private ItemConstants(){

    }

}
