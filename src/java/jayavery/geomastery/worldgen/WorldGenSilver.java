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

/** WorldGenerator for silver ore. */
public class WorldGenSilver extends WorldGenStone {

    public WorldGenSilver(World world, Random rand) {

        super(world, rand, GeoBlocks.ORE_SILVER.getDefaultState(), 10, 60, 10, 1);
    }

    @Override
    protected int getVeinSize() {

        int rand1 = this.rand.nextInt(2);
        int rand2 = this.rand.nextInt(2);
        int rand3 = this.rand.nextInt(2);

        return rand1 + rand2 + rand3 + 1;
    }

    @Override
    protected boolean shouldGenBlock() {

        return true;
    }
}
