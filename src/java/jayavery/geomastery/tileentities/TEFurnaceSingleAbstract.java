/*******************************************************************************
 * Copyright (C) 2017 Jay Avery
 * 
 * This file is part of Geomastery. Geomastery is free software: distributed
 * under the GNU Affero General Public License (<http://www.gnu.org/licenses/>).
 ******************************************************************************/
package jayavery.geomastery.tileentities;

import jayavery.geomastery.crafting.CookingManager;
import jayavery.geomastery.utilities.EnumSingle;
import net.minecraft.util.math.MathHelper;

/** Abstract superclass TileEntity for constant cooking furnace blocks. */
public abstract class TEFurnaceSingleAbstract extends
        TEFurnaceAbstract<EnumSingle> {

    public TEFurnaceSingleAbstract(CookingManager recipes) {
        
        super(recipes, 1);
    }
    
    @Override
    public boolean isHeating() {
        
        return this.isUsingFuel();
    }
    
    @Override
    public void update() {
        
        if (this.world.isRemote) {

            return;
        }
                
        boolean isDirty = false;
        
        // Fuel progress
        if (this.fuelLeft > 0) {

            this.fuelLeft--;
            
        } else if (!this.fuels.get(0).isEmpty()) {
            
            this.fuelEach = this.recipes.getFuelTime(this.fuels.get(0));
            this.fuelLeft = this.fuelEach;
            this.fuels.get(0).shrink(1);
            isDirty = true;
        }

        // Cook progress
        if (this.canCook() && this.fuelLeft > 0) {
            
            if (this.cookSpent < this.cookEach) {
                
                this.cookSpent++;
                
            } else if (this.cookSpent == this.cookEach) {
                
                this.cookSpent = 0;
                this.cookEach = this.recipes.getCookingTime(this.inputs.get(0));
                this.cookItem();
                isDirty = true;
            }
        }
        
        // Cook progress reverses if no fuel
        if (!this.isUsingFuel() && this.cookSpent > 0) {

            this.cookSpent = MathHelper.clamp(this.cookSpent - 2,
                    0, this.cookEach);
        }

        if (isDirty) {

            this.markDirty();
        }
        
        this.sendProgressPacket();
    }
    
    @Override
    public EnumSingle partByOrdinal(int ordinal) {
        
        return EnumSingle.SINGLE;
    }
}
