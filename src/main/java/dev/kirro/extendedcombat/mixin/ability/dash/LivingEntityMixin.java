package dev.kirro.extendedcombat.mixin.ability.dash;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.kirro.extendedcombat.behavior.ability.DashBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyReturnValue(method = "calculateFallDamage", at = @At("RETURN"))
    private int extendedcombat$dash(int original) {
        if ((Object) this instanceof Player player) {
            DashBehavior dashBehavior = player.getData(ModDataAttachments.DASH);
            if (dashBehavior.getImmunityTicks() > 0) {
                return 0;
            }
        }
        return original;
    }
}

