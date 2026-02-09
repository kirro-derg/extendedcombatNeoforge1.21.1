package dev.kirro.extendedcombat.behavior.ability;

import dev.kirro.extendedcombat.ExtendedCombatClient;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.CommonTickingComponent;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.payload.DashParticlePayload;
import dev.kirro.extendedcombat.enchantment.payload.DashPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

public class DashBehavior implements CommonTickingComponent {
    private final Player player;
    private boolean canRecharge = false, hasDash = false, wasPressingKey = false;
    private int cooldown = 0, lastCooldown = 0, immunityTicks = 0;

    public DashBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(CompoundTag nbtCompound, HolderLookup.Provider wrapperLookup) {
        canRecharge = nbtCompound.getBoolean("CanRecharge");
        cooldown = nbtCompound.getInt("Cooldown");
        lastCooldown = nbtCompound.getInt("LastCooldown");
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        tag.putBoolean("CanRecharge", canRecharge);
        tag.putInt("Cooldown", cooldown);
        tag.putInt("LastCooldown", lastCooldown);
    }

    private int level() {
        return this.getLevel(this.player, EquipmentSlot.LEGS);
    }

    private int cooldown() {
        return Mth.floor(this.getValue(level(), 1, -0.25f) * 20);
    }

    private float strength() {
        return this.getValue(level(), 0.85f, 0.3f);
    }

    @Override
    public void tick() {
        int playerCooldown = cooldown();
        hasDash = playerCooldown > 0;
        if (hasDash) {
            if (!canRecharge) {
                if (player.onGround()) {
                    canRecharge = true;
                }
            } else if (cooldown > 0) {
                cooldown--;
            }
        } else {
            canRecharge = false;
            setCooldown(0);
        }
        if (immunityTicks > 0) {
            immunityTicks--;
        }
    }

    @Override
    public void clientTick() {
        tick();
        if (hasDash && !player.isSpectator() && player == Minecraft.getInstance().player) {
            boolean pressingKey = ExtendedCombatClient.DASH.get().isDown();
            if (pressingKey && !wasPressingKey && canUse() && level() > 0) {
                use();
                DashParticlePayload.addParticles(player);
                DashPayload.send();
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

    public boolean hasDash() {
        return hasDash;
    }

    public boolean canUse() {
        return cooldown == 0 && !player.onGround() && ExtendedCombatUtil.isGrounded(player);
    }

    public void use() {
        reset();
        setImmunityTicks(6);
        float volume = hasStealth(player.getItemBySlot(EquipmentSlot.CHEST)) ? 0.05f : 0.25f;
        float strength = strength();
        Vec3 velocity = player.getLookAngle().normalize().scale(strength);
        player.setDeltaMovement(velocity.x(), velocity.y(), velocity.z());
        player.fallDistance = 0;
        player.hurtMarked = true;
        player.playSound(SoundEvents.WIND_CHARGE_BURST.value(), volume, 1.0f);
    }

    private boolean hasStealth(ItemStack chest) {
        return EnchantmentHelper.has(chest, ModEnchantmentEffects.STEALTH.get());
    }

    public void setImmunityTicks(int ticks) {
        immunityTicks = ticks;
    }

    public int getImmunityTicks() {
        return immunityTicks;
    }

    public void reset() {
        setCooldown(cooldown());
        canRecharge = false;
    }
}
