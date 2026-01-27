package dev.kirro.extendedcombat.mixin.enchantment.dash;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.kirro.extendedcombat.behavior.enchantment.DashBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyReturnValue(method = "calculateFallDamage", at = @At("RETURN"))
    private int extendedcombat$dash(int original) {
        DashBehavior dashBehavior = this.getData(ModDataAttachments.DASH);
        if (dashBehavior.getImmunityTicks() > 0) {
            return 0;
        }
        return original;
    }
}

