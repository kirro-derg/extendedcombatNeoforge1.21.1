package dev.kirro.extendedcombat.mixin.enchantment.obscurity;

import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.item.ModDataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.class)
public class EndermanMixin {
    @Inject(method = "isLookingAtMe", at = @At("HEAD"), cancellable = true)
    private void isPlayerStaring(Player player, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);
        if (EnchantmentHelper.has(stack, ModEnchantmentEffects.OBSCURITY.get())) {
            boolean hidden = stack.getOrDefault(ModDataComponents.HIDDEN, false);
            if (!hidden) {
                cir.setReturnValue(false);
            }
        }
    }
}
