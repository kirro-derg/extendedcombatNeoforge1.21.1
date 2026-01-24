package dev.kirro.extendedcombat.mixin.airmovement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.kirro.extendedcombat.behavior.enchantment.AirMovementBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    @Shadow
    @Final
    private Abilities abilities;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyReturnValue(method = "getFlyingSpeed", at = @At("RETURN"))
    private float getAirMovementSpeed(float original) {
        AirMovementBehavior airMovementBehavior = this.getData(ModDataAttachments.AIR_MOVEMENT);
        if (!this.abilities.flying) {
            return airMovementBehavior.movementMultiplier(original, this);
        }
        return original;
    }
}
