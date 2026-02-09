package dev.kirro.extendedcombat.api;

import net.minecraft.world.entity.player.Player;

public interface CommonTickingComponent extends Component {
    default void clientTick(){
        this.tick();
    }

    default void serverTick() {
        this.tick();
    }

    void tick();
}
