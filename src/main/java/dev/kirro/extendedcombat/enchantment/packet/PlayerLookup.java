package dev.kirro.extendedcombat.enchantment.packet;

import net.fabricmc.fabric.mixin.networking.accessor.EntityTrackerAccessor;
import net.fabricmc.fabric.mixin.networking.accessor.ServerChunkLoadingManagerAccessor;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.chunk.ChunkSource;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerLookup {

    public static Collection<ServerPlayer> tracking(Entity entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        ChunkSource manager = entity.level().getChunkSource();

        if (manager instanceof ServerChunkCache) {
            ChunkMap chunkLoadingManager = ((ServerChunkCache) manager).chunkMap;
            EntityTrackerAccessor tracker = ((ServerChunkLoadingManagerAccessor) chunkLoadingManager).getEntityMap().get(entity.getId());

            // return an immutable collection to guard against accidental removals.
            if (tracker != null) {
                return tracker.getPlayersTracking()
                        .stream().map(ServerPlayerConnection::getPlayer).collect(Collectors.toUnmodifiableSet());
            }

            return Collections.emptySet();
        }

        throw new IllegalArgumentException("Only supported on server worlds!");
    }
}
