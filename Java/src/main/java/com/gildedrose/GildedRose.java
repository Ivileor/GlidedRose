package com.gildedrose;

class GildedRose {
    Item[] items;
    static final String _CONJURED_SUFFIX = "Conjured";
    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality = decreaseQuality(item.name, item.quality);
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                                item.quality = decreaseQuality(item.name, item.quality);
                            }
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
        if(itemName.contains(_CONJURED_SUFFIX) && originalQuality > 1){
            return originalQuality - 2;
        }else {
            return originalQuality - 1;
        }
    }
}
