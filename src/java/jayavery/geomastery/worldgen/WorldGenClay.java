/*******************************************************************************
 * Copyright (C) 2017 Jay Avery
 * 
 * This file is part of Geomastery. Geomastery is free software: distributed
 * under the GNU Affero General Public License (<http://www.gnu.org/licenses/>).
 ******************************************************************************/
package jayavery.geomastery.worldgen;

import java.util.Random;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/** WorldGenerator for extra clay blocks. */
public class WorldGenClay extends WorldGenStone {

    public WorldGenClay(World world, Random rand) {
        
        super(world, rand, Blocks.CLAY.getDefaultState(), 40, 80, 3, 1);
        this.predicate = new Predicate<IBlockState>() {
            
            @Override
            public boolean apply(IBlockState state) {
                
                if (state == null) {
                    
                    return false;
                }
                
                Block block = state.getBlock();
                return block == Blocks.STONE || block == Blocks.DIRT ||
                        block == Blocks.GRASS;
            }
        };
    }

    @Override
    protected int getVeinSize() {

        return this.rand.nextInt(20) + 1;
    }

    @Override
    protected boolean shouldGenBlock() {

        return true;
    }
}
