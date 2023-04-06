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
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals(ItemConstants.BACKSTAGE_PASSES)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
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
        if(originalQuality > 0){
            if(itemName.contains(ItemConstants.CONJURED) && originalQuality > 1){
                return originalQuality - ItemConstants.CONJURED_QUALITY_DECREASING_FACTOR;
            }else {
                return originalQuality - ItemConstants.QUALITY_CHANGE_FACTOR;
            }
        }
        return originalQuality;
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
}
