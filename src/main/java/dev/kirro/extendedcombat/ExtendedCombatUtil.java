package dev.kirro.extendedcombat;

import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.HitResult;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class ExtendedCombatUtil {
    public static Set<UUID> BLINKING_PLAYERS = new HashSet<>();

    public static void removeEffectOfType(LivingEntity living, MobEffectCategory category) {
        Iterator<MobEffectInstance> iterator = living.getActiveEffects().iterator();
        iterator.forEachRemaining(instance -> {
            if (instance.getEffect().value().getCategory().equals(category)) {
                iterator.remove();
                living.onEffectRemoved(instance);
            }
        });
    }

    public static void setBlinking(UUID playerId, boolean blinking, int duration) {
        if (blinking && duration > 0) BLINKING_PLAYERS.add(playerId);
        else BLINKING_PLAYERS.remove(playerId);
    }

    public static boolean shouldHideArmour(Player player) {
        return BLINKING_PLAYERS.contains(player.getUUID());
    }

    private static boolean crouching(Entity entity) {
        if (entity.isCrouching()) {
            return true;
        }
        return entity instanceof Mob && entity.getControllingPassenger() instanceof Player player && player.isCrouching();
    }

    public static boolean canWalkOn(LivingEntity entity) {
        return !crouching(entity)
                && !isSubmergedPartial(entity)
                && EnchantmentHelper.has(entity.getItemBySlot(EquipmentSlot.FEET), ModEnchantmentEffects.FLUID_WALKER.get())
                && !entity.isUnderWater();
    }

    public static boolean isSubmergedPartial(Entity entity) {
        for (float i = 0.5f; i < entity.getBbHeight(); i+= 0.1f) {
            FluidState state = entity.level().getFluidState(BlockPos.containing(entity.position().add(0, i, 0)));
            if (!state.isEmpty() && !state.is(Fluids.EMPTY)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSubmergedFully(Entity entity) {
        for (float i = entity.getBbHeight() - 0.1f; i < entity.getBbHeight(); i+= 0.1f) {
            FluidState state = entity.level().getFluidState(BlockPos.containing(entity.position().add(0, i, 0)));
            if (!state.isEmpty() && !state.is(Fluids.EMPTY)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTouchingFluid(Entity entity) {
        for (float i = 0.0f; i < entity.getBbHeight(); i+= 0.1f) {
            FluidState state = entity.level().getFluidState(BlockPos.containing(entity.position().add(0, i, 0)));
            if (!state.isEmpty() && !state.is(Fluids.EMPTY)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTouchingFluidOfType(Entity entity, TagKey<Fluid> tag) {
        for (float i = 0.0f; i < entity.getBbHeight(); i+= 0.1f) {
            FluidState state = entity.level().getFluidState(BlockPos.containing(entity.position().add(0, i, 0)));
            if (!state.isEmpty() && state.is(tag)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGrounded(LivingEntity living, boolean allowWater) {
        if (living instanceof Player player && player.getAbilities().flying) {
            return false;
        }
        if (!allowWater) {
            if (living.isInWater() || living.isSwimming()) {
                return false;
            }
        }
        return !living.isFallFlying() && living.getVehicle() == null && !living.onClimbable();
    }

    public static boolean isGrounded(LivingEntity living) {
        return isGrounded(living, false);
    }

    public static boolean inAir(Entity entity, double altitude) {
        return entity.level().clip(new ClipContext(entity.position(), entity.position().add(0, -altitude, 0), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity)).getType() == HitResult.Type.MISS;
    }

    public static boolean isFlameResistant(LivingEntity entity) {
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = entity.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggings = entity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);

        return helmet.is(ModItemTags.FLAME_RESISTANT_ARMOR)
                && chestplate.is(ModItemTags.FLAME_RESISTANT_ARMOR)
                && leggings.is(ModItemTags.FLAME_RESISTANT_ARMOR)
                && boots.is(ModItemTags.FLAME_RESISTANT_ARMOR);
    }

    public static boolean isUnbreakable(ItemStack stack) {
        return Config.disableDurability && !stack.isEmpty() && stack.has(DataComponents.MAX_DAMAGE) && !stack.is(ModItemTags.ALWAYS_HAS_DURABILITY) || EnchantmentHelper.has(stack, ModEnchantmentEffects.KEEPSAKE.get());
    }
}
