/*******************************************************************************
 * Copyright (C) 2017 Jay Avery
 * 
 * This file is part of Geomastery. Geomastery is free software: distributed
 * under the GNU Affero General Public License (<http://www.gnu.org/licenses/>).
 ******************************************************************************/
package jayavery.geomastery.items;

import java.util.Collections;
import jayavery.geomastery.utilities.ToolType;
import net.minecraft.creativetab.CreativeTabs;

/** Hunting knife tool item. */
public class ItemHuntingknife extends ItemToolAbstract {

    public ItemHuntingknife(String name, ToolMaterial material) {

        super(1F, -3.1F, material, Collections.emptySet());
        ItemSimple.setupItem(this, name, 1, CreativeTabs.TOOLS);
        this.setHarvestLevel(ToolType.KNIFE.toString(), 1);
        this.efficiencyOnProperMaterial = 0.25F;
    }
}
