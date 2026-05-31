package dev.kirro.extendedcombat.enchantment.packet;

import dev.kirro.extendedcombat.behavior.ability.AirJumpBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AirJumpPacketHandler() {

    public static void serverPlayHandler(final AirJumpPacket packet, final IPayloadContext context) {
        Player player = context.player();
        AirJumpBehavior airJump = player.getData(ModDataAttachments.AIR_JUMP);
        if (airJump.getCanUse() && airJump.canUse()) {
            airJump.use();
            PacketDistributor.sendToAllPlayers(new AirJumpPacket(player.getId()));
        }
    }

    public static void clientPlayHandler(final AirJumpPacket packet, final IPayloadContext context) {
        Player player = context.player();
        Level level = player.level();
        Entity entity = level.getEntity(packet.entityId());
        addParticles(entity);
    }

    public static void addParticles(Entity entity) {
        for (int i = 0; i < 64; i++) {
            entity.level().addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS, entity.getRandomX(1), entity.getY(), entity.getRandomZ(1), 0, 0, 0);
        }
    }
}
