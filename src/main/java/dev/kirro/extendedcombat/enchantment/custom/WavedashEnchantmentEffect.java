package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record WavedashEnchantmentEffect(EnchantmentValueEffect wavedash) {
    public static final Codec<WavedashEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("wavedash").forGetter(WavedashEnchantmentEffect::wavedash)
    ).apply(instance, WavedashEnchantmentEffect::new));
}
