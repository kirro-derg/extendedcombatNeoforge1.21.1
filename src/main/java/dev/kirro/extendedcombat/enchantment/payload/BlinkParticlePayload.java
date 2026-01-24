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


public record BlinkParticlePayload(int entityId) implements CustomPacketPayload {
    public static final Type<BlinkParticlePayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(ExtendedCombat.MOD_ID, "blink_particles"));
    public static final StreamCodec<FriendlyByteBuf, BlinkParticlePayload> CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, BlinkParticlePayload::entityId, BlinkParticlePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void send(ServerPlayer player, int id) {
        ServerPlayNetworking.send(player, new BlinkParticlePayload(id));
    }

    public static void addParticles(Entity entity) {
            for (int i = 0; i < 64; i++) {
                entity.level().addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, entity.getRandomX(1), entity.getRandomY(), entity.getRandomZ(1), 0, 0, 0);
            }
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<BlinkParticlePayload> {
        @Override
        public void receive(BlinkParticlePayload payload, ClientPlayNetworking.Context context) {
            Entity entity = context.player().level().getEntity(payload.entityId());
            if (entity != null) {
                addParticles(entity);
            }
        }
    }
}
