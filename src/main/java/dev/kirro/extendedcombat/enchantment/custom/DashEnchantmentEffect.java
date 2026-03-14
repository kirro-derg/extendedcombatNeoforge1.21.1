package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record DashEnchantmentEffect(EnchantmentValueEffect vanity) {
    public static final Codec<DashEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("vanity").forGetter(DashEnchantmentEffect::vanity)
    ).apply(instance, DashEnchantmentEffect::new));
}
