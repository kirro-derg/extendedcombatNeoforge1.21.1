package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record AirJumpEnchantmentEffect(EnchantmentValueEffect vanity) {
    public static final Codec<AirJumpEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("vanity").forGetter(AirJumpEnchantmentEffect::vanity)
    ).apply(instance, AirJumpEnchantmentEffect::new));
}
