package dev.kirro.extendedcombat.mixin.enchantment.fluidWalker;

import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void canWalkOn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity living) {
            if (EnchantmentHelper.has(living.getItemBySlot(EquipmentSlot.FEET), ModEnchantmentEffects.FLUID_WALKER.get())) {
                cir.setReturnValue(true);
            }
        }
    }
}
