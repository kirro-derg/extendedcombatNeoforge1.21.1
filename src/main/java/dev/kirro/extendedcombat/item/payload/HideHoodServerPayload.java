package dev.kirro.extendedcombat.item.payload;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.ExtendedCombat;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public record HideHoodServerPayload(int entityId) implements CustomPacketPayload {
    public static final Type<HideHoodServerPayload> ID = new Type<>(ExtendedCombat.id("hide_hood_server"));
    public static final StreamCodec<FriendlyByteBuf, HideHoodServerPayload> CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, HideHoodServerPayload::entityId, HideHoodServerPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send(ServerPlayer player, int id) {
        ServerPlayNetworking.send(player, new HideHoodServerPayload(id));
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<HideHoodServerPayload> {
        @Override
        public void receive(HideHoodServerPayload payload, ClientPlayNetworking.Context context) {
        }
    }
}
