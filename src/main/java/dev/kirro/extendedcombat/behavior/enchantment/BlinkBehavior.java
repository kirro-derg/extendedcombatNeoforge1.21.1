package dev.kirro.extendedcombat.behavior.enchantment;


import dev.kirro.extendedcombat.ExtendedCombatClient;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.CommonTickingComponent;
import dev.kirro.extendedcombat.enchantment.custom.BlinkEnchantmentEffect;
import dev.kirro.extendedcombat.enchantment.payload.BlinkParticlePayload;
import dev.kirro.extendedcombat.enchantment.payload.BlinkPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

public class BlinkBehavior implements CommonTickingComponent {
    private boolean canRecharge = false, hasBlink = false, wasPressingKey = false, invisible = false;
    private int cooldown = 0, lastCooldown = 0, duration = 0;

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
    public void tick(Player player) {
        int playerCooldown = BlinkEnchantmentEffect.getCooldown(player);
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
            ExtendedCombatUtil.setBlinking(player.getUUID(), false, duration);
        }
    }

    @Override
    public void clientTick(Player player) {
        tick(player);
        if (hasBlink && !player.isSpectator() && player == Minecraft.getInstance().player) {
            boolean pressingKey = ExtendedCombatClient.BLINK.get().isDown();
            if (pressingKey && !wasPressingKey && canUse()) {
                use(player);
                BlinkParticlePayload.addParticles(player);
                BlinkPayload.send();
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
        return cooldown == 0 && duration == 0;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public void use(Player player) {
        reset(player);
        setInvisible(true);
        player.playSound(SoundEvents.TRIAL_SPAWNER_AMBIENT_OMINOUS, 1.0f, 1.0f);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void reset(Player player) {
        setCooldown(BlinkEnchantmentEffect.getCooldown(player));
        canRecharge = false;
        setInvisible(false);
        setDuration(BlinkEnchantmentEffect.getUsageDuration(player));
    }


}
