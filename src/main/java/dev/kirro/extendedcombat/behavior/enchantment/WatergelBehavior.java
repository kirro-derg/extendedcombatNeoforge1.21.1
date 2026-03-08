package dev.kirro.extendedcombat.behavior.enchantment;

import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.Ability;
import dev.kirro.extendedcombat.api.TickingAttachment;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.item.ModDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class WatergelBehavior implements TickingAttachment, Ability {
    private final Player player;
    private boolean canRecharge = false, canUse = false;
    private int cooldown = 1, lastCooldown = 0, usageCooldown = 0, usesLeft = 0, maxUses = 0;

    public WatergelBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        canRecharge = tag.getBoolean("CanRecharge");
        cooldown = tag.getInt("Cooldown");
        lastCooldown = tag.getInt("LastCooldown");
        usageCooldown = tag.getInt("UsageCooldown");
        usesLeft = tag.getInt("UsesLeft");
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        tag.putBoolean("CanRecharge", canRecharge);
        tag.putInt("Cooldown", cooldown);
        tag.putInt("LastCooldown", lastCooldown);
        tag.putInt("UsageCooldown", usageCooldown);
        tag.putInt("UsesLeft", usesLeft);
    }

    @Override
    public EquipmentSlot slot() {
        return EquipmentSlot.LEGS;
    }

    public boolean has() {
        return EnchantmentHelper.has(slotItem(player), ModEnchantmentEffects.WATERGEL.get());
    }

    private int level() {
        return this.getLevel(this.player, slot(), EnchantmentHelper.has(slotItem(player), ModEnchantmentEffects.WATERGEL.get()));
    }

    private int cooldown() {
        return Mth.floor(this.getValue(level(), 0.5f) * 20);
    }

    private int usageCooldown() {
        return Mth.floor(this.getValue(level(), 0.5f) * 20);
    }

    private int usageAmount() {
        return Mth.floor(this.getValue(level(), 5, 0.5f));
    }

    @Override
    public void tick() {
        usesLeft = slotItem(player).getOrDefault(ModDataComponents.CHARGE, 0);
        int playerCooldown = cooldown();
        maxUses = usageAmount();
        canUse = maxUses > 0;
        if (canUse) {
            if (!canRecharge) {
                if (player.isInWaterOrRain()) {
                    canRecharge = true;
                }
            } else if (cooldown > 0) {
                if (!player.isInWaterOrRain()) {
                    canRecharge = false;
                }
                cooldown--;
            }

            if (cooldown == 0 && usesLeft < maxUses) {
                slotItem(player).set(ModDataComponents.CHARGE, slotItem(player).getOrDefault(ModDataComponents.CHARGE, 0) + 1);
                setCooldown(playerCooldown);
            }
            if (usageCooldown > 0) {
                usageCooldown--;
            }
        } else {
            canRecharge = false;
            setCooldown(0);
            usageCooldown = 0;
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
        slotItem(player).set(ModDataComponents.CHARGE, slotItem(player).getOrDefault(ModDataComponents.CHARGE, 0) - 1);
    }
}