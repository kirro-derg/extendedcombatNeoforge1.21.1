package dev.kirro.extendedcombat.api;

import net.minecraft.world.entity.player.Player;

public interface CommonTickingComponent extends Component {
    default void clientTick(Player player){
        this.tick(player);
    }

    default void serverTick(Player player) {
        this.tick(player);
    }

    void tick(Player player);
}
