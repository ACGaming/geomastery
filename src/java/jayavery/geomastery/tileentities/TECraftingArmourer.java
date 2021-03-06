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
import jayavery.geomastery.tileentities.TECraftingArmourer.EnumPartArmourer;
import jayavery.geomastery.utilities.IMultipart;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** TileEntity for armourer crafting block. */
public class TECraftingArmourer extends TECraftingAbstract<EnumPartArmourer> {
    
    @Override
    protected EnumPartArmourer partByOrdinal(int ordinal) {

        return EnumPartArmourer.values()[ordinal];
    }
    
    /** Enum defining parts of the whole Armourer structure. */
    public enum EnumPartArmourer implements IMultipart {
        
        T("t"), L("l"), M("m"), R("r");
        
        private final String name;
        
        private EnumPartArmourer(String name) {
            
            this.name = name;
        }
        
        @Override
        public String getName() {
            
            return this.name;
        }

        @Override
        public ItemStack getDrop() {

            if (this == T) {
                
                return new ItemStack(GeoItems.CRAFTING_ARMOURER);
                
            } else {
                
                return ItemStack.EMPTY;
            }
        }
        
        @Override
        public BlockPos getMaster(BlockPos pos, EnumFacing facing) {
            
            switch (this) {
                
                case L:
                    return pos.up();
                case M:
                    return pos.offset(facing.rotateYCCW()).up();
                case R:
                    return pos.offset(facing.rotateYCCW(), 2).up();
                case T:
                default:
                    return pos;
            }
        }
        
        @Override
        public boolean shouldBreak(World world, BlockPos pos,
                EnumFacing facing) {
            
            BlockBuilding block = GeoBlocks.CRAFTING_ARMOURER;
            boolean broken = !block.isValid(world, pos);
            
            switch (this) {
                
                case T: {
                    
                    broken |= world.getBlockState(pos.down())
                            .getBlock() != block;
                    break;
                }
                
                case L: {
                    
                    boolean brokenT = world.getBlockState(pos.up())
                            .getBlock() != block;
                    boolean brokenM = world.getBlockState(pos
                            .offset(facing.rotateY())).getBlock() != block;
                    
                    broken |= brokenM || brokenT;
                    break;
                }
                
                case M: {
                    
                    boolean brokenL = world.getBlockState(pos
                            .offset(facing.rotateYCCW())).getBlock() != block;
                    boolean brokenR = world.getBlockState(pos
                            .offset(facing.rotateY())).getBlock() != block;
                    
                    broken |= brokenL || brokenR;
                    break;
                }
                
                case R: {
                    
                    broken |= world.getBlockState(pos
                            .offset(facing.rotateYCCW()))
                            .getBlock() != block;
                    break;
                }
            }
            
            return broken;
        }
        
        @Override
        public AxisAlignedBB getBoundingBox(EnumFacing facing) {
            
            int intFacing = facing.getHorizontalIndex();
            
            switch (this) {
                
                case R: 
                    return BlockNew.TWELVE;
                case L: 
                    return BlockNew.HALF[(intFacing + 1) % 4];
                case T: 
                    return BlockNew.CORNER[(intFacing + 2) % 4];
                case M: 
                default: 
                    return Block.FULL_BLOCK_AABB;
            }
        }
        
        @Override
        public AxisAlignedBB getCollisionBox(EnumFacing facing) {
            
            switch (this) {
                
                case R: 
                    return BlockNew.TWELVE;
                case M: 
                    return BlockNew.FOURTEEN;
                case L: 
                case T: 
                    return Block.NULL_AABB;
                default: 
                    return Block.FULL_BLOCK_AABB;
            }
        }
        
        @Override
        public boolean buildStructure(World world, BlockPos pos,
                EnumFacing facing) {
            
            if (this == M) {
                
                BlockPos posM = pos;
                BlockPos posL = posM.offset(facing.rotateYCCW());
                BlockPos posT = posL.up();
                BlockPos posR = posM.offset(facing.rotateY());
                
                BlockBuilding block = GeoBlocks.CRAFTING_ARMOURER;
                BlockPos[] basePositions = {posM, posL, posR};
                BlockPos[] upperPositions = {posT};
                boolean valid = true;
                
                for (BlockPos position : basePositions) {
                    
                    Block blockCheck = world.getBlockState(position).getBlock();
                    boolean replaceable = blockCheck
                            .isReplaceable(world, position);

                    boolean foundation = block.isValid(world, position);
                    
                    if (!replaceable || !foundation) {
                        
                        valid = false;
                        break;
                    }
                }
                
                for (BlockPos position : upperPositions) {
                    
                    Block blockCheck = world.getBlockState(position).getBlock();
                    boolean replaceable = blockCheck
                            .isReplaceable(world, position);
                    
                    if (!replaceable) {
                        
                        valid = false;
                        break;
                    }
                }
                
                if (valid) {
                
                    // Place all
                    IBlockState placeState = block.getDefaultState();
                    
                    world.setBlockState(posT, placeState);
                    world.setBlockState(posL, placeState);
                    world.setBlockState(posM, placeState);
                    world.setBlockState(posR, placeState);
                    
                    // Set up tileentities
                    ((TECraftingArmourer) world.getTileEntity(posT))
                            .setState(facing, T);
                    ((TECraftingArmourer) world.getTileEntity(posL))
                            .setState(facing, L);
                    ((TECraftingArmourer) world.getTileEntity(posM))
                            .setState(facing, M);
                    ((TECraftingArmourer) world.getTileEntity(posR))
                            .setState(facing, R);
                    
                    return true;
                }
            }
            
            return false;
        }
    }
}
