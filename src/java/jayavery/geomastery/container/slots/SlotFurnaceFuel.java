/*******************************************************************************
 * Copyright (C) 2017 Jay Avery
 * 
 * This file is part of Geomastery. Geomastery is free software: distributed
 * under the GNU Affero General Public License (<http://www.gnu.org/licenses/>).
 ******************************************************************************/
package jayavery.geomastery.container.slots;

import jayavery.geomastery.tileentities.TEFurnaceAbstract;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/** Container inventory slot for furnace fuels. */
public class SlotFurnaceFuel extends Slot {

    /** The furnace this slot draws inventory from. */
    private final TEFurnaceAbstract<?> furnace;
    /** The index of this slot in the furnace fuels list. */
    private final int index;
    
    public SlotFurnaceFuel(TEFurnaceAbstract<?> furnace, int index,
            int xPos, int yPos) {
        
        super(null, 0, xPos, yPos);
        this.furnace = furnace;
        this.index = index;
    }
    
    @Override
    public boolean isItemValid(ItemStack stack) {

        return this.furnace.recipes.getFuelTime(stack) > 0;
    }
    
    @Override
    public ItemStack getStack() {
        
        return this.furnace.getFuel(this.index);
    }
    
    @Override
    public void putStack(ItemStack stack) {
        
        this.furnace.setFuel(stack, this.index);
    }
    
    @Override
    public void onSlotChanged() {

        this.furnace.sort();
    }
    
    @Override
    public int getSlotStackLimit() {
        
        return 64;
    }
    
    @Override
    public ItemStack decrStackSize(int amount) {
        
        return this.furnace.getFuel(this.index).splitStack(amount);
    }
    
    @Override
    public boolean isHere(IInventory inv, int slot) {
        
        return false;
    }
    
    @Override
    public boolean isSameInventory(Slot slot) {
        
        return false;
    }
}
