package dev.kirro.extendedcombat.api;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public interface ClientTickingComponent extends Component {
    @CalledByAsm
    void clientTick(Player player);
}
