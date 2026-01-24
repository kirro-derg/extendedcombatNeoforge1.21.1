package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.kirro.extendedcombat.behavior.enchantment.AirJumpBehavior;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.item.ModDataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.mutable.MutableFloat;

public record AirJumpEnchantmentEffect(EnchantmentValueEffect airJumpStrength, EnchantmentValueEffect cooldown,
                                       EnchantmentValueEffect jumpCooldown, EnchantmentValueEffect jumpAmount) {
    public static final Codec<AirJumpEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("air_jump_strength").forGetter(AirJumpEnchantmentEffect::airJumpStrength),
            EnchantmentValueEffect.CODEC.fieldOf("cooldown").forGetter(AirJumpEnchantmentEffect::cooldown),
            EnchantmentValueEffect.CODEC.fieldOf("jump_cooldown").forGetter(AirJumpEnchantmentEffect::jumpCooldown),
            EnchantmentValueEffect.CODEC.fieldOf("jump_amount").forGetter(AirJumpEnchantmentEffect::jumpAmount)
    ).apply(instance, AirJumpEnchantmentEffect::new));

    public static float getAirJumpStrength(LivingEntity entity) {
        MutableFloat mutable = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (enchantment, level) -> {
                AirJumpEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.AIR_JUMP.get());
                if (effect != null) {
                    mutable.setValue(effect.airJumpStrength().process(level, entity.getRandom(), mutable.floatValue()));
                }
            });
        }
        return mutable.floatValue();
    }

    public static int getCooldown(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (enchantment, level) -> {
                AirJumpEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.AIR_JUMP.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.cooldown().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            });
        }
        return Mth.floor(mutableFloat.floatValue() * 20);
    }

    public static int getJumpCooldown(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (enchantment, level) -> {
                AirJumpEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.AIR_JUMP.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.jumpCooldown().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            });
        }
        return Mth.floor(mutableFloat.floatValue() * 20);
    }

    public static int getJumpAmount(LivingEntity entity) {
        MutableFloat mutableFloat = new MutableFloat(0);
        for (ItemStack stack : entity.getArmorSlots()) {
            EnchantmentHelper.runIterationOnItem(stack, (enchantment, level) -> {
                AirJumpEnchantmentEffect effect = enchantment.value().effects().get(ModEnchantmentEffects.AIR_JUMP.get());
                if (effect != null) {
                    mutableFloat.setValue(effect.jumpAmount().process(level, entity.getRandom(), mutableFloat.floatValue()));
                }
            });
        }
        return Mth.floor(mutableFloat.floatValue());
    }
}
