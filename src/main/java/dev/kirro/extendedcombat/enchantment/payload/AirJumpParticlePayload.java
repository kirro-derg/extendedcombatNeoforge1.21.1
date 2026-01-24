package dev.kirro.extendedcombat.enchantment.payload;

import dev.kirro.extendedcombat.ExtendedCombat;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public record AirJumpParticlePayload(int entityId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<AirJumpParticlePayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "air_jump_particles"));
    public static final StreamCodec<FriendlyByteBuf, AirJumpParticlePayload> CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, AirJumpParticlePayload::entityId, AirJumpParticlePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send(ServerPlayer player, int id) {
        ServerPlayNetworking.send(player, new AirJumpParticlePayload(id));
    }

    public static void addParticles(Entity entity) {
            for (int i = 0; i < 64; i++) {
                entity.level().addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS, entity.getRandomX(1), entity.getY(), entity.getRandomZ(1), 0, 0, 0);
            }
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<AirJumpParticlePayload> {
        @Override
        public void receive(AirJumpParticlePayload payload, ClientPlayNetworking.Context context) {
            Entity entity = context.player().level().getEntity(payload.entityId());
            if (entity != null) {
                addParticles(entity);
            }
        }
    }
}
