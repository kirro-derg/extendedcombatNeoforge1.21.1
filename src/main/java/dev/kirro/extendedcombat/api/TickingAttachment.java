package dev.kirro.extendedcombat.api;

public interface TickingAttachment extends Component {
    default void clientTick(){
        this.tick();
    }

    default void serverTick() {
        this.tick();
    }

    void tick();
}
