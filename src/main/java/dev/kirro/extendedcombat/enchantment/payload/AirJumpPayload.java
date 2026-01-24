package dev.kirro.extendedcombat.enchantment.payload;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.enchantment.AirJumpBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record AirJumpPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<AirJumpPayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "air_jump"));
    public static final StreamCodec<FriendlyByteBuf, AirJumpPayload> CODEC = StreamCodec.unit(new AirJumpPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send() {
        ClientPlayNetworking.send(new AirJumpPayload());
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<AirJumpPayload> {
        @Override
        public void receive(AirJumpPayload payload, ServerPlayNetworking.Context context) {
            Player player = context.player();
            AirJumpBehavior airJump = player.getData(ModDataAttachments.AIR_JUMP);
            if (airJump.getCanUse() && airJump.canUse(player)) {
                airJump.use(player);
                PlayerLookup.tracking(player).forEach(foundPlayer -> AirJumpParticlePayload.send(foundPlayer, player.getId()));
            }
        }
    }
}
