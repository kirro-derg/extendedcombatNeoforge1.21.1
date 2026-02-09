package dev.kirro.extendedcombat.mixin.ability.airJump;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.kirro.extendedcombat.behavior.ability.AirJumpBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @WrapOperation(method = "calculateFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getAttributeValue(Lnet/minecraft/core/Holder;)D", ordinal = 0))
    private double extendedcombat$airJump(LivingEntity instance, Holder<Attribute> attribute, Operation<Double> original) {
        double value = original.call(instance, attribute);
        if (instance instanceof Player player) {
            AirJumpBehavior airJumpBehavior = player.getExistingDataOrNull(ModDataAttachments.AIR_JUMP);
            if (airJumpBehavior.getCanUse()) {
                return value + airJumpBehavior.getMaxJumps() - airJumpBehavior.getJumpsLeft();
            }
        }
        return value;
    }
}
