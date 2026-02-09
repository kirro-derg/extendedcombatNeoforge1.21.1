package dev.kirro.extendedcombat.mixin.enchantment.fluidWalker;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.custom.FluidWalkerEnchantmentEffect;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot equipmentSlot);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "canStandOnFluid", at = @At("HEAD"), cancellable = true)
    private void canFluidWalk(FluidState fluidState, CallbackInfoReturnable<Boolean> cir) {
        if (ExtendedCombatUtil.canWalkOn((LivingEntity) (Object) this)) {
            cir.setReturnValue(true);
        }
    }

    @ModifyArg(method = "handleRelativeFrictionAndCalculateMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V"))
    private float increaseFluidMovementSpeed(float original) {
        ItemStack stack = this.getItemBySlot(EquipmentSlot.FEET);
        boolean submergedWithEnchant = ExtendedCombatUtil.isTouchingFluid(this) && EnchantmentHelper.has(stack, ModEnchantmentEffects.FLUID_WALKER.get());
        float value = submergedWithEnchant ? FluidWalkerEnchantmentEffect.getValue((LivingEntity) (Object) this) : 1;
        return original * value;
    }

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V"))
    private float increaseLavaMovementSpeed(float original) {
        ItemStack stack = this.getItemBySlot(EquipmentSlot.FEET);
        boolean submergedWithEnchant = ExtendedCombatUtil.isTouchingFluidOfType(this, FluidTags.LAVA) && EnchantmentHelper.has(stack, ModEnchantmentEffects.FLUID_WALKER.get());
        float value = submergedWithEnchant ? FluidWalkerEnchantmentEffect.getValue((LivingEntity) (Object) this) * 2 : 1;
        return original * value;
    }

    @ModifyReturnValue(method = "calculateFallDamage", at = @At(value = "RETURN"))
    private int handleFallDamage(int original) {
        if ((Object) this instanceof Player player) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
            if (stack.is(ModItemTags.NETHER_STEEL_WEARABLES) || stack.is(ModItemTags.ECHO_STEEL_WEARABLES) || stack.is(ModItemTags.HUNTER_BOOTS)) {
                return (int) (original * 0.7f);
            }
        }
        return original;
    }
}
