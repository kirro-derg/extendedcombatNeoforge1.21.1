package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import org.apache.commons.lang3.mutable.MutableFloat;

public record FluidWalkerEnchantmentEffect(EnchantmentValueEffect speed) {
    public static final Codec<FluidWalkerEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("speed").forGetter(FluidWalkerEnchantmentEffect::speed)
    ).apply(instance, FluidWalkerEnchantmentEffect::new));

    public static float getValue(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (enchantment, level) -> {
                FluidWalkerEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.FLUID_WALKER.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.speed().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            });
        }

        return mutableFloat.floatValue();
    }
}