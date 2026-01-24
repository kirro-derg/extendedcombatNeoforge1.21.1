package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import org.apache.commons.lang3.mutable.MutableInt;

public record ConcussionEnchantmentEffect(EnchantmentValueEffect duration) {
    public static final Codec<ConcussionEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("duration").forGetter(ConcussionEnchantmentEffect::duration)
    ).apply(instance, ConcussionEnchantmentEffect::new));

    public static int getDuration(LivingEntity entity) {
        MutableInt mutableFloat = new MutableInt(0);
        for (ItemStack stack : entity.getHandSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, ((enchantment, level) ->  {
                ConcussionEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.CONCUSSION.get());
                if (effect != null) {
                    mutableFloat.setValue(level);
                }
            }));
        }

        return mutableFloat.intValue() * 20;
    }
}
