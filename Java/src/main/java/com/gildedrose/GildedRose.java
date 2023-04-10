package com.gildedrose;

import com.gildedrose.constants.ItemConstants;
import com.gildedrose.inventory.QualityUpdater;

class GildedRose {
    Item[] items;
    private final QualityUpdater qualityUpdater = QualityUpdater.getInstance();

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.sellIn = qualityUpdater.decreaseSellIn(item.name, item.sellIn);

            if(item.name.equals(ItemConstants.AGED_BRIE)){
                item.quality = qualityUpdater.increaseQuality(item.quality, qualityUpdater.computeCommonItemQualityChange(item.name,item.sellIn));
            } else if (item.name.equals(ItemConstants.BACKSTAGE_PASSES)) {
                item.quality = qualityUpdater.backstagePassesComputingQuality(item.sellIn, item.quality);
            } else if (!item.name.equals(ItemConstants.SULFURAS)){
                item.quality = qualityUpdater.decreaseQuality(item.quality, qualityUpdater.computeCommonItemQualityChange(item.name, item.sellIn));
            }

        }
    }


}
