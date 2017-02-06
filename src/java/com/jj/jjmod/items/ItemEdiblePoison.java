package com.jj.jjmod.items;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemEdiblePoison extends ItemEdible {

    public ItemEdiblePoison(String name, int hunger, float saturation,
            int stackSize, FoodType foodType) {
        
        super(name, hunger, saturation, stackSize, foodType);
        this.setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.5F);
    }

}
