package com.jayavery.jjmod.blocks;

import com.jayavery.jjmod.init.ModItems;
import com.jayavery.jjmod.utilities.ToolType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSnow;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.BiomeTaiga;

/** Chickpea crop block. */
public class BlockCropChickpea extends BlockCropAbstract {
    
    public BlockCropChickpea() {
        
        super("chickpea", () -> ModItems.chickpeas, (rand) -> 2,
                0.3F, 0.2F, ToolType.SICKLE);
    }

    @Override
    public boolean isPermitted(Biome biome) {

        return biome instanceof BiomeBeach ||biome instanceof BiomePlains ||
                biome instanceof BiomeSwamp ||
                biome instanceof BiomeMushroomIsland ||
                biome instanceof BiomeJungle ||
                biome instanceof BiomeSavanna;
    }
}