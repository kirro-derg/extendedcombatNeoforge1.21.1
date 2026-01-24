package dev.kirro.extendedcombat.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties BLACK_APPLE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(1.2f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 1), 1.0f)
            .build();
    public static final FoodProperties BLACK_APPLE_SEED = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 5 * 20, 1), 1.0f)
            .alwaysEdible()
            .build();
    public static final FoodProperties GOLDEN_STEAK = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(1.7f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2 * 60 * 20, 1), 1.0f)
            .alwaysEdible()
            .build();
    public static final FoodProperties HONEY_BREAD = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 20, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 3 * 20, 1), 0.01f)
            .build();
}
