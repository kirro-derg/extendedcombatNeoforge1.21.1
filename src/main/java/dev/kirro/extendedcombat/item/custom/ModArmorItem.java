package dev.kirro.extendedcombat.item.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import dev.kirro.extendedcombat.item.ModArmorMaterials;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && !level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            evaluateArmorEffects(player);
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    private void evaluateArmorEffects(Player player) {
        if(hasPlayerCorrectArmorOn(player)) {
            addEffectToPlayer(player);
        }
    }

    private void addEffectToPlayer(Player player) {
        boolean hasPlayerEffect = player.hasEffect(MobEffects.FIRE_RESISTANCE);

        if(!hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1, false, false));
        }
    }

    private boolean hasPlayerCorrectArmorOn(Player player) {
        for(ItemStack armorStack : player.getArmorSlots()) {
            if(!(armorStack.getItem() instanceof ModArmorItem)) {
                return false;
            }
        }

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        return boots.is(ModItemTags.FLAME_RESISTANT_ARMOR)
                && leggings.is(ModItemTags.FLAME_RESISTANT_ARMOR)
                && chestplate.is(ModItemTags.FLAME_RESISTANT_ARMOR)
                && helmet.is(ModItemTags.FLAME_RESISTANT_ARMOR);
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }



}
