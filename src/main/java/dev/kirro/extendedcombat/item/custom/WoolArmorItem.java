package dev.kirro.extendedcombat.item.custom;

import dev.kirro.extendedcombat.item.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class WoolArmorItem extends ModArmorItem {
    public WoolArmorItem(Holder<ArmorMaterial> material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        if (stack.getItem() instanceof WoolArmorItem item) {
            ResourceLocation id = ResourceLocation.parse(item.getMaterial().getRegisteredName());
            tooltip.add(Component.translatable("tooltip.extendedcombat." + id.getPath() + ".cloak_tooltip").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, context, tooltip, type);
    }

    public static void cycleData(ItemStack stack, boolean hidden) {
        stack.set(ModDataComponents.HIDDEN, hidden);
    }

    public static boolean isHidden(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.HIDDEN, false);
    }
}
