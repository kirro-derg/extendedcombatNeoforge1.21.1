package dev.kirro.extendedcombat.enchantment.packet;

import dev.kirro.extendedcombat.behavior.ability.BlinkBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.data.ModDataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record BlinkPacketHandler() {

    public static void serverPlayHandler(final BlinkPacket packet, final IPayloadContext context) {
        Player player = context.player();
        BlinkBehavior blink = player.getData(ModDataAttachments.BLINK);
        player.getItemBySlot(EquipmentSlot.CHEST).set(ModDataComponents.BLINK, packet.invisible());

        if (blink.hasBlink() && blink.canUse()) {
            blink.use();
            PacketDistributor.sendToAllPlayers(new BlinkPacket(player.getId(),true));
        }
    }

    public static void clientPlayHandler(final BlinkPacket packet, final IPayloadContext context) {
        Player player = context.player();
        Level level = player.level();
        Entity entity = level.getEntity(packet.entityId());
        addParticles(entity);
    }

    public static void addParticles(Entity entity) {
            for (int i = 0; i < 64; i++) {
                entity.level().addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, entity.getRandomX(1), entity.getRandomY(), entity.getRandomZ(1), 0, 0, 0);
            }
    }
}
