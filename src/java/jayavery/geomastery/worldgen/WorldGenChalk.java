/*******************************************************************************
 * Copyright (C) 2017 Jay Avery
 * 
 * This file is part of Geomastery. Geomastery is free software: distributed
 * under the GNU Affero General Public License (<http://www.gnu.org/licenses/>).
 ******************************************************************************/
package jayavery.geomastery.worldgen;

import java.util.Random;
import jayavery.geomastery.main.GeoBlocks;
import net.minecraft.world.World;

/** WorldGenerator for chalk blocks. */
public class WorldGenChalk extends WorldGenStone {

    public WorldGenChalk(World world, Random rand) {
        
        super(world, rand, GeoBlocks.CHALK.getDefaultState(), 30, 256, 1, 1);
    }

    @Override
    protected int getVeinSize() {

        return this.rand.nextInt(12) + 1;
    }

    @Override
    protected boolean shouldGenBlock() {

        return true;
    }
}
