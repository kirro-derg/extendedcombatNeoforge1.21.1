package dev.kirro.extendedcombat.item.custom;

import dev.kirro.extendedcombat.item.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class HunterMaskItem extends WoolArmorItem {
    public HunterMaskItem(Holder<ArmorMaterial> material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        if (stack.getItem() instanceof WoolArmorItem item) {
            String id = item.getMaterial().getRegisteredName().replace("extendedcombat:", "");
            tooltip.add(Component.translatable("tooltip.extendedcombat." + id + ".mask_tooltip").withStyle(ChatFormatting.BLUE));
        }
    }

    public static void cycleData(ItemStack stack, boolean hidden) {
        stack.set(ModDataComponents.HIDDEN, hidden);
    }

    public static boolean isHidden(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.HIDDEN, false);
    }
}
