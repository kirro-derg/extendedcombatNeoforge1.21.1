package dev.kirro.extendedcombat.mixin.client;

import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class TwoHandedGreatswordMixin {
    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
    private static void extendedcombat$getArmPoseDR(AbstractClientPlayer player, InteractionHand hand,
                                                    CallbackInfoReturnable<ArmPose> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ModItemTags.GREATSWORDS)) cir.setReturnValue(ArmPose.CROSSBOW_HOLD);
    }
}
