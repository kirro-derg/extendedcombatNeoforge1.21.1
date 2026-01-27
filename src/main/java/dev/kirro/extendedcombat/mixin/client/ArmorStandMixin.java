package dev.kirro.extendedcombat.mixin.client;

import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.print.DocFlavor;

@Mixin(ArmorStand.class)
public class ArmorStandMixin {
    @Inject(method = "isShowArms", at = @At("RETURN"), cancellable = true)
    public void showArms(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
