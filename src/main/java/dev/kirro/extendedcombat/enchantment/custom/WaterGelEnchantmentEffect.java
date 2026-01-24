package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record WaterGelEnchantmentEffect(EnchantmentValueEffect watergel) {
    public static final Codec<WaterGelEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("watergel").forGetter(WaterGelEnchantmentEffect::watergel)
    ).apply(instance, WaterGelEnchantmentEffect::new));
}
