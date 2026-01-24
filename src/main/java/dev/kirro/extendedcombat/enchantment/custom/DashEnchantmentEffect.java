package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import org.apache.commons.lang3.mutable.MutableFloat;

public record DashEnchantmentEffect(EnchantmentValueEffect cooldown, EnchantmentValueEffect strength) {
    public static final Codec<DashEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("cooldown").forGetter(DashEnchantmentEffect::cooldown),
            EnchantmentValueEffect.CODEC.fieldOf("strength").forGetter(DashEnchantmentEffect::strength)
    ).apply(instance, DashEnchantmentEffect::new));

    public static int getCooldown(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (((enchantment, level) -> {
                DashEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.DASH.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.cooldown().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            })));
        }
        return Mth.floor(mutableFloat.floatValue() * 20);
    }

    public static float getStrength(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (((enchantment, level) -> {
                DashEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.DASH.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.strength().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            })));
        }
        return mutableFloat.floatValue();
    }
}
