package dev.kirro.extendedcombat.mixin.item;

import dev.kirro.extendedcombat.behavior.item.HideWoolHoodBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.item.ModDataComponents;
import dev.kirro.extendedcombat.item.custom.HunterMaskItem;
import dev.kirro.extendedcombat.item.custom.WoolArmorItem;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {

    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Inject(method = "clicked", at = @At("HEAD"), cancellable = true)
    private void onClick(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT && slotId >= 0 && slotId < this.slots.size()) {
            Slot slot = this.slots.get(slotId);
            ItemStack stack = slot.getItem();
            HideWoolHoodBehavior hoodBehavior = player.getData(ModDataAttachments.HIDE_HOOD);
            if (clickType == ClickType.QUICK_MOVE && stack.getItem() instanceof WoolArmorItem) {
                if (stack.is(ModItemTags.CLOAK)) {
                    hoodBehavior.useHood(player);
                    WoolArmorItem.cycleData(stack, !stack.getOrDefault(ModDataComponents.HIDDEN, false));
                }
                if (stack.is(ModItemTags.MASK)) {
                    hoodBehavior.useMask(player);
                    HunterMaskItem.cycleData(stack, !stack.getOrDefault(ModDataComponents.HIDDEN, false));
                }
                ci.cancel();
            }
        }
    }
}
