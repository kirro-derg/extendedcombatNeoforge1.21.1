package dev.kirro.extendedcombat.behavior.item;

import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ModRepairManager {

    public static void repairItemsWithXP(Player player, int xpAmount) {
        List<ItemStack> allItems = new ArrayList<>();
        allItems.addAll(player.getInventory().items);
        allItems.addAll(player.getInventory().armor);
        allItems.addAll(player.getInventory().offhand);

        for (ItemStack stack : allItems) {
            if (stack != null && stack.is(ModItemTags.REPAIRABLE_ITEMS) && stack.isDamaged()) {
                int repairAmount = (xpAmount * 16);
                int damage = stack.getDamageValue();
                int toRepair = Math.min(repairAmount, damage);

                stack.setDamageValue(damage - toRepair);
                xpAmount -= toRepair /2;

                if (xpAmount <= 0) break;
            }
        }
    }
}
