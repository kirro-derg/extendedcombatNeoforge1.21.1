package dev.kirro.extendedcombat.api;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

@FunctionalInterface
public interface ComponentPacketWriter {
    @Contract(mutates = "param1")
    void writeSyncPacket(RegistryFriendlyByteBuf buf, ServerPlayer recipient);
}
