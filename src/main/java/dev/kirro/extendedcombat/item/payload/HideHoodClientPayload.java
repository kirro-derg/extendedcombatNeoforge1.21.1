package dev.kirro.extendedcombat.item.payload;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.item.HideWoolHoodBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.payload.AirJumpPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public record HideHoodClientPayload() implements CustomPacketPayload {
    public static final Type<HideHoodClientPayload> ID = new Type<>(ExtendedCombat.id("hide_hood"));
    public static final StreamCodec<FriendlyByteBuf, HideHoodClientPayload> CODEC = StreamCodec.unit(new HideHoodClientPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send() {
        ClientPlayNetworking.send(new AirJumpPayload());
    }

    public static class Reciever implements ServerPlayNetworking.PlayPayloadHandler<HideHoodClientPayload> {
        @Override
        public void receive(HideHoodClientPayload payload, ServerPlayNetworking.Context context) {
            Player player = context.player();
            HideWoolHoodBehavior hood = player.getData(ModDataAttachments.HIDE_HOOD);
            if (hood.isHoodUsed()) hood.useHood(player);
            else if (hood.isMaskUsed()) hood.useMask(player);
            PlayerLookup.tracking(context.player()).forEach(foundPlayer -> HideHoodServerPayload.send(foundPlayer, player.getId()));
        }
    }
}
