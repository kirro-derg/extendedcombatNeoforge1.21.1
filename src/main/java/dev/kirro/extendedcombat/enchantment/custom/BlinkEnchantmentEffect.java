package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record BlinkEnchantmentEffect(EnchantmentValueEffect vanity) {
    public static final Codec<BlinkEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("vanity").forGetter(BlinkEnchantmentEffect::vanity)
    ).apply(instance, BlinkEnchantmentEffect::new));
}
