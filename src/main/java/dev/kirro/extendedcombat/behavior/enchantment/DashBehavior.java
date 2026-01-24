package dev.kirro.extendedcombat.behavior.enchantment;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.ExtendedCombatClient;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.ModEvents;
import dev.kirro.extendedcombat.api.AutoSyncedComponent;
import dev.kirro.extendedcombat.api.CommonTickingComponent;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.custom.BurstEnchantmentEffect;
import dev.kirro.extendedcombat.enchantment.custom.DashEnchantmentEffect;
import dev.kirro.extendedcombat.enchantment.payload.DashParticlePayload;
import dev.kirro.extendedcombat.enchantment.payload.DashPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

public class DashBehavior implements CommonTickingComponent {
    private boolean canRecharge = false, hasDash = false, wasPressingKey = false;
    private int cooldown = 0, lastCooldown = 0, immunityTicks = 0;

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

    @Override
    public void tick(Player player) {
        int playerCooldown = DashEnchantmentEffect.getCooldown(player);
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
    public void clientTick(Player player) {
        tick(player);
        if (hasDash && !player.isSpectator() && player == Minecraft.getInstance().player) {
            boolean pressingKey = ExtendedCombatClient.DASH.get().isDown();
            if (pressingKey && !wasPressingKey && canUse(player)) {
                use(player);
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

    public boolean canUse(Player player) {
        return cooldown == 0 && !player.onGround() && ExtendedCombatUtil.isGrounded(player);
    }

    public void use(Player player) {
        reset(player);
        setImmunityTicks(6);
        float volume = hasStealth(player.getItemBySlot(EquipmentSlot.CHEST)) ? 0.05f : 0.25f;
        float strength = DashEnchantmentEffect.getStrength(player);
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

    public void reset(Player player) {
        setCooldown(DashEnchantmentEffect.getCooldown(player));
        canRecharge = false;
    }
}
