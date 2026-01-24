package dev.kirro.extendedcombat.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public interface CommonTickingComponent extends ServerTickingComponent, ClientTickingComponent {
    @Override
    default void clientTick(Player player){
        this.tick(player);
    }

    @Override
    default void serverTick(Player player) {
        this.tick(player);
    }

    void tick(Player player);
}
