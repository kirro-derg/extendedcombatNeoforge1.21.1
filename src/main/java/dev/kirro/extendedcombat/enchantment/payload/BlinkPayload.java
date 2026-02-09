package dev.kirro.extendedcombat.enchantment.payload;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.behavior.ability.BlinkBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public record BlinkPayload() implements CustomPacketPayload {
    public static final Type<BlinkPayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "blink"));
    public static final StreamCodec<FriendlyByteBuf, BlinkPayload> CODEC = StreamCodec.unit(new BlinkPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send() {
        ClientPlayNetworking.send(new BlinkPayload());
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<BlinkPayload> {
        @Override
        public void receive(BlinkPayload payload, ServerPlayNetworking.Context context) {
            ServerPlayer player = context.player();
            BlinkBehavior blink = player.getData(ModDataAttachments.BLINK);
            if (blink != null && blink.hasBlink() && blink.canUse()) {
                blink.use();

                BlinkSyncPayload.broadcast(player, blink.isInvisible(), blink.getDuration());
                ExtendedCombatUtil.setBlinking(player.getUUID(), blink.isInvisible(), blink.getDuration());

                PlayerLookup.tracking(player).forEach(foundPlayer -> BlinkParticlePayload.send(foundPlayer, player.getId()));
            }
        }
    }
}
