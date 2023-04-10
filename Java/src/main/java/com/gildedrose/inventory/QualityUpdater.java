package com.gildedrose.inventory;

import com.gildedrose.constants.ItemConstants;

public final class QualityUpdater {

    private static QualityUpdater instance;

    private QualityUpdater(){}

    public static QualityUpdater getInstance(){
        if(instance == null){
            instance = new QualityUpdater();
        }
        return instance;
    }

    /***
     * calculating the decrease in quality, regard on item name :
     * - If the item name contain "Conjured" then quality is decrease twice
     * - Else the quality is decreased one time
     * @param originalQuality : given item quality before decrease computation
     * @param decrease : decrease applied to item quality
     * @return the item quality after the ending day decrease
     */
    public int decreaseQuality(int originalQuality, int decrease){
        return Math.max((originalQuality - decrease), ItemConstants.MIN_COMMON_ITEM_QUALITY_ALLOWED);
    }

    /***
     * Computing Backstage quality regarding the sell in situation :
     * - sellIn < 0 : quality = 0
     * - sellIn < 5 : quality increase by 3
     * - sellIn < 10 : quality increase by 2
     * - by default : quality increase by 1
     * @param sellIn : given item sellIn
     * @param originalQuality : given item quality before computation
     * @return quality at the end of day
     */
    public int backstagePassesComputingQuality(int sellIn, int originalQuality){
        if(sellIn < 0){
            return ItemConstants.MIN_COMMON_ITEM_QUALITY_ALLOWED;
        } else if(sellIn <= 5){
            return increaseQuality(originalQuality, ItemConstants.BACKSTAGE_PASSES_5_DAYS_INCREASE);
        } else if (sellIn <= 10) {
            return increaseQuality(originalQuality, ItemConstants.BACKSTAGE_PASSES_10_DAYS_INCREASE);
        } else {
            return increaseQuality(originalQuality, ItemConstants.COMMON_QUALITY_CHANGE);
        }
    }

    /***
     * Increase item quality without exceed the max allowed
     * @param originalQuality : given item quality
     * @param increase : increase applied to item quality
     * @return the item increased quality
     */
    public int increaseQuality(int originalQuality, int increase){
        return Math.min((originalQuality + increase), ItemConstants.MAX_COMMON_ITEM_QUALITY_ALLOWED);
    }

    /***
     * Calculating the decrease on an item sellIn attribute :
     * - If the item is not a "SULFURAS" type the sellIn is decrease by one
     * - Else the sellIn is not changed
     * @param itemName : given item name
     * @param originalSellIn : given item sellIn before decrease computation
     * @return the item sellIn attribute after process
     */
    public int decreaseSellIn(String itemName, int originalSellIn){
        if (!itemName.equals(ItemConstants.SULFURAS)) {
            return originalSellIn - ItemConstants.SELL_IN_DECREASING_FACTOR;
        }
        return originalSellIn;
    }

    /***
     * Calculating an item quality change regarding the sellIn and his "Conjured" status
     * @param itemName : given item name. Used to check a "Conjured" item
     * @param sellIn : given item sellIn
     * @return the quality change to apply on an item
     */
    public int computeCommonItemQualityChange(String itemName, int sellIn){
        int qualityChange = ItemConstants.COMMON_QUALITY_CHANGE;
        if(itemName.contains(ItemConstants.CONJURED)){
            qualityChange = ItemConstants.CONJURED_QUALITY_DECREASING;
        }

        if(sellIn < 0){
            return qualityChange * ItemConstants.EXPIRED_ITEM_QUALITY_CHANGE_FACTOR;
        }else{
            return qualityChange;
        }
    }
}
