package dev.kirro.extendedcombat.mixin.enchantment.fluidWalker;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
    private static void applyFog(Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean shouldCreateFog, float partialTick, CallbackInfo ci) {
        FogType type = camera.getFluidInCamera();
        Entity entity = camera.getEntity();
        if (entity instanceof LivingEntity living) {
            if (ExtendedCombatUtil.isSubmergedFully(living)
            && EnchantmentHelper.has(living.getItemBySlot(EquipmentSlot.FEET), ModEnchantmentEffects.FLUID_WALKER.get())
            && type == FogType.LAVA) {
                RenderSystem.setShaderFogStart(0.0f);
                RenderSystem.setShaderFogEnd(farPlaneDistance * 0.25f);
                RenderSystem.setShaderFogShape(FogShape.SPHERE);
                ci.cancel();
            }
        }
    }
}
