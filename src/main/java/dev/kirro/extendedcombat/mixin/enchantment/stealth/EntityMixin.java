package dev.kirro.extendedcombat.mixin.enchantment.stealth;

import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "canSpawnSprintParticle", at = @At("HEAD"),cancellable = true)
    public void shouldSpawnSprintingParticles(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Player player) {
            if (EnchantmentHelper.has(player.getItemBySlot(EquipmentSlot.CHEST), ModEnchantmentEffects.STEALTH.get())) {
                cir.setReturnValue(false);
            }
        }
    }
}
