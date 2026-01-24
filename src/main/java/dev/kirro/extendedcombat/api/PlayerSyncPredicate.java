package dev.kirro.extendedcombat.api;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public interface PlayerSyncPredicate {
    @Contract(pure = true)
    boolean shouldSyncWith(ServerPlayer player);

    default boolean isRequiredOnClient() {
        return true;
    }

    static PlayerSyncPredicate all() {
        return p -> true;
    }

    static PlayerSyncPredicate only(Player player) {
        return p -> p == player;
    }
}
