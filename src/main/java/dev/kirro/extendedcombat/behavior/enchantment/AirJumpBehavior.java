package dev.kirro.extendedcombat.behavior.enchantment;

import dev.kirro.extendedcombat.ExtendedCombatClient;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.AutoSyncedComponent;
import dev.kirro.extendedcombat.api.CommonTickingComponent;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.custom.AirJumpEnchantmentEffect;
import dev.kirro.extendedcombat.enchantment.payload.AirJumpParticlePayload;
import dev.kirro.extendedcombat.enchantment.payload.AirJumpPayload;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jetbrains.annotations.Nullable;

public class AirJumpBehavior implements CommonTickingComponent {
    private boolean canRecharge = false, canUse = false;
    private int cooldown = 0, lastCooldown = 0, jumpCooldown = 10, jumpsLeft, ticksInAir = 0, maxJumps;

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        canRecharge = tag.getBoolean("CanRecharge");
        cooldown = tag.getInt("Cooldown");
        lastCooldown = tag.getInt("LastCooldown");
        jumpCooldown = tag.getInt("JumpCooldown");
        jumpsLeft = tag.getInt("JumpsLeft");
        ticksInAir = tag.getInt("TicksInAir");
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {
        tag.putBoolean("CanRecharge", canRecharge);
        tag.putInt("Cooldown", cooldown);
        tag.putInt("LastCooldown", lastCooldown);
        tag.putInt("JumpCooldown", jumpCooldown);
        tag.putInt("JumpsLeft", jumpsLeft);
        tag.putInt("TicksInAir", ticksInAir);
    }

    @Override
    public void tick(Player player) {
        int playerCooldown = AirJumpEnchantmentEffect.getCooldown(player);
        maxJumps = AirJumpEnchantmentEffect.getJumpAmount(player);
        canUse = maxJumps > 0;
        if (canUse) {
            if (!canRecharge) {
                if (player.onGround()) {
                    canRecharge = true;
                }
            } else if (cooldown > 0) {
                cooldown--;
            }
            if (cooldown == 0 && jumpsLeft < maxJumps) {
                jumpsLeft++;
                setCooldown(playerCooldown);
            }
            if (jumpCooldown > 0) {
                jumpCooldown--;
            }
            if (player.onGround()) {
                ticksInAir = 0;
            } else {
                ticksInAir++;
            }
        } else {
            canRecharge = false;
            setCooldown(0);
            jumpCooldown = 0;
            jumpsLeft = 0;
            ticksInAir = 0;
        }
    }

    @Override
    public void clientTick(Player player) {
        tick(player);
        if (canUse && player.jumping && canUse(player)) {
            use(player);
            AirJumpParticlePayload.addParticles(player);
            AirJumpPayload.send();
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

    public int getJumpsLeft() {
        return jumpsLeft;
    }

    public int getMaxJumps() {
        return maxJumps;
    }

    public boolean getCanUse() {
        return canUse;
    }

    public boolean canUse(Player player) {
        int playerJumpCooldown = AirJumpEnchantmentEffect.getJumpCooldown(player);
        return jumpCooldown == 0 && jumpsLeft > 0 && ticksInAir >= (player.level().isClientSide ? playerJumpCooldown : playerJumpCooldown - 1) && !player.onGround() && ExtendedCombatUtil.isGrounded(player);
    }

    public void use(Player player) {
        float strength = AirJumpEnchantmentEffect.getAirJumpStrength(player);
        float volume = hasStealth(player.getItemBySlot(EquipmentSlot.CHEST)) ? 0.05f : 0.25f;
        player.jumpFromGround();
        player.setDeltaMovement(player.getDeltaMovement().x(), player.getDeltaMovement().y() * strength, player.getDeltaMovement().z());
        player.playSound(SoundEvents.WIND_CHARGE_BURST.value(), volume, 1.0f);
        player.fallDistance = 0;
        if (cooldown == 0 || jumpsLeft == maxJumps) {
            setCooldown(AirJumpEnchantmentEffect.getCooldown(player));
        }
        canRecharge = false;
        jumpCooldown = AirJumpEnchantmentEffect.getJumpCooldown(player);
        jumpsLeft--;
    }

    private boolean hasStealth(ItemStack chest) {
        return EnchantmentHelper.has(chest, ModEnchantmentEffects.STEALTH.get());
    }

    public void reset(Player player) {
        setCooldown(AirJumpEnchantmentEffect.getCooldown(player));
        jumpsLeft = 0;
    }
}
