package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.Mth;
import org.apache.commons.lang3.mutable.MutableFloat;

public record BlinkEnchantmentEffect(EnchantmentValueEffect cooldown, EnchantmentValueEffect usageDuration) {
    public static final Codec<BlinkEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("cooldown").forGetter(BlinkEnchantmentEffect::cooldown),
            EnchantmentValueEffect.CODEC.fieldOf("usage_duration").forGetter(BlinkEnchantmentEffect::usageDuration)
    ).apply(instance, BlinkEnchantmentEffect::new));

    public static int getCooldown(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, ((enchantment, level) ->  {
                BlinkEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.BLINK.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.cooldown().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            }));
        }
        return Mth.floor(mutableFloat.floatValue() * 20);
    }

    public static int getUsageDuration(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, ((enchantment, level) ->  {
                BlinkEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.BLINK.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.usageDuration().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            }));
        }
        return Mth.floor(mutableFloat.floatValue() * 20);
    }
}
