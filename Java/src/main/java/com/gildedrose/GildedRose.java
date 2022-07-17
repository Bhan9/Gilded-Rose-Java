package com.gildedrose;

class GildedRose {
    Item[] items;
    
    //TODO Issue-5: Add hardcoded item type Strings for comparison
    //TODO Issue-5: Add hardcoded quality cap constraint numbers (max, min, legendary)
    

    public GildedRose(Item[] items) {
    	

    	
        this.items = items;
      //TODO Issue-5: Validate quality constraints
        //this.items = validateItem(items);
      
    }

    
    
    
    public void updateQuality() {
    	//TODO Issue-5: Rewrite completely.
    	
    	for (int i = 0; i < items.length; i++) {
    	
    		Item currentItem = items[i];
    		//TODO Issue-5: Determine Item Type
    		//String itemType = getItemType(currentItem);
    		
    		
	    	//TODO Issue-5: Process by Item Type
    	
    	
    	}
    	
    }
    
    //TODO Issue-5: Implement.
    /*public void updateBasicItem(Item item) {
     * 
	}*/
    
    
    //TODO Issue-5: Implement.
    /*public void updateAgedBrieItem(Item item) {
     * 
	}*/
    
    
    //TODO Issue-5: Implement.
    /*public void updateBackstagePassItem(Item item) {
     * 
	}*/
    
    
    //TODO Issue-5: Implement.
    /*public void updateSulfurasItem(Item item) {
     * 
	}*/
    
    //TODO Issue-5: Implement.
    /*private Item[] validateItems(Item item){
     * 
     *     	for (int i = 0; i < items.length; i++) {
        	
    		Item currentItem = items[i];
    		//TODO Issue-5: Determine Item Type
    		//items[i] = validateItem(items[i]);
    		//Sulfuras: quality to 80
    		//Not Sulfuras: quality capped to 50 if above, increased to 0 if below
    		//Backstage pass: Quality to 0 if sellIn is negative
    		
    	
    	
    	}
     * 
     * 
     }*/
    
    
    //TODO Issue-5: Implement.
    /*private String getItemType(Item item){
     * 
     * 
     }*/
    
    
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
    
    
    //TODO Issue-5: Delete this method.
    /*
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }*/
    
    
}