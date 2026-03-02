package dev.kirro.extendedcombat.behavior.ability;

import dev.kirro.extendedcombat.Config;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.Ability;
import dev.kirro.extendedcombat.api.TickingAttachment;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AirMovementBehavior implements TickingAttachment, Ability {
    private final Player player;
    private int resetDelay = 0, airTime = 0, multiplierTicks = 0;
    private final int multiplyAfter = 10;
    private final float maxMovementMultiplier = 3.0f;

    public AirMovementBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(CompoundTag nbt, HolderLookup.Provider wrapperLookup) {
        resetDelay = nbt.getInt("ResetDelay");
        airTime = nbt.getInt("AirTime");
    }

    @Override
    public void writeToNbt(CompoundTag nbt, HolderLookup.Provider wrapperLookup) {
        nbt.putInt("ResetDelay", resetDelay);
        nbt.putInt("AirTime", airTime);
    }

    @Override
    public EquipmentSlot slot() {
        return EquipmentSlot.CHEST;
    }

    private int multiplyAfter() {
        return EnchantmentHelper.has(player.getItemBySlot(EquipmentSlot.LEGS), ModEnchantmentEffects.SWIFTNESS.get()) ? 5 : multiplyAfter;
    }

    @Override
    public void tick() {
        if (Config.airMovementActive) {
            if (resetDelay > 0) {
                resetDelay--;
            }
            if (player.onGround()) {
                if (resetDelay == 0) {
                    airTime = 0;
                }
            } else if (ExtendedCombatUtil.inAir(player, 1)) {
                airTime++;
                if (airTime >= multiplyAfter()) {
                    multiplierTicks++;
                }
            }

        } else {
            resetDelay = airTime = 0;
        }
    }

    private float movementMultiplier(Player player) {
        float m = 0;
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
        float addition = maxMovementMultiplier / 4;
        if (head.isEnchanted()) m += addition;
        if (chest.isEnchanted()) m += addition;
        if (legs.isEnchanted()) m += addition;
        if (feet.isEnchanted()) m += addition;
        return m;
    }

    public int getAirTime() {
        return airTime;
    }

    public float movementMultiplier(float original) {
        float multiplier = getAirTime() >= multiplyAfter() ? multiplierTicks : 1.0f;
        float multiply = EnchantmentHelper.has(player.getItemBySlot(EquipmentSlot.LEGS), ModEnchantmentEffects.SWIFTNESS.get())
                ? 0.5f
                : 0.0f;
        float movementMultiplier = Math.max(movementMultiplier(player), 1);
        float slow = player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? 1.0f : 0.0f;
        if (getAirTime() < multiplyAfter()) {
            return original;
        } else {
            return original * Math.min((movementMultiplier + multiply) - slow, multiplier);
        }
    }

    public void bypass() {
        resetDelay = 3;
        airTime = multiplyAfter;
    }
}
