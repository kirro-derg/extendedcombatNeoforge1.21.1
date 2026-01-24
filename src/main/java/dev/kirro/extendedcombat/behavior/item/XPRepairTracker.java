package dev.kirro.extendedcombat.behavior.item;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class XPRepairTracker {
    private static final Map<UUID, Integer> lastXPMap =  new HashMap<>();

    public static void tick(Player player) {
        UUID uuid = player.getUUID();
        int currentXP = player.totalExperience;
        int lastXP = lastXPMap.getOrDefault(uuid, currentXP);

        if (currentXP > lastXP) {
            int gainedXP = currentXP - lastXP;
            ModRepairManager.repairItemsWithXP(player, gainedXP);
        }

        lastXPMap.put(uuid, currentXP);
    }
}
