package dev.kirro.extendedcombat.mixin.ability.blink;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.behavior.ability.BlinkBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyReturnValue(method = "isInvisible", at = @At("RETURN"))
    public boolean isInvisible(boolean original) {
        if ((Object) this instanceof Player player) {
            BlinkBehavior blinkBehavior = player.getData(ModDataAttachments.BLINK);
            if (blinkBehavior.isInvisible() && blinkBehavior.getDuration() > 0 || ExtendedCombatUtil.shouldHideArmour(player)) {
                return true;
            }
        }
        return original;
    }
}
