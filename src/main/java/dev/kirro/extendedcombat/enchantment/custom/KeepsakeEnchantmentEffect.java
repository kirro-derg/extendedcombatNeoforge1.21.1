package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record KeepsakeEnchantmentEffect(EnchantmentValueEffect noDurability) {
        public static final Codec<KeepsakeEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                EnchantmentValueEffect.CODEC.fieldOf("noDurability").forGetter(KeepsakeEnchantmentEffect::noDurability)
        ).apply(instance, KeepsakeEnchantmentEffect::new));
}
