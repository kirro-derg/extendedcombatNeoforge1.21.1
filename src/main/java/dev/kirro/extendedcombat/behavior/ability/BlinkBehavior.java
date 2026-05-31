package dev.kirro.extendedcombat.behavior.ability;


import dev.kirro.extendedcombat.ExtendedCombatClient;
import dev.kirro.extendedcombat.api.Ability;
import dev.kirro.extendedcombat.api.TickingAttachment;
import dev.kirro.extendedcombat.data.ModDataComponents;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.packet.BlinkPacket;
import dev.kirro.extendedcombat.enchantment.packet.BlinkPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.network.PacketDistributor;

public class BlinkBehavior implements TickingAttachment, Ability {
    private final Player player;
    private boolean canRecharge = false, hasBlink = false, wasPressingKey = false, invisible = false;
    private int cooldown = 0, lastCooldown = 0, duration = 0;

    public BlinkBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        canRecharge = tag.getBoolean("CanRecharge");
        cooldown = tag.getInt("Cooldown");
        lastCooldown = tag.getInt("LastCooldown");
        invisible = tag.getBoolean("Invisible");
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        tag.putBoolean("CanRecharge", canRecharge);
        tag.putInt("Cooldown", cooldown);
        tag.putInt("LastCooldown", lastCooldown);
        tag.putBoolean("Invisible", invisible);
    }

    @Override
    public EquipmentSlot slot() {
        return EquipmentSlot.CHEST;
    }

    private int level() {
        return this.getLevel(this.player, slot(), EnchantmentHelper.has(slotItem(player), ModEnchantmentEffects.BLINK.get()));
    }

    private int cooldown() {
        return Mth.floor(this.getValue(level(), 15, -5f) * 20);
    }

    public int duration() {
        return Mth.floor(this.getValue(level(), 2, 3f) * 20);
    }

    @Override
    public void tick() {
        int playerCooldown = cooldown();
        hasBlink = playerCooldown > 0;
        if (hasBlink) {
            if (!canRecharge) {
                if (!player.isInvisible()) {
                    canRecharge = true;
                }
            } else if (cooldown > 0) {
                cooldown--;
            }
        } else {
            canRecharge = false;
            setCooldown(0);
        }
        if (duration > 0) {
            duration--;
        }
        if (duration == 0) {
            invisible = false;
            slotItem(player).set(ModDataComponents.BLINK, false);
        }
    }

    @Override
    public void clientTick() {
        tick();
        if (hasBlink && !player.isSpectator() && player == Minecraft.getInstance().player) {
            boolean pressingKey = ExtendedCombatClient.BLINK.get().isDown();
            if (pressingKey && !wasPressingKey && canUse()) {
                use();
                BlinkPacketHandler.addParticles(player);
                PacketDistributor.sendToServer(new BlinkPacket(player.getId(),true));
            }
            wasPressingKey = pressingKey;
        } else {
            wasPressingKey = false;
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
        lastCooldown = cooldown;
    }

    public int getLastCooldown() {
        return lastCooldown;
    }

    public boolean hasBlink() {
        return hasBlink;
    }

    public boolean canUse() {
        return cooldown == 0;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public void use() {
        reset();
        setInvisible(true);
        slotItem(player).set(ModDataComponents.BLINK, true);
        player.playSound(SoundEvents.TRIAL_SPAWNER_AMBIENT_OMINOUS, 1.0f, 1.0f);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void reset() {
        setCooldown(cooldown());
        canRecharge = false;
        setInvisible(false);
        setDuration(duration());
    }


}