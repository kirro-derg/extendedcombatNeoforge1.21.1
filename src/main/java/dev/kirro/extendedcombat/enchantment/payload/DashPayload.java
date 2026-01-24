package dev.kirro.extendedcombat.enchantment.payload;


import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.enchantment.DashBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record DashPayload() implements CustomPacketPayload {
    public static final Type<DashPayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "dash"));
    public static final StreamCodec<FriendlyByteBuf, DashPayload> CODEC = StreamCodec.unit(new DashPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send() {
        ClientPlayNetworking.send(new DashPayload());
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<DashPayload> {
        @Override
        public void receive(DashPayload payload, ServerPlayNetworking.Context context) {
            Player player = context.player();
            DashBehavior dash = player.getData(ModDataAttachments.DASH);
            if (dash.hasDash() && dash.canUse(player)) {
                dash.use(player);
                PlayerLookup.tracking(player).forEach(foundPlayer -> DashParticlePayload.send(foundPlayer, player.getId()));
            }
        }
    }
}
