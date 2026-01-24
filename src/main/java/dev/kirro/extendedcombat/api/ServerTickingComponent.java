package dev.kirro.extendedcombat.api;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public interface ServerTickingComponent extends Component {
    @CalledByAsm
    void serverTick(Player player);
}
