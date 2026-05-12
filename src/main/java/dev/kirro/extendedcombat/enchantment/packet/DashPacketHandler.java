package dev.kirro.extendedcombat.enchantment.packet;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.behavior.ability.DashBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record DashPacketHandler(int entityId) {

    public static void sendToClient(final DashPacket packet, final IPayloadContext context) {
        Player player = context.player();
        DashBehavior dash = player.getData(ModDataAttachments.DASH);
        if (dash.hasDash() && dash.canUse()) {
            dash.use();
            PlayerLookup.tracking(player).forEach(foundPlayer -> addParticles(player));
        }
    }

    public static void sendToServer(final DashPacket packet, final IPayloadContext context) {

    }

    public static void addParticles(Entity entity) {
        for (int i = 0; i < 64; i++) {
            entity.level().addParticle(ParticleTypes.OMINOUS_SPAWNING, entity.getRandomX(1), entity.getRandomY(), entity.getRandomZ(1), 0, 0, 0);
        }
    }
}
