package dev.kirro.extendedcombat.mixin.enchantment.watergel;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.kirro.extendedcombat.behavior.enchantment.WatergelBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin {
    @Shadow
    public abstract int getUseDuration(ItemStack stack, LivingEntity entity);

    @Inject(method = "releaseUsing", at = @At("RETURN"))
    private void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft, CallbackInfo ci) {
        if (entityLiving instanceof Player player) {
            int i = this.getUseDuration(stack, entityLiving) - timeLeft;
            if (i >= 10) {
                float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, player);
                WatergelBehavior watergel = player.getData(ModDataAttachments.WATERGEL);
                if (f > 0 && watergel.canUse() && watergel.getCanUse() && !player.isInWaterOrRain()) {
                    watergel.use();
                }
            }
        }
    }

    @ModifyExpressionValue(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInWaterOrRain()Z"))
    private boolean w(boolean original, ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            WatergelBehavior watergel = player.getData(ModDataAttachments.WATERGEL);
            return original || watergel.canUse();
        }
        return original;
    }

    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInWaterOrRain()Z"))
    private boolean watergelled(boolean original, Level level, Player player) {
        WatergelBehavior watergel = player.getData(ModDataAttachments.WATERGEL);
        return original || watergel.canUse();
    }

}
