package dev.kirro.extendedcombat.behavior.enchantment;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.ExtendedCombatUtil;
import dev.kirro.extendedcombat.api.AutoSyncedComponent;
import dev.kirro.extendedcombat.api.CommonTickingComponent;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class FluidMovementBehavior implements CommonTickingComponent {

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {

    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider holderLookup) {

    }

    @Override
    public void tick(Player player) {
    }

    private ItemStack getArmor(Player player, EquipmentSlot slot) {
        return player.getItemBySlot(slot);
    }

    @Override
    public void clientTick(Player player) {
        tick(player);
        AirMovementBehavior airMovement = player.getData(ModDataAttachments.AIR_MOVEMENT);
        if (EnchantmentHelper.has(getArmor(player, EquipmentSlot.FEET), ModEnchantmentEffects.FLUID_WALKER.get()) && ExtendedCombatUtil.isTouchingFluid(player)) {
            airMovement.bypass();
        }
    }
}
