package dev.kirro.extendedcombat.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.kirro.extendedcombat.item.custom.GreatswordItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerItemInHandLayer.class)
public class HideOffhandItemMixin {
    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    protected void renderArmWithItem(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext displayContext,
                                     HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                                     CallbackInfo ci) {
        if (!livingEntity.isUsingItem()) {
            if (arm.equals(livingEntity.getMainArm().getOpposite()) && livingEntity.getMainHandItem().getItem()
                    instanceof GreatswordItem) ci.cancel();
        }
    }
}
