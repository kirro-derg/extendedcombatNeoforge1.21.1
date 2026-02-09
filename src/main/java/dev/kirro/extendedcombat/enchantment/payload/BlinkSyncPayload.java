package dev.kirro.extendedcombat.enchantment.payload;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.ability.BlinkBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public record BlinkSyncPayload(int entityId, boolean invisible, int duration) implements CustomPacketPayload {
    public static final Type<BlinkSyncPayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "blink_sync"));
    public static final StreamCodec<FriendlyByteBuf, BlinkSyncPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, BlinkSyncPayload::entityId,
            ByteBufCodecs.BOOL, BlinkSyncPayload::invisible,
            ByteBufCodecs.VAR_INT, BlinkSyncPayload::duration,
            BlinkSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send(ServerPlayer target, int entityId, boolean invisible, int duration) {
        CustomPacketPayload payload = new BlinkSyncPayload(entityId, invisible, duration);
        ServerPlayNetworking.send(target, payload);
    }

    public static void handle(BlinkSyncPayload payload, ClientPlayNetworking.Context context) {
        Minecraft client = Minecraft.getInstance();
        client.execute( () -> {
            Entity entity = client.level.getEntity(payload.entityId());
            if (entity instanceof Player player) {
                BlinkBehavior blink = player.getData(ModDataAttachments.BLINK);
                blink.setInvisible(payload.invisible());
                blink.setDuration(payload.duration());
            }
        });
    }

    public static void broadcast(ServerPlayer source, boolean invisible, int duration) {
        int entityId = source.getId();
        for (ServerPlayer tracking : PlayerLookup.tracking(source)) {
            send(tracking, entityId, invisible, duration);
        }
    }
}
