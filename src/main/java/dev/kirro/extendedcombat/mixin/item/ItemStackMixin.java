package dev.kirro.extendedcombat.mixin.item;

import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract void setDamageValue(int damage);

    @Shadow
    public abstract int getDamageValue();

    @Inject(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isDamageableItem()Z"))
    private void extendedcombat$disablesDurability(int amount, ServerLevel serverLevel, LivingEntity living, Consumer<Item> consumer, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;
        if (living instanceof ServerPlayer player) {
            if (player != null && ExtendedCombatUtil.isUnbreakable(stack) || EnchantmentHelper.has(stack, ModEnchantmentEffects.KEEPSAKE.get())) {
                this.setDamageValue(0);
                CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger(player, stack, getDamageValue());
            }
        }
    }

    @Inject(method = "isDamageableItem", at = @At("HEAD"), cancellable = true)
    private void extendedcombat$disablesDurability(CallbackInfoReturnable<Boolean> cir) {
        if (ExtendedCombatUtil.isUnbreakable((ItemStack) (Object) this) || EnchantmentHelper.has(((ItemStack) (Object) this), ModEnchantmentEffects.KEEPSAKE.get())) {
            cir.setReturnValue(false);
        }
    }
}
