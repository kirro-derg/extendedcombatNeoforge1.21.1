package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record StealthEnchantmentEffect(EnchantmentValueEffect stealth) {
    public static final Codec<StealthEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("stealth").forGetter(StealthEnchantmentEffect::stealth)
    ).apply(instance, StealthEnchantmentEffect::new));
}
