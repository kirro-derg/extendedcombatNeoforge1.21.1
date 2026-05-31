package dev.kirro.extendedcombat.behavior.ability;

import dev.kirro.extendedcombat.ExtendedCombatClient;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.Ability;
import dev.kirro.extendedcombat.api.TickingAttachment;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.packet.DashPacket;
import dev.kirro.extendedcombat.enchantment.packet.DashPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

public class DashBehavior implements TickingAttachment, Ability {
    private final Player player;
    private boolean canRecharge = false, hasDash = false, wasPressingKey = false;
    private int cooldown = 0, lastCooldown = 0, immunityTicks = 0, jumpTicks = 0, wavedashTicks = 0;

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

    @Override
    public EquipmentSlot slot() {
        return EquipmentSlot.LEGS;
    }

    private int level() {
        return this.getLevel(this.player, slot(), EnchantmentHelper.has(slotItem(player), ModEnchantmentEffects.DASH.get()));
    }

    private int cooldown() {
        return Mth.floor(this.getValue(level(), 1, -0.20f) * 20);
    }

    private int wavedashTicks() {
        return Mth.floor(this.getValue(this.getLevel(player, slot(), ModEnchantmentEffects.WAVEDASH.get(), EquipmentSlot.CHEST), 3));
    }

    private float strength() {
        return this.getValue(level(), 0.8f, 0.3f);
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
            jumpTicks = 0;
            canRecharge = false;
            setCooldown(0);
        }
        if (canWavedash()) {
            if (player.jumping) setCooldown(0);
        }
        if (wavedashTicks > 0) {
            wavedashTicks--;
        }
        if (immunityTicks > 0) {
            immunityTicks--;
        }
    }

    @Override
    public void clientTick() {
        tick();
        if (hasDash && !player.isSpectator() && player == Minecraft.getInstance().player) {
            if (player.jumping) {
                jumpTicks++;
            } else {
                jumpTicks = 0;
            }
            boolean pressingKey = ExtendedCombatClient.DASH.get().isDown();
            if (pressingKey && !wasPressingKey && canUse() && level() > 0) {
                use();
                DashPacketHandler.addParticles(player);
                PacketDistributor.sendToServer(new DashPacket(player.getId()));
            }
            wasPressingKey = pressingKey;
        } else {
            wasPressingKey = false;
        }
    }

    public boolean canWavedash() {
        return jumpTicks < 3 && wavedashTicks > 0 && player.onGround();
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
        wavedashTicks = wavedashTicks();
        setImmunityTicks(6);
        float volume = hasStealth(slotItem(player)) ? 0.05f : 0.25f;
        float strength = strength();
        Vec3 velocity = player.getLookAngle().normalize().scale(strength);
        player.setDeltaMovement(velocity.x(), velocity.y(), velocity.z());
        player.causeFoodExhaustion(0.5f);
        player.fallDistance = 0;
        player.playSound(SoundEvents.WIND_CHARGE_BURST.value(), volume, 1.0f);
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
        wavedashTicks = 0;
        jumpTicks = 0;
    }
}