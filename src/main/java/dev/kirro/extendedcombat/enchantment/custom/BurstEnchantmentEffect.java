package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableFloat;

public record BurstEnchantmentEffect(EnchantmentValueEffect strength) {
    public static final Codec<BurstEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("strength").forGetter(BurstEnchantmentEffect::strength)
    ).apply(instance, BurstEnchantmentEffect::new));

    public static float getStrength(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, ((enchantment, level) ->  {
                BurstEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.BURST.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.strength().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            }));
        }
        return mutableFloat.floatValue();
    }
}
