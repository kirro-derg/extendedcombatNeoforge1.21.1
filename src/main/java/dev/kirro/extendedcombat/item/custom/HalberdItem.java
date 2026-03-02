package dev.kirro.extendedcombat.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static dev.kirro.extendedcombat.item.ModToolTiers.ECHO_STEEL;
import static dev.kirro.extendedcombat.item.ModToolTiers.NETHER_STEEL;
import static net.minecraft.world.item.Tiers.*;
import static net.minecraft.world.item.Tiers.DIAMOND;
import static net.minecraft.world.item.Tiers.GOLD;
import static net.minecraft.world.item.Tiers.NETHERITE;

public class HalberdItem extends AxeItem {
    public HalberdItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed, float interactionRange) {
        return createAttributes(tier, (float)attackDamage, attackSpeed, interactionRange);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed, float interactionRange) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage + tier.getAttackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, interactionRange,
                                AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND)
                .build();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayer attackingPlayer) {
            ServerLevel serverLevel = (ServerLevel) attacker.level();
            if (stack.getItem() instanceof TieredItem greatsword) {
                Tier tier = greatsword.getTier();
                switch (tier) {
                    case WOOD ->
                            serverLevel.playSound(null, attackingPlayer.getX(), attackingPlayer.getY(), attackingPlayer.getZ(), SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 1.0f, (float) (1.0f + attackingPlayer.getRandom().nextGaussian() / 10f));
                    case STONE ->
                            serverLevel.playSound(null, attackingPlayer.getX(), attackingPlayer.getY(), attackingPlayer.getZ(), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 1.0f, (float) (1.0f + attackingPlayer.getRandom().nextGaussian() / 10f));
                    case IRON, GOLD, DIAMOND ->
                            serverLevel.playSound(null, attackingPlayer.getX(), attackingPlayer.getY(), attackingPlayer.getZ(), SoundEvents.METAL_BREAK, SoundSource.PLAYERS, 1.0f, (float) (1.0f + attackingPlayer.getRandom().nextGaussian() / 10f));
                    case NETHERITE, NETHER_STEEL ->
                            serverLevel.playSound(null, attackingPlayer.getX(), attackingPlayer.getY(), attackingPlayer.getZ(), SoundEvents.NETHERITE_BLOCK_BREAK, SoundSource.PLAYERS, 1.0f, (float) (1.0f + attackingPlayer.getRandom().nextGaussian() / 10f));
                    case ECHO_STEEL ->
                            serverLevel.playSound(null, attackingPlayer.getX(), attackingPlayer.getY(), attackingPlayer.getZ(), SoundEvents.SCULK_BLOCK_BREAK, SoundSource.PLAYERS, 1.0f, (float) (1.0f + attackingPlayer.getRandom().nextGaussian() / 10f));
                    default -> super.hurtEnemy(stack, target, attacker);
                }
            }
        }
        return true;
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
}
