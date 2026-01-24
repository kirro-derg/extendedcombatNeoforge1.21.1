package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableFloat;

public record SwiftnessEnchantmentEffect(EnchantmentValueEffect multiplierBuff) {
    public static final Codec<SwiftnessEnchantmentEffect> CODEC = RecordCodecBuilder.create( instance -> instance.group(
        EnchantmentValueEffect.CODEC.fieldOf("multiplierBuff").forGetter(SwiftnessEnchantmentEffect::multiplierBuff)
    ).apply(instance, SwiftnessEnchantmentEffect::new));

    public static float getValue(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorAndBodyArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (enchantment, level) -> {
                SwiftnessEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.SWIFTNESS.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.multiplierBuff().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            });
        }

        return mutableFloat.floatValue();
    }
}
