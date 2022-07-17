package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
	
	
	//Basic test cases, one item of each "type" with 1 day left and a starting quality of 1.
	//Separate test case per item type to isolate a specific item type failing.
	
    @Test
    void basicItemOneUpdate() {
    	String basicItemName = "item";
    	Item basicItem = new Item(basicItemName, 1, 10);
        Item[] items = new Item[] { basicItem };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
                
        Item updatedItem = app.items[0];
        
        assertEquals(basicItemName, updatedItem.name);
        assertEquals(9, updatedItem.quality);
        assertEquals(0, updatedItem.sellIn);
    }
    
    
    @Test
    void agedBrieItemOneUpdate() {
    	String agedBrieItemName = "Aged Brie";
    	Item agedBrieItem = new Item(agedBrieItemName, 1, 10);
        Item[] items = new Item[] { agedBrieItem };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
                
        Item updatedItem = app.items[0];
        
        assertEquals(agedBrieItemName, updatedItem.name);
        assertEquals(11, updatedItem.quality);
        assertEquals(0, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item always increases in quality.
        //assertEquals("Never", app.items[0].sellIn);
    }
    
    @Test
    void backstagePassItemOneUpdate() {
    	String backstagePassItemName = "Backstage Pass";
    	Item backstagePassItem = new Item(backstagePassItemName, 1, 10);
        Item[] items = new Item[] { backstagePassItem };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        Item updatedItem = app.items[0];
                
        assertEquals(backstagePassItemName, updatedItem.name);
        assertEquals(13, updatedItem.quality);
        assertEquals(0, updatedItem.sellIn);
    }

    //More comprehensive test cases using multiple item types, days, and covering edge cases

    //Sulfuras quality and SellIn are unchanging, so we can create a comprehensive test easily enough
    @Test
    void sulfurasItemOneUpdate() {
    	String sulfurasItemName = "Sulfuras";
    	Item sulfurasItem = new Item(sulfurasItemName, 1, 10);
        Item[] items = new Item[] { sulfurasItem };
        GildedRose app = new GildedRose(items);
        
        Item updatedItem = app.items[0];
        assertEquals(sulfurasItemName, updatedItem.name);
        assertEquals(80, updatedItem.quality);
        assertEquals(1, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item never needs to be sold.
        //assertEquals("Never", app.items[0].sellIn);
        
        app.updateQuality();        
        updatedItem = app.items[0];
        assertEquals(80, updatedItem.quality);
        assertEquals(1, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item never needs to be sold.
        //assertEquals("Never", app.items[0].sellIn);
        
        app.updateQuality();        
        updatedItem = app.items[0];
        assertEquals(80, updatedItem.quality);
        assertEquals(1, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item never needs to be sold.
        //assertEquals("Never", app.items[0].sellIn);
        
        app.updateQuality();        
        updatedItem = app.items[0];
        assertEquals(80, updatedItem.quality);
        assertEquals(1, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item never needs to be sold.
        //assertEquals("Never", app.items[0].sellIn);
    }
    
    
    @Test
    void allItemsOneUpdate() {
    	
    	//Basic items just before and just after the sell by date.
    	String basicItemName = "item";
    	Item basicItemPositiveDay = new Item(basicItemName, 1, 10);
    	Item basicItemNegativeDay = new Item(basicItemName, -1, 10);
    	
    	//Aged Brie items should have no behaviour change before and after the sell by date.
    	String agedBrieItemName = "Aged Brie";
    	Item agedBrieItemPositiveDay = new Item(agedBrieItemName, 1, 10);
    	Item agedBrieItemNegativeeDay = new Item(agedBrieItemName, -1, 10);
    	
    	//Backstage Passes increase by 1 if 11 or more days out, by 2 if between 6 and 10 days out, 
    	//by 3 between 0 and 5 days out, and become 0 after expiry
    	String backstagePassItemName = "Backstage Pass";
    	Item backstagePassItemEleven = new Item(backstagePassItemName, 11, 10);
    	Item backstagePassItemSix = new Item(backstagePassItemName, 6, 10);
    	Item backstagePassItemTomorrow = new Item(backstagePassItemName, 1, 10);
    	Item backstagePassItemToday = new Item(backstagePassItemName, 0, 10);    	
    	Item backstagePassItemEventDone = new Item(backstagePassItemName, -1, 10);
    	
    	//Sulfuras items should always be quality 80.
    	String sulfurasItemName = "Sulfuras";
    	Item sulfurasItem = new Item(sulfurasItemName, 1, 10);
    	
        Item[] items = new Item[] { basicItemPositiveDay, basicItemNegativeDay, agedBrieItemPositiveDay, agedBrieItemNegativeeDay,
        		backstagePassItemEleven, backstagePassItemSix, backstagePassItemTomorrow, backstagePassItemToday,
        		backstagePassItemEventDone, sulfurasItem};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        //Basic
        //Only decrease by 1 since the day decreases after quality updates
        assertEquals(9, app.items[0].quality);   
        assertEquals(8, app.items[1].quality);
        
        //Aged Brie
        assertEquals(11, app.items[2].quality);
        assertEquals(11, app.items[3].quality);
        
        //Backstage passes
        //Not into the range to increase to +2 per day until tomorrow.
        assertEquals(11, app.items[4].quality);
        
        //Not into the range to increase to +3 per day until tomorrow.
        assertEquals(12, app.items[5].quality);
        assertEquals(13, app.items[6].quality);
        
        //Do not reset until after the event
        assertEquals(13, app.items[7].quality);
        assertEquals(0, app.items[8].quality);
        
        //Sulfuras
        assertEquals(80, app.items[9].quality);
    }

    @Test
    void basicItemMultipleUpdates() {
    	String basicItemName = "item";
    	Item basicItem = new Item(basicItemName, 1, 10);
        Item[] items = new Item[] { basicItem };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        Item updatedItem = app.items[0];
        assertEquals(9, updatedItem.quality);
        assertEquals(0, updatedItem.sellIn);
        
        
        app.updateQuality();        
        updatedItem = app.items[0];
        //Only decrease by 1 since the day decreases after quality updates
        assertEquals(8, updatedItem.quality);
        assertEquals(-1, updatedItem.sellIn);
        
        app.updateQuality();
        updatedItem = app.items[0];
        assertEquals(6, updatedItem.quality);
        assertEquals(-2, updatedItem.sellIn);

    }
    
    @Test
    void agedBrieItemMultipleUpdates() {
    	String agedBrieItemName = "Aged Brie";
    	Item agedBrieItem = new Item(agedBrieItemName, 1, 10);
        Item[] items = new Item[] { agedBrieItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
                
        Item updatedItem = app.items[0];
        assertEquals(11, updatedItem.quality);
        assertEquals(0, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item always increases in quality.
        //assertEquals("Never", updatedItem.sellIn);
        
        app.updateQuality();
        updatedItem = app.items[0];
        assertEquals(12, updatedItem.quality);
        assertEquals(-1, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item always increases in quality.
        //assertEquals("Never", updatedItem.sellIn);
        
        app.updateQuality();
        updatedItem = app.items[0];
        assertEquals(13, updatedItem.quality);
        assertEquals(-2, updatedItem.sellIn);
        //TODO Issue-8: Confirm if this is expected to be "Never" as the item always increases in quality.
        //assertEquals("Never", updatedItem.sellIn);
        
    }
    
    
    @Test
    void testMultipleBackstagePassDaysCrossingThresholds() {
	    String backstagePassItemName = "Backstage Pass";
		Item backstagePassItemEleven = new Item(backstagePassItemName, 11, 10);
		Item backstagePassItemSix = new Item(backstagePassItemName, 6, 10);
		Item backstagePassItemTomorrow = new Item(backstagePassItemName, 1, 10);
		Item backstagePassItemToday = new Item(backstagePassItemName, 0, 10);    	
		Item backstagePassItemEventDone = new Item(backstagePassItemName, -1, 10);
		
		Item[] items = new Item[] {backstagePassItemEleven, backstagePassItemSix, backstagePassItemTomorrow,
				backstagePassItemToday, backstagePassItemEventDone};
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        assertEquals(12, app.items[1].quality);
        assertEquals(13, app.items[2].quality);
        //Event tonight
        assertEquals(13, app.items[3].quality);
        assertEquals(0, app.items[4].quality);
        
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
        assertEquals(15, app.items[1].quality);
        //Event tonight
        assertEquals(16, app.items[2].quality);
        assertEquals(0, app.items[3].quality);
        
        app.updateQuality();
        assertEquals(15, app.items[0].quality);
        assertEquals(18, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
        
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(24, app.items[0].quality);
        //Event tonight
        assertEquals(30, app.items[1].quality);
        
        app.updateQuality();
        assertEquals(27, app.items[0].quality);
        assertEquals(0, app.items[1].quality);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        //Event tonight
        assertEquals(39, app.items[0].quality);
        
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
        assertEquals(0, app.items[3].quality);
        assertEquals(0, app.items[4].quality);
    
    }

}
