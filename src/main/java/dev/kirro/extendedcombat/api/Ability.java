package dev.kirro.extendedcombat.api;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public interface Ability {
    default float getValue(int level, float base, float perLevelValue) {
        if (level > 0) {
            return base + perLevelValue * (level - 1);
        }
        return 0;
    }

    default float getValue(int level, float base) {
        if (level > 0) {
            return base;
        }
        return 0;
    }

    default float getValue(int level, int base, int defaultValue) {
        if (level > 0) {
            return base;
        }
        return defaultValue;
    }

    default int getLevel(Player player, EquipmentSlot slot) {
        ItemStack stack = player.getItemBySlot(slot);
        if (stack.getItem() instanceof ArmorItem armorItem && !stack.isEmpty()) {
            ArmorMaterial material = armorItem.getMaterial().value();
            int defense = material.getDefense(ArmorItem.Type.BODY);
            return defense <= 4 ? 1 : defense <= 7 ? 2 : defense >= 11 ? 3 : 0;
        }
        return 0;
    }

    default int getLevel(Player player, EquipmentSlot slot, boolean condition) {
        ItemStack stack = player.getItemBySlot(slot);
        if (stack.getItem() instanceof ArmorItem armorItem && !stack.isEmpty() && condition) {
            ArmorMaterial material = armorItem.getMaterial().value();
            int defense = material.getDefense(ArmorItem.Type.BODY);
            return defense <= 4 ? 1 : defense <= 7 ? 2 : defense >= 11 ? 3 : 0;
        }
        return 0;
    }

    EquipmentSlot slot();

    default ItemStack slotItem(Player player) {
        return player.getItemBySlot(this.slot());
    }
}
