package dev.kirro.extendedcombat.mixin.enchantment.watergel;

import dev.kirro.extendedcombat.behavior.enchantment.WatergelBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "isInWaterOrRain", at = @At("RETURN"), cancellable = true)
    private void isWatergelled(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Player player) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.LEGS);
            if (EnchantmentHelper.has(stack, ModEnchantmentEffects.WATERGEL.get())) {
                WatergelBehavior watergel = player.getData(ModDataAttachments.WATERGEL);
                if (player.getMainHandItem().getItem() instanceof TridentItem || player.getOffhandItem().getItem() instanceof TridentItem) {
                    if (watergel.canUse() && watergel.getCanUse()) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }
}