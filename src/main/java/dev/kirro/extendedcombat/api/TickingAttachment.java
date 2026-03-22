package dev.kirro.extendedcombat.api;

public interface TickingAttachment extends Attachment {
    default void clientTick(){
        this.tick();
    }

    default void serverTick() {
        this.tick();
    }

    void tick();
}
