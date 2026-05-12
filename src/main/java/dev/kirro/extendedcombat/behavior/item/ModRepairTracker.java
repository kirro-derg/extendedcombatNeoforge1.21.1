package dev.kirro.extendedcombat.behavior.item;

import dev.kirro.extendedcombat.Config;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModRepairTracker {
    private static final Map<UUID, Integer> lastXPMap = new HashMap<>();
    private static int cooldown;

    public static void tick(Player player) {
        UUID uuid = player.getUUID();
        int currentXP = player.totalExperience;
        int lastXP = lastXPMap.getOrDefault(uuid, currentXP);

        if (currentXP > lastXP) {
            int gainedXP = currentXP - lastXP;
            ModRepairManager.repairItemsWithXP(player, gainedXP);
        }

        lastXPMap.put(uuid, currentXP);

        /*if (Config.repairCharm && player.getInventory().contains(ModItems.REPAIR_CHARM.toStack()) && cooldown == 0) {
            ModRepairManager.repairItems(player, Config.repairAmount, Config.repairType);
            cooldown = Config.repairInterval * 20;
        }

        if (cooldown > 0) {
            cooldown--;
        }*/
    }
}