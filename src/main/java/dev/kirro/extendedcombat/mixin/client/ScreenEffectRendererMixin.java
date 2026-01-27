package dev.kirro.extendedcombat.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    @Inject(method = "renderFire", at = @At("HEAD"), cancellable = true)
    private static void isOnFire(Minecraft minecraft, PoseStack poseStack, CallbackInfo ci) {
        if (minecraft.player instanceof LivingEntity entity) {
            if (ExtendedCombatUtil.isFlameResistant(entity)) {
                ci.cancel();
            }
        }
    }
}
