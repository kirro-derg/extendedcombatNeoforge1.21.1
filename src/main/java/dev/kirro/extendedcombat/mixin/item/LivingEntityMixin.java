package dev.kirro.extendedcombat.mixin.item;

import dev.kirro.extendedcombat.item.custom.GreatswordItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    public abstract ItemStack getMainHandItem();

    @Inject(method = "canDisableShield", at = @At("HEAD"), cancellable = true)
    protected void disablesShield(CallbackInfoReturnable<Boolean> cir) {
        if (this.getMainHandItem().getItem() instanceof GreatswordItem) {
            cir.setReturnValue(true);
        }
    }
}
