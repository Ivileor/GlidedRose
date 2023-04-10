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

            if(ItemConstants.AGED_BRIE.equals(item.name)){
                item.quality = qualityUpdater.increaseQuality(item.quality, qualityUpdater.computeCommonItemQualityChange(item.name,item.sellIn));
            } else if (ItemConstants.BACKSTAGE_PASSES.equals(item.name)) {
                item.quality = qualityUpdater.backstagePassesComputingQuality(item.sellIn, item.quality);
            } else if (!ItemConstants.SULFURAS.equals(item.name)){
                item.quality = qualityUpdater.decreaseQuality(item.quality, qualityUpdater.computeCommonItemQualityChange(item.name, item.sellIn));
            }

        }
    }


}
