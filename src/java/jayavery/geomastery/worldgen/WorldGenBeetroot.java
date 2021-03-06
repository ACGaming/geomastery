/*******************************************************************************
 * Copyright (C) 2017 Jay Avery
 * 
 * This file is part of Geomastery. Geomastery is free software: distributed
 * under the GNU Affero General Public License (<http://www.gnu.org/licenses/>).
 ******************************************************************************/
package jayavery.geomastery.worldgen;

import java.util.Random;
import jayavery.geomastery.main.GeoBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

/** WorldGenerator for beetroot crops. */
public class WorldGenBeetroot extends WorldGenCrop {

    public WorldGenBeetroot(World world, Random rand) {
        
        super(world, rand, GeoBlocks.BEETROOT.getFullgrown(), 20, 3);
    }
}
