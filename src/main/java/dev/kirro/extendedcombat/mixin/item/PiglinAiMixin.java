package dev.kirro.extendedcombat.mixin.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {
    @Inject(method = "isWearingGold", at = @At("HEAD"), cancellable = true)
    private static void wearingAcceptableArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        for (ItemStack stack : entity.getArmorSlots()) {
            ArmorTrim armorTrim = stack.get(DataComponents.TRIM);
            if (stack.getItem() instanceof ArmorItem && (stack.is(ItemTags.PIGLIN_LOVED) || (armorTrim != null && armorTrim.material().is(TrimMaterials.GOLD)))) {
                cir.setReturnValue(true);
            }
        }
    }
}
