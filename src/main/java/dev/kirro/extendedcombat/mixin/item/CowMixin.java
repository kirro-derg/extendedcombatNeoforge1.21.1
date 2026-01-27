package dev.kirro.extendedcombat.mixin.item;

import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cow.class)
public class CowMixin {
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void fillBottle(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(Items.GLASS_BOTTLE) && ((Cow)(Object) this).isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0f, 1.0f);
            ItemStack filledBottle = ItemUtils.createFilledResult(stack, player, ModItems.MILK_BOTTLE.toStack());
            player.setItemInHand(hand, filledBottle);
            cir.setReturnValue(InteractionResult.sidedSuccess(((Cow)(Object) this).level().isClientSide));
        }
    }
}
