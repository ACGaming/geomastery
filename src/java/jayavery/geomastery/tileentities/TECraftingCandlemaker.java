/*******************************************************************************
 * Copyright (C) 2017 Jay Avery
 * 
 * This file is part of Geomastery. Geomastery is free software: distributed
 * under the GNU Affero General Public License (<http://www.gnu.org/licenses/>).
 ******************************************************************************/
package jayavery.geomastery.tileentities;

import jayavery.geomastery.blocks.BlockBuilding;
import jayavery.geomastery.blocks.BlockNew;
import jayavery.geomastery.main.GeoBlocks;
import jayavery.geomastery.main.GeoItems;
import jayavery.geomastery.tileentities.TECraftingCandlemaker.EnumPartCandlemaker;
import jayavery.geomastery.utilities.IMultipart;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TECraftingCandlemaker extends
        TECraftingAbstract<EnumPartCandlemaker> {
    
    @Override
    protected EnumPartCandlemaker partByOrdinal(int ordinal) {
        
        return EnumPartCandlemaker.values()[ordinal];
    }
    
    /** Enum defining parts of the whole Candlemaker structure. */
    public enum EnumPartCandlemaker implements IMultipart {

        FRONT("front"), BACK("back");

        private final String name;

        private EnumPartCandlemaker(String name) {

            this.name = name;
        }

        @Override
        public String getName() {

            return this.name;
        }
        
        @Override
        public ItemStack getDrop() {
            
            if (this == FRONT) {
                
                return new ItemStack(GeoItems.CRAFTING_CANDLEMAKER);
                
            } else {
                
                return ItemStack.EMPTY;
            }
        }
        
        @Override
        public BlockPos getMaster(BlockPos pos, EnumFacing facing) {
            
            if (this == FRONT) {
                
                return pos;
                
            } else {
                
                return pos.offset(facing.getOpposite());
            }
        }
        
        @Override
        public boolean shouldBreak(World world, BlockPos pos,
                EnumFacing facing) {
            
            BlockBuilding block = GeoBlocks.CRAFTING_CANDLEMAKER;
            boolean broken = !block.isValid(world, pos);
            
            if (this == FRONT) {

                broken |= world.getBlockState(pos.offset(facing)).getBlock()
                        != block;

            } else {

                broken |= world.getBlockState(pos.offset(facing.getOpposite()))
                        .getBlock() != block;
            }
            
            return broken;
        }
        
        @Override
        public AxisAlignedBB getBoundingBox(EnumFacing facing) {
            
            return this == BACK ? BlockNew.TWELVE : BlockNew.CENTRE_FOUR;
        }
        
        @Override
        public AxisAlignedBB getCollisionBox(EnumFacing facing) {
            
            return this == BACK ? BlockNew.TWELVE : Block.NULL_AABB;
        }
        
        @Override
        public boolean buildStructure(World world, BlockPos pos,
                EnumFacing facing) {

            if (this == FRONT) {
                
                BlockPos posFront = pos;
                BlockPos posBack = posFront.offset(facing);
                
                BlockBuilding block = GeoBlocks.CRAFTING_CANDLEMAKER;
                BlockPos[] positions = {posFront, posBack};
                boolean valid = true;
                
                for (BlockPos position : positions) {
                    
                    Block blockCheck = world.getBlockState(position).getBlock();
                    boolean replaceable = blockCheck
                            .isReplaceable(world, position);
                    boolean foundation = block.isValid(world, position);
                    
                    if (!replaceable || !foundation) {
                        
                        valid = false;
                        break;
                    }
                }
                
                if (valid) {

                    // Place all
                    IBlockState placeState = block.getDefaultState();
                    
                    world.setBlockState(posBack, placeState);
                    world.setBlockState(posFront, placeState);
                    
                    // Set up tileentities
                    ((TECraftingCandlemaker) world.getTileEntity(posBack))
                            .setState(facing, BACK);
                    ((TECraftingCandlemaker) world.getTileEntity(posFront))
                            .setState(facing, FRONT);

                    return true;
                }
            }
            
            return false;
        }
    }
}
