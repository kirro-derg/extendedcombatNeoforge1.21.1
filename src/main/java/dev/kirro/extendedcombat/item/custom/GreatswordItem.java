package dev.kirro.extendedcombat.item.custom;

import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.enchantment.custom.ConcussionEnchantmentEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import static dev.kirro.extendedcombat.item.ModToolTiers.*;
import static net.minecraft.world.item.Tiers.*;

public class GreatswordItem extends SwordItem {
    public GreatswordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed, float sweepRatio) {
        return createAttributes(tier, (float)attackDamage, attackSpeed, sweepRatio);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed, float sweepRatio) {
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
                        Attributes.SWEEPING_DAMAGE_RATIO,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, sweepRatio,
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
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

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getMainHandItem().is(this) && !((Player) attacker).getCooldowns().isOnCooldown(this)) {
            int duration = ConcussionEnchantmentEffect.getDuration(attacker);
            if (EnchantmentHelper.has(stack, ModEnchantmentEffects.CONCUSSION.get())) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 10), null);
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, duration), null);
                target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, duration), null);
                ((Player) attacker).getCooldowns().addCooldown(this, duration * 2);
            }
        }
        super.postHurtEnemy(stack, target, attacker);
    }
}
