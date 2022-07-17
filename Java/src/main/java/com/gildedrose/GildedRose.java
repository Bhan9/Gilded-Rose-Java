package com.gildedrose;

import java.util.logging.Logger;

class GildedRose {
    Item[] items;
    
    private Logger log = Logger.getLogger(GildedRose.class.getName());
    
    private final String SULFURAS = "sulfuras";
    private final String BACKSTAGEPASS = "backstage pass";
    private final String AGEDBRIE = "aged brie";
    private final String BASIC = "basic";
    
    private final int LEGENDARYQUALITY = 80;
    private final int MAXQUALITY = 50;
    private final int MINQUALITY = 0;
    

    public GildedRose(Item[] items) {
        this.items = validateItems(items);
    }

    public void updateQuality() {    	
    	for (int i = 0; i < items.length; i++) {
    	
    		Item currentItem = items[i];
    		String itemType = getItemType(currentItem);
    		
    		if (itemType.equalsIgnoreCase(BASIC)) {
    			items[i] = updateBasicItem(currentItem);
    		}
    		else if (itemType.equalsIgnoreCase(AGEDBRIE)){
    			items[i] = updateAgedBrieItem(currentItem);
    			
    		}
    		else if (itemType.equalsIgnoreCase(BACKSTAGEPASS)){
    			items[i] = updateBackstagePassItem(currentItem);
    		}
    		else if (itemType.equalsIgnoreCase(SULFURAS)){
    			items[i] = updateSulfurasItem(currentItem);
    		}
    		else {
    			log.warning("Unidentified Item type during update: " + itemType + " for Item: " + currentItem.toString());
    		}
	
    	}
    	
    }
    
    private Item[] validateItems(Item[] items){
     
         	for (int i = 0; i < items.length; i++) {
        	
    		Item currentItem = items[i];
    		String itemType = getItemType(currentItem);
    		
    		if (itemType.equalsIgnoreCase(BASIC)) {
    			items[i] = validateBasicItem(currentItem);
    		}
    		else if (itemType.equalsIgnoreCase(AGEDBRIE)){
    			items[i] = validateAgedBrieItem(currentItem);
    			
    		}
    		else if (itemType.equalsIgnoreCase(BACKSTAGEPASS)){
    			items[i] = validateBackstagePassItem(currentItem);
    		}
    		else if (itemType.equalsIgnoreCase(SULFURAS)){
    			items[i] = validateSulfurasItem(currentItem);
    		}
    		else {
    			log.warning("Unidentified Item type during validation: " + itemType + " for Item: " + currentItem.toString());
    		} 	
    	}
         	
        return items;
     }
    
    private Item validateMinQuality(Item item) {
		if (item.quality < MINQUALITY) {
			item.quality = MINQUALITY;
		}
		return item;
    }
    
    private Item validateMaxQuality(Item item) {
    	if (item.quality > MAXQUALITY) {
			item.quality = MAXQUALITY;
		}
    	return item;
    }
    
    private Item itemInStandardQualityRange(Item item) {
    	item = validateMinQuality(item);
    	return validateMaxQuality(item);
    }
    
    private Item validateBasicItem(Item item) {
    	return itemInStandardQualityRange(item);
    }
    
    private Item validateAgedBrieItem(Item item) {
    	return itemInStandardQualityRange(item);
    }
    
    private Item validateBackstagePassItem(Item item) {
    	if (item.sellIn < 0) {
    		item.quality = 0;
    	}
    	return itemInStandardQualityRange(item);
    }
    
    private Item validateSulfurasItem(Item item) {
    	item.quality = LEGENDARYQUALITY;
		return item;
	}
    
    private Item updateSulfurasItem(Item item) {
    	item.quality = LEGENDARYQUALITY;
		return item;
	}

	private Item updateBackstagePassItem(Item item) {
		//If sellIn is equal to zero, the event is tonight, so we do not zero out until the day after the event
		if (item.sellIn < 0) {
			item.quality = 0;
		}
		else if (item.sellIn < 6) {
			item.quality+=3;
		}
		else if (item.sellIn < 11) {
			item.quality+=2;
		}
		else {
			item.quality+=1;
		}
		
		item.sellIn--;
		
		return validateMaxQuality(item);
	}


	private Item updateAgedBrieItem(Item item) {
		item.quality++;		
		
		//TODO Issue-8: Possible removal
		item.sellIn--;
		
		return validateMaxQuality(item);
	}

	private Item updateBasicItem(Item item) {
		if (item.sellIn < 0) {
			item.quality -=2 ;
		}
		else {
			item.quality--;
		}
		
		item.sellIn--;		
		
		return validateMinQuality(item);
	}
    
    //TODO Issue-7: Confirm order of precedence for Item Type
    private String getItemType(Item item){
    	String itemType = BASIC;
    	String itemName = item.name.toLowerCase();
    	
    	if (itemName.contains(SULFURAS)) {
    		itemType = SULFURAS;
    	}
    	else if (itemName.contains(BACKSTAGEPASS)) {
    		itemType = BACKSTAGEPASS;
    	}
    	else if (itemName.contains(AGEDBRIE)) {
    		itemType = AGEDBRIE;
    	}
    	
    	return itemType;
    }
    
    
    //TODO Issue-2: Implement. Cast the array to an Arraylist, call list.add, cast the Arraylist back to an array
    //the turn the items array into a new array created from list.toArray. Make sure not to lose order of the array
    //The New item should always be at the end of the array, with the array size going up by 1.
    /*public void addToItems(Item item){
     * 
     * }
     */
    
    //TODO Issue-2: Implement. Cast the array to an Arraylist, call list.remove(reference), cast the Arraylist back to an array
    //the turn the items array into a new array created from list.toArray. Make sure not to lose order of the array
    //Items before the removed item should maintain their place in the array, items after the removed item should have moved up one spot,
    //with the array size decreasing by 1.
    /*public void removeFromItems(int reference){
     * 
     * }
     */
    
}