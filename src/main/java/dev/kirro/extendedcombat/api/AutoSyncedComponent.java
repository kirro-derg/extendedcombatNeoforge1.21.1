package dev.kirro.extendedcombat.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;

public interface AutoSyncedComponent extends Component, ComponentPacketWriter, PlayerSyncPredicate {

    @Override
    default boolean shouldSyncWith(ServerPlayer player) {
        return true;
    }

    @Contract(mutates = "param1")
    @Override
    default void writeSyncPacket(RegistryFriendlyByteBuf buf, ServerPlayer recipient) {
        CompoundTag tag = new CompoundTag();
        this.writeToNbt(tag, buf.registryAccess());
        buf.writeNbt(tag);
    }

    @CheckEnvironment(Dist.CLIENT)
    default void applySyncPacket(RegistryFriendlyByteBuf buf) {
        CompoundTag tag = buf.readNbt();
        if (tag != null) {
            this.readFromNbt(tag, buf.registryAccess());
        }
    }
}
