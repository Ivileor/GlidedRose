package com.gildedrose;

import com.gildedrose.constants.ItemConstants;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (!item.name.equals(ItemConstants.AGED_BRIE)
                && !item.name.equals(ItemConstants.BACKSTAGE_PASSES)) {
                if (!item.name.equals(ItemConstants.SULFURAS)) {
                    item.quality = decreaseQuality(item.name, item.quality);
                }
            } else if (item.name.equals(ItemConstants.BACKSTAGE_PASSES)) {
                item.quality = backstagePassesComputingQuality(item.sellIn, item.quality);
            } else {
                item.quality = increaseQuality(item.quality, computeCommonItemQualityChange(item.name,item.sellIn));
            }

            item.sellIn = decreaseSellIn(item.name, item.sellIn);

            if (item.sellIn < 0) {
                if (!item.name.equals(ItemConstants.AGED_BRIE)) {
                    if (!item.name.equals(ItemConstants.BACKSTAGE_PASSES)) {
                        if (!item.name.equals(ItemConstants.SULFURAS)) {
                            item.quality = decreaseQuality(item.name, item.quality);
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    /***
     * calculating the decrease in quality, regard on item name :
     * - If the item name contain "Conjured" then quality is decrease twice
     * - Else the quality is decreased one time
     * @param itemName : given item name
     * @param originalQuality : given item quality before decrease computation
     * @return the item quality after the ending day decrease
     */
    private int decreaseQuality(String itemName, int originalQuality){
        if(originalQuality > ItemConstants.MIN_COMMON_ITEM_QUALITY_ALLOWED){
            if(itemName.contains(ItemConstants.CONJURED) && originalQuality > 1){
                return originalQuality - ItemConstants.CONJURED_QUALITY_DECREASING;
            }else {
                return originalQuality - ItemConstants.COMMON_QUALITY_CHANGE;
            }
        }
        return originalQuality;
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
    private int backstagePassesComputingQuality(int sellIn, int originalQuality){
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
    private int increaseQuality(int originalQuality, int increase){
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
    private int decreaseSellIn(String itemName, int originalSellIn){
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
    private int computeCommonItemQualityChange(String itemName, int sellIn){
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
