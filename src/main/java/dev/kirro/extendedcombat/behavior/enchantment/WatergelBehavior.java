package dev.kirro.extendedcombat.behavior.enchantment;

import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.Ability;
import dev.kirro.extendedcombat.api.CommonTickingComponent;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.payload.AirJumpParticlePayload;
import dev.kirro.extendedcombat.enchantment.payload.AirJumpPayload;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class WatergelBehavior implements CommonTickingComponent, Ability {
    private final Player player;
    private boolean canRecharge = false, canUse = false;
    private int cooldown = 0, lastCooldown = 0, usageCooldown = 0, usesLeft = 0, maxUses = 0;

    public WatergelBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        canRecharge = tag.getBoolean("CanRecharge");
        cooldown = tag.getInt("Cooldown");
        lastCooldown = tag.getInt("LastCooldown");
        usageCooldown = tag.getInt("JumpCooldown");
        usesLeft = tag.getInt("JumpsLeft");
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        tag.putBoolean("CanRecharge", canRecharge);
        tag.putInt("Cooldown", cooldown);
        tag.putInt("LastCooldown", lastCooldown);
        tag.putInt("JumpCooldown", usageCooldown);
        tag.putInt("JumpsLeft", usesLeft);
    }

    private int level() {
        return EnchantmentHelper.has(player.getItemBySlot(EquipmentSlot.LEGS), ModEnchantmentEffects.WATERGEL.get()) ?
                this.getLevel(this.player, EquipmentSlot.LEGS) : 0;
    }

    private int cooldown() {
        return Mth.floor(this.getValue(level(), 0.5f) * 20);
    }

    private int usageCooldown() {
        return Mth.floor(this.getValue(level(), 0.5f) * 20);
    }

    private int usageAmount() {
        return Mth.floor(this.getValue(level(), 5));
    }

    @Override
    public void tick() {
        int playerCooldown = Mth.floor(this.getValue(level(), 0.5f) * 20);
        maxUses = usageAmount();
        canUse = maxUses > 0;
        if (canUse) {
            if (!canRecharge) {
                if (ExtendedCombatUtil.isTouchingFluidOfType(player, FluidTags.WATER)) {
                    canRecharge = true;
                }
            } else if (cooldown > 0) {
                if (!ExtendedCombatUtil.isTouchingFluidOfType(player, FluidTags.WATER)) {
                    canRecharge = false;
                }
                cooldown--;
            }

            if (cooldown == 0 && usesLeft < maxUses) {
                usesLeft++;
                setCooldown(playerCooldown);
            }
            if (usageCooldown > 0) {
                usageCooldown--;
            }
        } else {
            canRecharge = false;
            setCooldown(0);
            usageCooldown = 0;
            usesLeft = 0;
        }
    }

    @Override
    public void clientTick() {
        tick();
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

    public int getUsesLeft() {
        return usesLeft;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public boolean getCanUse() {
        return canUse;
    }

    public boolean canUse() {
        return usageCooldown == 0 && usesLeft > 0 && !ExtendedCombatUtil.isTouchingFluidOfType(player, FluidTags.WATER);
    }

    public void use() {
        if (cooldown == 0 || usesLeft == maxUses) {
            setCooldown(cooldown());
        }
        canRecharge = false;
        usageCooldown = usageCooldown();
        usesLeft--;
    }

    public void reset() {
        setCooldown(cooldown());
        usesLeft = 0;
    }
}
