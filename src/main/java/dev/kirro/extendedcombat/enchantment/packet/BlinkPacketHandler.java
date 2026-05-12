package dev.kirro.extendedcombat.enchantment.packet;

import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.behavior.ability.BlinkBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.data.ModDataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public record BlinkPacketHandler(int entityId) {

    public static void sendToClient(final BlinkPacket packet, final IPayloadContext context) {
        Player player = context.player();
        BlinkBehavior blink = player.getData(ModDataAttachments.BLINK);
        if (blink.hasBlink() && blink.canUse()) {
            blink.use();

            PlayerLookup.tracking(player).forEach(foundPlayer -> addParticles(player));
        }
    }

    public static void sendToServer(final BlinkPacket packet, final IPayloadContext context) {
        Player player = context.player();
        BlinkBehavior blink = player.getData(ModDataAttachments.BLINK);
        ExtendedCombatUtil.setBlinking(player.getUUID(), blink.isInvisible(), blink.getDuration());
        blink.slotItem(player).set(ModDataComponents.BLINK, blink.isInvisible());
    }

    public static void addParticles(Entity entity) {
            for (int i = 0; i < 64; i++) {
                entity.level().addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, entity.getRandomX(1), entity.getRandomY(), entity.getRandomZ(1), 0, 0, 0);
            }
    }
}
